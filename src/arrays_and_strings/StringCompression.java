package arrays_and_strings;

public class StringCompression {
    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(compress1(chars));
    }

    public static int compress1(char[] chars) {
        int count = 1;
        int writeIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i + 1 == chars.length || chars[i] != chars[i + 1]) {
                chars[writeIndex++] = chars[i];
                if (count > 1) {
                    String s = String.valueOf(count);
                    for (char c : s.toCharArray()) {
                        chars[writeIndex++] = c;
                    }
                }
                //reset count
                count = 1;
            } else {
                count++;
            }
        }

        return writeIndex;
    }

    public static int compress(char[] chars) {
        int count = 1;
        int writeIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i + 1 == chars.length || chars[i] != chars[i + 1]) {
                chars[writeIndex++] = chars[i];
                if (count > 1) {
                    String countStr = String.valueOf(count);
                    for (int j = 0; j < countStr.length(); j++)
                        chars[writeIndex++] = countStr.charAt(j);
                }
                count = 1;
            } else {
                count++;
            }
        }
        return writeIndex;
    }

    public int compress2(char[] chars) {
        int index = 0;
        int i = 0;
        int j = 0;

        while (i < chars.length) {
            while (j < chars.length && chars[i] == chars[j]) {
                j++;
            }

            chars[index++] = chars[i];

            int def = j - i;

            if (def > 1) {
                String x = def + "";
                char[] arr = x.toCharArray();
                for (char c : arr) {
                    chars[index++] = c;
                }

            }

            i = j;

        }
        return index;


    }
}
