

public class Solution {  
	public int[] twoSum(int[] num, int target) {
		int l = num.length;
		if(l == 0) return 0;
		int[] ret = new int[2];
		int s = 0;
		int e = l-1;
		while(s < e){
			int t = num[s]+num[e];
			if(t == target) break;
			else if(t < target) s++;
			else e--;
		}
		ret[0] = s;
		ret[1] = e;
		return ret;
	}
}