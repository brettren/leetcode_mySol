/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * 
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8, A solution set is: 
 * [1, 7] 
 * [1, 2, 5] 
 * [2, 6] 
 * [1, 1, 6] 
 */

import java.util.ArrayList;
import java.util.Arrays;

public class CombinationSumII {
	public ArrayList<ArrayList<Integer>> combinationSum2(int[] num,
			int target) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();  // sol list
		ArrayList<Integer> solution = new ArrayList<Integer>();
		Arrays.sort(num);  // 先sort
		combinationSum(num, 0, 0, target, ret, solution);
		return ret;
	}

	private void combinationSum(int[] num, int start, int sum,
			int target, ArrayList<ArrayList<Integer>> ret,
			ArrayList<Integer> solution) {
		if (sum == target) {
			ret.add(new ArrayList<Integer>(solution));  // clone这个sol
			return;
		}
		if (sum > target)
			return;
		for (int i = start; i < num.length; i++) {  // 从start index开始，尝试遍历每个element作sol的开头
			if (i > start && num[i] == num[i-1])  // 注意是大于start  比如{2,2,3}, 5 就会出现{2,3} {2,3}两个sol
				continue; 			
			solution.add(num[i]);
			// 因为都是pos int，而且sort过，sol要求也是ascending，所以只要看比选中的数大的的那些数
			combinationSum(num, i+1, sum + num[i], target, ret, solution);// 因为是ascending order，后面加入的数必然大于等于candidates[i]
			// 把这个num再去掉
			solution.remove(solution.size() - 1);
		}
	}
}


// 01/08/15
public class Solution {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        int l = num.length;
        if(l == 0) return ret;
        Arrays.sort(num);
        helper(ret, tmp, num, target, 0, 0);
        return ret;
    }
    
    private void helper(List<List<Integer>> ret, List<Integer> tmp, int[] num, int target, int sum, int idx){
        int l = num.length;
        if(sum == target) {
            ret.add(new ArrayList<Integer>(tmp));
            return;
        }
        if(sum > target) return;
        
        for(int i = idx; i < l; i++){
            if(i > idx && num[i-1] == num[i]) continue;
            tmp.add(num[i]);
            helper(ret, tmp, num, target, sum+num[i], i+1);
            tmp.remove(tmp.size()-1);
        }
    }
}

// 03/13/15
// recursion，用一个tmp来不断add和remove，直到target == 0表示tmp里的和正好等于target，作为一组sol放入ret
// 因为不能有重复的sol，所以要检查，1123  target = 6， 123不能出现两次
public class Solution {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        int l = num.length;
        if(l == 0) return ret;
        Arrays.sort(num);
        List<Integer> tmp = new ArrayList<>();
        helper(ret, tmp, num, target, 0);
        return ret;
    }
    
    public void helper(List<List<Integer>> ret, List<Integer> tmp, int[] num, int target, int start){
        if(target == 0){
            ret.add(new ArrayList<Integer>(tmp));
            return;
        }
        if(target < 0){
            return;
        }
        int l = num.length;
        for(int i = start; i < l; i++){
            if(i > start && num[i] == num[i-1]) continue;
            tmp.add(num[i]);
            helper(ret, tmp, num, target-num[i], i+1);
            tmp.remove(tmp.size()-1);
        }
    }
}