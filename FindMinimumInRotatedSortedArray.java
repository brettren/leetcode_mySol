
// Suppose a sorted array is rotated at some pivot unknown to you beforehand.

// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

// Find the minimum element.

// You may assume no duplicate exists in the array.

public class Solution {
	public int findMin(int[] num) {  
	    if(num == null || num.length==0)  
	        return 0;  
	    int l = 0;  
	    int r = num.length-1;  
	    int min = num[0];  
	    while(l<r-1) {   // 不断收缩l和r之间的距离
	        int m = (l+r)/2;  
	        if(num[l]<num[m]){ 
	        	min = Math.min(num[l],min);  
	            l = m+1;  // 原来的[l,m]是递增，可以不用管了
	        }  
	        else{	//(num[l]>num[m])  
	            min = Math.min(num[m],min);  
	            r = m-1;  // 原来的[m,r]是递增，可以不用管了
	        }  
	    } 
	    // 退出循环时 l = r-1
	    min = Math.min(num[r],min);  
	    min = Math.min(num[l],min);  
	    return min;  
	}  
}


// 01/13/15
public class Solution {
    public int findMin(int[] num) {
        int l = num.length;
        int s = 0, e = l-1;
        while(s < e){
            int mid = (s+e)/2;
            if(num[mid] < num[e]){
                e = mid;
            }
            else{
                s = mid+1;
            }
        }
        return num[s];
    }
}


// 05/30/15
public class Solution {
	public int findMin(int[] num) {  
		int l = num.length;
		if(l == 0) return 0;
		int ret = num[0];		
		int s = 0, e = l-1;
		while(s <= e){
			int mid = (s+e)/2;
			if(num[mid] >= num[0]){
				s = mid+1;
			}
			else{
				ret = Math.min(ret, num[mid]);
				e = mid-1;
			}
		}
		return ret;
	}
}