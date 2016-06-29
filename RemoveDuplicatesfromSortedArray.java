

/**
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length.
 * 
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.
 * 
 * For example, Given input array A = [1,1,2],
 * 
 * Your function should return length = 2, and A is now [1,2].
 */

public class RemoveDuplicatesfromSortedArray {
	public int removeDuplicates(int[] A) {
		if (A.length == 0)
			return 0;
		else if (A.length == 1)
			return 1;
		else {
			int ret = 1;
			int p = 0;
			for (int p1 = 1; p1 < A.length; p1++) {
				if (A[p1 - 1] != A[p1]) {
					ret++;
					A[++p] = A[p1];  // p是slow pointer
				}
			}
			return ret;
		}
	}
}

// 01/07/15
public class Solution {
    public int removeDuplicates(int[] A) {
        int l = A.length;
        if(l == 1 || l == 0) return l;
        int ret = 1;
        int i = 1;
        while(i < l){
            if(A[i] == A[i-1]){
                i++;
            }
            else{
                A[ret] = A[i];
                i++;
                ret++;
            }
        }
        return ret;
    }
}

// 01/25/15
// 用双指针，如果不是重复，就放入slow指针对应位置；如果是重复就跳过
public class Solution {
    public int removeDuplicates(int[] A) {
        int l = A.length;
        if(l <= 1) return l;
        int idx = 1;
        for(int i = 1; i < l; i++){
            if(A[i] != A[i-1]){
                A[idx++] = A[i];
            }
        }
        return idx;
    }
}