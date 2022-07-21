package RevisionWithMike;

import java.util.*;

public class HashMapPractice {
    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();
        //TreeMap<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());

        map.put("a", 20);
        map.put("b", 21);
        map.put("x", 26);
        map.put("y", 10);


        //sort key
        LinkedHashMap<String, Integer> lMap = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .filter(x -> x.getValue() % 2 == 0)
                .forEach(x -> System.out.println(x.getValue()));

        StringBuilder sb = new StringBuilder();
        sb.insert(0, "mike");
        sb.insert(0, "derrick");
        sb.reverse();


        System.out.println(sb.toString());


        List<Integer> list = new ArrayList<>();
        Collections.sort(list, Collections.reverseOrder());
        Collections.reverse(list);

        list.add(5);
        // list.set(4, 10); //list need some data -> replace index 4 data
        //list.add(0, 6); //--> move data in index 0 to index 1

        System.out.println(list);

        Map<Integer, Map.Entry<Integer, Integer>>[] x = new HashMap[4];
        // x[9].put()

        // Character.isLetterOrDigit('');
        // Character.isDigit('');
        // Character.isLetter('');
        char cx = '9';
        int ccInt = cx - '0';
        System.out.println(ccInt);

        int[] array = new int[26];
        char c = 'A';
        int cxx = c - 'A';
        System.out.println(cxx);
        //array[c] = c;

        HashMap<String, Integer> map2 = new HashMap<>(map); //copy another map

        map2 = (HashMap<String, Integer>) map.clone();

        List<String> list2 = new ArrayList<>(map.keySet());

        Iterator<String> iterator = list2.iterator();
        iterator.remove();
        while (iterator.hasNext()) {

        }

        Set<String> set = new HashSet();
        set.add(null);
        set.add(null);


    }
}
