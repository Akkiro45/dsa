package trie;

public class Trie {

    public static final int ALPHABETS_SIZE = 26;
    private TrieNode root;
    
    static class TrieNode {
        boolean isEnd;
        TrieNode[] next;

        public TrieNode() {
            isEnd = false;
            next = new TrieNode[ALPHABETS_SIZE];
        }
    }

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        int i = 0;
        TrieNode pointer = root;
        int currCharInd;

        while(i < word.length()) {
            currCharInd = word.charAt(i) - 'a';
            if(pointer.next[currCharInd] == null) {
                pointer.next[currCharInd] = new TrieNode();
            }
            pointer = pointer.next[currCharInd];
            i++;
        }
        pointer.isEnd = true;
    }

    public boolean search(String word) {
        int i = 0;
        TrieNode pointer = root;
        int currCharInd;

        while(i < word.length()) {
            currCharInd = word.charAt(i) - 'a';
            if(pointer.next[currCharInd] == null) {
                return false;
            }
            pointer = pointer.next[currCharInd];
            i++;
        }

        return pointer.isEnd;
    }

    public void delete(String word) {
        delete(word, 0, root);
    }

    public boolean isEmpty(TrieNode pointer) {
        for(int i=0; i<ALPHABETS_SIZE; i++) {
            if(pointer.next[i] != null) {
                return false;
            }
        }
        return true;
    }

    public TrieNode delete(String word, int i, TrieNode pointer) {
        if(pointer == null) {
            return null;
        }
        if(i == word.length()) {
            if(pointer.isEnd) {
                pointer.isEnd = false;
            }
            if(isEmpty(pointer)) {
                pointer = null;
            }
            return pointer;
        }
        
        int charInd = word.charAt(i) - 'a';
        pointer.next[charInd] = delete(word, i + 1, pointer.next[charInd]);
        return pointer;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("akshay");
        trie.insert("rohit");
        System.out.println(trie.search("akshay"));
        System.out.println(trie.search("aksha"));
        System.out.println(trie.search("dhoni"));
        System.out.println(trie.search("rohit"));
        trie.delete("rohit");
        System.out.println(trie.search("rohit"));
    }
}
