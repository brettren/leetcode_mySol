

/**
 * There are two sorted arrays A and B of size m and n respectively. Find the
 * median of the two sorted arrays. The overall run time complexity should be
 * O(log (m+n)).
 */

// 中位数实际上是第(m+n)/2小的数。(m+n)/2 = k，所以只要解决了第k小数的问题，原问题也得以解决(这道题的核心目标！！！！！！！)
// 注意m和n不一样！！

// 首先假设数组A和B的元素个数都大于k/2，我们比较A[k/2-1]和B[k/2-1]两个元素，这两个元素分别表示A的第k/2小的元素和B的第k/2小的元素。
// 这两个元素比较共有三种情况：>、<和=。如果A[k/2-1]<B[k/2-1]，这表示A[0]到A[k/2-1]的元素都在A和B合并之后的前k小的元素中。
// 换句话说，A[k/2-1]不可能大于两数组合并之后的第k小值，所以我们可以将其抛弃。

// 因为A里前k/2-1个数的确小于A[k/2-1]，但是不能保证B里的前k/2-1个数小于A[k/2-1]，就算可以，两个部分加起来等于k-2个数，
// 所以A[k/2-1]不可能是median

// 查看两个数组的第k/2大的数(假设是A[k/2],B[k/2])，如果两个A[k/2]=B[k/2]，说明当前这个数即为两个数组剩余元素的第k大的数，
// 如果A[k/2]>B[k/2], 那么说明B的前k/2个元素都不是我们要的第k大的数，反之则排除A的前k/2个，如此每次可以排除k/2个元素，
// 最终k=1时即为结果。总的时间复杂度为O(logk),空间复杂度也是O(logk)，即为递归栈大小。
public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
		int k = m + n;
		if (k % 2 != 0) {
			return findKth(A, 0, B, 0, k / 2 + 1); // 奇数，median的index是k / 2 + 1
		} else {
			return (findKth(A, 0, B, 0, k / 2) + findKth(A, 0, B, 0, k / 2 + 1)) / 2.0; 
			 // 找到第k/2, 和第k/2 +1位，偶数取中间两位的average
		}
	}

	private double findKth(int A[], int a, int B[], int b, int k) {  // 表示要在A[a, length-1]和B[b,length-1]的范围内找到第k小的数
		//always assume that A.length - a is equal or smaller than B.length - b 
		if (A.length - a > B.length - b) {  // A[]剩下的更多
			return findKth(B, b, A, a, k);
		}
		if (a >= A.length) { //如果A已经全部排除，则直接查找B[]
			return B[b + k - 1];
		}
		if (k == 1) {  //如果k为1，我们只需要返回A[]和B[]中的较小值
			return Math.min(A[a], B[b]);
		}

		// divide k into two parts  
		// 比如A和B的length是3和5，这里的k是(3+5)/2=4,midA=k/2=2; 如果length分别是1和7，k=4，midA = A.length - a = 1
		int midA = Math.min(k / 2, A.length - a); // 取min，否则a+midA就超过A.length
		int midB = k - midA;  // midA + midB = k ！！！！
		if (A[a + midA - 1] < B[b + midB - 1]) {
			// 因为已经确定了前midA个[a, a + midA - 1]这些都必定小于median，可以扔掉，然后在sub problem里找的是第k-midA小的数
			return findKth(A, a + midA, B, b, k - midA);  
		} else if (A[a + midA - 1] > B[b + midB - 1]) {
			return findKth(A, a, B, b + midB, k - midB);
		} else {  //如果A[k/2-1]=B[k/2-1]，那就是这个数
			return A[a + midA - 1];
		}
	}
}

// 01/06/15
public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        int Al = A.length;
        int Bl = B.length;
        int l = Al + Bl;
        
        if(l % 2 == 0) return (helper(A, 0, B, 0, l/2) + helper(A, 0, B, 0, l/2+1))/2.0;
        else return helper(A, 0, B, 0, l/2+1);
    }
    
    private double helper(int A[], int as, int B[], int bs, int l){
        if(as >= A.length) return B[bs+l-1];
        if(bs >= B.length) return A[as+l-1];
        
        if(l == 1) return Math.min(A[as], B[bs]);
        
        int A_tmp = (as+l/2-1 < A.length) ? A[as+l/2-1] : Integer.MAX_VALUE;
        int B_tmp = (bs+l/2-1 < B.length) ? B[bs+l/2-1] : Integer.MAX_VALUE;
        
        if(A_tmp < B_tmp) return helper(A, as+l/2, B, bs, l-l/2);
        else return helper(A, as, B, bs+l/2, l-l/2);
    }
}

// 03/06/15

// 用反证法就可以知道kth不可能存在的区域，不断排除掉
public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        int la = A.length;
        int lb = B.length;
        int l = la + lb;
        if(l % 2 == 0) return (helper(A, 0, B, 0, l/2) + helper(A, 0, B, 0, l/2+1))/2.0;
        else return helper(A, 0, B, 0, l/2+1);  // 转化为找第kth数
    }
    
    public double helper(int A[], int as, int B[], int bs, int n){
        int la = A.length;
        int lb = B.length;
        if(as >= la) return B[bs+n-1];
        if(bs >= lb) return A[as+n-1];
        if(n == 1) return Math.min(A[as], B[bs]);
        int ta = (as+n/2-1 < la) ? A[as+n/2-1] : Integer.MAX_VALUE;
        int tb = (bs+n/2-1 < lb) ? B[bs+n/2-1] : Integer.MAX_VALUE;
        if(ta < tb) return helper(A, as+n/2, B, bs, n-n/2);  // 砍掉 n/2 个数
        // 因为已经确定了前midA个[a, a + midA - 1]这些都必定小于median，可以扔掉，然后在sub problem里找的是第k-midA小的数
        // 因为第kth数一定是两个k/2区域加起来最大的那个值，所以[s+k/2]小的那个肯定不是median，也就是不可能是kth数
        else return helper(A, as, B, bs+n/2, n-n/2);
    }
}
