package RevisionGoogle;

public class GoogleMock1 {
    /**
     * Feedback
     * //spaces and words in same loop
     * //I need for more practice -> optimised solution
     * -> Only one copy is need i.e  for the result;;;
     *
     * ---> QUestions (A little figure) ->  Look at words. Some key words are redistribute mean something.
     * redestributing meant something
     * --> Limitation of words --->
     *
     * practice a little more -> don't take more than 3 minutes to understand a quiz
     * -Take interviewer through the solution and once you agree and he/she understands start coding
     *
     * Explain more on the questions
     *
     * focus on understanding questions and focusing.
     */

    /**
     * /* Input: An array of chars, containing
     * - 0 or more leading spaces
     * - 0 or more words separated by 1 or more spaces
     * - 0 or more trailing spaces
     * <p>
     * Output: An array of chars where spaces are re-distributed between the words in such a way that there are
     * - no more leading spaces
     * - no more trailing spaces
     * - roughly the same space gaps between the words. I.e. the minimum and the maximum number of spaces between the words can not differ by more than 1.
     * <p>
     * Input: "..word1...word2....word3." //10
     * Output: "word1.....word2.....word3"
     * Gaps: 5
     * <p>
     * word1...word2....word3 //10 spaces -> 3 -1
     * <p>
     * Restriction: in-place algorithm. Do not use additional space for copying the characters.
     *
     * @param
     */

    //https://collabedit.com/
    //https://stackoverflow.com/questions/5864159/count-words-in-a-string-method
    //https://leetcode.com/problems/rearrange-spaces-between-words/submissions/

    //spaces ->
    //words ->

    //trim();
    //s = s.replaceAll("\\s+"," ")

    //No of spaces between word
    //   10/3 ->3 /1 -->1...2...3....4
    // 3 words  countSPaces/(3-1);
    public static void main(String[] args) {
        String s = "  word1  word2   word3    ";
        char[] array = s.toCharArray();
        redistributeSpaces(s);
        System.out.println(redistributeSpaces(s));
    }

    public static String redistributeSpaces(String s) {
        int whiteSPaces = 0;

        int wordCount = 0;

        int n = s.length();
        boolean isWord = false;
        int endOfLine = s.length() - 1;


        for (int i = 0; i < n; i++) {

            char c = s.charAt(i);

            if (c == ' ') whiteSPaces++;

            if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
                isWord = true;
                // if char isn't a letter and there have been letters before, // counter goes up.
            } else if (!Character.isLetter(s.charAt(i)) && isWord) {
                wordCount++;
                isWord = false;
                // last word of String; if it doesn't end with a non letter, it // wouldn't count without this.
            } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
                wordCount++;
            }

        }

        //trim
        s = s.trim();

        //create a proper sentense
        s = s.replaceAll("\\s+", " ");


        if(whiteSPaces == 0)
            return s;

        int totalSpaces = wordCount > 1 ? whiteSPaces / (wordCount - 1) : 0;
        int rem = wordCount > 1 ? whiteSPaces % (wordCount - 1) : whiteSPaces;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                int extra = (rem > 0) ? 1 : 0;
                String prefix = ".".repeat(totalSpaces + extra);
                sb.append(prefix);
                rem--;
            } else {
                sb.append(c);
            }

        }
        return sb.toString();
    }


}
