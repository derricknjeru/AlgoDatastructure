package dynamic_programming_and_recursion;

public class ReverseString {
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        helper(s,i,j);
    }
    /**
     In this case we are modifying the same array using diffrent reference variable.
     So, Constant Space = O(1)
     **/
    private void helper(char[] s, int i, int j){
        //base
        if(i>j) return;

        //swap
        char temp = s[i];
        s[i]=s[j];
        s[j]=temp;

        helper(s, ++i, --j);

    }
}
