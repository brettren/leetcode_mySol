// Compare two version numbers version1 and version2.
// If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

// You may assume that the version strings are non-empty and contain only digits and 
// the . character.

// The . character does not represent a decimal point and is used to separate number 
// sequences.

// For instance, 2.5 is not "two and a half" or "half way to version three", it is the 
// fifth second-level revision of the second first-level revision.

// Here is an example of version numbers ordering:

// 0.1 < 1.1 < 1.2 < 13.37




public class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < Math.max(v1.length, v2.length); i++) {
            int gap = (i < v1.length ? Integer.parseInt(v1[i]) : 0) - (i < v2.length ? Integer.parseInt(v2[i]) : 0);
            if (gap != 0) {
                return gap > 0 ? 1 : -1;
            }
        }
        return 0;
    }
}


// 01/18/15
public class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int l1 = v1.length;
        int l2 = v2.length;
        int i = 0;
        for(; i < Math.min(l1, l2); i++){
            int diff = Integer.parseInt(v1[i]) - Integer.parseInt(v2[i]);
            if(diff < 0) return -1;
            if(diff > 0) return 1;
        }
        if(l1 < l2 && !v2[i].equals("0")) return -1;
        if(l1 > l2 && !v1[i].equals("0")) return 1;
        return 0;
    }
}

// 02/10/15
// 用"."分隔返回string的数组，然后逐位比较。如果还有某个数组多出的部分，就和0比较
public class Solution {
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        int l1 = s1.length;
        int l2 = s2.length;
        int i = 0;
        for(; i < Math.min(l1, l2); i++){
            int a1 = Integer.parseInt(s1[i]);
            int a2 = Integer.parseInt(s2[i]);
            if(a1 > a2) return 1;
            if(a1 < a2) return -1;
        }
        if(l1 > l2 && Integer.parseInt(s1[i]) != 0) return 1; // 考虑到可能是"00"的情况，还是先转为int更好
        if(l1 < l2 && Integer.parseInt(s2[i]) != 0) return -1;
        return 0;
    }
}