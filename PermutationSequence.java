/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order, We get the
 * following sequence (ie, for n = 3):
 * 
 * "123" "132" "213" "231" "312" "321" Given n and k, return the kth permutation
 * sequence.
 * 
 * Note: Given n will be between 1 and 9 inclusive.
 * 
 */
//第一位每个数字开头的序列都有（n-1）！个序列，因此n个数字所以共有n*((n-1)!)个序列。
//选取第一个数后剩下全排列的个数为(n-1)! 所以选取的第一个数应该为第
//  K1 = k；
// a1 = K1/(n-1)!位数字
// 同理当选完a1后只剩下n-1个数字，在确定第二个数应该选择哪个.

//以此类推，第二位每一个数开头都有（n-2）！个序列。
//每次循环找到没使用过的数中第k/data[i]个数就是当前位的数字。


// n = 4   k = 12    selected = (12-1)/(4-1)! = 1   num[selected] = 2    0...n-1 / n = 0
// 1234 1243 ...  6
// 2134 .... ...  6
// 3124 .... ...  6
// 4123 .... ...  6

public class PermutationSequence {
    public String getPermutation(int n, int k) {  
		int[] num = new int[n]; 
		int perm_sum = 1;  
		for(int i = 0; i < n; ++i) {  
			num[i] = i + 1;  // 1,2,3,4,5...,n
			perm_sum *= (i + 1);   // 1*2*3...*n = n!  一共有n！个permutation
		}  
		StringBuffer ret = new StringBuffer();  
		//因为数组是从0到n-1的 所以基数从 0到k-1  
		--k;  
		for(int i = 0; i < n; ++i) {  
			perm_sum = perm_sum / (n - i);   // n!/n = (n-1)!
			int selected = k / perm_sum;  //  k/(n-1)!  
			ret.append((char)(num[selected] + '0'));  
			//选择一个数后重新构造剩下的数组  
			for(int j = selected; j < n - i - 1; ++j)  
				num[j] = num[j + 1];  // 左移   123 -> 13  相当于取走2,  右边的都左移一位，因为2已经被放入ret了。
			// 相当于在剩下可选的数字里挑一个
			//注意这里num[]内一直是ascending
			k = k % perm_sum;  // k%(n-1)!
		}  
		return ret.toString();  
    }  
}


// 递归会超时
public class Solution {
    public String getPermutation(int n, int k) {
        ArrayList<String> ret = new ArrayList<String>();
        int[] num = new int[n];
        for(int i = 1; i <= n; i++){
            num[i-1] = i;
        }
        helper(ret, num, n, 0);
        return ret.get(k-1);
    }
    
    public void helper(ArrayList<String> ret, int[] num, int n, int idx){
        if(idx >= n){
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < n; i++){
                sb.append((char)num[i]);
            }
            ret.add(sb.toString());
            return;
        }
        for(int i = idx; i < n; i++){
            swap(num, i, idx);
            helper(ret, num, n, idx+1);
            swap(num, i, idx);
        }
    }
    
    public void swap(int[] num, int i, int idx){
        int t = num[i];
        num[i] = num[idx];
        num[idx] = t;
    }
}



// 01/16/15
public class Solution {
    public String getPermutation(int n, int k) {
        int[] num = new int[n];
        int t = 1;
        for(int i = 0; i < n; i++){
            num[i] = i+1;
            t *= i+1;
        }
        StringBuffer sb = new StringBuffer();
        k--;
        for(int i = 0; i < n; i++){
            t /= n-i;
            int digit = k/t;
            sb.append((char)(num[digit] + '0'));  // 注意这里是num[digit]，是在num[]剩下的数字里找
            for(int j = digit; j < n-i-1; j++){
                num[j] = num[j+1];
            }
            k %= t;
        }
        return sb.toString();
    }
}

// 03/12/15
// 举个栗子方便理解，n = 4, k = 8; 
// 1234 1243 ...  6
// 2134 .... ...  6
// 3124 .... ...  6
// 4123 .... ...  6
// list初始化为 1,2,3,4
// idx = (8-1)/3*2*1 = 1  (list.get(1) == 2)
// 记住选取一个digit后，需要从list里remove
// 经过4个loop取完4个digit
public class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList<>();
        int product = 1;
        for(int i = 1; i <= n; i++){
            list.add(i);
            product *= i;
        }
        StringBuffer sb = new StringBuffer();
        k--;
        product /= n;
        while(true){
            int idx = k/product;
            k %= product;
            sb.append((char)(list.get(idx)+'0'));
            list.remove(idx);
            n--;
            if(n == 0) break;
            product /= n;
        }
        return sb.toString();
    }
}