

import java.util.HashSet;

/**
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * 
 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive elements
 * sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 */
//遍历到temp时候  再hashmap里找temp+1 和temp-1 看看有没有和temp连续的
//如果找到了就再temp++/temp-- 看最长能连多少
public class LongestConsecutiveSequence {
	public int longestConsecutive(int[] num) {
		int max = 0;
		HashSet<Integer> h = new HashSet<Integer>();
		for (int n : num) {
			h.add(n);  // 先把每个int放入hash
		}
		for (int n : num) {
			max = Math.max(max, getCount(h, n, false) + getCount(h, n + 1, true));  // 往上trace和往下trace
		}
		return max;
	}

	public int getCount(HashSet<Integer> h, int value, boolean asc) {
		int count = 0;
		while (h.contains(value)) {
			count++;
			h.remove(value);  
			// 注意如果算作是一个consecutive，就要把这个value从hash中remove, 这样在下一次遇到consecutive里的value时不需要再trace了
			// 所以能实现O(n)的复杂度
			if (asc)  // ascending
				value++;
			else
				value--;
		}
		return count;
	}
}

// 01/05/15
public class Solution {
    public int longestConsecutive(int[] num) {
        HashSet<Integer> map = new HashSet<Integer>();
        int ret = 0;
        for(int n: num){
            map.add(n);
        }
        for(int n: num){   // 这里再次遍历num[]
            int max = 0;
            int tmp = n;
            while(map.contains(tmp)){
                map.remove(tmp);
                max++;
                tmp++;
            }
            tmp = n-1;  // 从n-1开始
            while(map.contains(tmp)){
                map.remove(tmp);
                max++;
                tmp--;
            }
            if(max > ret) ret = max;
        }
        return ret;
    }
}

// 02/11/15
public class Solution {
    public int longestConsecutive(int[] num) {
        int l = num.length;
        if(l == 0) return 0;
        Set<Integer> set = new HashSet<Integer>();
        for(int i: num){
            set.add(i);
        }
        int ret = 0;
        for(int i: num){
            int max = 1;
            int tmp = i+1;
            while(set.contains(tmp)){
                set.remove(tmp);
                tmp++;
                max++;
            }
            tmp = i-1;
            while(set.contains(tmp)){
                set.remove(tmp);
                tmp--;
                max++;
            }
            ret = Math.max(ret, max);
        }
        return ret;
    }
}