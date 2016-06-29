

/**
 * Implement strStr().
 * 
 * Returns a pointer to the first occurrence of needle in haystack, or null if
 * needle is not part of haystack.
 */


public class ImplementStrStr {
	public int strStr(String haystack, String needle) {
		int len1 = haystack.length();
		int len2 = needle.length();
		if (len1 < len2) {
			return -1;
		}
		for (int i = 0; ; i++) { //haystack的start
			for (int j = 0; ; j++) {  //needle的start
				if (j == len2) return i; // 一直到超过了needle的length都没有break这个for循环，说明前面都对上了
				if (i + j == len1) return -1;  // 如果haystack剩下的length小于needle的length时还没有找到，那就肯定不存在了
				if (needle.charAt(j) != haystack.charAt(i + j)) break;
			}
		}	
	}
}

// 03/03/15
public class Solution {
    public int strStr(String haystack, String needle) {
        int l1 = haystack.length();
        int l2 = needle.length();
        if(l1 < l2) return -1;
        for(int i = 0; i <= l1-l2; i++){
            if(haystack.substring(i, i+l2).equals(needle)){
                return i;
            }
        }
        return -1;
    }
}

// 03/11/15  不用substring
// 循环，以haystack每一位当做needle的开头，检查是否substring == needle，如果某一位不等，就从下一位重新开始match
// 注意如果needle是空字符串就返回0
public class Solution {
    public int strStr(String haystack, String needle) {
        int l1 = haystack.length();
        int l2 = needle.length();
        if(l1 < l2) return -1;
        if(l2 == 0) return 0;
        for(int i = 0; i <= l1-l2; i++){
            for(int j = 0; j < l2; j++){
                char c1 = haystack.charAt(i+j);
                char c2 = needle.charAt(j);
                if(c1 != c2) break;
                if(j == l2-1) return i;
            }
        }
        return -1;
    }
}