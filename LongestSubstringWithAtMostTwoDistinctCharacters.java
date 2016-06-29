// Given a string S, find the length of the longest substring T that contains at most two distinct characters.
// For example,
// Given S = “eceba”,
// T is “ece” which its length is 3.

// 思路：
// 维护一个窗口 [i..j]，保证[i..j]里面最多有两种字符。
// （1） 如果当前窗口字符种类不超过两个，我们可以向右滑动j，直到出现第三种字符，同时统计一下两种字符出现的次数。) 
// （2） 当j滑动到指向第三种字符，我们向右滑动i，同时减少对应字符的个数，在滑动到j之前，必然出现 [i..j - 1]只有一种字符的情况，这时[i..j]就有两种字符了。
// 于是重复（1）和（2）就可以了。
// 陷阱：注意下标越界的特殊情况。 还有出现字符的种类，只有一种字符的时候，我们认为它是c1……
public class Solution{
	public int lengthOfLongestSubstringWithTwoDistinct(String s) {
		int l = s.length();
		if(l <= 2) return l;
		int i = 0;
		int j = 1;
		int count = 1;
		int[] map = new int[256];
		map[(int)(s.charAt(0))] = 1;
		int ret = 0;
		while(j < l){
			if(map[(int)(s.charAt(j))] > 0){
				map[(int)(s.charAt(j))]++;
				continue;
			} 
			if(count == 1){
				count++;
				map[(int)(s.charAt(j))]++;
			}
			else{
				int t = j-i;
				ret = Math.max(ret, t);
				while(map[(int)(s.charAt(i))] > 0) {
					map[(int)(s.charAt(i))]--;
					i++;
					if(map[(int)(s.charAt(i-1))] == 0) break;  // 如果发现剩下的[i, j-1]只有一个char就break
				}
				map[(int)(s.charAt(j))]++;
			}
			j++;
		}
		ret = Math.max(ret, j-i);
		return ret;
	}
}

// 03/20/15
// 和sliding window同理，用数组的map来记录window里每个char出现的次数，
// 分两种情况：新的char出现；下一个char已经在window内
// 当发现count > 2的情况时，先更新ret max，然后开始从slow指针向右缩小window，直到满足一个char在window内的出现次数为0
// 最后出了loop不要忘记末尾的部分也要检查更新ret
public class Solution{
	public int lengthOfLongestSubstringWithTwoDistinct(String s) {
		int l = s.length();
		if(l <= 2) return l;
		int[] map = new int[256];
		int i = 0, j = 0;
		int count = 0;
		int ret = 0;
		while(j < l){
			if (map[s.charAt(j)] == 0) {
				if (count < 2) {
					map[s.charAt(j)]++;
					count++;
				}
				else{
					ret = Math.max(ret, j-i);
					while(true){
						map[s.charAt(i)]--;
						if (map[s.charAt(i) == 0]) {
							i++;
							break;
						}
						i++;
					}
				}
			}
			else{
				map[s.charAt(j)]++;
			}
			j++;
		}
		ret = Math.max(ret, j-i);
		return ret;
	}
}