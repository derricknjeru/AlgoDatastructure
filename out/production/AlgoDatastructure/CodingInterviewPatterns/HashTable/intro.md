# Hashtable
Hash Table is a data structure which organizes data using hash functions in order to support quick insertion and search.

There are two different kinds of hash tables: hash set and hash map.

- The hash set is one of the implementations of a set data structure to store no repeated values.
- The hash map is one of the implementations of a map data structure to store (key, value) pairs.

## Keys to Design a Hash Table

### Hash Function
Hash function is used to map the key to a specific bucket.
- It is an open problem to design a hash function. The idea is to try to assign the key to the bucket as uniformly as you can.
 Ideally, a perfect hash function will be a one-one mapping between the key and the bucket. However, in most cases, a hash function is not perfect and it is a tradeoff between the amount of buckets and the capacity of a bucket.

### Collision Resolution
A collision resolution algorithm should solve the following questions:

1. How to organize the values in the same bucket?
2. What if too many values are assigned to the same bucket?
3. How to search for a target value in a specific bucket?

Let's assume that the bucket, which holds the maximum number of keys, has N keys.

Typically, if N is constant and small, we can simply use an array to store keys in the same bucket. If N is variable or large, we might need to use height-balanced binary search tree instead. or LinkedList

To solve collision we typically chain keys to the same bucket (separate chaining)

Collision resolutions methods.
- Separate chaining
- Open addressing (Need to study this).
1. Linear probing (Typing if a key is aready taken we look at the next index until we find an empty space to insert key that
has a collision. The bucket list has to be larger than the inputs.
2. quadratic probing
3. double hashing

Performance

## Load factor

A critical statistic for hashtable is the load factor defined as lf=n/k
where
n is the number of entries occupied in the hashtable
k is the number of buckets

e.g 10000/15000 -> 0.667. Generally it should be below 1.

Variance
-> looking at the chaining.



Designing hashmap.

Why not use array - For large dataset, search will take more time O(n) since it's a linear search.

For ideal hashmap search is a constant time O(1) or close to 1.

One has to design a hash_function or algo that convert keys to integers and then converts them to a range 0 -N-1 (K%N)

hashmap look up is better than linked-list

---->SIDE NOTE<----
Arrays -> we can access each item at constant time.
downside of array - size is fixed, because they store data contiguously.

linked list -> can grow in size, they don't have fixed size. Delection is also first
downside
To access the item we want, we need to traverse until we find the element. (0(n);)

hashtable
can grow over time
look up and insertion, deletion can be constant time in theory.

### Hashmap/set
- The time complexity of lookup, insertion and deletion in a hashtable is typically O(1) on average, but in the worst case, they can have O(n) time complexity.
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