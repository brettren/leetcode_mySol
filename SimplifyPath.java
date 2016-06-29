import java.util.LinkedList;
import java.util.List;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example,
 * 
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * 
 * click to show corner cases.
 * 
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 *
 */
//简化unix 下的路径名  实现题 
//在unix里 . 表示 当前文件夹 ..表示parent文件夹  光一个/ 表示root
//所以 /a/./b/../../c/ -->  
//  a/还是a/b/回上一层变a 再回上一层变root/c 所以简化成 /c
//知道题意后 就容易做了  "/+" 拆分后(就已经没有 )  新建一个 paths String[]
// 然后扫描原数组 如果是. 或者空 就不动， 如果是.. 就remove path最后一个
// 如果是正常的字母就存入arraylist
//最后每个paths都插入/ 就行
public class SimplifyPath {
	public String simplifyPath(String path) {
		int length = path.length();
		if (length == 0)
			return path;
		Stack<String> dicts = new Stack<String>();
		int slow = 0;
		int fast = 0;
		while (true) {
			while (slow < length && path.charAt(slow) == '/') {
				slow++; // 跳过重复的'/'  直到一个dict名的开头
			}
			if (slow >= length)
				break;
			fast = slow;
			while (fast < length && path.charAt(fast) != '/') {
				fast++; // 直到这个dict名末尾
			}
			String s = path.substring(slow, fast); // 表示两个'/'中间的dict
			if (s.equals("..")) {
				if (!dicts.isEmpty()) {  // 如果dict里有上一个路径，就remove
					dicts.pop();
				}
			} else if (!s.equals(".")) {
				dicts.push(s);
			}
			slow = fast;
		}
		// 根据简化后的路径list来重新写路径
		StringBuffer ret = new StringBuffer();
		for (String s : dicts) {
			ret.append('/');
			ret.append(s);
		}
		return ret.length() == 0 ? "/" : ret.toString();
	}
}

// 03/08/15
// 用slow和fast两个指针找到两个/中间的string, /......../ , 然后检查看是否是/../还是/./
// 先是s找到string起点，然后f找到string的结尾
// 最后遍历stack里面的string放入结果
public class Solution {
    public String simplifyPath(String path) {
        int l = path.length();
        if(l == 0) return "";
        Stack<String> st = new Stack<>();
        int s = 0;
        int f = 0;
        while(true){
            while(s < l && path.charAt(s) == '/'){
                s++;
            }
            if(s == l) break;
            f = s;
            while(f < l && path.charAt(f) != '/'){
                f++;
            }
            String tmp = path.substring(s, f);
            if(tmp.equals("..")){
                if(!st.isEmpty()){
                    st.pop();
                }
            }
            else if(!tmp.equals(".")){
                st.push(tmp);
            }
            s = f;
        }
        StringBuffer sb = new StringBuffer();
        for(String i: st){
            sb.append("/");
            sb.append(i);
        }
        return st.isEmpty() ? "/" : sb.toString();
    }
}