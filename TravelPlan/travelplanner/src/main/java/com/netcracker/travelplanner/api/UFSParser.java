package com.netcracker.travelplanner.api;

import com.netcracker.travelplanner.model.exceptions.UFSIOException;
import com.netcracker.travelplanner.model.exceptions.UFSNoDataException;
import com.netcracker.travelplanner.model.entities.Edge;
import com.netcracker.travelplanner.model.entities.Point;
import com.netcracker.travelplanner.model.entities.TrainTicketsInfo;
import com.netcracker.travelplanner.model.entities.TransitEdge;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;


public class UFSParser implements ApiInterface {

    private static final Logger logger = LoggerFactory.getLogger(UFSParser.class);

    @Override
    public List<Edge> findEdgesFromTo(Point from, Point to, LocalDate date, int numberOfAdults, int numberOfChildren, int numberOfInfants) throws UFSNoDataException, UFSIOException {
        String url = "https://www.ufs-online.ru/en/kupit-zhd-bilety/" +
                from.getRussianName() +
                "/" +
                to.getRussianName() +
                "?date=" +
                date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        List<Edge> edgeList = new ArrayList<>();

        if (!from.getRussianName().equals(to.getRussianName())) {
            Document doc;
            try {
                doc = Jsoup
                        .connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36")
                        .get();
            } catch (IOException e) {
                logger.error("ошибка получения по запросу {}", url);
                throw new UFSIOException(e);
            }

            Elements records = doc.select("div.wg-train-container");

            if (records != null && records.size() > 0) {
                for (Element record : records) {
                    Edge edge = new Edge();
                    edge.setCreationDate(new Date());
                    edge.setTransportType("train");
                    edge.setCost(0.0);
                    edge.setDuration(travelTimeToDuration(record.selectFirst("span.wg-track-info__travel-time").text()));
                    edge.setCurrency("RUB");
                    edge.setNumberOfTransfers(1);

                    //учитываем таймзоны
                    Element timeElementFrom = record.select("span.wg-track-info__time").first();
                    LocalDateTime localDateTimeFrom = convertTimeAndDate(timeElementFrom.ownText(), record.select("span.wg-track-info__date").first().text(), date);

                    if (timeElementFrom.text().contains("Seats")){
                        edge.setStartDate(localDateTimeFrom);
                    } else {
                        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTimeFrom, ZoneId.of("Europe/Moscow"));
                        edge.setStartDate(zonedDateTime.withZoneSameInstant(ZoneId.of(from.getTimezone())).toLocalDateTime());
                    }

                    Element timeElementTo = record.select("span.wg-track-info__time").last();
                    LocalDateTime localDateTimeTo = convertTimeAndDate(timeElementTo.ownText(), record.select("span.wg-track-info__date").last().text(), date);

                    if (timeElementTo.text().contains("Seats")){
                        edge.setEndDate(localDateTimeTo);
                    } else {
                        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTimeTo, ZoneId.of("Europe/Moscow"));
                        edge.setEndDate(zonedDateTime.withZoneSameInstant(ZoneId.of(to.getTimezone())).toLocalDateTime());
                    }

                    edge.setStartPoint(new Point(from.getName()
                            , from.getLatitude()
                            , from.getLongitude()
                            , from.getIataCode()
                            , ""
                            , from.getRussianName()
                            , from.getTimezone()));
                    edge.setEndPoint(new Point(to.getName()
                            , to.getLatitude()
                            , to.getLongitude()
                            , to.getIataCode()
                            , ""
                            , to.getRussianName()
                            , to.getTimezone()));

                    List<TransitEdge> transitEdges = new LinkedList<>();
                    transitEdges.add(new TransitEdge(
                            new Point(from.getName()
                                    , from.getLatitude()
                                    , from.getLongitude()
                                    , from.getIataCode()
                                    , ""
                                    , from.getRussianName()
                                    , from.getTimezone()
                            )
                            , new Point(to.getName()
                                    , to.getLatitude()
                                    , to.getLongitude()
                                    , to.getIataCode()
                                    , ""
                                    , to.getRussianName()
                                    , to.getTimezone()
                             )
                            , edge.getStartDate()
                            , edge.getEndDate()

                    ));
                    edge.setTransitEdgeList(transitEdges);
                    edge.setPurchaseLink(url);

                    //полная инфа о ценах, типе вагона и кол-ве мест
                    List<String> types = new ArrayList<>();
                    for (Element typeEl : record.select("div.wg-wagon-type__title")) {
                        types.add(typeEl.text());
                    }
                    List<Double> prices = new ArrayList<>();
                    for (Element priceEl : record.select("span.wg-wagon-type__price").select("a")) {
                        prices.add(Double.parseDouble(priceEl.ownText().replace(" ", "").replace(",", ".")));
                    }
                    List<Integer> availableSeats = new ArrayList<>();
                    for (Element seats : record.select("span.wg-wagon-type__available-seats")) {
                        availableSeats.add(Integer.parseInt(seats.text().replaceAll("[^0-9]+", "")));
                    }
                    List<TrainTicketsInfo> fullInfo = new ArrayList<>();
                    for (int i = 0; i < types.size(); i++) {
                        fullInfo.add(new TrainTicketsInfo(types.get(i), prices.get(i), availableSeats.get(i)));
                    }
                    edge.setTrainTicketsInfoList(fullInfo);

                    //цена в зависимости от наличия мест
                    int numOfPassengers = numberOfAdults + numberOfChildren;
                    for (TrainTicketsInfo TrainTicketsInfo : fullInfo) {
                        if (numOfPassengers > TrainTicketsInfo.getAvailableSeats()) {
                            edge.setCost(BigDecimal.valueOf(edge.getCost()).add(BigDecimal.valueOf(TrainTicketsInfo.getCost()).multiply(BigDecimal.valueOf(TrainTicketsInfo.getAvailableSeats()))).doubleValue());
                            numOfPassengers -= TrainTicketsInfo.getAvailableSeats();
                        } else {
                            edge.setCost(BigDecimal.valueOf(edge.getCost()).add(BigDecimal.valueOf(TrainTicketsInfo.getCost()).multiply(BigDecimal.valueOf(numOfPassengers))).doubleValue());
                            numOfPassengers = 0;
                        }
                        if (numOfPassengers == 0){
                            //добавление эджа происходит только если мест в поезде хватает
                            edgeList.add(edge);
                            break;
                        }
                    }

                }
            } else {
                logger.error("нет данных по запросу {}", url);
                throw new UFSNoDataException();
            }
        }
        return edgeList;
    }

    private LocalDateTime convertTimeAndDate(String time, String date, LocalDate dateOfRequest) {
        date = date.substring(5).replace(".", "").concat(" " + String.valueOf(dateOfRequest.getYear()));
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd MMM yyyy")
                .toFormatter(Locale.ENGLISH);
        LocalDate formatDate = LocalDate.parse(date, formatter);
        //парсер не возвращает год. Поэтому если запрос сделан на декабрь, а возвратился январь, то плюсуем 1 год
        if (dateOfRequest.getMonthValue() == 12 && formatDate.getMonthValue() == 1){
            formatDate = formatDate.plusYears(1);
        }
        return LocalDateTime.of(formatDate, LocalTime.parse(time));
    }

    private Double travelTimeToDuration(String travelTime){
        List<Integer> durationList = new ArrayList<>();
        for (String stringPart : travelTime.split(" ")) {
            if (stringPart.matches("\\d*")){
                durationList.add(Integer.parseInt(stringPart));
            }
        }
        int duration = 0;
        switch (durationList.size()) {
            case 3:
                duration = (durationList.get(0) * 86400) + (durationList.get(1) * 3600) + (durationList.get(2) * 60);
                break;
            case 2:
                duration = (durationList.get(0) * 3600) + (durationList.get(1) * 60);
                break;
            case 1:
                duration = (durationList.get(0) * 60);
                break;
            default:
                break;
        }
        return (double)duration;
    }
}
