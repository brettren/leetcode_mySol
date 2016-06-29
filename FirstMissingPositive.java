

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 * 
 * Your algorithm should run in O(n) time and uses constant space.
 */
// 能否用additional array

	// 扫描2遍数组， 第一遍 负数无视 然后扫到是正数的且小于A.length,假设值是i
	// 就把这个正数放到num[i-1]位置上去 eg 1就放到num[0] 位
	// (eg:为什么不放1位？如果A={0} 那么第二遍i只能=0开始遍历 然后A[0]==0 return元素长度+1 就变2了)
	// 换好后 再去从1扫描所租 第一个下标不等于值的就是first missing possible
	// 如果都等match上 比方说12345 那么fmp就是 A.length+1 6

// value A[i] 1 2 3 4
// index   i  0 1 2 3
public class FirstMissingPositive {
	public int firstMissingPositive(int[] A) {
		if (A.length == 0)
			return 1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] > 0 && A[i] <= A.length && A[i] - 1 != i && A[i] != A[A[i] - 1]) {  // 不管负数, swap的两个数不能相同
				int t = A[A[i] - 1];
				A[A[i] - 1] = A[i];
				A[i] = t;  // swap A[i] 和 A[A[i] - 1]   A[0] = 3 , swap A[0] 和 A[2], 把3放到A[2]上
				i--;  // swap后仍然停在原来的位置！！！！！！！！
			}// 也就是把每个值放到对应的A[index]位置上   比如1,5,2,7,9    5应该被移到A[5-1]的位置
		}

		for (int j = 0; j < A.length; j++) {
			if (A[j] - 1 != j) {
				return j + 1;  // 当所有的num都在对应index位置上，第一个出现不相等的位置j，返回对应的值j+1
			}
		}
		return A.length + 1;
	}
}

// 01/09/15
public class Solution {
    public int firstMissingPositive(int[] A) {
        int l = A.length;
        if(l == 0) return 1;
        for(int i = 0; i < l; i++){
            if(A[i] != i+1 && A[i] > 0 && A[i]-1 < l && A[i] != A[A[i]-1]){  // 两个swap值不能相同，不然就成了死循环
                swap(A, i, A[i]-1);
                i--;  //因为swap后，原来的位置被新值占了，需要重新检查安排这个新的值
            }
        }
        int i = 0;
        for( ; i < l; i++){
            if(A[i] != i+1)
                break;
        }
        return i+1;
    }
    
    private void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}

// 03/12/15
// 这里就是遍历数组，把每个数放到对应的idx
// 三种情况，如果相对应，比如1放在[0]位置，就跳过；如果不对应，但是需要放的位置超过了数组区域，或者需要放的位置已经存在那个数了，也跳过
// 如果可以swap，交换后不要调到下一个，再检查swap过来的值是否match index
// 一遍处理完后，从头检查，不match就说明找到了

 // 这里如果找不到对应的那个value swap过来，就保持原地， 直到swap过来的值超了bound
public class Solution {
    public int firstMissingPositive(int[] A) {
        int l = A.length;
        if(l == 0) return 1;
        for(int i = 0; i < l; ){
            int val = A[i];
            if(i == val-1) {
                i++;
                continue;
            }
            if(val-1 < 0 || val-1 >= l || A[val-1] == val){ 
                i++;
                continue;
            }
            swap(A, i, val-1);
        }
        for(int i = 0; i < l; i++){
            if(A[i] != i+1){
                return i+1;
            }
        }
        return l+1;
    }
    
    public void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}