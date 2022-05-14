package HashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MinimumIndexSumOfTwoLists {
    //https://leetcode.com/problems/minimum-index-sum-of-two-lists/
    //Soln https://leetcode.com/problems/minimum-index-sum-of-two-lists/solution/

    public String[] findRestaurant(String[] list1, String[] list2) {
        /**
         * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
         *
         * You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.
         *
         *
         *
         * Example 1:
         *
         * Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"]
         * Output: ["Shogun"]
         * Explanation: The only restaurant they both like is "Shogun".
         * Example 2:
         *
         * Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["KFC","Shogun","Burger King"]
         * Output: ["Shogun"]
         * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
         *
         *
         * Constraints:
         *
         * 1 <= list1.length, list2.length <= 1000
         * 1 <= list1[i].length, list2[i].length <= 30
         * list1[i] and list2[i] consist of spaces ' ' and English letters.
         * All the stings of list1 are unique.
         * All the stings of list2 are unique.
         */

        HashMap<String, Integer> map = new HashMap<>();
        int index = 0;
        for(String s : list1) map.put(s,index++);

        List<String> store = new ArrayList<>();

        int min_sum = Integer.MAX_VALUE;
        int sum = 0;

        for(int i=0; i<list2.length;i++){

            if(map.containsKey(list2[i])){

                sum = i + map.get(list2[i]);

                if(sum < min_sum){

                    store.clear();
                    store.add(list2[i]);
                    min_sum=sum;

                }else if(sum == min_sum){
                    store.add(list2[i]);
                }

            }


        }

        String [] arr = store.toArray(new String[store.size()]);

        return arr;
    }
}
