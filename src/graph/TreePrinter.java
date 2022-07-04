package graph;

import java.util.List;

public class TreePrinter {
    //https://www.youtube.com/watch?v=V0xjK_6ZoEY&t=33s
    //https://www.youtube.com/watch?v=dQdM86yRDCs
    //https://algorithms.tutorialhorizon.com/implement-graph-using-map-java/
    public static void printTree(List<Relation> rs) {

    }


    public static class Relation {
        String parent;
        String child;

        public Relation(String parent, String child) {
           this.child=child;
           this.parent=parent;
        }
    }

    public static void main(String[] args) {

    }
}
