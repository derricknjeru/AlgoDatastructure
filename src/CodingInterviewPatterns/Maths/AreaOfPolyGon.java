package CodingInterviewPatterns.Maths;

public class AreaOfPolyGon {
    /**
     * Below we will define an n-interesting polygon. Your task is to find the area of a polygon for a given n.
     * <p>
     * A 1-interesting polygon is just a square with a side of length 1. An n-interesting polygon is obtained by taking the n - 1-interesting polygon and appending 1-interesting polygons to its rim, side by side. You can see the 1-, 2-, 3- and 4-interesting polygons in the picture below.
     * <p>
     * <p>
     * <p>
     * Example
     * <p>
     * For n = 2, the output should be
     * solution(n) = 5;
     * For n = 3, the output should be
     * solution(n) = 13.
     */

    static int solution(int n) {
        if (n == 1) {
            return 1; // Area of a 1-interesting polygon is 1
        } else {
            int previousPolygonArea = solution(n - 1);
            int additionalRimArea = (n - 1) * 4; // Each 1-interesting polygon adds 4 units to the perimeter
            return previousPolygonArea + additionalRimArea;
        }
    }

    public static void main(String[] args) {
        int n = 2;
        int area = solution(n);
        System.out.println("The area of the " + n + "-interesting polygon is: " + area);

        n = 3;
        area = solution(n);
        System.out.println("The area of the " + n + "-interesting polygon is: " + area);
    }
}
