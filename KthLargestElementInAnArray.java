// Find the kth largest element in an unsorted array. 
// Note that it is the kth largest element in the sorted order, 
// not the kth distinct element.

// For example,
// Given [3,2,1,5,6,4] and k = 2, return 5.

// Note: 
// You may assume k is always valid, 1 ≤ k ≤ array's length.


// quickselect, 类似quicksort，就是找第l-k+1个smallest
public class Solution {
    public int findKthLargest(int[] nums, int k) {
		if (k < 1 || nums == null) {
			return 0;
		}
		int l = nums.length;
		return getKth(l - k +1, nums, 0, l - 1);  // find l-k+1 th smallest
	}
	 
	public int getKth(int k, int[] nums, int start, int end) {
		int pivot = nums[end];  // choose the last as the pivot
		int left = start;
	 
	 	for(int i = start; i < end; i++){
	 		if(nums[i] < pivot){
	 			swap(nums, left, i);
	 			left++;
	 		}
	 	}
	 
		swap(nums, left, end);
	 
		if (k == left + 1) {
			return pivot;
		} else if (k < left + 1) {
			return getKth(k, nums, start, left - 1);
		} else {
			return getKth(k, nums, left + 1, end);
		}
	}
	 
	public void swap(int[] nums, int n1, int n2) {
		int tmp = nums[n1];
		nums[n1] = nums[n2];
		nums[n2] = tmp;
	}
}