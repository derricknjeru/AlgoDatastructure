package CodingInterviewPatterns.tips;

public class cooltricks {
    public static void main(String[] args) {
        //consist of lowercase English letters only implies
        int[] arr = new int[26];

        //This is how you get index of letters
        char c = 'z';
        arr[c - 'a'] = 1; //or increment it /or do somthing else
        System.out.println(arr[25]); //we got index for 25

        //convert integer character to a integer
        char c1 = '9';
        int a = c1 - '0';
        System.out.println(a);

        ////horners rule
        int no = 9;
        int result = 1;
        result = result * 10 + no;
        System.out.println(result); //should print 19
    }

}
