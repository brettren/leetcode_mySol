/**
 * Given an array of integers, find two numbers such that they add up to a
 * specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
 */

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
    	HashMap<Integer, Integer> map=new HashMap<Integer, Integer>(); // <value, index>		
		int[] ret = new int[2];
		for (int i=0; i<numbers.length; i++) {
			if (map.containsKey(target - numbers[i])) {
				ret[0] = map.get(target - numbers[i])+1;  // 注意加一，因为[0]表示为index 1
				ret[1] = i+1;
				return ret;
			}
			else{
				map.put(numbers[i],i);
			}
		}
		return null;
    }
}

// 01/02/15
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        int[] ret = new int[2];
        int l = numbers.length;
        for(int i = 0; i < l; i++){  // 先检查partner，再放pair到map里
            if(map.containsKey(target - numbers[i])){
                ret[0] = map.get(target - numbers[i])+1;  // idx1 < idx2
                ret[1] = i+1;
                return ret;
            }
            map.put(numbers[i], i);  // 如果找不到对应的partner，才放入新值
        }
        return null;
    }
}

// TE 超时
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        Arrays.sort(numbers);
        int[] ret = new int[2];
        int s = 0;
        int e = numbers.length - 1;
        while(s < e){
            int sum = numbers[s]+numbers[e];
            if(sum == target){
                ret[0] = s+1;
                ret[1] = e+1;
                return ret;
            }
            else if(sum > target){
                e--;
            }
            else{
                s++;
            }
        }
        return ret;
    }
}