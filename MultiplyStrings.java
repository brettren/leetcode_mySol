
//Given two numbers represented as strings, return multiplication of the numbers as a string.
//                         任意的
//Note: The numbers can be arbitrarily large and are non-negative.  都是非负数

//本位 进位思想 比如385 * 97, 就是个位=5 * 7，十位=8 * 7 + 5 * 9 ，百位=3 * 7 + 8 * 9 …
//	可以每一位用一个Int表示，存在一个int[]里面。
//    385
//     97
	
	
	//	1 翻转string
//	2 建立数组，双层循环遍历两个string，把单位的乘积累加到数组相应的位置
//	3 处理进位并输出
//	4 注意前导零的corner case
//	
//  一般乘法    21×31
//   反转后  12
//           13
//  ---------------
//           36
//          12
// --------------------
//          156 
//  又 d[i+j]=d[i+j]+(num1.charAt(i)-'0')*(num2.charAt(j)-'0');
//      所以 d[1]=d[1][0]+d[0][1]=3×1+1×2=5
 // d[i]=正常乘法后第i本位之和  (没有进位)	

public class MultiplyStrings {
	public String multiply(String num1, String num2) {
		//反转后 个位在最前 便于计算 
		num1=new StringBuilder(num1).reverse().toString();
		num2=new StringBuilder(num2).reverse().toString();
		//even 99*99 is <10000, so 所以乘积的length肯定是 length1+length2就够用了;
		int len1 = num1.length();
		int len2 = num2.length();
		int[] d = new int[len1 + len2];
		for(int i=0;i<len1;i++){ // 从LSB开始
			for(int j=0;j<len2;j++){
				d[i+j]+=(num1.charAt(i)-'0')*(num2.charAt(j)-'0');
			}
		} 
				
		StringBuilder sb = new StringBuilder();  
		for(int i=0; i<d.length; i++){  
		    int digit = d[i]%10;        // 当前本位  
		    int carry = d[i]/10;        // 进位 .如果d[i]>0的话 会有进到前面位去 
		    if(i+1<d.length){  
		        d[i+1] += carry;  // 把carry加到高一位
		    }  
		    sb.insert(0, digit);        // 因为前面反转了 num1,2 所以 每次都把当本位  插入stringbuilder的第0位 然后越后面的插到最前面
		}  
				//到这里似乎完成了
		//但是比方说100×100我们前面准备了6位做len3 但是只用了10000 5位
		//所以会成为 010000 所以要去掉前面的0
		while(sb.charAt(0)=='0' && sb.length()>1){
			sb.deleteCharAt(0);
		}
		return sb.toString();		
	}
}


// 01/06/15
public class Solution {
    public String multiply(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        int l = l1+l2;
        int[] digit = new int[l];
        for(int i = l1-1; i >= 0; i--){
            int a = num1.charAt(i) - '0';
            for(int j = l2-1; j >= 0; j--){
                int b = num2.charAt(j) - '0';
                digit[i+j+1] = a*b + digit[i+j+1];
            }
        }
        
        StringBuffer sb = new StringBuffer();
        for(int i = l-1; i > 0; i--){
            int d = digit[i]%10;
            int carry = digit[i]/10;
            sb.insert(0, d);
            digit[i-1] += carry;
        }
        sb.insert(0, digit[0]);
        
        while(sb.charAt(0) == '0' && sb.length() > 1){   // 注意如果结果只是'0',那就不能删掉这个'0'
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}

// 03/09/15
// 第i位和第j位的乘积加到ret数组的第i+j+1位上
// 然后对每一位开始得到一个位的digit，把carry加到前面的高位
// 最后要注意去掉前面的0
public class Solution {
    public String multiply(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        int l = l1+l2;
        int[] ret = new int[l];
        for(int i = l1-1; i >= 0; i--){
            int a = num1.charAt(i) - '0';
            for(int j = l2-1; j >= 0; j--){
                int b = num2.charAt(j) - '0';
                ret[i+j+1] += a*b;
            }
        }
        StringBuffer sb = new StringBuffer();
        int carry = 0;
        for(int i = l-1; i >= 0; i--){
            ret[i] += carry;
            int digit = ret[i] % 10;
            carry = ret[i]/10;
            sb.insert(0, digit);
        }
        while(sb.charAt(0) == '0' && sb.length() > 1){
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}

// 用 BigInteger 非常方便的解决
import java.math.*;
public class Solution {
    public String multiply(String num1, String num2) {
        BigInteger n1 = new BigInteger(num1);
        BigInteger n2 = new BigInteger(num2);
        BigInteger ret = n1.multiply(n2);
        return ret.toString();
    }
}