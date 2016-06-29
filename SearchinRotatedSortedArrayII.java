

/**
 * Follow up for "Search in Rotated Sorted Array": What if duplicates are
 * allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * 
 * Write a function to determine if a given target is in the array.
 */

public class SearchinRotatedSortedArrayII {
	public boolean search(int[] A, int target) {
		int low = 0;
		int high = A.length - 1;
		int mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			if (A[mid] == target)
				return true;
			else if (A[low] != A[high]) {
				// 这里和没有dup的sol一样
				if (A[low] <= A[mid]) {  // 说明low到mid是ascending
					if (target >= A[low] && target < A[mid]) {
						high = mid - 1;
					} else {
						low = mid + 1;
					}
				} else {  // 说明mid到high是ascending
					if (target <= A[high] && target > A[mid]) {
						low = mid + 1;
					} else {
						high = mid - 1;
					}
				}
			} else {  // A[low] == A[high]   如果两边都相等，那只有一个个遍历比较了
				for (int k = low; k < high; k++) {
					if (A[k] == target)
						return true;
				}
				return false;
			}
		}
		return false;  // 如果一开始low > high, 说明A[]为空
	}
}



// We used linear search for this question just to indicate that the 
// time complexity of this question is O(n) regardless of binary search is applied or not.

// 如果A[s] != A[e] 说明能够判断曲线趋势
public class Solution {
    public boolean search(int[] A, int target) {
        int l = A.length;
        if(l == 0) return false;
        int s = 0;
        int e = l-1;
        while(s <= e){
            int mid = (s+e)/2;
            if(A[mid] == target) return true;
            if(A[s] != A[e]){ 
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
            else{  // 当发现两端s和e都相同，那就只能linear search了
                break;
            }
        }
        
        for(int i = s; i <= e; i++){
            if(A[i] == target) return true;
        }
        return false;
    }
}




