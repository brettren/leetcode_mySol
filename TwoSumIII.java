// Design and implement a TwoSum class. It should support the following operations:add and find.

// add - Add the number to an internal data structure.
// find - Find if there exists any pair of numbers which sum is equal to the value.

public class TwoSum {

	private Map<Integer, Integer> map;

	public TwoSum(){
		map = new HashMap<Integer, Integer>;
	}

	public void add(int d){
		if(map.contains(d)){
			map.add(d, map.get(d)+1);
		}
		else{
			map.add(d, 1);
		}
	}

	public boolean find(int sum){
		for(int t: map.keySet()){
			int d = sum-t;
			if(d == t && map.get(d) > 1){   // 这里要小心，如果两个数相等，就要看hash是否有两个同样地值
				return true;
			}
			else if(d != t && map.contains(d)){
				return true;
			}
		}
		return false;
	}

}




// 03/12/15
// 这里用map来记录每个值出现的次数
// 如果sum-i == i，那就要看是否出现2次或以上能够成对，否则就直接看map是否有sum-i
public class TwoSum {
	Map<Integer, Integer> map = new HashMap<>();
	public void add(int d){
		if (map.containsKey(d)) {
			map.put(d, map.get(d)+1);
		}
		else{
			map.put(d, 1);
		}
	}

	public boolean find(int sum){
		for (int i: map.keySet()) {
			int tmp = sum-i;
			if (i == tmp && map.get(i) > 1) {
				return true;
			}
			if(i != tmp && map.contains(tmp) > 0){
				return true;
			}
		}
		return false;
	}
}