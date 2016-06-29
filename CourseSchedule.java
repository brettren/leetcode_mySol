// There are a total of n courses you have to take, labeled from 0 to n - 1.

// Some courses may have prerequisites, for example to take course 0 you have 
// to first take course 1, which is expressed as a pair: [0,1]

// Given the total number of courses and a list of prerequisite pairs, is it 
// possible for you to finish all courses?

// For example:

// 2, [[1,0]]
// There are a total of 2 courses to take. To take course 1 you should have 
// finished course 0. So it is possible.

// 2, [[1,0],[0,1]]
// There are a total of 2 courses to take. To take course 1 you should have 
// finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

// Note:
// The input prerequisites is a graph represented by a list of edges, not adjacency matrices.

// Note: The input prerequisites is a graph represented by a list of edges

// 此问题等价于判断有向图中是否有环。如果存在环路，无法完成拓扑排序，也就不可能修完所有的课程
// Topological sorting 就是不能有dependency的问题来排序


// DFS search, 用visited来prune，减少重复search
public class Solution {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int l = prerequisites.length;
		if(l == 0) return true;

		// use the map to store what courses depend on a course
	    // create Graph, keep each node's child list  建立graph
		Map<Integer, List<Integer>> map = new HashMap<>();

		for(int[] p: prerequisites){
			List<Integer> tmp = null;
			if(map.containsKey(p[1])){
				tmp = map.get(p[1]);
				tmp.add(p[0]);
			}
			else{
				tmp = new ArrayList<>();
				tmp.add(p[0]);
				map.put(p[1], tmp);
			}
		}

		int[] visited = new int[numCourses];

		for(int i = 0; i < numCourses; i++){
			if(searchCycle(map, visited, i)){
				return false;
			}
		}
		return true;
	}

	public boolean searchCycle(Map<Integer, List<Integer>> map, int[] visited, int i){
		if(visited[i] == -1) return true;  // 又回到了当前path, find cycle
		if(visited[i] == 1) return false;  // 这个node已经被探索到底，继续探索下去也不会发现cycle
		if(!map.containsKey(i)) return false;

		visited[i] = -1;  // 表示当前path的标记
		List<Integer> child = map.get(i);
		for (Integer c: child) {
			if(searchCycle(map, visited, c)) return true;
		}
		visited[i] = 1; // 表示这个node继续探索下去也不会发现cycle
		return false;
	}
}





























