// 1 -> A
// 2 -> B
// 3 -> C
// ...
// 26 -> Z
// 27 -> AA
// 28 -> AB 

public class Solution {
    public String convertToTitle(int n) {
        StringBuffer sb = new StringBuffer();
        while(n != 0){
            int d = (n-1) % 26;
            char c = (char)(d+'A');
            sb.append(c);
            n = (n-1)/26;
        }
        return sb.reverse().toString();
    }
}

// 03/07/15
public class Solution {
    public String convertToTitle(int n) {
        StringBuffer sb = new StringBuffer();
        while(n > 0){
            char c = (char)((n-1) % 26 + 'A');
            n = (n-1) / 26;
            sb.insert(0, c);
        }
        return sb.toString();
    }
}