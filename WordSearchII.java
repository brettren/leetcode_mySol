// Given a 2D board and a list of words from the dictionary, find all words in the board.

// Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

// For example,
// Given words = ["oath","pea","eat","rain"] and board =

// [
//   ['o','a','a','n'],
//   ['e','t','a','e'],
//   ['i','h','k','r'],
//   ['i','f','l','v']
// ]
// Return ["eat","oath"]


// Implementation Trie, 利用Trie数据结构，也就是前缀树。然后dfs时，如果当前形成的单词不在Trie里，就没必要继续dfs下去了。
// 如果当前字符串在trie里，就说明board可以形成这个word。

// DFS来形成prefix，查找trie里是否存在这个prefix
public class Solution {
    Set<String> res = new HashSet<String>();  
      
    public List<String> findWords(char[][] board, String[] words) {  
        Trie trie = new Trie();  
        for (String word : words) {  
            trie.insert(word);  
        }  
          
        int m = board.length;  
        int n = board[0].length;  
        boolean[][] visited = new boolean[m][n];  // 标记DFS的路径
        for (int i = 0; i < m; i++) {  
            for (int j = 0; j < n; j++) {  
                dfs(board, visited, "", i, j, trie);   // 遍历每个char作string开头
            }  
        }  
          
        return new ArrayList<String>(res);  
    }  
      
    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {  
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;  
        if (visited[x][y]) return;  
          
        str += board[x][y];  
        if (!trie.startsWith(str)) return;  
          
        if (trie.search(str)) {  
            res.add(str);  
        }  
          
        visited[x][y] = true;  
        dfs(board, visited, str, x - 1, y, trie);  
        dfs(board, visited, str, x + 1, y, trie);  
        dfs(board, visited, str, x, y - 1, trie);  
        dfs(board, visited, str, x, y + 1, trie);  
        visited[x][y] = false;  
    }  
}