// Given two strings S and T, determine if they are both one edit distance apart.

// Hint:
// 1. If | n – m | is greater than 1, we know immediately both are not one-edit distance apart.
// 2. It might help if you consider these cases separately, m == n and m ≠ n.
// 3. Assume that m is always ≤ n, which greatly simplifies the conditional statements. If m > n, we could just simply swap S and T.
// 4. If m == n, it becomes finding if there is exactly one modified operation. If m ≠ n, you do not have to consider the delete operation. Just consider the insert operation in T.

// 两种情况，如果m == n，那就是有一位没对上；如果shift == 1，那就是多出一个位来，跳过继续比较剩下的
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (m>n) return isOneEditDistance(t, s);
        if (n-m>1) return false; // 默认为 n >= m
        int i =0, shift = n-m;  // shift是0或1
        while (i<m && s.charAt(i)==t.charAt(i)) ++i;
        // 找到第一个对不上的位
        if (i==m) return shift > 0; // if two string are the same (shift==0), return false
        if (shift==0) i++; // if n==m skip current char in s (modify operation in s) 跳过这个对不上的位
        while (i<m && s.charAt(i)==t.charAt(i+shift)) i++; // use shift to skip one char in t
        return i == m; 
    }
}

// 02/12/15
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int ls = s.length();
        int lt = t.length();
        if(Math.abs(ls-lt) > 1) return false;
        if(ls < lt) return isOneEditDistance(t, s);
        int diff = ls-lt;
        int i = 0;
        while(i < lt){
            if(s.charAt(i) != t.charAt(i)) break;
            i++;
        }
        if(i == lt){  // 说明前面都match了
            if(diff == 0) return false;
            else return true;
        } 
        // 说明已经找到一位不match
        if(diff == 0){
            i++;
            while(i < lt){
                if(s.charAt(i) != t.charAt(i)) return false;
            }
        }
        else {
            while(i < lt){
                if(s.charAt(i+1) != t.charAt(i)) return false;
            }
        }
        return true;
    }
}

// 03/13/15
// 先判断长度diff不能大于1，然后分情况，如果ls == lt，逐位检查，只能且必须只有一个unmatch
// 如果ls 和lt差一位，就检查出第一个unmatch，然后后面剩下的必须match，或者正好是前面都match，只是s多一位
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int ls = s.length();
        int lt = t.length();
        if(Math.abs(ls-lt) > 1) return false;
        if(ls < lt) return isOneEditDistance(t, s);
        if (ls == lt) {
            int i = 0;
            while(i < ls){
                if(s.charAt(i) != t.charAt(i)) break;
                i++;
            }
            if(i == lt) return false;
            while(i < ls){
                if(s.charAt(i) != t.charAt(i)) return false;
                i++;
            }
            return true;
        }
        else{
            int i = 0;
            while(i < lt){
                if(s.charAt(i) != t.charAt(i)) break;
                i++;
            }
            if(i == lt) return true;
            while(i < lt){
                if(s.charAt(i+1) != t.charAt(i)) return false;
                i++;
            }
        }
        return true;
    }
}


// 06/20/15
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int ls = s.length(), lt = t.length();
        if(Math.abs(ls-lt) > 1) return false;
        if(s.equals(t)) return false;
        if(ls < lt) return isOneEditDistance(t, s);  // ganrantee the ls >= lt
        int i = 0;
        for (; i < lt; i++) {
            if (s.charAt(i) != t.charAt(i)) {  // find the first mismatch
                break;
            }
        }
        if(i == lt) return true;
        int shift = 1;
        if(ls == lt){  // if length equal, both skip one char; otherwise the longer skip one char
            i++;
            shift = 0;
        } 
        while(i < lt) {
            if (s.charAt(i+shift) != t.charAt(i)) {
                return false;
            }
            i++;
        }
        return true;
    }
}

































