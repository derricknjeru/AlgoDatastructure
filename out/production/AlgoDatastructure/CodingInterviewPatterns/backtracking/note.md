# Backtracking

1. Backtracking is a technique that explores multiple paths to find the solution.
2.  It builds the solution step by step by increasing values with time and removes the choices that don’t contribute to the problem’s solution based on some constraints. 
3. Backtracking is different from recursion because, in recursion, the function calls itself until it reaches a base case whereas backtracking tries to explore all possible paths to a solution.

#### Summary
- The way backtracking works is that it first explores one possible option. If the required criteria have been met with that option, we choose a path that stems from that option and keep on exploring that path. If a solution is reached from this path, we return this solution. Otherwise, if a condition is violated from the required set of conditions, we backtrack and explore another path.

- The backtracking approach is better than brute-force since we don’t have to generate all possible solutions and choose our required solution from among these. It provides us with the option to check our required condition at each possible recursive call. 
- If the condition is met, we continue exploring that path. If it isn’t, we take a step back and explore another path. In this way, we avoid generating redundant solutions.

#### Does my problem match this pattern?
##### Yes, if any of these conditions is fulfilled:

- While constructing any single candidate solution, all paths must be explored. That is, if exploring a certain path results in a deadend, we need to move back one level and explore all the other paths in the solution space.
The problem requires us to consider all feasible solutions in order to select the best one. Doing so, not a single feasible solution may be ignored. In certain problems, even if some feasible solutions are eventually discarded, we still need to find and evaluate them.
The problem requires us to compile a list of all feasible solutions.
##### No, if either of these conditions is fulfilled:

- The problem structure is such that, while constructing a candidate solution, failing to meet the acceptability criteria of the solution eliminates all other possibilities within that solution. So, we don’t need to check those remaining possibilities.
It is sufficient to construct just one feasible solution to solve the problem.
