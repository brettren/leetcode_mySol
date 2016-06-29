

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

//这里巧妙在当以其中一个point为准，比较它和其他point相连的line，只需要比较slope，因为这些lines都经过这个point，
//只要slope相同，就必然在同一条line上

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {
	public int maxPoints(Point[] points) {
		Map<Double, Integer> map = new HashMap<Double, Integer>();
		int ret = 0;
		int size = points.length;
		for (int i = 0; i < size; i++) {  // 每次loop以其中一点为基准，和其他比较
			int invalidK = 0;
			int add = 1;  // 先包括自己这个point
			for (int j = i + 1; j < size; j++) {  // 两两比较
				if (points[j].x == points[i].x) {
					if (points[j].y == points[i].y) {
						add++; // 相同坐标的count加一
					} else {
						invalidK++;  // 斜率是invalid的，两点连线是vertical的lines count
					}
					continue;
				}
				// 上面排除了vertical和相同坐标的情况
				double k = points[j].y == points[i].y ? 0.0
						: (1.0 * (points[j].y - points[i].y))
								/ (points[j].x - points[i].x);
				if (map.containsKey(k)) {
					int count = map.get(k);
					map.put(k, count + 1);  // 相同斜率的line加一
				} else {
					map.put(k, 1);  // 第一个slope为k的line，记为count 1
				}
			}
			for (Integer it : map.values()) {  // 遍历每个不同斜率的数量
				if (it + add > ret) {
					ret = it + add;  // 找到同斜率最多数量的，因为都经过points[i]点，所以相同斜率的line都是同一条
				}
			}
			ret = Math.max(invalidK + add, ret); // 再和vertical的line上的points数量比较取max
			map.clear();  // 原来的数据清零
		}
		return ret;
	}
}


// 01/16/15
// 第一个loop我们得到经过point[0]的有最多point的line，所以在后面的loop不用再考虑point[0]
// 因为如果后面又得到最多point的line同时也穿过point[0]，我们肯定在前面已经计算过这个line了
public class Solution {
	public int maxPoints(Point[] points) {
		int ret = 0;
		int l = points.length;
		for (int i = 0; i < l; i++) {  // 每次loop以其中一点为基准，和其他比较
			int vertical = 0;
			int add = 1;  // 先包括自己这个point
			int tmp = 0;
			Map<Double, Integer> map = new HashMap<Double, Integer>();
			Point a = points[i];
			for (int j = i+1; j < l; j++) {  // 两两比较
			    Point b = points[j];
				if(a.x == b.x && a.y == b.y){
                    add++;
                    continue;
                }
                if(a.x == b.x && a.y != b.y){
                    vertical++;
                    continue;
                }
				// 上面排除了vertical和相同坐标的情况
				double k = 0.0;
				if(a.y != b.y) k = (1.0*(a.y-b.y))/(a.x-b.x);
				
				if (map.containsKey(k)) {
					map.put(k, map.get(k) + 1);  // 相同斜率的line加一
				} else {
					map.put(k, 1);  // 第一个slope为k的line，记为count 1
				}
			}
			for (Integer count : map.values()) {  // 遍历每个不同斜率的数量
				tmp = Math.max(tmp, count + add);  // 找到同斜率最多数量的，因为都经过points[i]点，所以相同斜率的line都是同一条
			}
			tmp = Math.max(tmp, vertical + add);
			ret = Math.max(tmp, ret); // 再和vertical的line上的points数量比较取max
		}
		return ret;
	}
}