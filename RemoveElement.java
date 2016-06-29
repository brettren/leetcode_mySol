

/**
 * Given an array and a value, remove all instances of that value in place and
 * return the new length.
 * 
 * The order of elements can be changed. It doesn't matter what you leave beyond
 * the new length.
 */

public class RemoveElement {
	public int removeElement(int[] A, int elem) {
		if (A.length == 0)
			return 0;
		else {
			int p = 0;
			for (int i = 0; i < A.length; i++) {
				if (A[i] != elem) {
					A[p++] = A[i];
				}
			}
			return p;
		}
	}
}

// 01/04/15
// 双指针从首位端开始靠拢，把s指向的等于elem，就把右边e指向的移过来replace这个位置
public class Solution {
    public int removeElement(int[] A, int elem) {
        int l = A.length;
        if(l == 0) return 0;
        int s = 0;
        int e = l-1;
        while(s <= e){
            if(A[s] == elem){
                A[s] = A[e];
                e--;  // 注意只是e--，没有s++，因为在下一个loop还要检查A[s]
            }
            else{
                s++;
            }
        }
        return s;  // s表示下一个可以放入的index
    }
}

// 03/22/15
// 双指针遍历，如果等于elem就不放，如果等于就放入
public class Solution {
    public int removeElement(int[] A, int elem) {
        int l = A.length;
        if(l == 0) return 0;
        int s = 0, e = 0;
        while(e < l){
            if(A[e] == elem){
                e++;
            }
            else{
                A[s++] = A[e++];
            }
        }
        return s;
    }
}