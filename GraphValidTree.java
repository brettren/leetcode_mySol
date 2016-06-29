
// Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
// write a function to check whether these edges make up a valid tree.

// For example:

// Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

// Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

// Hint:

// Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
// According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two 
// vertices are connected by exactly one path. In other words, any connected graph without simple cycles 
// is a tree.”
// Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
// [0, 1] is the same as [1, 0] and thus will not appear together inedges.




// choose 0 as start node to DFS, if no cycle and all nodes are visited, return true;
// 因为是undirected，所以一个edge加两个directed edge到map

public class Solution {
    public boolean validTree(int n, int[][] edges) {
    	Map<Integer, List<Integer>> map = new HashMap<>();
    	for(int[] edge: edges){
    		if(!map.containsKey(edge[0])){
    			map.put(edge[0], new ArrayList<>());
    		}
    		map.get(edge[0]).add(edge[1]);

    		if(!map.containsKey(edge[1])){
    			map.put(edge[1], new ArrayList<>());
    		}
    		map.get(edge[1]).add(edge[0]);
    	}
    	boolean[] onpath = new boolean[n];
    	boolean[] visited = new boolean[n];
    	if(hasCycle(map, 0, -1, onpath, visited)) return false;
    	for(boolean v: visited){
    		if(v == false) return false;  // if there's disjointed node, return false
    	}
    	return true;
    }

    // if the path go through cur node find cycle, return true
    // @para onpath   mark search path, if all paths from cur are searched, mark cur node as false
    public boolean hasCycle(Map<Integer, List<Integer>> map, int cur, int parent, boolean[] onpath, boolean[] visited){
    	if(onpath[cur] == true) return true;
    	visited[cur] = true;
    	onpath[cur] = true;
    	for(int nei: map.get(cur)){
    		if(nei != parent && hasCycle(map, nei, cur, onpath, visited)){
    			return true;
    		}
    	}
    	onpath[cur] = false;
    	return false;
    }
}






