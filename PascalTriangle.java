

import java.util.ArrayList;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * 
 * For example, given numRows = 5, Return
 * 
 * [      
 *        [1], 
 *       [1,1], 
 *      [1,2,1], 
 *     [1,3,3,1], 
 *    [1,4,6,4,1] 
 * ]
 */
//杨辉三角形第n层（顶层称第0层，第1行，第n层即第n+1行，此处n为包含0在内的自然数）
//正好对应于二项式\left(a+b\right)^{n}展开的系数。
//例如第二层1 2 1是幂指数为2的二项式(a+b)^2展开形式a^2+2ab+b^2的系数。


public class PascalTriangle {
	public ArrayList<ArrayList<Integer>> generate(int numRows) {
		ArrayList<ArrayList<Integer>> pt = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < numRows; i++) {
			ArrayList<Integer> r = new ArrayList<Integer>();  // create numRows个list
			for (int j = 0; j < i + 1; j++) {
				r.add(1);
			}
			pt.add(r);
		}  // 形成一个全为1的triangle
		for (int f = 2; f < pt.size(); f++) {
			ArrayList<Integer> prev = pt.get(f - 1);  // 找到上一个level
			ArrayList<Integer> current = pt.get(f);
			for (int g = 1; g < current.size() - 1; g++) {   // 除去两端的1，中间区域[1, current.size()-2]
				current.set(g, prev.get(g - 1) + prev.get(g));
			}
		}
		return pt;
	}
}


// 01/26/15
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(numRows == 0) return ret;
        List<Integer> tmp = new ArrayList<Integer>();
        tmp.add(1);
        ret.add(tmp);
        for(int i = 1; i < numRows; i++){
            List<Integer> cur = new ArrayList<Integer>();
            List<Integer> lastlevel = ret.get(ret.size()-1);
            cur.add(1);
            for(int j = 0; j < i-1; j++){
                cur.add(lastlevel.get(j)+lastlevel.get(j+1));
            }
            cur.add(1);
            ret.add(cur);
        }
        return ret;
    }
}

// 02/11/15
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(numRows == 0) return ret;
        for(int i = 1; i <= numRows; i++){
            List<Integer> tmp = new ArrayList<Integer>();
            tmp.add(1);
            if(i == 1) {
                ret.add(tmp);  // 先把第一个level放入
                continue;
            }
            List<Integer> prev = ret.get(ret.size()-1);
            for(int j = 0; j < prev.size()-1; j++){
                tmp.add(prev.get(j)+prev.get(j+1));
            }
            tmp.add(1);
            ret.add(tmp);
        }
        return ret;
    }
}

// 06/02/2015
public class Solution {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> ret = new;
        ArrayList<> tmp = new;
        tmp.add(1);
        ret.add(tmp);
        for (int i = 1; i < numRows; i++) {
            ArrayList<> tmp = new;
            tmp.add(1);
            ArrayList<> lastlevel = ret.get(i-1);
            for (int j = 1; j < i; ) {
                tmp.add(lastlevel.get(j-1)+lastlevel.get(j));
            }
            tmp.add(1);
            ret.add(tmp);
        }
        return ret;
    }
}