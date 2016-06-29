// Find the total area covered by two rectilinear rectangles in a 2D plane.

// Each rectangle is defined by its bottom left corner and top right corner 
// as shown in the figure.

// Rectangle Area
// Assume that the total area is never beyond the maximum possible value of int.




// 这里要考虑两个可能，如果不重叠，那就直接相加两个area，否则先计算出重叠面积，再从两个area的和减去这个重叠部分
public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int top = Math.min(D, H);
        int bottom = Math.max(B, F);
        int cover = (right-left)*(top-bottom);
        if(right < left || top < bottom) cover = 0;
        int area1 = (C-A)*(D-B);
        int area2 = (G-E)*(H-F);
        return area1+area2-cover;
    }
}