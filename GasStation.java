
/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
 * You begin the journey with an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * 
 * Note:
 * 
 * The solution is guaranteed to be unique.
 *
 */
// 
public class GasStation {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int sum = 0, total = 0, len = gas.length, index = 0;
		for (int i = 0; i < len; i++) {
			sum += gas[i] - cost[i];
			total += gas[i] - cost[i];
			if (sum < 0) {  //说明从i开到i+1的路上没油了
				index = i + 1; // 新的起点，尝试以i+1为起点
				sum = 0;
		//每次不够油就更新index,所以跑完for循环后的index+1是最后一次没油的地方
	 
			}
		}
		//只要total>=0，就一定能找到一个start，可以设p[i] = gas[i] - cost[i],sum of p[i] > =0
		return total >= 0 ? index : -1;
	}
}

// 01/29/15
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
        int tmp = 0;
        int start = 0;
        int l = gas.length;
        for(int i = 0; i < l; i++){
            tmp += gas[i]-cost[i];
            total += gas[i]-cost[i];
            if(tmp < 0) {
                tmp = 0;
                start = i+1;
            }
        }
        if(total < 0) return -1;
        return start;
    }
}

// 用反证法，首先我们已经知道必然有一个解，而这个数之前的都不可能是解（否则就不可能出现负值），而此数是接下来最大序列的开端，
//也就是任何从他之后开始的数列不可能累积出更多的gas，既然答案不在之前也不在之后，就只可能是该点了

// 也可以用排除法来理解 
//如果只有一个解能满足过程中sum >= 0, 如果到某个时候是负值，说明前面的都不可能是start了，排除掉，尝试新的start，如果后面能够顺利走完，那就是这个start了
// sum[0,start-1]一定是 < 0, 如果total >= 0, 那sum[start, l) > 0
// 03/13/15
// 统计total剩下的值，可以知道是否有解
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int l = gas.length;
        int sum = 0;
        int total = 0;
        int start = 0;
        for(int i = 0; i < l; i++){
            total += gas[i] - cost[i];
            sum += gas[i] - cost[i];
            if(sum < 0){
                sum = 0;
                start = i+1;
            }
        }
        if(total < 0) return -1;
        return start;
    }
}