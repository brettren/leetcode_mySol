// Add Digits 

// 10  1
// 11  2


// 18  9
// 19  1
// 20  2


// (18-1)%9 + 1

public class Solution {
    public int addDigits(int num) {
        if(num == 0) return 0;
        return (num-1)%9 + 1;
    }
}