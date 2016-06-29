

/**
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Here are few examples. 
 * [1,3,5,6], 5 -> 2
 * [1,3,5,6], 2 -> 1 
 * [1,3,5,6], 7 -> 4
 * [1,3,5,6], 0 -> 0
 */

// O(lgN)
public class SearchInsertPosition {
	public int searchInsert(int[] A, int target) {
		int length = A.length;
		if (A.length == 0)
			return 0;
		int i = 0, j = length - 1;
		while (i < j) {
			int mid = (i + j) / 2;
			if (A[mid] == target)
				return mid;
			if (A[mid] < target) {
				i = mid + 1;
			} else {
				j = mid - 1;
			}
		}
		// 如果找不到target，最后i == j，或A[i] < target, 或A[i] > target
		return A[i] < target ? i + 1 : i;  
	}
}



// 01/11/15
public class Solution {
    public int searchInsert(int[] A, int target) {
        int l = A.length;
        if(l == 0) return 0;
        int s = 0;
        int e = l-1;
        if(A[e] < target) return l;  // 注意可能target超出了A的最大值
        while(s <= e){
            int mid = (s+e)/2;
            if(A[mid] == target) return mid;
            if(mid > 0 && A[mid-1] < target && A[mid] > target){
                return mid;
            }
            if(A[mid] > target){
                e = mid-1;
            }
            if(A[mid] < target){
                s = mid+1;
            }
        }
        return 0;
    }
}


// 03/24/15
// 先排除target大于最后位的可能，这样mid+1也就不会超出bound了
// 如果A[mid] < target, 那么mid肯定不是结果
// 如果A[mid] > target, 那么mid可能是结果，所以需要再check A[mid+1]
public class Solution {
    public int searchInsert(int[] A, int target) {
        int l = A.length;
        if(l == 0) return 0;
        int s = 0;
        int e = l-1;
        if(A[e] < target) return l;  // 防止下面loop mid+1溢出，先检查最后一个
        while(s <= e){
            int mid = (s+e)/2;
            if(A[mid] == target) return mid;
            if(A[mid] < target && A[mid+1] > target) return mid+1;
            if(A[mid] < target) s = mid+1;
            if(A[mid] > target) e = mid-1;
        }
        return 0;  // 如果A[0] > target, 那就直接插入0位
    }
}