// Follow up for "Find Minimum in Rotated Sorted Array":
// What if duplicates are allowed?

// Would this affect the run-time complexity? How and why?
// Suppose a sorted array is rotated at some pivot unknown to you beforehand.

// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

// Find the minimum element.

// The array may contain duplicates.

public class Solution {
	public int findMin(int[] num) {  
	    if(num == null || num.length==0)  
	        return 0;  
	    int l = 0;  
	    int r = num.length-1;  
	    int min = num[0];  
	    while(l<r-1) {  
	        int m = (l+r)/2;  
	        if(num[l]<num[m]){ 
	        	min = Math.min(num[l],min);  
	            l = m+1;  // 原来的[l,m]是递增，可以不用管了
	        }  
	        else if(num[l]>num[m]){
	            min = Math.min(num[m],min);  
	            r = m-1;  // 原来的[m,r]是递增，可以不用管了
	        }  
		    else{  //num[l]==num[m]
	            l++;  //最坏情况就会出现每次移动一步，总共移动n此，算法的时间复杂度变成O(n)
	        }
	    }  
	    min = Math.min(num[r],min);  
	    min = Math.min(num[l],min);  
	    return min;  
	}  
}


// 01/13/15
public class Solution {
    public int findMin(int[] num) {
        int l = num.length;
        if(l == 0) return -1;
        if(num[0] < num[l-1]) return num[0];
        int s = 0;
        int e = l-1;
        while(s < e){
            int mid = (s+e)/2;
            if(num[mid] < num[e]){
                e = mid;
            }
            else if(num[mid] > num[e]){
                s = mid+1;
            }
            else{
                e--;
            }
        }
        return num[s];
    }
}

// 01/26/15
public class Solution {
    public int findMin(int[] num) {
        int l = num.length;
        if(l == 0) return -1;
        int s = 0;
        int e = l-1;
        while(s < e){
            int mid = (s+e)/2;
            if(num[s] != num[e]){
                if(num[e] < num[mid]){
                    s = mid+1;
                }
                else{
                    e = mid;
                }
            }
            else{
                break;
            }
        }
        int ret = num[s];
        for(int i = s+1; i <= e; i++){
            ret = Math.min(ret, num[i]);
        }
        return ret;
    }
}