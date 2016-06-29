
import java.util.ArrayList;

/**
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * 
 * For example, [1,1,2] have the following unique permutations: 
 * [1,1,2], [1,2,1], and [2,1,1].
 */

// 比如 1,2,3,4,2,5
//      2 (1 3 4 2)
//      2 (2 3 4 1) 
//      两个子递归程序获得的permutation是重复的

// 先尝试后面所有的值和第一位值swap，然后是第二位和后面所有值swap(因为第二位之前已经和第一位换过了)...
// 当arr[end] = 2 再次出现，就跳过
public class PermutationsII {
	public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		permuteUnique(num, 0, result);
		return result;
	}

	void permuteUnique(int[] num, int begin,
			ArrayList<ArrayList<Integer>> result) {
		if (begin > num.length - 1) {
			ArrayList<Integer> item = new ArrayList<Integer>();
			for (int h = 0; h < num.length; h++) {
				item.add(num[h]);  // 得到一个sol list
			}
			result.add(item);  // 把这个sol放入result list
		}
		for (int end = begin; end < num.length; end++) {  // begin保持不变，end往后移
			if (isSwap(num, begin, end)) {  // 当begin和end内没有和end重复的值，才需要swap，因为相同的值之前已经和begin swap过了！！！！！！
											// 避免产生重复的sol，这里是换各个不同值的数和begin swap
				// 比如1,2,3,4,2  begin是1，end是后面的2，因为前面的2已经和begin swap过了
				swap(num, begin, end);
				permuteUnique(num, begin + 1, result);
				swap(num, begin, end);  // 再换回来
			}
		}
	}

	boolean isSwap(int[] arr, int i, int j) {
		for (int k = i; k < j; k++) {
			if (arr[k] == arr[j]) {  // 在[begin,end)范围内，如果有和arr[end]重复出现的值
				return false;
			}
		}
		return true;  // 当i = j，直接返回true
	}
	
	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}



// 03/13/15
// 算法就是shuffle card，选一个位置i，找到[i, l)的其中一个位置来swap
// 但是要注意，如果[i, j)内有个数和j相等，那就跳过，因为会产生dup
// 2 1 3 1
// 1 2 3 1
// 1 1 3 2
// 这里 2 3 1 和 1 3 2的permutation是一样的
public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        helper(ret, num, 0);
        return ret;
    }
    
    private void helper(List<List<Integer>> ret, int[] num, int idx){
        int l = num.length;
        if(idx >= l) {
            List<Integer> tmp = new ArrayList<Integer>();
            for(int n: num){
                tmp.add(n);
            }
            ret.add(tmp);
            return;
        }
        for(int i = idx; i < l; i++){
            if(canSwap(num, i, idx)){
                swap(num, i, idx);
                helper(ret, num, idx+1);  // 注意这里是idx+1 否则成了无限循环
                swap(num, i, idx);
            }
        }
    }
    
    private boolean canSwap(int[] num, int e, int s){
        for(int i = s; i < e; i++){
            if(num[i] == num[e]) return false;
        }
        return true;
    }
    
    private void swap(int[] num, int i, int idx){
        int t = num[i];
        num[i] = num[idx];
        num[idx] = t;
    }
}