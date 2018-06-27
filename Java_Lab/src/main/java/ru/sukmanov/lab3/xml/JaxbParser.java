package ru.sukmanov.lab3.xml;

import ru.sukmanov.lab3.common.AbstractRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbParser {

    //Unmarchalling
    public static AbstractRepository converXmlToObject (String filePath) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(AbstractRepository.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return  (AbstractRepository) unmarshaller.unmarshal(new File(filePath));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Machalling
    public static void converObjectToXml(AbstractRepository t, String filePath) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(AbstractRepository.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(t, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
