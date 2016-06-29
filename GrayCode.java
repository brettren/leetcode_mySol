

import java.util.ArrayList;

/**
 * The gray code is a binary numeral system where two successive values differ
 * in only one bit.
 * 
 * Given a non-negative integer n representing the total number of bits in the
 * code, print the sequence of gray code. A gray code sequence must begin with 0.
 * 
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 
 * Note:
 * 
 * For a given n, a gray code sequence is not uniquely defined.
 * 
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * 
 * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */

//格雷码转换  输入二进制长度  输出 一条从头转到底的路径
//格雷码定义就是在二进制数字变换时候 不管你有多少bit 每次只能变一个 bit
//像上面的例子一样  如果是2位bit 那么一共就是2*2种=4种数字 你返回的结果一定要把四种数字的int值都返回 但是每2个之间最多变一位 bit
//比方说 n=4时候 需要返回16个数字 从0000开始 但是相邻数字 每次之变1 bit 。 返回一种顺序即可。
//00 - 0
//01 - 1
//11 - 3
//10 - 2
//
//Try one more example, n = 3:
//
//000 - 0
//001 - 1
//011 - 3
//010 - 2
//110 - 6
//111 - 7
//101 - 5
//100 - 4 
//看出规律了么 n = 2: [0,1,3,2] and n=3: [0,1,3,2,6,7,5,4]
//前面是一样的  后面6754 就是  [2+4,3+4,1+4,0+4] 0132反过来2310 +4  2^(3-1)
//所以每次递归找出前一层然后再反过来 +2^(n-1)即可  

public class GrayCode {
	public ArrayList<Integer> grayCode(int n) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (n == 0) {
			result.add(0);
			return result;
		}
		
		result.add(0);
		result.add(1);  // 如果n = 1，那就是0,1
		for (int i = 1; i < n; i++) {  // 还剩n-1个loop
			Integer a = 1 << i;  // 2, 4, 8, 16...
			int size = result.size() - 1;
			for (int k = size; k >= 0; k--) {  // 这里是反过来，所以是递减
				result.add(result.get(k) + a);  //每次递归找出前一层然后再反过来 +(n-1)^2即可  
			}
		}
		return result;
	}
}


// 01/09/15
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        ret.add(0);
        if(n == 0) return ret;
        ret.add(1);
        int tmp = 2;
        for(int i = 1; i < n; i++){
            int size = ret.size();
            for(int j = size-1; j >= 0; j--){
                ret.add(ret.get(j) + tmp);
            }
            tmp <<= 1;
        }
        return ret;
    }
}

// 03/22/15
// 记下ret当前size，然后从末尾开始，每取一个值再加上base放入ret
// 0 1 | 3 2
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        ret.add(0);
        if(n == 0) return ret; // 这题n = 0的时候也要返回一个0值
        int base = 1;
        for(int i = 1; i <= n; i++){
            int size = ret.size();
            for(int j = size-1; j >= 0; j--){
                int tmp = ret.get(j);
                ret.add(tmp+base);
            }
            base *= 2;
        }
        return ret;
    }
}