/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 *
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 *
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
//其实本质是 如果不需要merge 就直接插入，如果需要融合就融合到底
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class InsertInterval {
	public class IntervalCmp implements Comparator<Interval> {
		@Override
		public int compare(Interval i1, Interval i2) {
			return i1.start - i2.start;
		}
	}

	public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
		intervals.add(newInterval);  // 只是加了这一步
		Interval[] arr = new Interval[intervals.size()];
		intervals.toArray(arr);
		Arrays.sort(arr, new IntervalCmp());
		intervals.clear(); // 重置arraylist
		
		int start = arr[0].start;
		int end = arr[0].end;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].start <= end) { // [1,3],[2,6]
				end = Math.max(end, arr[i].end);
			} else {  // [2,6],[8,10] 
				intervals.add(new Interval(start, end));
				start = arr[i].start;
				end = arr[i].end;
			}
		}
		intervals.add(new Interval(start, end));
		return intervals;
	}
}


// 03/09/15
// 分三种情况，new在tmp后面，new在tmp前面，或两个重叠需要得到重叠的最大newInterval，remove原来的那个interval
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if(newInterval == null) return intervals;
        int s = newInterval.start;
        int e = newInterval.end;
        ListIterator<Interval> it = intervals.listIterator(); 
        while(it.hasNext()){
            Interval cur = it.next();
            if(cur.start > e){
                it.previous();
                it.add(new Interval(s, e));
                return intervals;
            }
            else if(cur.end < s){
                continue;
            }
            else{
                s = Math.min(s, cur.start);
                e = Math.max(e, cur.end);
                it.remove();
            }      
        }
        it.add(new Interval(s, e));
        return intervals;
    }
}
}  