package framework;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Properties {

    private ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<String>();
        try {
            FileReader reader = new FileReader("src/test/resources/properties.xml");
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Чтение файла настроек невозможно!");
        }
        return list;
    }

    public String getParameter(String parameter) {
        ArrayList<String> list = getList();
        String result = "";
        for (String item : list) {
            if (item.startsWith(parameter)) {
                int a = item.indexOf("\"");
                int b = item.lastIndexOf("\"");
                result = item.substring(a + 1, b);
                break;
            }
        }
        return result;
    }
}
