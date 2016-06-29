// Design a data structure that supports the following two operations:

// void addWord(word)
// bool search(word)
// search(word) can search a literal word or a regular expression string 
// containing only letters a-z or .. A . means it can represent any one letter.

// For example:

// addWord("bad")
// addWord("dad")
// addWord("mad")
// search("pad") -> false
// search("bad") -> true
// search(".ad") -> true
// search("b..") -> true
// Note:
// You may assume that all words are consist of lowercase letters a-z.


// 这里是Trie 的结构，用DFS来search
public class WordDictionary {

	public class TrieNode{
		char c;
		Map<Character, TrieNode> child = new HashMap<>();
		boolean isleaf;

		public TrieNode(){

		}

		public TrieNode(char c){
			this.c = c;
		}
	}

	TrieNode root = new TrieNode();

    // Adds a word into the data structure.
    public void addWord(String word) {
    	int l = word.length();
    	Map<Character, TrieNode> child = root.child;
        for(int i = 0; i < l; i++){
        	char c = word.charAt(i);
        	TrieNode t;
        	if(child.containsKey(c)){
        		t = child.get(c);
        	}
        	else{
        		t = new TrieNode(c);
        		child.put(c, t);
        	}
        	child = t.child;

        	// 记得最后一个node要标记为isleaf
        	if(i == l-1){
        		t.isleaf = true;
        	}
        }
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
    	return dfs(root, word, 0);
    }

    public boolean dfs(TrieNode n, String word, int idx){
    	int l = word.length();

    	// 不match的时候n == null, word还有剩余的char，或者node还有剩余的child
    	if (n == null || (idx == l && !n.isleaf)){
            return false;
        }
        // last node match last char of word
        if (idx == l && n.isleaf){
            return true;
        }
        char c = word.charAt(idx);
        Map<Character, TrieNode> child = n.child;
        if (c == '.') {
            for (char tmp: child.keySet()) {
                if(dfs(child.get(tmp), word, idx+1))
                    return true;
            }
            return false;
        } else {
            return dfs(child.get(c), word, idx+1);  // 自动找到match的那个node去search
    	}
    }
}

