# ArrayList

- You can remove object or remove item by index

1. Remove by object

``` java
   class test {
   
    public static void main(String[] args) {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        /**
         * The remove method internally uses equals to find the matching object
         * and then shifts the subsequent elements to fill the gap. 
         * In this scenario the elements 4 and 5 need to be shifted one position to the left.
         */
         //Therefore, the overall time complexity of 
         //removing an object without an index from an ArrayList is O(n).
        list.remove(Integer.valueOf(3)); //by object O(n)
        //list.remove(new Integer(val)); by object
        
    }
}
```

2. Removing by index

```java
  class Test2 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        /**
         * The remove method internally uses System.arraycopy or a similar mechanism to shift the subsequent
         * elements to fill the gap created by the removed element.
         * In this scenario, the elements 4 and 5 need to be shifted one position to the left.
         *
         * As a result, removing an element at a specific index requires shifting the subsequent elements, which has a time complexity of O(n). 
         */

        list.remove(2);

    }
}
```

3. Removing the last item in list
    - When removing an element from the last index of an ArrayList in Java, the time complexity is O(1). This is because
      ArrayList supports constant-time removal of the last element.
    - In this case, the element at the last index (index size()-1) is being removed. The remove method in ArrayList
      updates the size of the internal array and adjusts the size of the list accordingly. No shifting of elements is
      required because it only needs to adjust the size.

4. Remove first item.
    - When removing the first item from an ArrayList in Java, the time complexity is O(n), where n is the size of the
      list. This is because removing the first element requires shifting all the subsequent elements to fill the gap
      left by the removed element.


5. Time complexity of set function
   - The time complexity of the v.set(index, lastVal) operation in an ArrayList is O(1). 
   - This is because the set method in ArrayList directly assigns the specified element at the given index without requiring any element shifting or resizing.

```java
import java.util.ArrayList;

class test {
    public static void main(String[] args) {
        List<Integer> v = new ArrayList<>();
        v.set(index, lastVal);
    }
}
```
6. Adding item
- The time complexity of the add operation in an ArrayList depends on whether the element is being added at the end of the list or at a specific index.

1. Adding at the end:
- If the element is being added at the end of the ArrayList using add(element), the amortized time complexity is O(1). This is because ArrayList has a dynamic array as its underlying data structure, and when the capacity of the internal array is reached, it needs to be resized by creating a new array with a larger size and copying the existing elements. However, the resizing operation occurs infrequently, and on average, each add operation takes constant time.
2. Adding at a specific index:
- If the element is being added at a specific index using add(index, element), the time complexity is O(n), where n is the size of the list. This is because adding an element at a specific index requires shifting all the subsequent elements to make space for the new element. The number of elements to shift depends on the index of insertion and the size of the list. In the worst case, if the element is being added at index 0, all existing elements need to be shifted, resulting in a time complexity of O(n).
- It's important to note that in both cases, the time complexity mentioned above is the worst-case time complexity. In practice, ArrayList provides efficient constant-time or near-constant-time performance for most operations, including adding elements at the end. However, when adding elements at specific indices, the time complexity can degrade to O(n) in the worst case.