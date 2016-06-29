//Rotate an array of n elements to the right by k steps.

// For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

// Could you do it in-place with O(1) extra space?

// 可以先全部翻转，然后pivot两边各自翻转
public class Solution {
    public void rotate(int[] nums, int k) {
        int l = nums.length;
        k %= l; // 注意求模
        if(k == 0) return;
        reverse(nums, 0, l-k-1);
        reverse(nums, l-k, l-1);
        reverse(nums, 0, l-1);
    }
    
    public void reverse(int[] nums, int s, int e){
        while(s < e){
            int tmp = nums[s];
            nums[s] = nums[e];
            nums[e] = tmp;
            s++;
            e--;
        }
    }
}