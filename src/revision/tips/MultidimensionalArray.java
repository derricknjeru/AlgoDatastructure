package revision.tips;

public class MultidimensionalArray {
    public static void main(String[] args) {
        int[][] twoDArray = new int[2][2];

        System.out.println("TwoDArray size" + 2 * 2);

        int[][] twoDArray1 = {{1, 2}, {3, 4}};

        int totalLength = 1;

        for (int i = 0; i < twoDArray1.length; i++) {
            totalLength *= twoDArray1[i].length;
            for (int j = 0; j < twoDArray1[i].length; j++) {
                System.out.println(twoDArray1[i][j]);
            }
        }

        System.out.println("twoDArray1::" + totalLength);

    }
}
