

/**
 * Given a sorted array of integers, find the starting and ending position of a
 * given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 */

public class SearchforaRange {
	public int[] searchRange(int[] A, int target) {
		int low = findLow(A, target, 0, A.length - 1);
		int high = findHigh(A, target, 0, A.length - 1);
		int[] ret = new int[2];  // 表示重复部分的start和end的index
		ret[0] = low;
		ret[1] = high;
		return ret;
	}

	private int findLow(int[] A, int target, int l, int r) {
		int mid = 0;
		int ret = -1;
		while (l <= r) {
			mid = (l + r) / 2;
			if (A[mid] == target) {
				ret = mid;
				// 继续logN搜索
				int next = findLow(A, target, l, mid - 1); //找左就是在A[mid]=target时候 end=mid 这样就保证在答案范围内mid越来越左边 
				if (next != -1) { // 说明mid左边还有和A[mid]相同的
					ret = next;
				}
				break;  // 一旦找到，就不断返回这个值
			} else if (A[mid] < target) {
				l = mid + 1;  //说明需要往右search
			} else {
				r = mid - 1;  //说明还需要往做search
			}

		}
		return ret;
	}

	private int findHigh(int[] A, int target, int l, int r) {
		int mid = 0;
		int ret = -1;
		while (l <= r) {
			mid = (l + r) / 2;
			if (A[mid] == target) {
				ret = mid;
				int next = findHigh(A, target, mid + 1, r);
				if (next != -1) {
					ret = next;
				}
				break;
			} else if (A[mid] < target) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return ret;
	}
}



// 03/19/15
// 二分法，两个子程序，一个找最左，一个找最右
// 子程序中需要不断更新找到的bound index满足[mid] == target，在divide的时候往左或往右减一半
public class Solution {
    public int[] searchRange(int[] A, int target) {
        int l = A.length;
        int[] ret = new int[2];
        int s = findleft(A, target, 0, l-1);
        int e = findright(A, target, 0, l-1);
        ret[0] = s;
        ret[1] = e;
        return ret;
    }
    
    private int findleft(int[] A, int target, int l, int r){
        int ret = -1;
        while(l <= r){
            int mid = (l+r)/2;
            if(A[mid] > target){
                r = mid-1;
            }
            else if(A[mid] < target){
                l = mid+1;
            }
            else{
                ret = mid;  // 不断更新找到的leftmost
                r = mid-1;
            }
        }
        return ret;
    }
    
    private int findright(int[] A, int target, int l, int r){
        int ret = -1;
        while(l <= r){
            int mid = (l+r)/2;
            if(A[mid] > target){
                r = mid-1;
            }
            else if(A[mid] < target){
                l = mid+1;
            }
            else{
                ret = mid;
                l = mid+1;
            }
        }
        return ret;
    }    
}