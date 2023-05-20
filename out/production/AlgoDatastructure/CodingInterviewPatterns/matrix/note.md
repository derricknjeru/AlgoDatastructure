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



