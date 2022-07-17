package SearchingAndSorting;

public class SqrtX {
    public int mySqrt(int x) {

        int low = 0;

        int high = x;

        if (x < 2) {
            return x;
        }
       //https://www.youtube.com/watch?v=VYtEKhxKd1Q

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (mid == x / mid) {
                return mid;
            }

            if (x / mid > mid) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }


        }

        return low - 1;

    }
}
