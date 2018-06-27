package ru.sukmanov.lab3.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DomParser {

    public void parsing(String path) {

        try {
            //Создаём строитель документов
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //Создаём дерево DOM документа из файла
            Document document = documentBuilder.parse(path);

            //Получаем корневой элемент
            Node root = document.getDocumentElement();
            //Просматриваем все элементы корневого
            NodeList elements = root.getChildNodes();
            for (int i = 0; i < elements.getLength(); i++) {
                Node element = elements.item(i);
                //Если эта нода не текст, то это блок записи - значит заходим внутрь
                if (element.getNodeType() != Node.TEXT_NODE) {
                    //Вытаскиваем информацию по каждой записи
                    NodeList items = element.getChildNodes();
                    for (int j = 0; j < items.getLength(); j++) {
                        Node item = items.item(j);
                        //Если нода не текст, то это один из пунктов информации о записи
                        if (item.getNodeType() != Node.TEXT_NODE) {
                            System.out.printf("%s: %s\n",item.getNodeName(), item.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println();
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
