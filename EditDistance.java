/**
 * Given two words word1 and word2, find the minimum number of steps required to
 * convert word1 to word2. (each operation is counted as 1 step.)
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * a) Insert a character 
 * b) Delete a character 
 * c) Replace a character
 */

//把A词变位B词需要最少几步？
//这题思路和机器人左上到右下是一样的  预处理矩阵 
//你可以想象在一个矩阵里 word1是i长度 word2 是j长度 那么矩阵里 matrix[i][j]的值就是 word1 0~i位到word2 0~j位变换的距离  
//那比方说 矩阵里[i-1][j-1]就是 word1 0~i-1位到word 2 0~j-1位变换的次数（distance）
//那么 预处理完这个矩阵后到时候只要return 会矩阵的值就可以 （如果word1某位和word2某位的字符是一样的 那么 就不用操作）
//     比方说a换到bbc就是3次。 a换b 加一个b 再加一个b

//  那么结果就是distance[4][4] 就是
//0 1 2 3 4 
//1 1 2 3 4 
//2 2 2 3 4 
//3 3 3 3 4 
//4 4 4 4 4 
public class EditDistance {
	public int minDistance(String word1, String word2) {
		if (word1.length() == 0 || word2.length() == 0)
			return word1.length() == 0 ? word2.length() : word1.length();
		int[][] arr = new int[word2.length() + 1][word1.length() + 1];
		for (int i = 0; i <= word1.length(); i++) {
			arr[0][i] = i;  // 表示word1 length i -> word2 length 0
		}
		for (int j = 0; j <= word2.length(); j++) {
			arr[j][0] = j;  // 表示word1 length 0 -> word2 length j
		}

		//word1 length 1 ，convert to word2 of length 1,2,3,4...
		//word1 length 2 ，convert to word2 of length 1,2,3,4...
		//word1 length 3 ，convert to word2 of length 1,2,3,4...
		//...
		for (int i = 0; i < word1.length(); i++) {
			for (int j = 0; j < word2.length(); j++) {
				if (word1.charAt(i) == word2.charAt(j)) {  //word1某位和word2某位的字符是一样的
					arr[j + 1][i + 1] = arr[j][i];  // 所以不需要任何操作
				} else {
					// w1 ...a
					// w2 ...c
					int dis = Math.min(arr[j][i + 1], arr[j + 1][i]);  // delete或insert
					arr[j + 1][i + 1] = Math.min(arr[j][i], dis) + 1;  // 或replace
				}
			}
		}
		return arr[word2.length()][word1.length()];
	}
	
	public static void main (String[] args) {
		System.out.println(new EditDistance().minDistance("ab", "bc"));
	}
}




// 03/14/15
// 二维数组DP，长宽是两个word的length
// 两种情况，如果对应位match，就看ret[i-1][j-1], 已经是min；unmatch就看delete，insert，replace三种情况
public class Solution {
    public int minDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        int[][] ret= new int[l1+1][l2+1];
        
        for(int i = 0; i < l1+1; i++){
            ret[i][0] = i;
        }
        for(int i = 0; i < l2+1; i++){
            ret[0][i] = i;
        }
        
        for(int i = 1; i < l1+1; i++){
            for(int j = 1; j < l2+1; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    ret[i][j] = ret[i-1][j-1];
                }
                else{
                    int tmp = Math.min(ret[i-1][j], ret[i][j-1]);
                    ret[i][j] = Math.min(tmp, ret[i-1][j-1])+1;  // delete/insert/replace
                }
            }
        }
        return ret[l1][l2];
    }
}