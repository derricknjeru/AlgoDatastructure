# Matrix key concepts

### Getting rows and colums

- int rows = ```matrix.length```;
- int cols = ```matrix[0].length```;

### Getting neighboring directions

Assuming we have a matrix represented by matrix[row][col], the neighboring directions can be described as follows:

- Up: ```matrix[row-1][col]``` represents the room above the current room.
- Down: ```matrix[row+1][col]``` represents the room below the current room.
- Left: ```matrix[row][col-1]``` represents the room to the left of the current room.
- Right: ```matrix[row][col+1]``` represents the room to the right of the current room.

### Another way

int[][] dirs = ```{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}```;. declares a 2D array called dirs that contains four elements,
each representing a direction in a 2D grid.

- The dirs array represents the four cardinal directions: right, down, left, and up

Let's break down the array elements:

- {1, 0}: Represents the direction to move one step right in the grid (row index does not change, column index increases
  by 1).
- {0, 1}: Represents the direction to move one step down in the grid (row index increases by 1, column index does not
  change).
- {-1, 0}: Represents the direction to move one step left in the grid (row index does not change, column index decreases
  by 1).
- {0, -1}: Represents the direction to move one step up in the grid (row index decreases by 1, column index does not
  change).

By using this dirs array, you can easily iterate through the neighboring cells in a 2D grid by adding the corresponding
direction values to the current row and column indices. It is a convenient way to handle movement or exploration in a
grid-based problem.

Example:

```` java  
for (int[] dir : directions) {
     int x = i + dir[0];
     int y = j + dir[1];
}

````

### Including diagonal

In the Minesweeper game, the dirs array {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}}
represents the eight possible directions for exploring neighboring cells in a 2D grid
Here's an explanation of each direction:

- {0, 1}: Represents the direction to move one step to the right.
- {0, -1}: Represents the direction to move one step to the left.
- {1, 0}: Represents the direction to move one step downwards.
- {-1, 0}: Represents the direction to move one step upwards.
- {1, 1}: Represents the direction to move one step diagonally down and to the right.
- {-1, -1}: Represents the direction to move one step diagonally up and to the left.
- {1, -1}: Represents the direction to move one step diagonally down and to the left.
- {-1, 1}: Represents the direction to move one step diagonally up and to the right.

Implementation

``` java
  private static int example (String[][] board, int i, int j) {
        int count = 0;
        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) continue;
            if (board[x][y].equals("*")) count++;
        }
        return count;
    }
```
