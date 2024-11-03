import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        List<Employee> list = parseXML("data.xml");
        String json = listToJson(list);
        writeString(json);

    }

    public static List<Employee> parseXML(String s) throws ParserConfigurationException, IOException, SAXException {
        List<String> el = new ArrayList<>();
        List<Employee> listing = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder bilder = factory.newDocumentBuilder();
        Document document = bilder.parse(new File(s));

        Node root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                NodeList nodeListNext = node.getChildNodes();
                for (int j = 0; j < nodeListNext.getLength(); j++) {
                    Node nodeNext = nodeListNext.item(j);
                    if (Node.ELEMENT_NODE == nodeNext.getNodeType())
                        el.add(nodeNext.getTextContent());
                }
                listing.add(new Employee(
                        Long.parseLong(el.get(0)),
                        el.get(1),
                        el.get(2),
                        el.get(3),
                        Integer.parseInt(el.get(4))));
                el.clear();
            }
        }
        return listing;
    }


    private static String listToJson(List<Employee> list) {
        GsonBuilder gBild = new GsonBuilder();
        gBild.setPrettyPrinting();
        Gson gson = gBild.create();
        return gson.toJson(list);
    }


    private static void writeString(String json) {
        try (FileWriter saveJson = new FileWriter("data.json")) {
            saveJson.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
