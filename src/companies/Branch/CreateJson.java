package companies.Branch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateJson {
    /**
     * productId, name, description, cost
     * 1, cup, it is a cup, $10
     * 2, water, water bottle, $$3
     * 3, soap, bar soap, $2
     * 4, fruits,'Apple, banana,Mango', $2
     * <p>
     * [
     * {
     * "productId": 1,
     * "name":"cup",
     * "description": "it is a cup",
     * "cost": "$10"
     * },
     * {
     * "productId": 2,
     * "name":"water",
     * "description": "it is a cup",
     * "cost": "$10"
     * }
     * ]
     */
    public static void main(String[] args) {

        String input = "" +
                "productId, name, description, cost\n" +
                "1, cup, it is a cup, $10\n" +
                "2, water, water bottle, $$3\n" +
                "3, soap, bar soap, $2 \n" +
                "productId, name, description, cost\n" + "4, fruits,'Apple, banana,Mango', $2";

        System.out.println(generateJson(input));
    }

    private static List<HashMap<String, String>> generateJson(String input) {
        List<HashMap<String, String>> result = new ArrayList<>();
        String[] inputArray = input.split("\n");
        String[] columnArray = inputArray[0].split(",");

        for (int i = 1; i < inputArray.length; i++) {
            String row = inputArray[i];
            String[] rowArray = processRow(row, columnArray.length);
            createJson(result, rowArray, columnArray);
        }

        return result;
    }

    private static String[] processRow(String row, int size) {
        //"3, soap, bar soap, $2\n" +
        //"4, fruits,'Apple, banana,Mango', $2";
        //String[] res =  new String[size];//
        List<String> res = new ArrayList<>();
        if (row.contains("'")) {
            char[] arr = row.toCharArray();
            StringBuilder sb = new StringBuilder();
            int i = 0;

            while (i < arr.length) {
                if (arr[i] == ',') {
                    res.add(sb.toString());
                    sb = new StringBuilder();
                } else if (arr[i] == '\'') {
                    i++;
                    while (i < arr.length && arr[i] != '\'') {
                        sb.append(arr[i]);
                        i++;
                    }
                } else {
                    sb.append(arr[i]);
                }
                i++;
            }

            res.add(sb.toString());
            return res.toArray(new String[0]);
        } else {
            return row.split(",");
        }

    }

    private static void createJson(List<HashMap<String, String>> result, String[] rowArray, String[] columnArray) {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < columnArray.length; i++) {
            map.put(columnArray[i], rowArray[i]);
        }
        result.add(map);
    }
}
