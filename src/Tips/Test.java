package Tips;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Test hs = new Test();

        int stones[] = {2, 7, 4, 1, 8, 1};
        //int stones[] = {1,1};

        int stoneDiff = hs.heavyStoneOutput(stones);

        System.out.println();

        System.out.println("output: " + stoneDiff);

    }

    public int heavyStoneOutput(int stones[]) {
        if (stones.length < 1 || stones.length > 30) {
            return 0;
        }

        List<Integer> stoneList = new ArrayList<Integer>();
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] >= 1 && stones[i] <= 1000) {
                stoneList.add(stones[i]);
            }
        }

        Collections.sort(stoneList);

        for (int i = 0; i < stones.length; i++) {

            if (stoneList.size() > 1) {
                int diffs = stoneList.get(stoneList.size() - 1) - stoneList.get(stoneList.size() - 2);

                stoneList.set(stoneList.size() - 1, diffs);
                stoneList.remove(stoneList.size() - 2);
                Collections.sort(stoneList);

                //System.out.print(stoneList.toString());
            }
        }

        if (!stoneList.isEmpty()) {
            return stoneList.get(0);
        }

        return 0;
    }

}

