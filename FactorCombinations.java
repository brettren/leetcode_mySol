// Numbers can be regarded as product of its factors. For example,

// 1
// 8 = 2 x 2 x 2;
//   = 2 x 4.

// Write a function that takes an integer n and return all possible combinations of its factors.

// Note:

// Each combination’s factors must be sorted ascending, for example: 
// The factors of 2 and 6 is [2, 6], not [6, 2].

// You may assume that n is always positive.

// Factors should be greater than 1 and less than n.

// Examples:

// input: 1

// output:

// []

// input: 37

// output:

// []

// input: 12

// output:

// [
//   [2, 6],
//   [2, 2, 3],
//   [3, 4]
// ]

// input: 32

// output:

// [
//   [2, 16],
//   [2, 2, 8],
//   [2, 2, 2, 4],
//   [2, 2, 2, 2, 2],
//   [2, 4, 4],
//   [4, 8]
// ]


public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helper(ret, tmp, n, 2);
        return ret;
    }

    public void helper(List<List<Integer>> ret, List<Integer> tmp, int n, int start){
        if(n <= 1){
            if(tmp.size() > 1){ // size == 1 means include n itself
                ret.add(new ArrayList<Integer>(tmp));
            }
            return;
        }
        for(int i = start; i <= n; i++){
            if(n % i == 0){
                tmp.add(i);
                helper(ret, tmp, n/i, i);
                tmp.remove(tmp.size()-1);
            }
        }
    }
}




