// Given two strings s and t, determine if they are isomorphic.

// Two strings are isomorphic if the characters in s can be replaced to get t.

// All occurrences of a character must be replaced with another character 
// while preserving the order of characters. No two characters may map to the 
// same character but a character may map to itself.

// For example,
// Given "egg", "add", return true.

// Given "foo", "bar", return false.

// Given "paper", "title", return true.

// Note:
// You may assume both s and t have the same length.

// 两个map用来映射 s 到 t 的dict，和t 到 s 的dict，要注意这两个mapping可以不同
// 比如 ab -> bc
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        int l = s.length();
        if(l == 0) return true;
        Map<Character, Character> mapS = new HashMap<>();
        Map<Character, Character> mapT = new HashMap<>();
        for(int i = 0; i < l; i++){
        	char c1 = s.charAt(i);
        	char c2 = t.charAt(i);
        	if(mapS.containsKey(c1)){
        		if(c2 != mapS.get(c1)) return false;
        	}
        	if(mapT.containsKey(c2)){
        		if(c1 != mapT.get(c2)) return false;
        	}
        	else{
        		mapS.put(c1, c2);
        		mapT.put(c2, c1);
        	}
        }
        return true;
    }
}