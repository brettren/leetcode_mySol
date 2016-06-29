

import java.util.ArrayList;

/**
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 * 
 * For example: Given "25525511135",
 * 
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */

//首先我们要明确传统IP 地址的规律是分4个Part，每个Part是从0-255的数字
//
//0-255的数字，转换成字符，即每个Part 可能由一个字符组成，二个字符组成，
//或者是三个字符组成。那这又成为组合问题了，dfs便呼之欲出
//每一个枝桠深到最后就是一个dfs
//所以我们写一个For循环每一层从1个字符开始取一直到3个字符，
//再加一个isValid的函数来验证取的字符是否是合法数字，如果是合法的数字，
//我们再进行下一层递归，否则跳过

public class RestoreIPAddresses {
	public ArrayList<String> restoreIpAddresses(String s) {
		ArrayList<String> ips = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		restoreIpAddresses(s, 0, ips, sb, 0);
		return ips;
	}

	private void restoreIpAddresses(String s, int i, ArrayList<String> ips,
			StringBuffer ip, int step) {
		if ((step >= 4 && i < s.length()) || (step < 4 && i >= s.length()))
			return;  // 每个address最多4个segment，发现还有剩下的部分，或者还未形成4个segment就结束了，说明不符合address要求，return
		if (step >= 4 && i >= s.length()) {
			ips.add(ip.substring(0, ip.length() - 1));  //形成一个IP address，不要包括最后的'.'，所以是到length()-1
		} else {
			if (s.charAt(i) == '0') {
				ip.append('0');
				ip.append('.'); // 0开头后面直接用'.'断开，因为不会有0xx的部分
				restoreIpAddresses(s, i + 1, ips, ip, step + 1);
				ip.deleteCharAt(ip.length() - 1);
				ip.deleteCharAt(ip.length() - 1);  // 再删去'0.'
			} else {
				for (int j = 1; j <= 3 && i + j <= s.length(); j++) {  // 每个segment最多3个digits
					StringBuffer nextSeg = new StringBuffer();
					nextSeg.append(s.substring(i, i + j));  // 每一个loop，放1个，2个，3个
					int n = Integer.parseInt(nextSeg.toString());  // 转为int
					if (n > 0 && n <= 255) {  // ip地址每一个set都在这个范围内
						ip.append(nextSeg.toString());
						ip.append('.');
						restoreIpAddresses(s, i + j, ips, ip, step + 1);  // 处理下一个segment
						ip.deleteCharAt(ip.length() - 1); // 移走末尾的'.'
						for (int k = 0; k < nextSeg.length(); k++)
							ip.deleteCharAt(ip.length() - 1);  // 把刚放入的nextSeg再删了
					} else {
						break;
					}
				}
			}
		}
	}
}


// 01/07/15
public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        helper(ret, s, sb, 0, 0);
        return ret;
    }
    
    private void helper(List<String> ret, String s, StringBuffer sb, int idx, int level){
        int l = s.length();
        if(idx >= l && level < 4) return;
        if(idx < l && level >= 4) return;
        if(idx >= l && level >= 4) {
            ret.add(sb.substring(0, sb.length()-1));
            return;
        }
        
        if(s.charAt(idx) == '0'){
            sb.append('0');
            sb.append('.');
            helper(ret, s, sb, idx+1, level+1);
            sb.deleteCharAt(sb.length()-1);
            sb.deleteCharAt(sb.length()-1);
        }
        else{
            for(int i = 1; i <= 3 && idx+i<=l; i++){   // 这里要注意idx+i <=l
                String tmp = s.substring(idx, idx+i);
                int t = Integer.parseInt(tmp);
                if(t >= 0 && t <= 255){
                    sb.append(tmp);
                    sb.append('.');
                    helper(ret, s, sb, idx+i, level+1);
                    sb.deleteCharAt(sb.length()-1);
                    for(int j = 1; j <= i; j++){
                        sb.deleteCharAt(sb.length()-1);
                    }
                }
                else{
                    break;
                }
            }
        }
    }
}

// 03/09/15
// 这题要小心，如果是'0'开头的level，后面不能加数字了，只能把单个'0'当做一个level
// 只有满足刚好四个level，并且没有剩余的char，才是solution
public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<>();
        int l = s.length();
        if(l == 0) return ret;
        StringBuffer sb = new StringBuffer();
        helper(ret, sb, s, 0, 0);
        return ret;
    }
    
    public void helper(List<String> ret, StringBuffer sb, String s, int level, int start){
        int l = s.length();
        if(level == 4 && start == l){
            sb.deleteCharAt(sb.length()-1);
            ret.add(sb.toString());
            sb.append('.');
            return;
        }
        if(level == 4) return;
        if(start == l) return;
        if(s.charAt(start) == '0'){
            sb.append('0').append('.');
            helper(ret, sb, s, level+1, start+1);
            sb.deleteCharAt(sb.length()-1);
            sb.deleteCharAt(sb.length()-1);
        }
        else{
            for(int i = start; i < start+3; i++){
                if(i == l) break;
                int num = Integer.parseInt(s.substring(start, i+1));
                if(num > 255) break;
                sb.append(s.substring(start, i+1)).append('.');
                helper(ret, sb, s, level+1, i+1);
                sb.deleteCharAt(sb.length()-1);
                for(int j = 0; j <= i-start; j++){
                    sb.deleteCharAt(sb.length()-1);
                }
            }
        }
    }
}