
// Question:
// Given an array of numbers, verify whether it is the correct preorder traversal sequence of 
// a binary search tree.
// You may assume each number in the sequence is unique.

// Follow up:
// Could you do it using only constant space complexity?

	 5
    / \
   3   7
  /     \
 1       9

 5 3 1 7 9

// worstcase: O(n^2)
// bestcase:  O(nlgn)
public class Solution{
	public boolean verifyPreorder(int[] preorder) {
		return helper(preorder, 0, preorder.length-1);
	}

	public boolean helper(int[] preorder, int left, int right){
		if(left >= right) return true;
		int pivot = preorder[left];
		int largerIndex = -1;
		for(int i = left+1; i <= right; i++){
			if(preorder[i] > pivot && largerIndex == -1) largerIndex = i;
			if(preorder[i] > pivot && largerIndex != -1) return false;
		}
		if(largerIndex == -1){
			return helper(preorder, left+1, right);
		}
		else{
			return helper(preorder, left+1, largerIndex-1) && helper(preorder, largerIndex, right);
		}
	}
}
