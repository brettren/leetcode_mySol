// Given a string, determine if a permutation of the string could form a palindrome.
// For example,
// "code" -> False, "aab" -> True, "carerac" -> True.

// 統計每個字數出現的次數, 出現奇數次的最多只能有一個
public class Solution {
    public boolean canPermutePalindrome(String s) {
    	if (s == null || s.length == 0) {
    		return true;
    	}
    	Map<Character, Integer> map = new HashMap<>();
    	for(int i = 0; i < s.length(); i++){
    		char c = s.charAt(i);
    		if(!map.containsKey(c)){
    			map.put(c, 1);
    		}
    		else{
    			map.put(c, map.get(c)+1);
    		}
    	}
    	boolean odd = false;
    	for(char c: map.keySet()){
    		if(map.get(c) % 2 != 0){
    			if(odd == false){
    				odd = true;
    			}
    			else{
    				return false;
    			}
    		}
    	}

    }
}