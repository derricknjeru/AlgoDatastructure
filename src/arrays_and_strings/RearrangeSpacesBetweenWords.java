package arrays_and_strings;

public class RearrangeSpacesBetweenWords {
    //https://stackoverflow.com/questions/5864159/count-words-in-a-string-method
    public String reorderSpaces(String s) {
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
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c==' '){
                String prefix = " ".repeat(totalSpaces);
                sb.append(prefix);
            }else{
                sb.append(c);
            }

        }

        return sb + " ".repeat(rem);

    }
}
