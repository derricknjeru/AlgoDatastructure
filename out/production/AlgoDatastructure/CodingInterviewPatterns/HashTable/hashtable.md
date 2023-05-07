# Links
- [Hash Tables CS50](https://www.youtube.com/watch?v=h2d9b_nEzoA&ab_channel=CS50)
- [Hash function](https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/6546848606322688/6032520048279552)
- [Complexity analysis](https://leetcode.com/explore/learn/card/hash-table/182/practical-applications/1111/)

# Arrays vs LinkedList vs HashTables
## Arrays
Allows us to store elements of a single data type contiguously in memory.

### Advantage
1. Random access to all the elements in an array - we can access each item at constant time.
### disadvantage
1. size is fixed, because they store data contiguously in memory, you must specify an array size when declaring
  the array

## Linked list 
Is a linear data structure, each element in the linked list is actually a separate object while all the objects 
are linked together by the reference field in each element.

### Advantage 
1. size is not fixed since elements are not contiguous in memory
### disadvantage 
1. More memory is required in the linked list as compared to an array
2. To access the item we want, we need to traverse until we find the element. (0(n);)

# HashTables
Hash Table is a data structure which organizes data using hash functions in order to support quick insertion and search.

A hash function takes a piece of data as input(key) and output an integer commonly referred to us, as hash value. hash value maps 
a key to a particular index in the hash table. We use same hash value to retrieve data. For this reason it is crucial that
the hash function behaves consistently and outputs the same hash value for identical keys

**Hashtable are used when insertion, deletion & lookup of elements in a priority**

### Adv
1. can grow over time
2. Can store data of any typw
3. Lookup, insertion & deletion can be constant time in theory.

### Load factor
A critical statistic for hashtable is the load factor defined as lf=n/k
where
n is the number of entries occupied in the hashtable
k is the number of buckets

e.g 10000/15000 -> 0.667. Generally it should be below 1.
As of java 10, the load factor is 0.75 ---if 75 percent of size is taken, we rehash

### Collision Resolution
Result of two keys hashing to the same index in the table.
We have 2 common collisions resolution methods 
- Linear probing --- looks for the next available slot in the table to insert the collided element. This means that if the slot at index i is already occupied, the algorithm probes the slot at index i+1, then i+2, and so on. 
  However, it can lead to clustering of elements and poor performance if the hash function is not well-designed.

- separate chaining - elements are stored in a separate data structure attached to the original slot of the hash table. Each slot in the hash table contains a pointer to a linked list or an array. This technique does not require additional memory to store collided elements and can handle a large number of collisions without affecting the performance of the hash table. However, it may require more time to search for an element if the linked lists or arrays become long, which depends on the size and quality of the hash table and the distribution of the keys.

  ![Separate chaining!](../../images/separate-chaining.png "Separate chaining")


## Summary of load factor and Collisions

###  1.0 Collisions
For this to work correctly, equal keys must have the same hash, however, different keys can have the same hash. If two different keys have the same hash, the two values belonging to them will be stored in the same bucket. Inside a bucket, values are stored in a list and retrieved by looping over all elements. The cost of this is O(n).

As of Java 8 (see JEP 180), the data structure in which the values inside one bucket are stored is changed from a list to a balanced tree if a bucket contains 8 or more values, and it's changed back to a list if, at some point, only 6 values are left in the bucket. This improves the performance to be O(log n).

### 1.1 Capacity and Load Factor
To avoid having many buckets with multiple values, the capacity is doubled if 75% (the load factor) of the buckets become non-empty. The default value for the load factor is 75%, and the default initial capacity is 16. Both can be set in the constructor.



## Complexity 
In our design example
- insertion is constant time because we are inserting to the head of the linked list
- search is constant time on average but in worst case scenario it will be O(n/k) ---N is the size of the list and K is the number of slots or 
size of the hashtable/(We will be distributing on the N elements over the K slots. N/K). This is assuming
the hash function is giving perfect uniform distribution on avg time.
- Remove():The average-case complexity is O(1) , while the worst-case complexity is
O(n). Deletion always depends on search.


  
## The Principle of Built-in Hash Table
The typical design of built-in hash table is:

- The key value can be any hashable type. And a value which belongs to a hashable type will have a hashcode. This code will be used in the mapping function to get the bucket index.
- Each bucket contains an array to store all the values in the same bucket initially.
- If there are too many values in the same bucket, these values will be maintained in a height-balanced binary search tree instead.

- The average time complexity of both insertion and search is still O(1). And the time complexity in the worst case is O(logN) for both insertion and search by using height-balanced BST. It is a trade-off between insertion and search.
- Deletion depends on search time complexity

## Links
[Need for hashmap](https://www.youtube.com/watch?v=EK0PyKMnJV4&ab_channel=TECHDOSE)

## Most common operations performed on a DS

- search/lookup
- insertion
- deletion

**NB**: Choice of datastructures depends on the most frequent operation to be performed.

### Example

| Most frequent operation                |      DS      | Time complexity |
|----------------------------------------|:------------:|----------------:|
| insertion at the beginning of the list | linked list  |            O(1) |
| finding min/max elements in array      |     Heap     |            O(1) |
| search of sorted keys                  | balanced BST |         O(logN) |



**NOTE** There are other DS performing similarly or better to above example



### What happens if search is a frequent operation? Doing a random search?

| DS               |       OP -will do        | Time complexity |
|------------------|:------------------------:|----------------:|
| Unsorted array   |      Linear search       |            O(N) |
| Sorted array     |      Binary search       |         O(logN) |
| Linked list      |      Linear search       |         O(logN) |
| Balanced Tree    | Tree maybe skewed or not |            O(N) |
| Balanced BST     |    Height of the tree    |        O(log N) |
| Priority queue   |      linear search       |            O(N) |

**NB** On above simple DS operations order of logN is the best time you can do for a random search

Hashmap allows searching a key possible in just O(1) avg time. Worst case scenario is O(N).
**::** Use hashing when search based on key is a frequent operation.