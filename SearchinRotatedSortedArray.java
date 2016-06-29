

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 */

public class SearchinRotatedSortedArray {
	public int search(int[] A, int target) {
		int low = 0;
		int high = A.length - 1;
		int mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			if (A[mid] == target)
				return mid;
			else {
				if (A[low] <= A[mid]) {  // 表示从low到mid范围是ascending的，同时也包括s = mid
					if (target >= A[low] && target < A[mid]) {
						high = mid - 1;
					} else {
						low = mid + 1;
					}
				} else {  // 表示从mid到high范围是ascending的
					if (target <= A[high] && target > A[mid]) {
						low = mid + 1;
					} else {
						high = mid - 1;
					}
				}
			}
		}
		return -1;
	}
}


// 03/20/15
// 分情况：mid在左边的递增区间还是右边的递增区间
// 然后再分情况，target是在递增区间内还是断层区间内
public class Solution {
    public int search(int[] A, int target) {
        int l = A.length;
        int s = 0;
        int e = l-1;
        while(s <= e){
            int mid = (s+e)/2;
            if(A[mid] == target) return mid;
            if(A[s] <= A[mid]){
                if(target < A[mid] && target >= A[s]){
                    e = mid-1;
                }
                else{
                    s = mid+1;
                }
            }
            else{
                if(target > A[mid] && target <= A[e]){
                    s = mid+1;
                }
                else{
                    e = mid-1;
                }
            }
        }
        return -1;
    }
}