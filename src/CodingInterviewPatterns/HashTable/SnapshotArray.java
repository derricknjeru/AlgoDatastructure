package CodingInterviewPatterns.HashTable;

import java.util.*;

public class SnapshotArray {
    //https://www.geeksforgeeks.org/treemap-in-java/?ref=lbp
    //https://www.youtube.com/watch?v=W8WalXRwqrM
    //https://www.youtube.com/watch?v=Z9UI9fj4Jy0&t=449s
    private int currentSnapshot;

    private Map<Integer, Integer>[] snapshotMap;

    public SnapshotArray(int length) {
        this.snapshotMap = new HashMap[length];
        for (int i = 0; i < length; i++) {
            this.snapshotMap[i] = new HashMap<>();
        }
        this.currentSnapshot = 0;
    }

    public void set(int index, int val) {
        this.snapshotMap[index].put(this.currentSnapshot, val);
    }

    public int snap() {
        this.currentSnapshot++;
        return this.currentSnapshot - 1;
    }

    public int get(int index, int snap_id) {
        Map<Integer, Integer> map = this.snapshotMap[index];

        if (map.containsKey(snap_id)) {
            return map.get(snap_id);
        } else {
            //If we can't find value at this snap ID (as shown above) so  we need to
            //look for the snap Id that was last recorded since that the last version that
            //was saved when current snap ID was taken.

            //We can use TreeMap to get the floorEntry
            while (snap_id >= 0) {
                if (map.containsKey(snap_id)) {
                    return map.get(snap_id);
                }
                snap_id--;
            }
        }
        return 0;
    }

    static class SnapshotArrayWithTreeMap {
        private List<TreeMap<Integer, Integer>> map;
        private int snapId;

        public SnapshotArrayWithTreeMap(int length) {
            snapId = 0;
            map = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                map.add(new TreeMap<>());
                map.get(i).put(0, 0);
            }
        }

        public void set(int index, int val) {
            map.get(index).put(snapId, val);
        }

        public int snap() {
            snapId++;
            return snapId - 1;
        }

        public int get(int index, int snap_id) {
            return map.get(index).floorEntry(snap_id).getValue();
        }
    }
}
