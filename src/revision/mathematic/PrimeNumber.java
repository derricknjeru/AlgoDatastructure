package revision.mathematic;

public class PrimeNumber {
    public static void main(String[] args) {
        int N = 12;
        System.out.println(isPrimeNumber2(N));
    }

    /**
     * A simple solution is to iterate through all numbers from 2 to n-1 and for every number check if it divides n.
     * If we find any number that divides, we return false.
     *
     * @param N
     * @return
     */
    //Time complexity is O(N)
    private static boolean isPrimeNumber(int N) {
        if (N < 2) return false;

        for (int i = 2; i < N; i++) {
            System.out.println(i);
            if (N % i == 0) return false;
        }
        return true;

    }

    //More optimized
    //The factors of a number are the numbers that divide into it exactly.
    //There are no factors behold n/2 e.g factors of 12 ---> 1,2,3,6,12
    //SO we will reduce the loop by dividing by 2

    //Time complexity ia O(n/2) not much difference in time

    private static boolean isPrimeNumber2(int N) {
        if (N < 2) return false;

        for (int i = 2; i < N / 2; i++) {
            System.out.println(i);
            if (N % i == 0) return false;
        }
        return true;

    }

    //Optimise it further
    //We have two factors a and b (small factor && large factor)
    //Small factor will always be equal to or less than square root of N
    //so a<=√N and b(large factor is) b =N/2
    // https://www.youtube.com/watch?v=T0XbxCYLBmc&ab_channel=PrepBytes
    //https://www.youtube.com/watch?v=7VPA-HjjUmU&ab_channel=mycodeschool

    //Time complexity is equal to O(√N)
    private static boolean isPrimeNumber3(int N) {
        if (N < 2) return false;

        int R = (int) Math.sqrt(N);
        //i <= R or i*i <=N
        for (int i = 2; i <= R; i++) {
            System.out.println(i);
            if (N % i == 0) return false;
        }
        return true;

    }

}
