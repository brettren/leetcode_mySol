
// Moore voting algo 只适用于找到有超过N/2出现次数的value
public class Solution {
    public int majorityElement(int[] num) {
        int l = num.length;
        if(l == 0) return -1;
        int count = 1;
        int vote = num[0];
        for(int i = 1; i < l; i++){
            if(count == 0) {
                vote = num[i];
                count++;
            }
            else if(vote == num[i]) {
                count++;
            }
            else{
                count--;
            }
        }
        return vote;
    }
}

// 03/22/15
// 如果是ret就count++
// 如果不是ret，那就看count为1就把ret换成新的num[i]，不是1就count--
// 因为如果有一个值超过n/2，那么其他的值加起来都不能让count减到0，就算中途改了ret，也一定会改回到这个majority值
public class Solution {
    public int majorityElement(int[] num) {
        int l = num.length;
        int ret = num[0];
        int count = 1;
        for(int i = 1; i < l; i++){
            if(num[i] == ret){
                count++;
            }
            else{
                if(count == 1){  // 当发现要变0就重置ret
                    ret = num[i];
                }
                else{
                    count--;
                }
            }
        }
        return ret;
    }
}