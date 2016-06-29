// There are a total of n courses you have to take, labeled from 0 to n - 1.

// Some courses may have prerequisites, for example to take course 0 you have 
// to first take course 1, which is expressed as a pair: [0,1]

// Given the total number of courses and a list of prerequisite pairs, return 
// the ordering of courses you should take to finish all courses.

// There may be multiple correct orders, you just need to return one of them. 
// If it is impossible to finish all courses, return an empty array.

// For example:

// 2, [[1,0]]
// There are a total of 2 courses to take. To take course 1 you should have 
// finished course 0. So the correct course order is [0,1]

// 4, [[1,0],[2,0],[3,1],[3,2]]
// There are a total of 4 courses to take. To take course 3 you should have 
// finished both courses 1 and 2. Both courses 1 and 2 should be taken after 
// you finished course 0. So one correct course order is [0,1,2,3]. 
// Another correct ordering is[0,2,1,3].

// Note:
// The input prerequisites is a graph represented by a list of edges, not adjacency matrices.



// DFS  add stack, the first element is the leaf node
// 因为这里标注visited，不会重复search，所以stack里存的也不会重复
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++){
        	adj.add(i, new ArrayList<>());
        }
        for (int[] p: prerequisites){ 
        	adj.get(p[1]).add(p[0]);  // add child
        }
        int[] visited = new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();
        // search from each node
        for (int i = 0; i < numCourses; i++) {
            if (!topologicalSort(adj, i, stack, visited)) 
            	return new int[0];
        }
        int i = 0;
        int[] result = new int[numCourses];
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();  // pop from begin course
        }
        return result;
    }

    private boolean topologicalSort(List<List<Integer>> adj, int v, Stack<Integer> stack, int[] visited) {
        if (visited[v] == 1) return true;
        if (visited[v] == -1) return false;
        visited[v] = -1;
        for (Integer u : adj.get(v)) {
            if (!topologicalSort(adj, u, stack, visited)) return false;
        }
        // no loop searching from v
        visited[v] = 1; // set v visited, all nodes from v are visited as well
        stack.push(v);
        return true;
    }
}




















