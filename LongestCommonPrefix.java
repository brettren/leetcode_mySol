

/**
 * Write a function to find the longest common prefix string amongst an array of
 * strings.
 */

public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		int index = 0;
		while (index < strs[0].length()) {
			char c = strs[0].charAt(index); // 第一个string的第index个char
			for (int i = 1; i < strs.length; ++i) {  // 从第二个string开始
				if (index >= strs[i].length() || strs[i].charAt(index) != c) {  // 考虑到每个string的length可能都不同
					return strs[0].substring(0, index); //当前index不包括在内
				}
			} // 检查完其余所有的strings的这个index的char
			index++;
		}
		return strs[0];
	}
}

// 01/29/15
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuffer sb = new StringBuffer();
        int l = strs.length;
        if(l == 0) return sb.toString();
        int i = 0;
        while(i < strs[0].length()){
            char c = strs[0].charAt(i);  // 把第一个string当做参照，后面每个string和它比较
            for(int j = 1; j < l; j++){
                if(i >= strs[j].length()) return sb.toString();
                if(c != strs[j].charAt(i)) return sb.toString();
            }
            sb.append(c);
            i++;
        }
        return sb.toString();
    }
}


// 02/08/15
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        int l = strs.length;
        if(l == 0) return "";
        int i = 0;
        while(i < strs[0].length()){
            char c = strs[0].charAt(i);
            for(int j = 1; j < l; j++){
                if(i >= strs[j].length() || c != strs[j].charAt(i)) {  // 当不match或者超过size就return
                    return strs[0].substring(0, i);
                }
            }
            i++;
        }
        return strs[0].substring(0, i);
    }
}

// 03/14/15
// 以strs[0]当做template，每一位都和其他的str进行check，如果超过了某个str的长度，或者不match，说明已经找到了longest
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuffer sb = new StringBuffer();
        int l = strs.length;
        if(l == 0) return "";
        if(l == 1) return strs[0];
        int wl = strs[0].length();
        for(int i = 0; i < wl; i++){
            char c = strs[0].charAt(i);
            for(int j = 1; j < l; j++){
                if(i == strs[j].length()) return sb.toString();
                if(c != strs[j].charAt(i)) return sb.toString();
            }
            sb.append(c);
        }
        return sb.toString();
    }
}