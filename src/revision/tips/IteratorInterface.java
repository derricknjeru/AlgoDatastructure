package revision.tips;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

//https://www.javatpoint.com/java-reader-class
//https://www.techiedelight.com/implement-iterable-interface-for-each-loops/
public class IteratorInterface {
    public static void main(String[] args) {

        /*Integer[] ints = new Integer[]{1, 2, 3};

        CollectionIter<Integer> iter = new CollectionIter<>(ints);

        Iterator<Integer> iterator = iter.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\n");

        for (Integer a : iter) {
            System.out.println(a);
        }
       */

        Reader reader = null;

        try {
            reader = new FileReader("file.txt");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        SolutionIter solutionIter = new SolutionIter(reader);

        for (Integer integer : solutionIter) {
            // System.out.println("HHhh" + integer);
        }


    }

    static class SolutionIter implements Iterable<Integer> {
        Reader inp;
        int size = 0;
        private List<Integer> list = new ArrayList<>();

        public SolutionIter(Reader inp) {
            try {
                this.inp = inp;
                int data = inp.read();
                while (data != -1) {
                    int a = Integer.parseInt(String.valueOf((char) data));
                    System.out.println(a);
                    list.add(a);
                    data = inp.read();
                }
                inp.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());

            }

            System.out.println(list);
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<>() {
                private int i = 0;

                @Override
                public boolean hasNext() {
                    return i < size;
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return list.get(i++);
                }
            };
        }
    }

    static class CollectionIter<Integer> implements Iterable<Integer> {
        private Integer[] array;
        private int len;

        public CollectionIter(Integer[] array) {
            this.array = array;
            this.len = array.length;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private int i = 0;

                @Override
                public boolean hasNext() {
                    return i < len && array != null;
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return array[i++];
                }
            };
        }
    }
}

