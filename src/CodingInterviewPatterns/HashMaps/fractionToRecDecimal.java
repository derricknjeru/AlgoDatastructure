package CodingInterviewPatterns.HashMaps;

import java.util.HashMap;
import java.util.Map;

public class fractionToRecDecimal {
    public static void main(String[] args) {
        //https://www.youtube.com/watch?v=a-62yK1S1O4
        //

    }

    class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            if(numerator ==0) return "0";
            boolean isNegative = false;

            if(denominator<0 && numerator>0 || numerator<0 && denominator>0) isNegative = true;

            long d = Math.abs((long)denominator);
            long n = Math.abs((long)numerator);

            long quotient = n/d;

            StringBuilder sb = new StringBuilder();
            if(isNegative) sb.append("-"+quotient);
            else sb.append(quotient);

            long rem = n%d;
            if(rem == 0) return sb.toString();

            sb.append(".");

            Map<Long,Integer> map = new HashMap<>();

            while(rem!=0){

                if(map.containsKey(rem)){
                    sb.insert(map.get(rem),"(");
                    sb.append(")");
                    break;
                }else{
                    map.put(rem, sb.length());
                    rem *=10;
                    quotient = rem/d;
                    sb.append(quotient);
                    rem = rem%d;
                }

            }

            return sb.toString();

        }
    }
}
