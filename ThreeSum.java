

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a +
 * b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * 
 * Note:
 * 
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ? b ?
 * c) The solution set must not contain duplicate triplets.
 * 
 * For example, given array S = {-1 0 1 2 -1 -4},
 * 
 * A solution set is: (-1, 0, 1) (-1, -1, 2)
 */

public class ThreeSum {
	public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		Arrays.sort(num);  // 先sort
		int a, b, c, k, l;
		for (int i = 0; i <= num.length - 3; i++) {
			if (i > 0 && num[i] == num[i - 1]) 
				continue;  // 跳过重复的   比如 -1 -1 0 1，跳过第二个-1，以免产生重复的sol
			a = num[i];
		    if(a > 0)
		        break; // 如果num[i]大于0，那么a,b,c的和必然大于0			
			k = i + 1;
			l = num.length - 1;  // 最后一个element
			while (k < l) {
				b = num[k];
				c = num[l];
				if (a + b + c == 0) {
					if (k != i + 1 && num[k] == num[k - 1]) {  // 不能和上一个数重复，注意这里是上一个k不能是i
						k++;
						continue;  // 跳过重复的
					}
					if (l != num.length - 1 && num[l] == num[l + 1]) {
						l--;
						continue;  // 跳过重复的
					}
					ArrayList<Integer> item = new ArrayList<Integer>();
					item.add(a);
					item.add(b);
					item.add(c);
					result.add(item);
					l--;
					k++;  // 如果只是l--或k++,那下一组肯定大于0或小于0
				} else if (a + b + c > 0) {
					l--;
				} else {
					k++;
				}
			}
		}
		return result;
	}
}

// 01/02/15
public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        int l = num.length;
        
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(num);
        
        int a,b,c,j,k;
        for(int i = 0; i < l-2; i++){
            if(i > 0 && num[i] == num[i-1]) continue;
            a = num[i];
            if(a > 0) break;  // 因为是ascending order，a > 0，后面都大于0，sum不会为0
            j = i + 1;
            k = l - 1;
            while(j < k){
                b = num[j];
                c = num[k];
                if(a + b + c == 0){
                    if(j != i+1 && num[j] == num[j-1]){
                        j++;
                        continue;
                    }
                    if(k != l-1 && num[k] == num[k+1]){
                        k--;
                        continue;
                    }
                    ArrayList<Integer> item = new ArrayList<Integer>();
                    item.add(a);
                    item.add(b);
                    item.add(c);
                    ret.add(item);
                    j++;
                    k--;
                }
                else if(a + b + c < 0){
                    j++;
                }
                else{
                    k--;
                }
            }
        }
        return ret;
    }
}

// 02/12/15
public class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int l = num.length;
        if(l < 3) return ret;
        Arrays.sort(num);
        int s = 0;
        while(s < l-2){
            if(s > 0 && num[s] == num[s-1]){
                s++;
                continue;
            }
            int m = s+1;
            int e = l-1;
            while(m < e){
                if(m > s+1 && num[m] == num[m-1]){
                    m++;
                    continue;
                }
                if(e < l-1 && num[e] == num[e+1]){
                    e--;
                    continue;
                }
                int sum = num[s]+num[m]+num[e];
                if(sum == 0){
                    List<Integer> tmp = new ArrayList<Integer>();
                    tmp.add(num[s]);
                    tmp.add(num[m]);
                    tmp.add(num[e]);
                    ret.add(tmp);
                    m++;
                    e--;
                }
                else if(sum < 0){
                    m++;
                }
                else{
                    e--;
                }
            }
            s++;
        }
        return ret;
    }
}

// 03/06/15
public class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> ret = new ArrayList<>();
        int l = num.length;
        if(l == 0) return ret;
        Arrays.sort(num);
        for(int i = 0; i < l-2; i++){
            if(num[i] > 0) break;
            if(i > 0 && num[i] == num[i-1]) continue;
            int s = i+1;
            int e = l-1;
            while(s < e){
                if(s > i+1 && num[s] == num[s-1]){
                    s++;
                    continue;
                }
                if(e < l-1 && num[e] == num[e+1]){
                    e--;
                    continue;
                }
                int sum = num[i]+num[s]+num[e];
                if(sum == 0){
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(num[i]);
                    tmp.add(num[s]);
                    tmp.add(num[e]);
                    ret.add(tmp);
                    s++;
                    e--;
                }
                else if(sum > 0){
                    e--;
                }
                else{
                    s++;
                }
            }
        }
        return ret;
    }
}