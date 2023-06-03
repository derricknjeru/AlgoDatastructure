# Recursion
[Introduction](https://www.youtube.com/watch?v=Mv9NEXX1VHc)

```text
 Recursion is an approach to solving problems using a function that calls itself as a subroutine.
 ```

- The trick is that each time a recursive function calls itself, it reduces the given problem into subproblems
  
A recursive function should have the following properties so that it does not result in an infinite loop:
1. A simple base case (or cases) — a terminating scenario that does not use recursion to produce an answer.
2. A set of rules, also known as recurrence relation that reduces all other cases towards the base case.

The space complexity of the recursion is O(n) as well.
* Each recursive call adds a new frame to the call stack, which requires space to store the local variables and parameters of that call.
* the space complexity is linear with respect to the length of the input string.

```text
In most programming languages, including Java, the call stack used for recursion is managed by the runtime system and is not subject to garbage collection. The call stack is a specific region of memory that keeps track of function calls and their corresponding local variables and parameters.
```


