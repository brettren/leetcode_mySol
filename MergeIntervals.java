import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/** 
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */


/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

//题目给你的interval都是start小 end大
public class MergeIntervals {
	public class IntervalCmp implements Comparator<Interval> {
		@Override
		public int compare(Interval i1, Interval i2) {
			if (i1.start < i2.start) return -1;
			if (i1.start == i2.start && i1.end <= i2.end) return -1;
			if (i1.start == i2.start && i1.end == i2.end) return 0;
			return 1;
		}
		// 上面在leetcode通不过，可以用下面的
		// public int compare(Interval i1, Interval i2) {
		// 		return i1.start - i2.start;
		// }		
	}
	
	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		ArrayList<Interval> ret = new ArrayList<Interval>();
		if (intervals.size() == 0) return ret;
		Interval[] arr = new Interval[intervals.size()];
		intervals.toArray(arr);  // 把arraylist转为array,放入arr
		Arrays.sort(arr, new IntervalCmp());  // 按照各个interval的start sorting
		int start = arr[0].start;
		int end = arr[0].end;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].start <= end) {  // [1,3],[2,6]
				end = Math.max(end, arr[i].end);  // update new end
			} else {  // [2,6],[8,10] 
				ret.add(new Interval(start, end)); // 把[2,6]放入ret
				start = arr[i].start;
				end = arr[i].end;
			}
		}
		ret.add(new Interval(start, end));
		return ret;
	}
}






public class Solution {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
    	// inline的comparator
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		});
		
		ArrayList<Interval> result = new ArrayList<Interval>();
			for (int i = 0; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			while (i < intervals.size() - 1 && curr.end >= intervals.get(i + 1).start) {
				curr.end = Math.max(curr.end, intervals.get(i + 1).end);
				i++;
			}//while出来说明curr已经吃饱了，可以加入result了。
			result.add(curr);
		}
		return result;
    }
}



public class Solution {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
    	// inline的comparator
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		});
		
		ArrayList<Interval> ret = new ArrayList<Interval>();
		int l = intervals.size();
		if(l == 0) return intervals;
		int start = intervals.get(0).start;
		int end   = intervals.get(0).end;
		for (int i = 1; i < l; i++) {
			if (intervals.get(i).start <= end) {  // [1,3],[2,6]
				end = Math.max(end, intervals.get(i).end);  // update new end
			} else {  // [2,6],[8,10] 
				ret.add(new Interval(start, end)); // 把[2,6]放入ret
				start = intervals.get(i).start;
				end = intervals.get(i).end;
			}		
		}
		ret.add(new Interval(start, end));
		return ret;
    }
}


// 02/11/15
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ret = new ArrayList<Interval>();
        if(intervals == null || intervals.size() == 0) return ret;
        int l = intervals.size();
        Comparator<Interval> comp = new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2){
                if(i1.start < i2.start) return -1;
                else if(i1.start > i2.start) return 1;
                else return 0;
            }
        };
        Collections.sort(intervals, comp);
        
        int s = intervals.get(0).start, e = intervals.get(0).end;
        
        for(int i = 1; i < l; i++){
            int t1 = intervals.get(i).start;
            int t2 = intervals.get(i).end;
            if(t2 <= e) continue;
            if(t1 > e){
                ret.add(new Interval(s, e));
                s = t1;
                e = t2;
            }
            else{
                e = t2;
            }
        }
        ret.add(new Interval(s, e));
        return ret;
    }
}

// 03/10/15
// 先是根据start对intervals list排序，然后记下第一个的s和e，遍历每个interval
// 新建一个list来insert
// 如果e < i.start, 说明断层，可以插入新的interval；如果e > i.start, 说明有重叠，更新记下的e值，继续下一个loop
// 注意最后需要把记下的最后一段[s，e]插入结果
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ret = new ArrayList<>();
        if(intervals.size() == 0) return ret;
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                if(i1.start < i2.start) return -1;
                else if(i1.start > i2.start) return 1;
                else return 0;
            } 
        });
        int s = intervals.get(0).start;
        int e = intervals.get(0).end;
        for(Interval i: intervals){
            if(e < i.start){
                ret.add(new Interval(s, e));
                s = i.start;
                e = i.end;
            }
            else{
                e = Math.max(e, i.end);
            }
        }
        ret.add(new Interval(s, e));
        return ret;
    }
}