

import java.util.ArrayList;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */

//nRows = 4
// 0     6     12 ...  一个zig可以认为是012345
// 1   5 7   11
// 2 4   8 10  
// 3     9

// 01234... -> 0612157...
//也就是把zigzag表示的string以一行行的顺序读出来
public class ZigZagConversion {
	public String convert(String s, int nRows) {
		if (nRows <= 1 || s.length() < 2)
			return s;
		ArrayList<StringBuilder> sbs = new ArrayList<StringBuilder>();  // 每一个row是一个stringBuilder
		for (int k = 0; k < nRows; k++) {
			sbs.add(new StringBuilder());
		}

		int nCount = 2 * (nRows - 1);  // 一个zag的size，2*3=6， 0~5是一个zag，6到11是一个zag，一个zag的count是6
		for (int i = 0; i < s.length(); i++) {
			sbs.get(nRows - 1 - Math.abs(nRows - 1 - (i % nCount))).append(s.charAt(i));  // 找到对应的row，append char					  
			//  nRows - 1 = 3， Math.abs(nRows - 1 - (i % nCount))表示的是这个值与row size的差，再用nRows - 1减去这个差值就是index了
		}
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < nRows; j++) {
			sb.append(sbs.get(j)); // 每次读取一个row再append，这样就把多个rows都combine成一行了
		}
		return sb.toString();
	}
}



//nRows = 4
// 0     6     12 ...  一个zig可以认为是012345
// 1   5 7   11
// 2 4   8 10  
// 3     9

// 03/11/15
// 先把第一行append进去，然后计算每个zig首部的差值，用两个指针表示一个zig的同一行的两个值，比如1，5，下一个zig就是1+6，5+6
// 当两个指针相同时，表示到了最后一行
// 要注意当nRows == 1时，直接返回原string
public class Solution {
    public String convert(String s, int nRows) {
        int l = s.length();
        if(l <= 1 || nRows <= 1) return s;
        StringBuffer sb = new StringBuffer();
        int dis = 2*nRows-2;  // 确定dist = 6
        for(int i = 0; i < l; i += dis){
            sb.append(s.charAt(i));  // row 0
        }
        int a = 1, b = dis-1;  // 用来标记每一个row的起始index
        while(a < b){
            int i = a, j = b;
            while(i < l){
                sb.append(s.charAt(i));
                if(j < l){
                    sb.append(s.charAt(j));
                }
                i += dis;
                j += dis;
            }
            a++;
            b--;
        }
        for(int i = a; i < l; i+=dis){
            sb.append(s.charAt(i));  // last row
        }
        return sb.toString();
    }
}