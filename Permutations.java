

import java.util.ArrayList;

/**
 * Given a collection of numbers, return all possible permutations.
** For example,
** [1,2,3] have the following permutations:
** [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 */

// 比如 1,2,3,4,2,5    所有permutation的个数为 6*5*4...*2*1
// 先尝试后面所有的值和第一位值swap，然后是第二位和后面所有值swap(因为第二位之前已经和第一位换过了)...
// 当arr[end] = 2 再次出现，就跳过

// 通过swap来实现各种permutation
public class PermutationsII {
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
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
		for (int end = begin; end < num.length; end++) { // end从begin开始，要把swap前的array也放到result里
			swap(num, begin, end);
			permuteUnique(num, begin + 1, result); // 在swap后的基础上再递归
			swap(num, begin, end);  // 再换回来
		}
	}
	
	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}


// 01/04/15

// 这里DFS要注意，是在选一个数和start swap后，继续在start后面DFS
public class Solution {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        helper(ret, num, 0);
        return ret;
    }
    
    private void helper(List<List<Integer>> ret, int[] num, int idx){
        if(idx >= num.length){
            List<Integer> tmp = new ArrayList<Integer>();
            for(int n: num){
                tmp.add(n);
            }
            ret.add(tmp);
        }
        for(int i = idx; i < num.length; i++){
            swap(num, idx, i);
            helper(ret, num, idx + 1);  
            swap(num, idx, i);
        }
    }
    
    private void swap(int[] num, int idx, int i){
        int tmp = num[idx];
        num[idx] = num[i];
        num[i] = tmp;
    }
}