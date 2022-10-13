/**
 * Amazon Fresh is a new grocery store designed from the ground up to offer a seamless grocery shopping experience to consumers. As part of a stock clearance exercise at the store, given many piles of fresh products, follow the rules given below to stack the products in an orderly manner. •
 * <p>
 * There are a total of n piles of products.
 * <p>
 * • The number of products in each pile is represented by the array numProducts.
 * <p>
 * • Select any subarray from the array numProducts and pick up products from that subarray such that the number of products you pick from the fh pile is strictly less than the number of products you pick from the (i+1 ) th for all indices of the subarray.
 * <p>
 * Find the maximum number of products that can be picked Example The numbers of products in each pile are 1 left There are a total of piles of products. •
 * <p>
 * The number of products in each pile is represented by the array numProducts • Select any subarray from the array numProducts and pick up products from that subarray such that the number of products you pick from the ith pile is strictly less than the number of products you pick from the (i+13th pile for all indices i of the subarray.
 * <p>
 * Find the maximum number of products that can be picked.
 * <p>
 * Example: The numbers of products in each pile are numProducts = [7, 4, 5, 2, 6, 5). These are some ways strictly increasing subarrays can be chosen (1-based index):
 * <p>
 * • Choose subarray from indices (1, 3) and pick products [3, 4, 5] respectively from each index, which is 12 products.
 * <p>
 * Note that we are forced to pick only 3 products from index 1 as the maximum number of products we can pick from index 2 is 4 and we need to make sure it is greater than the number of products picked from index 1.
 * <p>
 * • Choose subarray from indices (3,6) Find the maximum number of products that can be picked ALL .
 * <p>
 * Example The numbers of products in each plle are numProducts = [7, 4, 5, 2, 6,5).
 * <p>
 * These are some ways strictly increasing subarrays can be chosen (1-based index): 2 • Choose subarray from indices (1, 3) and pick products [3, 4, 5) respectively from each index, which is 12 products. Note that we are forced to pick only 3 products from index 1 as the maximum number of products we can pick from index 2 is 4 and we need to make sure it is greater than the number of products picked from index 1.
 * <p>
 * • Choose subarray from indices (3, 6) and pick products (1, 2, 4, 5] respectively from each index, which is 12 products.
 * <p>
 * Similar to the above case, we are forced to pick only 1 product from index 3 as the number of products at index 4 is only 2. • Choose subarray from indices (3,5) and pick products [1, 2, 6] respectively from each index, which is 9 products,
 * <p>
 * • Choose subarray from indices (1, 1) and pick all the 7 products - The maximum number of products is 12.
 * <p>
 * Function Description Complete the function findMaxProducts in the editor below. findMaxProducts has the following parameters: int numProducts[n]: the number of products in each pile. Returns int: the maximum number of products that can be picked Constraints .
 * <p>
 * Sample input
 * <p>
 * STdin:
 * <p>
 * 6 ----> Number of products size n
 * <p>
 * 2
 * <p>
 * 9
 * <p>
 * 4
 * <p>
 * 7
 * <p>
 * 5
 * <p>
 * 2
 * <p>
 * Output :16
 **/

package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://www.chegg.com/homework-help/questions-and-answers/java-problem-find-max-products-sub-array-questions-details-screenshot-q95046577

class SumNoOfProduct {
    public static void main(String[] args) {
        List list = Arrays.asList(7, 4, 5, 2, 6, 5);

        System.out.println(findMaxProducts(Arrays.asList(2, 9, 4, 7, 5, 2)));

        //7, 4, 5, 2, 6, 5

        //7, 4, 5, 2, 6
        //7, 4, 5, 2,
    }

    public static long findMaxProducts(List<Integer> products) {
        int l = products.size();
        long max = 0;

        //7, 4, 5, 2, 6,  5


        //5,4,2,1 ==> 12
        //6,2,1
        //2,1
        //5,4,


        for (int i = l - 1; i >= 0; --i) {

            //if (i != l - 1 && products.get(i) < products.get(i + 1)) continue; //??

            long localMax = products.get(i);

            long prev = localMax;
            //5
            for (int j = i - 1; j >= 0; --j) {
                //prev = 4
                prev = Math.min(prev - 1, products.get(j));
                localMax += prev;
                if (prev == 1) break;
               //max 9
            }

            max = Math.max(localMax, max);
        }
        return max;
    }

}