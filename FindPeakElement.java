// A peak element is an element that is greater than its neighbors.

// Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

// The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

// You may imagine that num[-1] = num[n] = -∞.

// For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

// 01/13/15
public class Solution {
    public int findPeakElement(int[] num) {
        int l = num.length;
        if(l == 0) return -1;
        if(l == 1) return 0;
        int s = 0;
        int e = l-1;
        while(s < e){
            int mid = (s+e)/2;
            if(mid == 0){
                if(num[mid] > num[mid+1]) return mid;
                else s = mid+1;
            }
            else{  // 分四种情况分析
                if(num[mid] > num[mid+1] && num[mid] > num[mid-1]) return mid;
                else if(num[mid] > num[mid+1] && num[mid] < num[mid-1]){
                    e = mid-1;
                }
                else if(num[mid] < num[mid+1] && num[mid] < num[mid-1]){
                    e = mid-1;
                }
                else{
                    s = mid+1;
                }
            }
        }
        return s; // 因为必然有一个解，所以当 s = e的时候，就是在最后一位了
    }
}

// 03/22/15
// 因为要和mid两边的数比较，所以要考虑两种情况，mid在[0]和[l-1]，[l-1]可以被排除，所以需要判断[0]
// 必然有一个解
public class Solution {
    public int findPeakElement(int[] num) {
        int l = num.length;
        if(l == 0) return -1;
        if(l == 1) return 0;
        int s = 0, e = l-1;
        while(s < e){  // 当 s != e的时候mid不可能为e，可以避免mid+1超出bound 
            int mid = (s+e)/2;
            if(mid == 0){  // 要考虑到l == 2的情况， mid = (0+1)/2 = 0
                if(num[mid] > num[mid+1]) return mid;
                else return mid+1;
            } 
            else{  // 分四种情况，递增，递减，波谷，波峰
                if(num[mid] > num[mid+1] && num[mid] > num[mid-1]) return mid;
                else if(num[mid] > num[mid+1] && num[mid] < num[mid-1]) e = mid-1;
                else if(num[mid] < num[mid+1] && num[mid] > num[mid-1]) s = mid+1;
                else if(num[mid] < num[mid+1] && num[mid] < num[mid-1]) e = mid-1;
            }
        }
        return s; // 在[s,e]范围内必然有一个解，当s == e的时候就是这个解
    }
}