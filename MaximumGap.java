// Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

// Try to solve it in linear time/space.

// Return 0 if the array contains less than 2 elements.

// You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

public class Solution {
	public int maximumGap(int[] num) {
	    if (num == null || num.length < 2)
	        return 0;
	    // get the max and min value of the array
	    int min = num[0];
	    int max = num[0];
	    for (int i:num) {
	        min = Math.min(min, i);
	        max = Math.max(max, i);
	    }
	    // the minimum possibale gap, ceiling of the integer division
	    int gap = (int)Math.ceil((double)(max - min)/(num.length - 1));
	    int[] bucketsMIN = new int[num.length - 1]; // store the min value in that bucket
	    int[] bucketsMAX = new int[num.length - 1]; // store the max value in that bucket
	    Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
	    Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
	    // put numbers into buckets
	    for (int i:num) {
	        if (i == min || i == max)
	            continue;
	        int idx = (i - min) / gap; // index of the right position in the buckets
	        bucketsMIN[idx] = Math.min(i, bucketsMIN[idx]);
	        bucketsMAX[idx] = Math.max(i, bucketsMAX[idx]);
	    }
	    // scan the buckets for the max gap
	    int maxGap = Integer.MIN_VALUE;
	    int previous = min;
	    for (int i = 0; i < num.length - 1; i++) {
	        if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
	            // empty bucket
	            continue;
	        // min value minus the previous value is the current gap
	        maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
	        // update previous bucket value
	        previous = bucketsMAX[i];
	    }
	    maxGap = Math.max(maxGap, max - previous); // updata the final max value gap
	    return maxGap;
	}
}

// 02/06/15
public class Solution {
    public int maximumGap(int[] num) {
        int l = num.length;
        if(l < 2) return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int t: num){
            min = Math.min(min, t);
            max = Math.max(max, t);
        }
        int gap = (int)Math.ceil((double)(max-min)/(l-1));  // 注意是得到ceiling，先double，再转为int
        int[] minset = new int[l-1];
        int[] maxset = new int[l-1];
        Arrays.fill(minset, Integer.MAX_VALUE); // 在min放入max
        Arrays.fill(maxset, Integer.MIN_VALUE);
        for(int t: num){
            if(t == min || t == max) continue;  // 如果已经是min或max就跳过
            int idx = (t-min)/gap;
            minset[idx] = Math.min(t, minset[idx]);
            maxset[idx] = Math.max(t, maxset[idx]);
        }
        int ret = 0;
        int prev = min;  // 起始点在min
        for(int i = 0; i < l-1; i++){
            if(minset[i] == Integer.MAX_VALUE && maxset[i] == Integer.MIN_VALUE){
                continue;
            }
            ret = Math.max(ret, minset[i]-prev);
            prev = maxset[i];
        }
        ret = Math.max(ret, max-prev);
        return ret;
    }
}

// 03/12/15
// 用bucket sorting，如果每个值之间的gap都相等，那就是mingap，所以一个bucket的length就是这个mingap
// 用max和min确定区间，分割bucket，要注意是用 Math.ceil((double)(max-min)/(l-1)); 
// 用（num[i]-min）/dist来找到对应所属的bucket，如果是max就跳过，不然会超过idx的界限
// 最后遍历每个bucket，用cur.min-prev.max来更新ret max gap
public class Solution {
    public int maximumGap(int[] num) {
        int l = num.length;
        if(l < 2) return 0;
        int max = num[0];
        int min = num[0];
        for(int i = 1; i < l; i++){
            max = Math.max(max, num[i]);
            min = Math.min(min, num[i]);
        }
        int dist = (int)Math.ceil((double)(max-min)/(l-1));
        int[] bucketMax = new int[l-1];
        int[] bucketMin = new int[l-1];
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        for(int i = 0; i < l; i++){
            if(num[i] == min || num[i] == max) continue; // 如果是max就跳过，不然会超过idx的界限
            int idx = (num[i]-min)/dist;
            bucketMax[idx] = Math.max(bucketMax[idx], num[i]);
            bucketMin[idx] = Math.min(bucketMin[idx], num[i]); // 只要bucket内有值，那么这个bucket的max和min一定都是有效值
        }
        int prev = min;
        int ret = 0;
        for(int i = 0; i < l-1; i++){
            if(bucketMax[i] == Integer.MIN_VALUE) continue; // 说明bucket为空
            ret = Math.max(ret, bucketMin[i]-prev);
            prev = bucketMax[i];
        }
        ret = Math.max(ret, max-prev);
        return ret;
    }
}