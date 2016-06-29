
// Given a string s, return all the palindromic permutations (without duplicates) of it. 
// Return an empty list if no palindromic permutation could be form.

// For example:

// Given s = "aabb", return ["abba", "baab"].

// Given s = "abc", return [].

public class Solution {
    public List<String> generatePalindromes(String s) {
    	List<String> ret = new ArrayList<>();
    	if (s == null || s.length == 0) {
    		return ret;
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
    	char oddChar = 'a';
    	char[] array = new char[s.length()];
    	int i = 0;
    	for(char c: map.keySet()){
    		if(map.get(c) % 2 != 0){
    			if(odd == false){
    				odd = true;
    				oddChar = c;
    			}
    			else{
    				return ret;
    			}
    		}
    		else{
    			while(map.get(c) != 0){
    				array[i++] = c;
    				map.put(c, map.get(c)-2);
    			}
    		}
    	}
    	if(odd == true){
    		array[i] = oddChar;
    	}
    	dfs(array, ret, 0, i-1);
    	return ret;
    }

    public void dfs(char[] array, List<String> ret, int start, int end){
    	
    }
}