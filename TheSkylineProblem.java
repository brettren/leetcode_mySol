
// buildings[][]: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ]

// return [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]


// store the 2N edges in a sorted array. Maintain a max-heap of building heights while 
// scanning through the edge array: If the current edge is a left edge, then add the height 
// of its associated building to the max-heap; if the edge is a right one, remove the 
// associated height from the heap.

// It takes O(NlogN) time to sort the edge array. For each of the 2N edges, it takes O(1) 
// time to query the maximum height but O(logN) time to add or remove elements. 
// Overall, this solution takes O(NlogN) time.


// 一个sorted array记录edge，一个max heap记录当前index的max height
// 然后遍历array，如果是start就看是否大于heap里已有的height；如果是end就remove后看是否大于remove后的max height
public class Solution {

	class Edge {
		int x;
		int height;
		boolean isStart;
	 
		public Edge(int x, int height, boolean isStart) {
			this.x = x;
			this.height = height;
			this.isStart = isStart;
		}
	}

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> ret = new ArrayList<int[]>();
 
    	if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
    		return ret;
    	}
     
    	List<Edge> edges = new ArrayList<Edge>();
     
    	// add all left/right edges
    	for (int[] b: buildings) {
    		Edge startEdge = new Edge(b[0], b[2], true);
    		edges.add(startEdge);
    		Edge endEdge = new Edge(b[1], b[2], false);
    		edges.add(endEdge);
    	}
     
    	// sort edges 
    	Collections.sort(edges, new Comparator<Edge>() {
    		public int compare(Edge a, Edge b) {
    			if (a.x != b.x)
    				return Integer.compare(a.x, b.x);
     			
     			// 都是start，高的排在前面
    			if (a.isStart && b.isStart) {
    				return Integer.compare(b.height, a.height);
    			}
     			
     			// 都是end，低的排在前面
    			if (!a.isStart && !b.isStart) {
    				return Integer.compare(a.height, b.height);
    			}
     
    			return a.isStart ? -1 : 1;
    		}
    	});
     
    	// process edges
    	// create max heap
    	PriorityQueue<Integer> heap = new PriorityQueue<Integer>(10, Collections.reverseOrder());
     
    	for (Edge edge : edges) {
    		if (edge.isStart) {
    			if (heap.isEmpty() || edge.height > heap.peek()) {
    				ret.add(new int[] { edge.x, edge.height });
    			}
    			heap.add(edge.height);
    		} else {
    			heap.remove(edge.height);
     
    			if(heap.isEmpty()){
    				ret.add(new int[] {edge.x, 0});
    			}else if(edge.height > heap.peek()){
    				ret.add(new int[]{edge.x, heap.peek()});
    			}
    		}
    	}
     
    	return ret;
    }
}