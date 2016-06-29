// The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
// The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
// Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
// In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

// Notes:

// The knight's health has no upper bound.
// Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.


// 要注意blood无论何时都不能为0，至少也得是1，当格子为负值，那就减去这个负值得到min blood required，当是正值，也许只需要1个blood就够
public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int w = dungeon.length;
        if(w == 0) return 0;
        int l = dungeon[0].length;
        int[][] ret = new int[w][l];
        ret[w-1][l-1] = dungeon[w-1][l-1] >= 0 ? 1 : 1-dungeon[w-1][l-1];  // 表示从这一步到dest至少要多少hp
        
        for(int i = w-2; i >= 0; i--){  // col
            ret[i][l-1] = minhp(ret[i+1][l-1] - dungeon[i][l-1]);  // 表示到这一个区域前至少需要多少hp
        }
        for(int i = l-2; i >= 0; i--){  // row
            ret[w-1][i] = minhp(ret[w-1][i+1] - dungeon[w-1][i]);
        }
        
        for(int i = w-2; i >= 0; i--){
            for(int j = l-2; j >= 0; j--){
                ret[i][j] = minhp(Math.min(ret[i+1][j], ret[i][j+1]) - dungeon[i][j]); // 找到所需最少hp的路，所以是min
            }
        }
        
        return ret[0][0];
    }
    
    private int minhp(int x){
        return x <= 0 ? 1 : x;
    }
}