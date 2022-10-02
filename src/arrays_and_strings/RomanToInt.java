package arrays_and_strings;

import java.util.HashMap;

public class RomanToInt {
    public static void main(String[] args) {

    }

    /**
     * var romanToInt = function(s) {
     *
     *     const map = {I:1, V:5, X:10, L:50, C:100, D:500,M:1000}
     *
     *     var result = 0;
     *
     *     for(var i =0; i<s.length; i++){
     *
     *         if(i>0 && map[s[i]]>map[s[i-1]]){
     *             result+=map[s[i]] - 2*map[s[i-1]];
     *         }else{
     *             result+=map[s[i]];
     *         }
     *
     *     }
     *
     *     return result;
     *
     * };
     * @param s
     * @return
     */

    //https://www.youtube.com/watch?v=hEhf_oz3wsM&ab_channel=JavaBrains
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && map.get(s.charAt(i)) > map.get(s.charAt(i - 1)))

                result += map.get(s.charAt(i)) - 2 * map.get(s.charAt(i - 1));
            else
                result += map.get(s.charAt(i));
        }
        return result;
    }
}
