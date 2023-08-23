| Algo        |  Avg  | Best  | wordt |
|:------------|:-----:|:-----:|:-----:|
| Bubble sort |  n^2  |   n   |  n^2  |
| Merge sort  | nlogn | nlogn | nlogn |
| Quick sort  | nlogn | nlogn |  n^2  |

# Quick sort
This sorting algorithm uses the idea of divide and conquer. It finds the element called pivot
which divides the array into two halves in such a way that elements in the left half are 
smaller than the pivot and elements in the right are greater than the pivot.

## Implementation
We recursively perform 3 steps:
- Bring the pivot to it appropriate position such that left of the pivot is smaller 
and right is greater
- quick sort the left side
- quick sort the right part



