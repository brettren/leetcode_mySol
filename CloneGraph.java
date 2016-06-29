/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * 
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * 
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * 
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 *
 *      1
 *     / \
 *    /   \
 *   0 --- 2
 *        / \
 *        \_/
 */
//0的neighbour是 1,2,| 1是2,|2是2 重复的前面已经连起来了，就不记录了

//	1.因为label是唯一的 所以建一个hashmap<label,Node> 然后递归调用方法 cloneGraphHelper(node, map).
//	在里面根据node的值newNode了 之后 再遍历老node的邻居们,如果在map里有就加到newNode的 arraylist neighbor里，
//	如果没有 就递归调用本方法  newnode.neighbors.add(cloneGraphHelper(point,map));


// /**
//  * Definition for undirected graph.
//  * class UndirectedGraphNode {
//  *     int label;
//  *     List<UndirectedGraphNode> neighbors;
//  *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
//  * };
//  */
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null)
			return node;
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();  // 这里用的是bfs
		queue.add(node);
		Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();// <original, clone> 
		//这里的map相当于mirroring，key是original node，value是新建的clone node，一一对应
		//这里map也当做判断是否visited的功能     
		map.put(node, new UndirectedGraphNode(node.label));
		while (!queue.isEmpty()) {
			UndirectedGraphNode n = queue.remove();	// 从queue里取出的，都是有clone，但是还没有探索过neighbor的		
			UndirectedGraphNode clone = map.get(n); // 得到这个node的clone	
			for (UndirectedGraphNode child : n.neighbors) {  // 遍历original node的每一个neighbor			
				UndirectedGraphNode cloneChild;
				if (!map.containsKey(child)) {  // 看当前child是否已经有了clone
					queue.add(child);  // 说明这个child还没有探索过自己的neighbor，把它放入queue作待处理
					cloneChild = new UndirectedGraphNode(child.label);
					map.put(child, cloneChild);					
				} else {
					cloneChild = map.get(child);
				}
				clone.neighbors.add(cloneChild);
			}
		}
		return map.get(node);
	}
}

// 01/12/15
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        q.add(node);
        map.put(node, new UndirectedGraphNode(node.label)); //放入queue的同时也放入map
        while(!q.isEmpty()){  // queue里的node都是已经有copy，但是copy node的neighbor list还是空的
            UndirectedGraphNode tmp = q.remove();
            UndirectedGraphNode clone = map.get(tmp);
            for(UndirectedGraphNode child: tmp.neighbors){
                UndirectedGraphNode clonechild = null;
                if(map.containsKey(child)){
                    clonechild = map.get(child);
                }
                else{
                    clonechild = new UndirectedGraphNode(child.label);
                    q.add(child); // 每当创建一个新的copy时，都放入queue，等到后面来补齐这个copy的neighbor list
                    map.put(child, clonechild);  // 放入map的一定是已经实例化的key value对
                }
                clone.neighbors.add(clonechild);
            }
        }
        return map.get(node);
    }
}



// 03/12/15
// 这题用图模拟一遍看得更清楚 BFS
 // *      1
 // *     / \
 // *    /   \
 // *   0 --- 2
 // *        / \
 // *        \_/
 // */
// 用map把original和clone连起来
// 每次探索一个node的neighbors，检查map里有没有，有就直接放入clone的neighours，没有就新建一个放入map，同时插入queue
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        map.put(node, new UndirectedGraphNode(node.label));
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.add(node);
        while(!q.isEmpty()){
            UndirectedGraphNode cur = q.remove();
            UndirectedGraphNode clone = map.get(cur);
            for(UndirectedGraphNode child: cur.neighbors){
                if(map.containsKey(child)){
                    clone.neighbors.add(map.get(child));
                }
                else{
                    map.put(child, new UndirectedGraphNode(child.label));
                    q.add(child);  // 说明queue里面还没有这个child，需要explore
                    clone.neighbors.add(map.get(child));
                }
            }
        }
        return map.get(node);
    }
}