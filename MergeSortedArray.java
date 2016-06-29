/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * 
 * Note: 
 * 
 * You may assume that A has enough space to hold additional elements from B. 
 * The number of elements initialized in A and B are m and n respectively.
 */

public class MergeSortedArray {
	public void merge(int A[], int m, int B[], int n) {
		int i = m - 1, j = n - 1;
		int k = m + n - 1;
		while (i >= 0 && j >= 0) {
			A[k--] = A[i] >= B[j] ? A[i--] : B[j--];
		}
		while (j >= 0) {  // 这里只需要考虑到B还有剩下的情况，如果A还有剩下的，本来就在A[]里了
			A[k--] = B[j--];
		}
	}
}

// update 01/02/15

public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        int i = m + n - 1;
        while(m > 0 && n > 0){
            if(A[m-1] < B[n-1]){
                A[i] = B[n-1];
                i--;
                n--;
            }
            else{
                A[i] = A[m-1];
                i--;
                m--;
            }
        }
        
        while(n > 0){
                A[i] = B[n-1];
                i--;
                n--; 
        }
    }
}


// 03/20/15
// 从m+n-1开始，比较A[m]和B[n]大小逐个放入A[]中merge，如果A还有剩下那就不用管了，如果还有B剩下那就全部放入剩下的slot
// 放入的部分是已经排好序的，不会覆盖A中还没有提取的部分
public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        int l = m+n;
        if(l == 0) return;
        l--;
        m--;
        n--;
        while(m >= 0 && n >= 0){
            if(A[m] > B[n]){
                A[l--] = A[m--];
            }
            else{
                A[l--] = B[n--];
            }
        }
        while(n >= 0){
            A[l--] = B[n--];
        }
    }
}