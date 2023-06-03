# Disjoint set

- [Theory](https://www.youtube.com/watch?v=wU6udHRIkcc)

- A disjoint-set data structure is defined as a data structure that keeps track of a set of elements partitioned into a number of disjoint (non-overlapping) subsets. 

A union-find algorithm is an algorithm that performs two useful operations on such a data structure:

1. Find: Determine which subset a particular element is in. This can be used for determining if two elements are in the same subset.
2. Union: Join two subsets into a single subset. Here first we have to check if the two subsets belong to same set. If no, then we cannot perform union.
