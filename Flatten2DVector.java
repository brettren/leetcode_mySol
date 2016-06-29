// Implement an iterator to flatten a 2d vector.

// For example,
// Given 2d vector =

// [
//   [1,2],
//   [3],
//   [4,5,6]
// ]
// By calling next repeatedly until hasNext returns false, 
// the order of elements returned by next should be: [1, 2, 3, 4, 5, 6].

public class Vector2D{
	private Iterator<List<Integer>> i;
    private Iterator<Integer> j;

	public Vector2D(List<List<Integer>> vec2d) {
		i = vec2d.iterator();
	}	

	public int next() {
		hasNext(); // jump to the next element
		return j.next();
	}

	public boolean hasNext() {
		while((j == null || !j.hasNext()) && i.hasNext()){  // in the initial OR at the end of list
			j = i.next().iterator();
		}
		return j != null && j.hasNext();
	}
}
