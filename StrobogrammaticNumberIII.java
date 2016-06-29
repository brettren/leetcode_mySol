
// Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

// TODO:

// from low.length() to high.length()

public class Solution{
	private char[] validNumbers = new char[]{'0', '1', '6', '8', '9'};
    private char[] singleable = new char[]{'0', '1', '8'};

	public List<String> findStrobogrammatic(int n) {
		
		List<String> ret = new ArrayList<>();
		char[] array = new char[n];
		helper(array, ret, 0, n-1);
		return ret;
	}

	public void helper(char[] array, List<String> ret, int left, int right){
		if(left > right){
			ret.add(new String(array));
			return;
		}
		if(left == right){
			for(int i = 0; i < singleable.length; i++){
				array[left] = singleable[i];
				helper(array, ret, left+1, right-1);
			}
		}
		else{
			for(int i = 0; i < validNumbers.length; i++){
				array[left] = validNumbers[i];
				array[right] = mapping(validNumbers[i]);
				helper(array, ret, left+1, right-1);
			}
		}
	}

	public char mapping(char c){
		switch(c){
			case '0': return '0';
			case '1': return '1';
			case '6': return '9';
			case '8': return '8';
			case '9': return '6';
			default: return '0';
		}
	}
}