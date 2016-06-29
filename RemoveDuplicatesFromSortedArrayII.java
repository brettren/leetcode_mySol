/**
 * Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
 * 
 * For example,
 * 
 * Given sorted array A = [1,1,1,2,2,3],
 * 
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 * 不仅要返回值，还要把A[]里多余的删了
 */
public class RemoveDuplicatesFromSortedArrayII {
	public int removeDuplicates(int[] A) {
		int length = A.length;
		if (length < 3)
			return length;
		int slow = 0, fast = 1, idx = 0;
		while (fast < length) {
			while (fast < length && A[fast] == A[slow]) {
				fast++;
			}
			if (fast - slow <= 2) {  // [slow,fast) 表示一段重复data的开头和末尾
				while (slow < fast) {
					A[idx++] = A[slow++];
				}
			} else {  // 多余2个重复的情况
				A[idx++] = A[slow++];
				A[idx++] = A[slow++];  // 最多只能有2个duplicate
				slow = fast;
			}
			fast++;
		}
		while (slow < length) {  // 这里slow可能是最后一个，也可能已经超出了length
			A[idx++] = A[slow++];
		}
		return idx;  //返回的已经是length
	}
}



// 01/10/15
public class Solution {
    public int removeDuplicates(int[] A) {
        int l = A.length;
        if(l <= 2) return l;
        int s = 0;
        int f = 1;
        int i = 0;
        for(; f < l; f++){
            while(f < l && A[f] == A[s]){
                f++;
            }
            if(f-s <= 2){
                while(s < f){
                    A[i++] = A[s++];
                }
            }
            else{
                A[i++] = A[s++];
                A[i++] = A[s++];
                s = f;
            }
        }
        while(s < l){
            A[i++] = A[s++];
        }
        return i;
    }
}


// 01/26/15
public class Solution {
    public int removeDuplicates(int[] A) {
        int l = A.length;
        if(l < 3) return l;
        int i = 0;
        int s = 0;
        int e = 1;  // 注意有3个pointer
        while(e < l){
            if(A[s] == A[e]){
                e++;
            }
            else{
                if(e - s <= 2){
                    while(s < e){
                        A[i++] = A[s++];
                    }
                }
                else{
                    A[i++] = A[s++];
                    A[i++] = A[s++];
                    s = e;
                }
            }
        }
        // 注意最后一段s到e的不确定有几个数
        if(e - s <= 2){
            while(s < e){
                A[i++] = A[s++];
            }
        }
        else{
            A[i++] = A[s++];
            A[i++] = A[s++];
        }
        return i;
    }
}


// 02/11/15
public class Solution {
    public int removeDuplicates(int[] A) {
        int l = A.length;
        if(l <= 2) return l;
        int s = 0, m = 0, e = 1;
        while(e < l){
            if(A[e] == A[e-1]){
                e++;
                continue;
            }
            else{
                int diff = e - m;
                diff = diff > 2 ? 2: diff;
                for(int i = 0; i < diff; i++){
                    A[s++] = A[m++];
                }
                m = e;
                e++;
            }
        }
        int diff = e - m;
        diff = diff > 2 ? 2: diff;
        for(int i = 0; i < diff; i++){
            A[s++] = A[m++];
        }
        return s;
    }
}

// 03/20/15
// 需要三个指针，i是下一个放的位置，[s,e)表示dup的区间
// 当A[s] != A[e]表示找到了dup的bound，根据e-s的length来判断是否大于2
// 如果大于2就只能放两个，否则就按照原length来放入i指的位置
public class Solution {
    public int removeDuplicates(int[] A) {
        int l = A.length;
        if(l <= 2) return l;
        int i = 0, s = 0, e = 1;
        while(e < l){
            if(A[s] == A[e]){
                e++;
            }
            else{
                int diff = e-s;
                diff = Math.min(diff, 2);
                while(diff > 0){
                    A[i++] = A[s++];
                    diff--;
                }
                s = e;
                e = s+1;
            }
        }
        // 检查末尾部分
        int diff = e-s;
        diff = Math.min(diff, 2);
        while(diff > 0){
            A[i++] = A[s++];
            diff--;
        }
        return i;
    }
}