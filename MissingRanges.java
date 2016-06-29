// Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
// For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]

public class Solution {
    public List<String> findMissingRanges(int[] vals, int start, int end) {
    	List<String> ret = new ArrayList<String>();
    	int l = vals.length;
    	int prev = start;  // prev表示空缺的开始
    	for(int i = 0; i <= l; i++){
    		int cur = (i == l)?end+1:vals[i]; // 等于存在一个值是end+1
    		if(cur == prev){
    			prev++;
    		}
    		else if (cur - prev == 1) {
    			ret.add(String.valueOf(prev));
    			prev = cur+1;
    		}
    		else{
				String tmp = prev + "->" + (cur-1);
				ret.add(tmp);
				prev = cur+1;
			}
    	}
    	return ret;
    }
}

// 03/12/15
// 这里要考虑当数组遍历完，可能最后一个值也小于end，所以要把最后的[s, end]也补上
// 在判断是否读完时，cur 设为 end+1，这样cur-1才是正确的bound
// 考虑三种情况，如果cur > s, 插入一个数还是插入区间；如果cur == s就跳过
//[0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]
public class Solution {
    public List<String> findMissingRanges(int[] vals, int start, int end) {
        List<String> ret = new ArrayList<>();
        int l = vals.length;
        if(l == 0) return ret;
        int s = start;
        for (int i = 0; i <= l; i++) {
            int cur = (i == l) ? end+1 : vals[i];
            if (cur > s) {
                if (cur == s+1) {
                    String tmp = String.valueOf(s);
                    ret.add(tmp);
                    s = cur+1;
                }
                else{
                    String tmp = s + "->" + (cur-1);
                    ret.add(tmp);
                    s = cur+1;
                } 
            }
            else if (cur == s) {
                s++;
            }
        }
        return ret;
    }
}