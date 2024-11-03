import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readString;

public class Main {
    public static void main(String[] args) throws IOException {

        String json = readString(Path.of("new_data.json"));
        List<Employee> list = jsonToList(json);
        list.forEach(System.out::println);


    }


    private static List<Employee> jsonToList(String json) {

        List<Employee> list = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Object obj = new JSONParser().parse(json);
            JSONArray array = (JSONArray) obj;
            for (Object object : array) {
                list.add(gson.fromJson(object.toString(), Employee.class));
            }
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return list;
    }


//    private static String readString() {
//        StringBuilder sb = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new FileReader("new_data.json"))) {
//            String s;
//            while ((s = br.readLine()) != null) {
//                sb.append(s);
//                sb.append("\n");
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return sb.toString();
//    }

}
