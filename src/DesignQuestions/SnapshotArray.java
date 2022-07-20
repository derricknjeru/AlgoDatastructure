package DesignQuestions;

import java.util.HashMap;
import java.util.Map;

public class SnapshotArray {
    //https://www.geeksforgeeks.org/treemap-in-java/?ref=lbp
    //https://www.youtube.com/watch?v=W8WalXRwqrM
    private int currentSnapshot;

    private Map<Integer, Integer>[] snapshotMap;

    public SnapshotArray(int length) {
        this.snapshotMap = new HashMap[length];
        for(int i = 0 ; i < length ; i++){
            this.snapshotMap[i] = new HashMap<>();
        }
        this.currentSnapshot = 0;
    }

    public void set(int index, int val) {
        this.snapshotMap[index].put(this.currentSnapshot,val);
    }

    public int snap() {
        this.currentSnapshot++;
        return this.currentSnapshot-1;
    }

    public int get(int index, int snap_id) {
        Map<Integer,Integer> map  = this.snapshotMap[index];

        while(snap_id >= 0){
            if(map.containsKey(snap_id)){
                return map.get(snap_id);
            }
            snap_id--;
        }
        return 0;
    }

}
