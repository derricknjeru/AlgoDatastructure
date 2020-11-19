package revision.strings_and_arrays;

public class OccurrencesOfAWordInString {
    public static void main(String[] args) {
        String str = "GeeksforGeeks A computer science portal for geeks computer ";
        String word = "computer";
        System.out.printf("The countOccurrences %d", countOccurrences(str, word));
    }

    static int countOccurrences(String str, String word) {
        int occurrences = 0;

        String[] arr = str.split(" ");

        for (String s : arr) {
            if (s.equals(word)) {
                occurrences++;
            }
        }
        return occurrences;
    }


}
