package HashTable;

import java.util.*;

public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();

        for(int a : nums1){
            set1.add(a);
        }

        Set<Integer> setInTer = new HashSet<>();

        for(int a: nums2){
            if(set1.contains(a)){
                setInTer.add(a);
            }
        }

        int [] intersection = new int[setInTer.size()];

        int index =0;

        for(int a : setInTer){
            intersection[index++]=a;
        }



        return intersection;
    }
    //Built in set union
    public int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int [] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;
    }
}
