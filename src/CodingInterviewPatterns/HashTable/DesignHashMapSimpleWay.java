package CodingInterviewPatterns.HashTable;

public class DesignHashMapSimpleWay {
    //This will have operations has 0(n) and space complexity of 1000001(size of the array);
    private int[] _v = new int[1000001];

    public DesignHashMapSimpleWay() {
        //Insert -1
        for(int i=0; i<1000001; i++){
            _v[i]=-1;
        }
    }

    public void put(int key, int value) {
        _v[key]=value;
    }

    public int get(int key) {
        return _v[key];
    }

    public void remove(int key) {
        _v[key]=-1;
    }
}
