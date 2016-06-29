

import java.util.ArrayList;

/**
 * Given a digit string, return all possible letter combinations that the number
 * could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * 
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */

public class LetterCombinationsofaPhoneNumber {
	private char[][] map = new char[][] { { 'a', 'b', 'c' }, { 'd', 'e', 'f' },  // 从数字2开始，一个数字对应一个set
			{ 'g', 'h', 'i' }, { 'j', 'k', 'l' }, { 'm', 'n', 'o' },
			{ 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

	public ArrayList<String> letterCombinations(String digits) {
		ArrayList<String> ret = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		letterCombinations(digits, 0, sb, ret);  // 从string的第0位开始
		return ret;
	}

	private void letterCombinations(String digits, int i, StringBuilder sb,
			ArrayList<String> ret) {
		if (i >= digits.length()) {
			ret.add(sb.toString());  // 生成一个sol
		} else {
			int index = digits.charAt(i) - '2';  // digit string 的每一位   从数字2开始
			int size = map[index].length;
			for (int j = 0; j < size; j++) {  // 遍历当前数字所对应的char子集
				sb.append(map[index][j]);  // 把当前的char放入sb
				letterCombinations(digits, i + 1, sb, ret);  // digits.charAt(i+1) 探索digits string的下一位
				sb.deleteCharAt(sb.length() - 1);  // 再删掉刚才加的char
			}
		}
	}
}


// 01/05/15
public class Solution {
    private char[][] map = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };
    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        helper(ret, sb, digits, 0);
        return ret;
    }
    
    private void helper(List<String> ret, StringBuffer sb, String digits, int idx){
        if(idx >= digits.length()) {
            ret.add(sb.toString());
            return;
        }
        char[] tmp = map[digits.charAt(idx) - '2'];
        for(int i = 0; i < tmp.length; i++){
            sb.append(tmp[i]);
            helper(ret, sb, digits, idx+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}

// 03/15/15
// 先建立map，每一个数字都对应一组char
// 然后用递归来遍历每个数字转化为char的所有可能
// 这题体现了递归比循环的优势，不需要知道有几个loop嵌套，只需要设置什么时候循环到底就行了
public class Solution {
   public char[][] map = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };
    
    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<String>();
        int l = digits.length();
        StringBuffer sb = new StringBuffer();
        helper(ret, sb, digits, 0);
        return ret;
    }
    
    private void helper(List<String> ret, StringBuffer sb, String digits, int idx){
        int l = digits.length();
        if(idx == l) {
            ret.add(sb.toString());
            return; // 注意return
        }
        int d = digits.charAt(idx) - '2';
        for(int i = 0; i < map[d].length; i++){
            sb.append(map[d][i]);
            helper(ret, sb, digits, idx+1); // 到digits的下一位去
            sb.deleteCharAt(sb.length()-1);
        }
    }
}