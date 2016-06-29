
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique
 * combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * 
 * Elements in a combination (a1, a2, ... , ak) must be in non-descending order.
 * (ie, a1 <= a2 <= ... <= ak).
 * 
 * The solution set must not contain duplicate combinations. For example, given
 * candidate set 2,3,6,7 and target 7, A solution set is: [7] [2, 2, 3]  // 因为可以选多次同样的数
 */

public class CombinationSum {
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates,
			int target) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();  // sol list
		ArrayList<Integer> solution = new ArrayList<Integer>();
		Arrays.sort(candidates);  // 先sort
		combinationSum(candidates, 0, 0, target, ret, solution);
		return ret;
	}

	private void combinationSum(int[] candidates, int start, int sum,
			int target, ArrayList<ArrayList<Integer>> ret,
			ArrayList<Integer> solution) {
		if (sum == target) {
			ret.add(new ArrayList<Integer>(solution));  // clone这个sol
			return;
		}
		if (sum > target)
			return;
		for (int i = start; i < candidates.length; i++) {  // 从start index开始，尝试遍历每个element放入sol
			if (i > start && candidates[i] == candidates[i-1])
				continue;  // 因为在这里每个数可以重复使用，所以重复的元素也就没有作用了，所以应该跳过那层递归。否则会产生重复的sol
			solution.add(candidates[i]);
			// 因为都是pos int，而且sort过，sol要求也是ascending，所以只要看比选中的数大的的那些数
			combinationSum(candidates, i, sum + candidates[i], target, ret, solution);// 因为是ascending order，后面加入的数必然大于等于candidates[i]
			// 把这个num再去掉
			solution.remove(solution.size() - 1);
		}
	}
}


// 01/05/15
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        Arrays.sort(candidates);
        helper(ret, tmp, candidates, target, 0, 0);
        return ret;
    }
    
    private void helper(List<List<Integer>> ret, List<Integer> tmp, int[] candidates, int target, int sum, int idx){
        if(sum == target) {
            ret.add(new ArrayList<Integer>(tmp));
            return;
        }
        if(sum > target){
            return;
        }
        int l = candidates.length;
        for(int i = idx; i < l; i++){
            tmp.add(candidates[i]);
            helper(ret, tmp, candidates, target, sum + candidates[i], i);
            tmp.remove(tmp.size() - 1);
        }
    }
}

// 03/19/15
// 递归，注意这里可以重复用一个值，所以递归的index不需要+1，继续尝试同一个值
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int l = candidates.length;
        if(l == 0) return ret;
        List<Integer> tmp = new ArrayList<Integer>();
        Arrays.sort(candidates);
        helper(candidates, target, ret, tmp, 0);
        return ret;
    }
    
    private void helper(int[] candidates, int target, List<List<Integer>> ret, List<Integer> tmp, int idx){
        if(target < 0) return;  // the array value is positive
        if(target == 0){
            ret.add(new ArrayList<Integer>(tmp));
            return;
        }
        int l = candidates.length;
        for(int i = idx; i < l; i++){
            tmp.add(candidates[i]);
            helper(candidates, target - candidates[i], ret, tmp, i);  // 注意一个数可以重复选取，所以下一个还是从i开始
            tmp.remove(tmp.size()-1);
        }
    }
}