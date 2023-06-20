package CodingInterviewPatterns.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NextTest {

    /**
     * [["I", "am", "a", "dev"],["","",""]]
     * Organize into an easy to search pattern
     * This pattern shows the preceding word and subsequent words that come after them base on the
     * frequency of occurrence
     *
     * {"I" => [{"am" => 2}, "will" => 1], "am" => [{"Mike" => 1}, {"the" => 1}]}
     *
     */

    private HashMap<String, List<HashMap<String, Integer>>> searchMap = new HashMap<>();


    public NextTest() {
        List<List<String>> dictionary = Arrays.asList(
                Arrays.asList("I", "am", "Mike"),
                Arrays.asList("I", "am", "the", "dev"),
                Arrays.asList("the", "dev", "is", "cool"),
                Arrays.asList("Mike", "is", "a", "cool", "guy"),
                Arrays.asList("I", "will", "be", "back"));

        Iterator<List<String>> listIterator = dictionary.iterator();

        while (listIterator.hasNext()) {
            List<String> subDictionary = listIterator.next();
            for (int i = 0; i < subDictionary.size(); i++) {
                if (i > 0) {
                    storeWords(subDictionary.get(i-1), subDictionary.get(i));
                }
            }
        }
    }

    /**
     * Load set of words into an easily extracted data structure
     * {"I" => [{"am" => 2}, "will" => 1], "am" => [{"Mike" => 1}, {"the" => 1}]}
     * @param parentWord preceding
     * @param nextWord next
     */
    public void storeWords(String parentWord, String nextWord) {
        if (searchMap.containsKey(parentWord)) {
            List<HashMap<String, Integer>> groupList = searchMap.get(parentWord);
            Iterator<HashMap<String, Integer>> groupIterator = groupList.iterator();
            while (groupIterator.hasNext()) {
                HashMap<String, Integer> freqMap = groupIterator.next();
                if (freqMap.containsKey(nextWord)) {
                    freqMap.put(nextWord, freqMap.get(nextWord)+1);
                } else {
                    freqMap.put(nextWord, 1);
                }
            }
        } else {
            List<HashMap<String, Integer>> groupList = new ArrayList<>();
            HashMap<String, Integer> wordFreq = new HashMap<>();
            wordFreq.put(nextWord, 1);
            groupList.add(wordFreq);

            searchMap.put(parentWord, groupList);
        }
    }

    /**
     * returns next word
     * @param searchWord search string
     * @return next word
     */
    public String nextWord(String searchWord) {
        String nextWord = "";

        List<HashMap<String, Integer>> hashMapList = searchMap.get(searchWord);
        int max = Integer.MIN_VALUE;

        HashMap<String, Integer> wordFrequencyMap = hashMapList.get(0);

        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            if (entry.getValue() >  max) {
                max = entry.getValue();
                nextWord = entry.getKey();
            }
        }

        return nextWord;
    }

    /**
     *
     * @param searchWord search
     * @param nWords count of sequence after initial search input
     * @return next set of words separated by space
     */
    public String nextWord(String searchWord, int nWords) {
        StringBuilder stringBuilder = new StringBuilder();

        do {
            String nextWord = nextWord(searchWord);
            stringBuilder.append(nextWord);
            nWords--;
            searchWord = nextWord;
            if (nWords > 0) {
                stringBuilder.append(" ");
            }
        } while (nWords > 0);

        return stringBuilder.toString();
    }

    /**
     * {the=[{dev=2}], a=[{cool=1}], Mike=[{is=1}], be=[{back=1}], dev=[{is=1}], will=[{be=1}], cool=[{guy=1}], I=[{will=1, am=2}], is=[{a=1, cool=1}], am=[{the=1, Mike=1}]}
     * @param args args
     */
    public static void main(String[] args) {
        NextTest nextWordPredictor = new NextTest();

        System.out.println("=======================================================");

        System.out.println(nextWordPredictor.searchMap);

        System.out.println("=======================================================");

        String currentWord = "I";
        String nextWord = nextWordPredictor.nextWord(currentWord);
        System.out.println("Current Word: " + currentWord + " -> Next word: " + nextWord);

        String currentWord2 = "cool";
        System.out.println("Current Word: " + currentWord2 + " -> Next word: " + nextWordPredictor.nextWord(currentWord2));

        String currentWord3 = "be";
        System.out.println("Current Word: " + currentWord3 + " -> Next word: " + nextWordPredictor.nextWord(currentWord3));

        System.out.println("=======================================================");

        String currentWord4 = "Mike";
        System.out.println("Current Word: " + currentWord4 + " -> Next word: " +
                nextWordPredictor.nextWord(currentWord4, 4));
    }
}