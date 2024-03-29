package CodingInterviewPatterns.DynamicProgramming;

public class GreatestCommonDivisor {
    //https://www.interviewbit.com/problems/greatest-common-divisor/
    //https://www.youtube.com/watch?v=uAWxDcFaVwc
    //Euclidean Algorithm for GCD
    //https://www.geeksforgeeks.org/euclidean-algorithms-basic-and-extended/

    //Time complexity of euclidean algo is log min(a, b)

    public int gcd(int A, int B) {
        if (A == 0) return B;
        return gcd(B % A, A);
    }

}
