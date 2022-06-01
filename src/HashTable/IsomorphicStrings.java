package HashTable;

public class IsomorphicStrings {
    //Strings that have 1 to 1 mapping
    /**
     * Given two strings s and t, determine if they are isomorphic.
     *
     * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
     *
     * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "egg", t = "add"
     * Output: true
     * Example 2:
     *
     * Input: s = "foo", t = "bar"
     * Output: false
     * Example 3:
     *
     * Input: s = "paper", t = "title"
     * Output: true
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 104
     * t.length == s.length
     * s and t consist of any valid ascii character.
     */

    //https://www.youtube.com/watch?v=iZ2eG3ySN68

    public boolean isIsomorphic(String s, String t) {
        if(s==null || t==null) return false;

        int [] m1 = new int[256]; //extended Aschii

        int [] m2 = new int[256];

        for(int i=0; i<s.length(); i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if(m1[c1]!=m2[c2]) return false;

            m1[c1]=i+1;
            m2[c2]=i+1;

        }

        return true;
    }

    // 3  ---> 2 ---> 0 ---> -4 --->2
    //                        s
    //                               f
}
