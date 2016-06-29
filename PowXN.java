

/** 
 * Implement pow(x, n) 
 */
	//二分法思想处理该问题	
	//利用指数运算规则 比如 2^3*2^5=2^8
    //同理 2^8=2^4*2^4;就1分2了 然后再2分4 这样就是不用1个1个乘8次 lgn复杂度即可
//注意n可以为负 eg:10^-2 是1/100
public class PowXN {
	public double pow(double x, int n) {
		if (n == 0)
			return 1;
		else if (n % 2 == 0) {  // 偶数
			double d = pow(x, n / 2);
			return d * d;
		} else if (n > 0) {  // 奇数
			double d = pow(x, (n - 1) / 2);  // 往0靠拢
			return d * d * x;
		} else {  //注意n可以为负
			double d = pow(x, (n + 1) / 2);  // 往0靠拢
			return d * d / x;
		}
	}
}

	
// update 01/02/15
public class Solution {
    public double pow(double x, int n) {
        if(n == 0){
            return 1;
        }
        if(n % 2 == 0){
            double d = pow(x, n/2);
            return d*d;
        }
        else if(n > 0){
            double d = pow(x, n/2);
            return d*d*x;
        }
        else{
            double d = pow(x, n/2);
            return d*d/x;
        }
    }
}

// 03/15/15
// 递归给n/2的程序
// 分情况，n是正和负，n是奇数偶数 3/2 = 1   -3/2 = -1
public class Solution {
    public double pow(double x, int n) {
        if(n == 0) return 1;
        double tmp = pow(x, n/2); // 为了避免重复递归，先把递归的结果得到
        if(n > 0){
            if(n%2 == 0){
                return tmp*tmp;
            }
            else{
                return tmp*tmp*x;
            }
        }
        else{
            if(n%2 == 0){
                return tmp*tmp;
            }
            else{
                return tmp*tmp/x;
            }
        }
    }
}