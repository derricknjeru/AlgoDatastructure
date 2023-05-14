package companies.Google;

public class MaximumPhotoNumber {
    public static void main(String[] args) {
        //https://leetcode.com/discuss/interview-experience/2072074/Google-or-Software-Engineer-L3-or-Banglore-or-May-2022-Reject
        //https://leetcode.com/discuss/interview-experience/1724410/google-jan-2022
        //https://leetcode.com/discuss/interview-question/1835838/google-onsite-coding-question-2022
        //https://leetcode.com/discuss/interview-experience/1744483/Google-or-L4-or-Bangalore-or-January-2022-or-Offer-(L3)/1253481
        PhotosClient client = new PhotosClient(10);
        client.ack(1);
        System.out.println("Max so far " + client.getMax());
        client.ack(2);
        System.out.println("Max so far " + client.getMax());
        client.ack(5);
        System.out.println("Max so far " + client.getMax());
        client.ack(4);
        System.out.println("Max so far " + client.getMax());
        client.ack(3);
        System.out.println("Max so far " + client.getMax());

    }

    /**
     * Problem statement:
     * Suppose you are working on companies.Google Photos. you are wring the client application. Request comes to you to upload N photos. you fire the request to server to upload those N photos to server. Then the server responds back with acknowledgements that a particular photo is uploaded.
     * Example. Suppose you are uploading 10 Photos, The server can respond back in any order, such as 1,2,4,5,3,7,6,10,9,8 . Now at any given point of time we need to check what is the maximum photo number which has been uploaded continously.
     * Example.
     * <p>
     * ack(1),
     * getMax()-> returns 1, because the maximum photo uploaded is 1
     * ack(2),
     * getMax()-> returns 2, because the maximum photo uploaded is 2
     * ack(4)
     * getMax()-> returns 2 only because 3 has not been recieved yet
     * ack(5)
     * getMax()-> returns 2 again because 3 has not been recieved yet
     * ack(3)
     * getMax()-> returns 5 because we recieved 3 and 4 and 5 also we recieved eralier. using this example you have to complete the following class
     * <p>
     * public class PhotosClient {
     * <p>
     * // initializer
     * public PhotosClient(int n) {
     * <p>
     * }
     * // this method is called each time you receive acknowledgement forom the server
     * public void ack(int x) {
     * <p>
     * }
     * // this method will be called in between to check what the maximum photo number has been uploaded successfully
     * public int getMax() {
     * <p>
     * }
     * }
     */

    public static class PhotosClient {
        int max = 1;
        boolean[] photos;

        // initializer
        public PhotosClient(int n) {
            photos = new boolean[n + 1];
        }

        // this method is called each time you receive acknowledgement forom the server
        // O(N) wost case(ack: 5,4,3,2,1), O(1) Best case
        public void ack(int x) {
            if (x >= photos.length) return; // throw exception
            photos[x] = true;
            while (photos[max]) {
                max++;
            }
        }

        // this method will be called in between to check what the maximum photo number has been uploaded successfully
        // O(1)
        public int getMax() {
            return max - 1;
        }
    }
}
