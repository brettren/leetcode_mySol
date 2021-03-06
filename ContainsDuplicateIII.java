// sliding window

// use TreeSet to store values in window, use ceiling() and floor() to get the closest value
// nlgn
public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0 || k < 1) return false;
        TreeSet<Integer> set = new TreeSet();
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            if(set.floor(n) != null && n <= t + set.floor(n) || 
                    set.ceiling(n) != null && set.ceiling(n) <= t + n){
                return true;        
            }
            set.add(n);
            if(i >= k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }
}