/**
 * 
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * For example:
 * 
 * Given array A = [2,3,1,1,4]
 * 
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */

//按照贪心去推，贪心的规则就是在能够到达的范围之内，选择一个能够到达最远距离的点，更新步数，和更新最远到达的范围。

//因为在覆盖范围0~2内都是可以跳的，所以这里不用去管跳到index 1还是index2算一步。
//用greedy的方法，先找到最远能够到达的地方index2，如果发现在覆盖范围内能跳到更远的，那再把上一次的step当做跳到了index 1
//因为在覆盖范围内跳哪里都算是跳了1步
public class JumpGameII {
	public int jump(int[] A) {
		int ret = 0;
		int last = 0; // 表示现在所在的位置
		int maxcover = 0;
		//maxcover表示最远能覆盖到的地方，last表示当前所在的位置
		for (int i = 0; i < A.length; ++i) {  // 遍历每个位置，更新能够到达的最远位置
			// 只有超过了当前所在的位置，才有必要更新last，说明原来last还不是终点
			if (i > last) {  //直到超过了所在位置，那么更新覆盖范围，同时更新step数，因为我们是经过了多一跳才能继续前进的。
				last = maxcover;  // 更新新的所在位置
				++ret;  // 增加一次step，每次last跳到maxcover都是一个step
			}
			maxcover = Math.max(maxcover, i + A[i]);  // 更新最远能覆盖到的地方
		}
		return ret;
	}
}

// 其实就是greedy，每一个step都尽可能跳到最远
public class JumpGameII {
	public int jump(int[] A) { 
        int maxx=0,temp=0,num=0; 
        int n = A.length; 
        for(int i=0;i<n;) {  
            if(temp>=n-1) break;   
            while(i<=temp) {  // 虽然跳到了temp，但是需要一直遍历到A[temp]为止，看下一步能跳的最远位置
                maxx=Math.max(maxx,i+A[i]);   // maxx是最远cover到的位置
                ++i;  
            } 
            // 这时i == temp 
            num++;  
            temp=maxx;   // temp是现在所在的位置           
        }  
        return num;          
    }  
}


// 01/09/15
public class Solution {
    public int jump(int[] A) {
        int l = A.length;
        int max = 0;
        int ret = 0;
        int cur = 0;
        for(int i = 0; i < l; ){
            if(i > max) return -1;
            if(max >= l-1) return ret;
            while(i <= cur){
                max = Math.max(max, A[i]+i);
                i++;
            }
            cur = max;
            ret++;
        }
        return ret;
    }
}

// 03/13/15
// greedy算法
// 找到当前所知能到达的最远距离cover，更新pos到这个位置
// 然后从i到pos遍历一直更新cover，等到i > pos时再跳到现在所知的最远距离
public class Solution {
    public int jump(int[] A) {
        int l = A.length;
        if(l == 0) return 0;
        int step = 0;
        int pos = 0;
        int cover = 0;
        for(int i = 0; i < l; ){
            if(i > cover) return -1;
            if(cover >= l-1) return step;
            while(i <= pos) {
                cover = Math.max(cover, i+A[i]); // 在pos前面所有的位更新cover最远的距离
                i++;
            }
            pos = cover; // 更新下一个step能到的pos
            step++;
        }
        return step;
    }
}