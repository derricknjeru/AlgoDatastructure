package revision.strings_and_arrays;

import java.util.*;

public class ContainsDuplicate {
    public static void main(String[] args) {

    }

    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }

        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int a : nums) {
            if (set.contains(a)) {
                return true;
            }

            set.add(a);
        }

        return false;
    }

    public int singleNumber(int[] nums) {


        HashMap<Integer,Integer> map = new HashMap<>();

        for(int a : nums){
            if(map.containsKey(a)){
                map.put(a, map.get(a)+1);
            }else {
                map.put(a,map.getOrDefault(a,1));
            }

        }

        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(entry.getValue()==1){
                return entry.getValue();
            }
        }

        return -1;


    }

}
