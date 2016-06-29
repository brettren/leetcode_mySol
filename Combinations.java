

import java.util.ArrayList;

/**
 * Given two integers n and k, return all possible combinations of k numbers out
 * of 1 ... n. For example, If n = 4 and k = 2, a solution is:
 * 
 * [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 * 
 */
//就是说 从1到N 随机的排列 总元素数为K   combinations of k numbers
//上面那个例子就是从1到4 里面 挑2个的所有排列。

//这题和premutation/cc的8.4很类似  是求排列 但是不是全排列。
public class Combinations {
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> subset = new ArrayList<Integer>();
		int[] num = new int[n];
		for (int j = 0; j < n; j++) {
			num[j] = j + 1;
		}
		subsets(n, k, num, 0, subset, result);
		return result;
	}

	private void subsets(int n, int k, int[] num, int begin,
			ArrayList<Integer> subset, ArrayList<ArrayList<Integer>> result) {
		if (subset.size() >= k) {
			ArrayList<Integer> c = new ArrayList<Integer>(subset);  // 注意这里一定要创建一个新的对象把subset内容复制过来
			result.add(c);
		} else {
			for (int i = begin; i < n; i++) {  // 这里从begin开始，就不会插入前面的数，避免重复
				subset.add(num[i]);
				subsets(n, k, num, i + 1, subset, result); // i+1是递归子程序的begin
				subset.remove(subset.size() - 1);
			}
		}
	}
}

// 01/03/15
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        int[] num = new int[n];
        for(int i = 0; i < n; i++){
            num[i] = i+1;
        }
        helper(ret, num, tmp, n, k, 0);
        return ret;
    }
    
    public void helper(List<List<Integer>> ret, int[] num, List<Integer> tmp, int n, int k, int idx){
        if(tmp.size() == k) {
            ret.add(new ArrayList<Integer>(tmp));
            return;
        }
        for(int i = idx; i < n; i++) {
            tmp.add(num[i]);
            helper(ret, num, tmp, n, k, i + 1);  // 递归，在index[i+1, ]的范围内继续选
            tmp.remove(tmp.size() - 1);
        }
    }
}

// 03/20/15
// 用数组num存1到n的数，然后用递归，因为不知道k是几
// 当tmp的size为k，表示一组set形成，放入ret
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(n == 0) return ret;
        int[] num = new int[n];
        for(int i = 0; i < n; i++){
            num[i] = i+1;
        }
        List<Integer> tmp = new ArrayList<Integer>();
        helper(ret, num, tmp, k, 0);
        return ret;
    }
    
    private void helper(List<List<Integer>> ret, int[] num, List<Integer> tmp, int k, int idx){
        int l = tmp.size();
        if(l == k){
            ret.add(new ArrayList<Integer>(tmp));
            return;
        }
        int n = num.length;
        if(idx >= n) return;
        for(int i = idx; i < n; i++){
            tmp.add(num[i]);
            helper(ret, num, tmp, k, i+1);
            tmp.remove(tmp.size()-1);
        }
    }
}