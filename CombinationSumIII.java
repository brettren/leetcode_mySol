// Find all possible combinations of k numbers that add up to a number n, given that 
// only numbers from 1 to 9 can be used and each combination should be a unique set 
// of numbers.

// Ensure that numbers within the set are sorted in ascending order.


// Example 1:

// Input: k = 3, n = 7

// Output:

// [[1,2,4]]


// Example 2:

// Input: k = 3, n = 9

// Output:

// [[1,2,6], [1,3,5], [2,3,4]]


// recursion的方法
public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        if(k == 0) return ret;

        helper(k, n, ret, tmp, 1);

        return ret;
    }

    public void helper(int k, int n, List<List<Integer>> ret, List<Integer> tmp, int start){
    	if(n < 0) return;  // 因为是increasing order，所以后面都是positive
    	if(n == 0 && tmp.size() == k){  // 只有刚好是k个数，并且sum == n才是sol
    		ret.add(new ArrayList<>(tmp));
    		return;
    	}
    	if(tmp.size() > k) return;

    	for(int i = start; i <= 9; i++){
    		tmp.add(i);
    		helper(k, n-i, ret, tmp, i+1);
    		tmp.remove(tmp.size()-1);
    	}
    }
}













