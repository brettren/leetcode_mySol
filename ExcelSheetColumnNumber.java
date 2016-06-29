public class Solution {
    public int titleToNumber(String s) {
        int l = s.length();
        if(l == 0) return 0;
        int i = l-1;
        int ret = 0;
        int base = 1;
        while(i >= 0){
            char c = s.charAt(i);
            int t = c-'A'+1;
            ret += t*base;
            base *= 26;
            i--;
        }
        return ret;
    }
}

// 03/24/15
// 可以从MSB开始，每到下一位就ret*26+digit
public class Solution {
    public int titleToNumber(String s) {
        int l = s.length();
        if(l == 0) return 0;
        int ret = 0;
        for(int i = 0; i < l; i++){
            char c = s.charAt(i);
            ret = ret*26 + c-'A'+1;
        }
        return ret;
    }
}