// Given a non-empty binary search tree and a target value, find k values in the BST that are 
// closest to the target.

// Note:

// Given target value is a floating point.
// You may assume k is always valid, that is: k ≤ total nodes.
// You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 

// Follow up:
// Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

// use MaxHeap to keep k closest value
if there's parent pointer, we can find the closet node, then trace both side for predecessor and successor

