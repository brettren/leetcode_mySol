
// A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

// Write a function to determine if a number is strobogrammatic. The number is represented as a string.

// For example, the numbers "69", "88", and "818" are all strobogrammatic.

public class Solution{
	public boolean isStrobogrammatic(String num) {
		Map<Character, Character> map = new HashMap<>();
		map.put('0', '0');
		map.put('1', '1');
		map.put('6', '9');
		map.put('8', '8');
		map.put('9', '6');
		int i = 0, j = num.length()-1;
		while(i <= j){
			char a = num.charAt(i);
			char b = num.charAt(j);
			if(!map.containsKey(a) || !map.containsKey(b)) return false;
			if(map.get(a) != b) return false;
			i++;
			j--;
		}
		return true;
	}
}