



public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
    	if(s == null || s.length() == 0) return 0;
    	Map<Character, Integer> map = new HashMap<>();
    	int i = 0, j = 0;
    	int ret = 0;
    	while(j < s.length()){
    		if(map.size() > k){
    			char c = s.charAt(i);
    			Integer count = map.get(c);
    			if(count == 1){
    				map.remove(c);
    			}
    			else{
    				map.put(c, count-1);
    			}
    			i++;
    		}
    		else{
    			char c = s.charAt(j);
    			Integer count = map.get(c);
    			if(count == null){
    				map.put(c, 1);
    			}
    			else{
    				map.put(c, count+1);
    			}
    			j++;
    		}

    		if(map.size() <= k){
    			ret = Math.max(ret, j-i);
    		}
    	}
    	return ret;
    }
}