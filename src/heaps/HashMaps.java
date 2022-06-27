package heaps;

import java.util.*;


public class HashMaps {
    public static void main(String[] args) {
        /**
         * With hashing we get O(1) search time on average (under reasonable assumptions) and O(n) in worst case.
         */

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(Map.Entry.comparingByValue(Collections.reverseOrder()));

        HashMap<Double, Integer> map = createPrintHashMap();

       // Map.Entry.comparingByKey(Collections.reverseOrder());
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).forEach(x -> {

        });

        Comparator<Map.Entry<String,String>> valueComparator=(k,v)-> v.getValue().compareTo(v.getValue());


        sortMapByKeyUsingTreeMap(map);
        sortValueUsingComparator(map);
        sortValueUsingStreamAPI(map);

        List<Person> list = createList();
        sortListComparator(list);


    }

    private static void sortValueUsingStreamAPI(HashMap<Double, Integer> map) {
        System.out.println("\n------------sorted list by value using StreamAPI---------------------\n");
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(x -> {
        });
    }

    private static void sortListComparator(List<Person> list) {
        //Collections.sort(list, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        Collections.sort(list, (o1, o2) -> o1.getAge() - o2.getAge());
        System.out.println("\n------------sorted list---------------------\n");
        for (Person m : list) {
            System.out.println("Key " + m.getName() + " Value " + m.getAge());
        }
        System.out.println(list);



    }

    private static ArrayList<Person> createList() {
        System.out.println("\n------------Create list---------------------\n");
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("Ninah", 23));
        list.add(new Person("Derrick", 28));
        list.add(new Person("Fred", 27));
        list.add(new Person("Carol", 32));

        for (Person m : list) {
            System.out.println("Key " + m.getName() + " Value " + m.age);
        }

        return list;
    }

    private static void sortValueUsingComparator(HashMap<Double, Integer> map) {
        Comparator<Map.Entry<Double, Integer>> valueComparator = (x, y) -> y.getValue().compareTo(x.getValue());

        List<Map.Entry<Double, Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, valueComparator);

        System.out.println("\n------------Sort by value using Comparator---------------------\n");
        for (Map.Entry<Double, Integer> m : list) {
            System.out.println("Key " + m.getKey() + " Value " + m.getValue());
        }

    }

    private static void sortMapByKeyUsingTreeMap(HashMap<Double, Integer> map) {
        TreeMap<Double, Integer> treeMap = new TreeMap<>(map);

        System.out.println("\n------------Sort by key using TreeMap---------------------\n");

        for (Map.Entry<Double, Integer> m : treeMap.entrySet()) {
            System.out.println("Key " + m.getKey() + " Value " + m.getValue());
        }
    }

    private static HashMap<Double, Integer> createPrintHashMap() {
        HashMap<Double, Integer> map = new HashMap<>();
        map.put(4.0, 5);
        map.put(2.0, -1);
        map.put(1.3, 9);
        map.put(1.4, 13);
        map.put(5.0, -67);
        map.put(6.0, 100);
        map.put(7.0, 99);

        for (Map.Entry<Double, Integer> m : map.entrySet()) {
            System.out.println("Key " + m.getKey() + " Value " + m.getValue());
        }

        return map;
    }

    static class Person {
        private String name;
        private int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}
