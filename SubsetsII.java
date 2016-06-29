import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a collection of integers that might contain duplicates, S, return all
 * possible subsets.
 * 
 * Note: 
 * 
 * Elements in a subset must be in non-descending order. The solution set
 * must not contain duplicate subsets. For example, If S = [1,2,2], a solution
 * is:
 * 
 * [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 */
// [ [], [1], [2], [1,2]             ]
public class SubsetsII {
	public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> lastLevel = null;
		ret.add(new ArrayList<Integer>());  // 先放空集
		Arrays.sort(num);  // 先sort
		for (int i = 0; i < num.length; i++) {
			ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> prev = (i == 0 || num[i] != num[i - 1]) ? ret : lastLevel;  //[ [], [1] ]
			for (ArrayList<Integer> s : prev) {
				ArrayList<Integer> newSet = new ArrayList<Integer>(s);  // 把原来的list内的subsets都copy过来
				newSet.add(num[i]);
				tmp.add(newSet);
			}
			ret.addAll(tmp);  //[ [], [1], [2], [1,2] ]
			lastLevel = tmp;  // 记下这个arraylist   [ [2], [1,2] ] , 在下一个重复的2时，只需要再把2加到lastLevel的list后面
		}
		return ret;
	}
}


// 01/11/15
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int l = num.length;
        if(l == 0) return ret;
        Arrays.sort(num); 
        ret.add(new ArrayList<Integer>());
        List<List<Integer>> lastlevel = null;
        for(int i = 0; i < l; i++){
            List<List<Integer>> tmp = new ArrayList<List<Integer>>();
            List<List<Integer>> prev = null;
            if(i > 0 && num[i] == num[i-1]){
                prev = lastlevel;
            }
            else{
                prev = ret;
            }
            for(List<Integer> set: prev){
                List<Integer> newSet = new ArrayList<Integer>(set);
                newSet.add(num[i]);
                tmp.add(newSet);
            }
            ret.addAll(tmp);
            lastlevel = tmp;
        }
        return ret;
    }
}

// 03/15/15
// 如果当前的num[i]和上一个num[i-1]相等，那就取prev，否则就取ret，然后把num[i]插到每个list里
// 要注意的是，需要每个list都完全复制，然后再插入新值，所以每个loop都需要新建一个二维list来放复制过来的内容
// 每个一维list都插入新值后，把这个二维list和原来的ret合并
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int l = num.length;
        if(l == 0) return ret;
        Arrays.sort(num);
        ret.add(new ArrayList<Integer>());
        List<List<Integer>> lastlevel = null;
        for(int i = 0; i < l; i++){
            List<List<Integer>> cur = new ArrayList<List<Integer>>();
            List<List<Integer>> prev = null;
            if(i > 0 && num[i] == num[i-1]){  // 先要确定prev是哪一个
                prev = lastlevel;
            }
            else{
                prev = ret;
            }
            for(List<Integer> set: prev){  // 从prev的list里对每个sublist复制，这里需要深复制
                List<Integer> tmp = new ArrayList<Integer>(set);
                tmp.add(num[i]);
                cur.add(tmp);
            }
            ret.addAll(cur);
            lastlevel = cur;
        }
        return ret;
    }
}