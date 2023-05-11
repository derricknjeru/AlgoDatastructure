# Linked List

1. Linked list -> stores a list of values but in a way that each value is connected or linked to the next value
2. Single linked list -> Each value is only connected to the next node. DATA|NEXT
3. Double linked list -> Each value is connected to the next node and previous node.
4. Circular linked list-> the last node is connected to the head. It has an address to the head

**NB**
<p>
Storing a list of values in a linked list can take up more memory than using a simple array because in addition to values
we have to store next and sometimes previous fields for each node. However adding a new value to a linked list is easy
Since you just extend the last node to add the new node also deleting is easy as you can just change the next
field of the previous node to point to the node after the node you wish to remove.
</p>
Unlike the array, we are not able to access a random element in a singly-linked list in constant time. If we want to get the ith element, we have to traverse from the head node one by one. It takes us O(N) time on average to visit an element by index, where N is the length of the linked list.

## Basic Linked List Operations

| Operations	       | Description                                          |   |   
|-------------------|------------------------------------------------------|---|
| insertAtEnd(data)	 | Inserts an element at the end of the linked list     |
| insertAtHead(data)	 | Inserts an element at the start/head of the linked list |
| delete(data)	     | Inserts an element at the start/head of the linked list|
| deleteAtHead()    |  Deletes the first element of the list
| deleteAtEnd()	    |Deletes the last element of the list|
| Search(data)	        |  Searches an element from the linked list|
| isEmpty()         |            Returns True if the linked list is empty, otherwise returns False|

1. It takes us O(N) time on average to visit an element by index, where N is the length of the linked list.
2. insert a new node into a linked list in O(1) time complexity if you have a reference to prev, which is very
   efficient. E.g. At head or a know node
3. Insert Node at the End of a Linked List

## Let's briefly review the performance of the singly linked list and doubly linked list.

### They are similar in many operations:

Both of them are not able to access the data at a random position in constant time.
Both of them can add a new node after given node or at the beginning of the list in O(1) time.
Both of them can delete the first node in O(1) time.

### But it is a little different to delete a given node (including the last node).

In a singly linked list, it is not able to get the previous node of a given node so we have to spend O(N) time to find
out the previous node before deleting the given node.
In a doubly-linked list, it will be much easier because we can get the previous node with the "prev" reference field. So
we can delete a given node in O(1) time.

## Comparison

Here we provide a comparison of time complexity between the linked list and the array.

Note: The given time complexities for the Doubly-Linked List assume that the Doubly-Linked List implementation keeps a
reference to the tail node. If a reference to the tail node is not kept, then adding a node after the last node or
deleting the last node would also require O(N) time.

### After this comparison, it is not difficult to come up with our conclusion:

- If you need to add or delete a node frequently, a linked list could be a good choice.

- If you need to access an element by index often, an array might be a better choice than a linked list.
