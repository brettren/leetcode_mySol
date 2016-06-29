// Given an array of meeting time intervals consisting of start and end times 
// [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

// For example,

// Given [[0, 30],[5, 10],[15, 20]],
// return false.

public class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
    	Arrays.sort(intervals, new Comparator<Interval>(){
    		public int compare(Interval i1, Interval i2){
    			return Integer.compare(i1.start, i2.start);
    		}
    	});
    	for(int i = 0; i < intervals.length-1; i++){
    		Interval i1 = intervals[i];
    		Interval i2 = intervals[i+1];
    		if(i1.end > i2.start) return false;
    	}
    	return true;
    }
}