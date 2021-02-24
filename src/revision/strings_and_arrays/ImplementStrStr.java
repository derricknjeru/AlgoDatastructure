package revision.strings_and_arrays;

public class ImplementStrStr {
    public static void main(String[] args) {

    }

    public int strStr(String haystack, String needle) {
        if (needle == null || haystack == null) {
            return -1;
        } else if (needle.isEmpty()) {
            return 0;
        } else if (haystack.isEmpty()) {
            return -1;
        } else {
            return haystack.indexOf(needle);
        }

    }

    public int strStr2(String haystack, String needle) {
        if (needle.isEmpty())
            return 0;

        int i = 0;

        int m = haystack.length();
        int n = needle.length();

        if (m < n) return -1;

        while (i <= m - n) {

            int j = 0;

            while (j < n && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }

            if (j == n) return i;

            i++;
        }


        return -1;


    }

    public int strStr3(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;

        if (needle.length() > haystack.length())
            return -1;

        for (int i = 0; i < haystack.length(); i++) {
            int j = 0, z = i;
            for (; j < needle.length() && z < haystack.length(); j++, z++) {
                if (haystack.charAt(z) != needle.charAt(j)) break;
            }
            if (j == needle.length()) return i;
        }
        return -1;
    }
}
