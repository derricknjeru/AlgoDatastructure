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

## Time complexity
- [Time complexity](https://leetcode.com/explore/learn/card/recursion-i/256/complexity-analysis/1669/)
## Space complexity
The recursion related space refers to the memory cost that is incurred directly by the recursion, i.e. the stack to keep track of recursive function calls.In order to complete a typical function call, the system allocates some space in the stack to hold three important pieces of information:

1. The returning address of the function call. Once the function call is completed, the program must know where to return to, i.e. the line of code after the function call. 
2. The parameters that are passed to the function call. 
3. The local variables within the function call.
- [Space complexity](https://leetcode.com/explore/learn/card/recursion-i/256/complexity-analysis/1671/)

### Tail Recursion
- Tail recursion is a recursion where the recursive call is the final instruction in the recursion function. And there should be only one recursive call in the function.
Example:
- example of tail recursion in the solution of Reverse String.
- [Tail recursion](https://leetcode.com/explore/learn/card/recursion-i/256/complexity-analysis/2374/)



