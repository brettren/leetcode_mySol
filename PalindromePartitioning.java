

import java.util.ArrayList;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab", Return
 * 
 * [ ["aa","b"], ["a","a","b"] ]
 */
//就是把string的所有按照回文分割（有多种可能 ）
//比方说 aab 可以分成 aa b 和 a a  b 要求每个都是回文

// DP
public class PalindromePartitioning {
	public ArrayList<ArrayList<String>> partition(String s) {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		ArrayList<String> r = new ArrayList<String>();
		int length = s.length();
		boolean[][] map = new boolean[length][length];  // start end
		findPartition(s, 0, ret, r, map);
		return ret;
	}

	private void findPartition(String s, int start,
			ArrayList<ArrayList<String>> ret, ArrayList<String> r,
			boolean[][] map) {
		int length = s.length();
		if (start == length && r.size() != 0) {  // start已经超过length，并且sol里有值
			ArrayList<String> clone = new ArrayList<String>(r);  // 复制这个结果放入ret
			ret.add(clone);
		} else {
			for (int j = start; j < length; j++) {
				if (start == j
					|| ( j - start > 1 && s.charAt(start) == s.charAt(j) && map[start + 1][j - 1] )
					|| ( j - start == 1 && s.charAt(start) == s.charAt(j) ) ) {
					map[start][j] = true; // 表示s[start,j]是palindrome ！！！！！！！！
					r.add(s.substring(start, j + 1)); //如果s[start，j]是palindrome，那就从这里断开提取这个substring放入sol
					findPartition(s, j + 1, ret, r, map); // j+1是递归子程序的start，也就是先对s[j+1,end]进行palindrome的判断操作
					r.remove(r.size() - 1);
				}
			}
		}
	}
}


// 01/03/15
public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<List<String>>();
        List<String> tmp = new ArrayList<String>();
        int l = s.length();
        if(l == 0) return ret;
        
        boolean[][] map = new boolean[l][l];
        helper(s, map, ret, tmp, 0);
        return ret;
    }
    
    private void helper(String s, boolean[][] map, List<List<String>> ret, List<String> tmp, int start){
        int l = s.length();
        if(start == l && tmp.size() != 0) ret.add(new ArrayList<String>(tmp)); // 如果start == l，说明一定是递归程序过来的，前面都已经partition好了
        for(int i = start; i < l; i++){
            if(i == start || (i - start == 1 && s.charAt(start) == s.charAt(i)) 
                || (i - start > 1 && s.charAt(start) == s.charAt(i) && map[start+1][i-1]) ) { 
                tmp.add(s.substring(start, i+1));
                map[start][i] = true;
                helper(s, map, ret, tmp, i+1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}

// 03/14/15
// 用二维数组map来存某个区间内[i,j]是否为palindrom，用递归来判断并且把符合palindrom的substring放入buffer
// 到底了就把buffer放入ret作为一个sol
// 在判断后要把map对应位置置为true
public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<List<String>>();
        int l = s.length();
        if(l == 0) return ret;
        boolean[][] map = new boolean[l][l];
        List<String> tmp = new ArrayList<String>();
        helper(ret, tmp, s, map, 0);
        return ret;
    }
    
    private void helper(List<List<String>> ret, List<String> tmp, String s, boolean[][] map, int idx){
        int l = s.length();
        if(idx >= l) {
            ret.add(new ArrayList<String>(tmp));
            return;
        }
        for(int i = idx; i < l; i++){
            if(s.charAt(i) == s.charAt(idx) && (i-idx <= 2 || map[idx+1][i-1])){
                map[idx][i] = true;   // 注意这里是[idx, i] !!!!  注意需要不断标记map[][]
                tmp.add(s.substring(idx,i+1));
                helper(ret, tmp, s, map, i+1);  // 这里递归的路径，其实是从右边开始的substring开始分析
                tmp.remove(tmp.size()-1);
            }
        }
    }
}