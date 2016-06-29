/**
 * The set [1,2,3,��,n] contains a total of n! unique permutations.
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
//��һλÿ�����ֿ�ͷ�����ж��У�n-1���������У����n���������Թ���n*((n-1)!)�����С�
//ѡȡ��һ������ʣ��ȫ���еĸ���Ϊ(n-1)! ����ѡȡ�ĵ�һ����Ӧ��Ϊ��
//  K1 = k��
// a1 = K1/(n-1)!λ����
// ͬ��ѡ��a1��ֻʣ��n-1�����֣���ȷ���ڶ�����Ӧ��ѡ���ĸ�.

//�Դ����ƣ��ڶ�λÿһ������ͷ���У�n-2���������С�
//ÿ��ѭ���ҵ�ûʹ�ù������е�k/data[i]�������ǵ�ǰλ�����֡�


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
			perm_sum *= (i + 1);   // 1*2*3...*n = n!  һ����n����permutation
		}  
		StringBuffer ret = new StringBuffer();  
		//��Ϊ�����Ǵ�0��n-1�� ���Ի����� 0��k-1  
		--k;  
		for(int i = 0; i < n; ++i) {  
			perm_sum = perm_sum / (n - i);   // n!/n = (n-1)!
			int selected = k / perm_sum;  //  k/(n-1)!  
			ret.append((char)(num[selected] + '0'));  
			//ѡ��һ���������¹���ʣ�µ�����  
			for(int j = selected; j < n - i - 1; ++j)  
				num[j] = num[j + 1];  // ����   123 -> 13  �൱��ȡ��2,  �ұߵĶ�����һλ����Ϊ2�Ѿ�������ret�ˡ�
			// �൱����ʣ�¿�ѡ����������һ��
			//ע������num[]��һֱ��ascending
			k = k % perm_sum;  // k%(n-1)!
		}  
		return ret.toString();  
    }  
}


// �ݹ�ᳬʱ
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
            sb.append((char)(num[digit] + '0'));  // ע��������num[digit]������num[]ʣ�µ���������
            for(int j = digit; j < n-i-1; j++){
                num[j] = num[j+1];
            }
            k %= t;
        }
        return sb.toString();
    }
}

// 03/12/15
// �ٸ����ӷ�����⣬n = 4, k = 8; 
// 1234 1243 ...  6
// 2134 .... ...  6
// 3124 .... ...  6
// 4123 .... ...  6
// list��ʼ��Ϊ 1,2,3,4
// idx = (8-1)/3*2*1 = 1  (list.get(1) == 2)
// ��סѡȡһ��digit����Ҫ��list��remove
// ����4��loopȡ��4��digit
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