

/**
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 * 
 * Note: 
 * 
 * You are not suppose to use the library's sort function for this problem.
 * 
 * Follow up: 
 * A rather straight forward solution is a two-pass algorithm using counting sort. 
 * 
 * First, iterate the array counting number of 0's, 1's, and 2's,
 * then overwrite array with total number of 0's, then 1's and followed by 2's.
 * 
 * Could you come up with an one-pass algorithm using only constant space?
 */
//O(n)解法 三个指针 left i right
//3point 一次遍历可以搞定。0的指针从头开始 2的指针从尾巴开始   扫描到0就 curr和0指针的数换位置 然后0指针++ 
//扫描到2就就curr和2换位置 然后2--  如果等A[curr]=1 就curr++
// 0 1 0 2 0 1 2 1
public class SortColors {
	public void sortColors(int[] A) {
		int length = A.length;
		int left = -1;
		int right = length;
		int i = 0;
		while (i < right) {
			if (A[i] == 0) {
				swap(A, ++left, i++);  // left 指向最后一个0
			} else if (A[i] == 2) {
				swap(A, i, --right);  // right 指向最前一个2   
				//注意这里i不自增，因为swap过来的数可能是0，不能跳过，需要在下一个loop放到left去
			} else {
				i++;
			}
		}
	}

	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}


// 01/11/15
// 核心在于i遍历的时候，把0都放到左边去，把2都放到右边去，这样中间的肯定都是1了
public class Solution {
    public void sortColors(int[] A) {
        int l = A.length;
        if(l == 0) return;
        int left = 0; // left 指向 0 list的右边
        int right = l-1;  // right 指向 2 list的左边  
        int i = 0;
        while(i <= right){
            if(A[i] == 0){
                swap(A, i, left);
                left++;
                i++;
            }
            else if(A[i] == 2){
                swap(A, i, right);
                right--;
                // 注意这里swap之后新的A[i]不知道是什么数字，所以不能i++
            }
            else i++;
        }
    }
    
    private void swap(int[] A, int i, int j){
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}

// 03/22/15
// 三指针，如果中指针是0，那就和前指针swap，因为前指针和中指针之间必然是1
// 前指针一定是在0 sequence的末尾的后面
// 后指针必定在2 sequence的前端的前面
// 中指针必定在1 sequence的末尾的后面
public class Solution {
    public void sortColors(int[] A) {
        int l = A.length;
        if(l == 0 || l == 1) return;
        int s = 0;
        int tmp = 0;
        int e = l-1;
        while(tmp <= e){
            if(A[tmp] == 0){
                swap(A, s, tmp);
                s++;
                tmp++; // 因为tmp前面的都是已经sort过的，所以swap过来的肯定是1，所以直接++
            }
            else if(A[tmp] == 1) {
                tmp++;
            }
            else{
                swap(A, tmp, e);
                e--;
            }
        }
    }
    
    private void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}