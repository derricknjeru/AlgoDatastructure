# Links

[You tube link](https://www.youtube.com/watch?v=BgLTDT03QtU)

### constant tine O(1)

E.g.

### Array

#### The time complexity of array operations in Java depends on the specific operation being performed:

- Accessing an element(Random access): The time complexity of accessing an element in an array in Java is O(1), as it
  takes constant time to compute the memory address of the element based on its index.
- Insertion or deletion: The time complexity of inserting or deleting an element in an array in Java is typically O(n),
  where n is the size of the array. This is because adding or removing an element in the middle of the array requires
  shifting all the subsequent elements to fill the gap.
- Searching: The time complexity of searching for an element in an unsorted array in Java is O(n), where n is the size
  of the array. This is because every element in the array may need to be checked to determine whether it matches the
  search criteria. In a sorted array, searching can be done in O(log n) time using binary search.

#### Dynamic arrays

- Accessing an element: O(1)
- Insertion or deletion at the end: O(1) on average, but can be O(n) in worst-case scenarios, if the current capacity of
  the array is full and a resize is needed
- Insertion or deletion at the beginning or middle: O(n) - requires shifting all subsequent elements to fill the gap. If
  it's middle it is O(n-k). K is the index.
- Insertion or deletion at a specific index: O(n)
- Searching: O(n)
- The time complexity of the indexOf() method in Java's ArrayList class is O(n), where n is the number of elements in
  the list, because in the worst-case scenario, the entire list needs to be searched to find the desired element.

### Hashmap/set

- The time complexity of lookup, insertion and deletion in a hashtable is typically O(1) on average, but in the worst
  case, they can have O(n) time complexity.
- NB: The worse case can occur if the hash function is poorly designed or if there are too many collisions between keys.

#### Here is a breakdown of the time complexity of different operations on hash tables in Java:

- Search: O(1) on average, O(n) in the worst case
- Insertion: O(1) on average, O(n) in the worst case
- Deletion: O(1) on average, O(n) in the worst case

#### Here are some tips for improving the performance of hash tables in Java:

- Use a good hash function.
- Use a large hash table.
- Avoid using keys that are likely to collide.
- Use a load factor of 0.75 or less.
- Rehash the hash table when it becomes too full.

### linear runtime O(n)

#### Sometimes even nested loops can be O(n).
- monotonic stack - e.g. daily temperature problem
- sliding window - (longest-substring without repeating characters)

### quadratic O(n2)
- nested loops e.g. traversing a square grid [[][][]]
- 








