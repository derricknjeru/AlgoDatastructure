
# what is a tree?

- A tree is a data structure that is often used to represent hierarchical data.
e.g . 
  - How files and folders are organised.
  - Representing organization structure.

- The basic structure of a tree consists of the following components:

   - Nodes: Hold data 
   - Root: The uppermost node of a tree 
   - Parent Node: A node which is connected to one or more nodes on the lower level (Child Nodes). 
   - Child Node: A node which is linked to an upper node (Parent Node)
   - Sibling Node: Nodes that have the same Parent Node 
   - Leaf Node – A node that does not have any Child Node 
   - Nodes are connected by edges. Each node contains a value or data, and it may or may not have a child node
- Terminology Used in Trees
  - Sub-tree: A subtree is a portion of a tree that can be viewed as a complete tree on its own. Any node in a tree, together with all the connected nodes below it, comprise a subtree of the original tree. Think of the sub-tree as an analogy for the term, proper subset.
  - Degree:The degree of a node refers to the total number of sub-trees of a node

  - Depth:The number of connections (edges) from the root to a node is known as the depth of that node.
  - Level: (DepthOfNode)+1 
  - Height of a Node:The maximum number of connections between the node and a leaf node in its path is known as the height of that node. 
  - Height of a Tree:The height of a tree is simply the height of its root node.
- Types of Trees in Data Structure
  - General Tree: each node can have infinite number of children.
  - Binary tree:  each parent can have at most 2 children, right and left child. When certain constraints and properties are imposed on Binary tree it results in a number of other widely used trees like BST (Binary Search Tree), AVL tree, RBT tree etc.
  - Binary Search Tree: is an extension of Binary tree with some added constraints, In BST, the value of the left child of a node must be smaller than or equal to the value of its parent and the value of the right child is always larger than or equal to the value of its parent
  - AVL Tree: is a self-balancing binary search tree. The common operations like lookup, insertion and deletion takes O(log n) time in AVL tree. It is widely used for Lookup operations.The name AVL is given on the name of its inventors Adelson-Velshi and Landis. This was the first dynamically balancing tree. In AVL tree, each node is assigned a balancing factor based on which it is calculated whether the tree is balanced or not. In AVL tree, the heights of children of a node differ by at most 1. The valid balancing factor in AVL tree are 1, 0 and -1.  When a new node is added to the AVL tree and tree becomes unbalanced then rotation is done to make sure that the tree remains balanced. The common operations like lookup, insertion and deletion takes O(log n) time in AVL tree. It is widely used for Lookup operations.
  - Red-Black Tree: is another type of self-balancing tree.The name Red-Black is given to it because each node in a Red-Black tree is either painted Red or Black according to the properties of the Red- Black Tree. This make sure that the tree remains balanced. Although the Red-Black tree is not a perfectly balanced tree but its properties ensure that the searching operation takes only O(log n) time. Whenever a new node is added to the Red-Black Tree, the nodes are rotated and painted again if needed to maintain the properties of the Red-Black Tree .
  - N-ary: In an N-ary tree, the maximum number of children that a node can have is limited to N. A binary tree is 2-ary tree as each node in binary tree has at most 2 children. Trie data structure is one of the most commonly used implementation of N-ary tree. A full N-ary tree is a tree in which children of a node is either 0 or N. A complete N-ary tree is the tree in which all the leaf nodes are at the same level.
- binary search tree
  - General Tree: is an extension of Binary tree with some added constraints, In BST, the value of the left child of a node must be smaller than or equal to the value of its parent and the value of the right child is always larger than or equal to the value of its parent
  - Balanced/unbalanced - Algo ensures tree are balanced
  - Traversal:
  1. Inorder traversal  left - then root - then right
  2. Preorder traversal - root - then left - then right
  3. Postorder traversal - left  - then right - then root
  4. Level order traversal
  - Typically in binary search tree we want to do inorder traversal because
  that actually allows the nodes to be printed inorder.