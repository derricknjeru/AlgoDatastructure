package CodingInterviewPatterns.Interesting;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int width) {

        List<String> result = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        int noOfwords = 0;

        for (int i = 0; i < words.length; i++) {

            String word = words[i];
            int wordCount = sb.length() + word.length();

            if (wordCount <= width) {
                sb.append(words[i]);
                sb.append(" ");
                noOfwords++;
            } else {
                result.add(formattedString(sb.toString().trim(), width, false, noOfwords));
                sb = new StringBuilder();
                sb.append(word);
                sb.append(" ");
            }

        }

        result.add(formattedString(sb.toString().trim(), width, true, 1));
        return result;
    }

    private String formattedString(String s, int width, boolean isLast, int noOfWords) {

        //int noOfWords = s.split("\\s+").length;
        int totalCharacters = s.length();

        StringBuilder sb = new StringBuilder();
        if (!isLast) {

            int whiteSpaces = width - (totalCharacters - (noOfWords - 1));
            int slots = noOfWords - 1;

            int individualSpace = noOfWords > 1 ? whiteSpaces / slots : 0;
            int rem = noOfWords > 1 ? whiteSpaces % slots : whiteSpaces;


            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    int extra = (rem > 0) ? 1 : 0;
                    int totalSpace = individualSpace + extra;
                    String prefix = " ".repeat(totalSpace);
                    sb.append(prefix);
                    rem--;

                } else {
                    sb.append(c);
                }
            }
            if (noOfWords == 1) {
                int spaces = width - totalCharacters;
                sb.append(" ".repeat(spaces));
            }
        } else {
            sb.append(s);
            int whiteSpaces = width - totalCharacters;
            String prefix = " ".repeat(whiteSpaces);
            sb.append(prefix);
        }

        return sb.toString();
    }
}
