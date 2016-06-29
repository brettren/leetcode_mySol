

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 * 
 * Note: Elements in a subset must be in non-descending order. The solution set
 * must not contain duplicate subsets. For example, If S = [1,2,3], a solution
 * is:
 * 
 * [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 */

public class Subsets {
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
		Arrays.sort(S); // 先sort
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		ret.add(new ArrayList<Integer>());  // 先放空集[]
		for (int i = 0; i < S.length; i++) {  //每一个loop, subsets的size都*2
			int size = ret.size(); // 记下原来的size
			for (int j = 0; j < size; j++) {
				ArrayList<Integer> subset = new ArrayList<Integer>(ret.get(j));
				subset.add(S[i]);  // 取前面每个list，都在后面加上当前的value，把这些新的list放入subsets  
				ret.add(subset);
			}
		}
		return ret;
	}
}

// 01/04/15
public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int l = S.length;
        if(l == 0) return ret;

        ret.add(new ArrayList<Integer>());
        
        for(int i = 0; i < l; i++){
            int size = ret.size();
            for(int j = 0; j < size; j++){
                List<Integer> tmp = new ArrayList<Integer>(ret.get(j));
                tmp.add(S[i]);
                ret.add(tmp);
            }
        }
        return ret;
    }
}

// 01/28/15
public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        int l = S.length;
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(l == 0) return ret;
        Arrays.sort(S);
        List<Integer> tmp = new ArrayList<Integer>();
        ret.add(tmp);
        for(int i = 0; i < l; i++){
            int size = ret.size();  // record the original size first
            for(int j = 0; j < size; j++){
                tmp = new ArrayList<Integer>(ret.get(j));
                tmp.add(S[i]);
                ret.add(tmp);
            }
        }
        return ret;
    }
}