package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import java.util.*;

public class CurrencyConversion {
    public static void main(String[] args) {
        //https://www.youtube.com/watch?v=Uei1fwDoyKk
        //https://interviewing.io/questions/currency-conversion
        //https://www.youtube.com/watch?v=L9Me2tDDgY8&t=1014s
        /**-
         * Question
         * Paramenters:
         *-
         * array of currency conversion rates. E.g. ['USD', 'GBP', 0.77] which means 1 USD is equal to 0.77 GBP
         * an array containing a 'from' currency and a 'to' currency
         * Given the above parameters, find the conversion rate that maps to the 'from' currency to the 'to' currency.
         * Your return value should be a number.
         *-
         * Example:
         * You are given the following parameters:
         *-
         * Rates: ['USD', 'JPY', 110] ['USD', 'AUD', 1.45] ['JPY', 'GBP', 0.0070]
         * To/From currency ['GBP', 'AUD']
         * Find the rate for the 'To/From' curency. In this case, the correct result is 1.89.
         * -
         *  Example 2
         *  Rates: ['USD', 'JPY', 100] ['JPY', 'CHN', 20] ['CHN', 'THAI', 200]
         *  [['USD', 'CHN'], ['JPY', 'THAI'], ['USD', 'AUD']]
         *  Output: [2000.0, 4000.0, -1.0]
         */
        List<Rate> rates = new ArrayList<>();
        rates.add(new Rate("USD", "JPY", 100.0));
        rates.add(new Rate("JPY", "CHN", 20.0));
        rates.add(new Rate("CHN", "THAI", 200.0));

        List<List<String>> currencyList = new ArrayList<>(Arrays.asList(
                Arrays.asList("USD", "CHN"),
                Arrays.asList("JPY", "THAI"),
                Arrays.asList("USD", "AUD")
        ));


        List<Double> result = getRates(rates, currencyList);
        System.out.println(result);
    }

    private static List<Double> getRates(List<Rate> rates, List<List<String>> currencyList) {
        List<Double> result = new ArrayList<>();

        // Build map
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (Rate rate : rates) {
            if (rate.getCurrency1() == null || rate.getCurrency2() == null) {
                continue;
            }
            graph.computeIfAbsent(rate.getCurrency1(), key -> new HashMap<>()).put(rate.getCurrency2(), rate.getRate());
            graph.computeIfAbsent(rate.getCurrency2(), key -> new HashMap<>()).put(rate.getCurrency1(), 1 / rate.getRate());
        }

        for (List<String> currency : currencyList) {
            String from = currency.get(0);
            String to = currency.get(1);

            double res = dfs2(graph, from, to,1.0, new HashSet<>());
            result.add(res);
        }

        return result;
    }

    private static double dfs(Map<String, Map<String, Double>> graph, String from, String to, Set<String> visited) {
        if (from.equals(to)) {
            return 1.0;
        }

        visited.add(from);
        Map<String, Double> nei = graph.get(from);

        if (nei != null) {
            for (Map.Entry<String, Double> entry : nei.entrySet()) {
                String neiNext = entry.getKey();
                double neiWeight = entry.getValue();
                if (!visited.contains(neiNext)) {
                    double result = dfs(graph, neiNext, to, visited);
                    if (result != -1.0) {
                        return result * neiWeight;
                    }
                }
            }
        }
        //could not find the destination
        return -1.0;
    }

    private static double dfs2(Map<String, Map<String, Double>> graph, String start, String end, double value, Set<String> visited) {
        if (!graph.containsKey(start) || visited.contains(start)) {
            return -1.0;
        }
        if (start.equals(end)) {
            return value;
        }
        visited.add(start);
        Map<String, Double> neighbors = graph.get(start);
        for (String neighbor : neighbors.keySet()) {
            double rate = dfs2(graph, neighbor, end, value * neighbors.get(neighbor), visited);
            if (rate != -1.0) {
                return rate;
            }
        }
        return -1.0;
    }

    private static double bfs(Map<String, Map<String, Double>> graph, String from, String to, Set<String> visited) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(from, 1.0));

        visited.add(from);

        while (!q.isEmpty()) {
            Node currentNode = q.poll();

            if (currentNode.currency.equals(to)) {
                return currentNode.rate;
            }

            Map<String, Double> nei = graph.get(currentNode.currency);
            if (nei != null) {
                for (Map.Entry<String, Double> entry : nei.entrySet()) {
                    if (!visited.contains(entry.getKey())) {
                        visited.add(entry.getKey());
                        q.add(new Node(entry.getKey(), currentNode.rate * entry.getValue()));
                    }
                }
            }
        }
        return -1.0;
    }

    static class Node {
        double rate;
        String currency;

        Node(String currency, double rate) {
            this.currency = currency;
            this.rate = rate;
        }
    }

    static class Rate {
        String currency1;
        String currency2;
        double rate;

        Rate(String currency1, String currency2, double rate) {
            this.currency1 = currency1;
            this.currency2 = currency2;
            this.rate = rate;
        }

        public double getRate() {
            return rate;
        }

        public String getCurrency1() {
            return currency1;
        }

        public String getCurrency2() {
            return currency2;
        }
    }
}
