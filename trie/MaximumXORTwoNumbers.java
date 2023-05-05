package trie;

public class MaximumXORTwoNumbers {

    static TrieNode1 root;

    static class TrieNode1 {
        TrieNode1[] next;

        public TrieNode1() {
            next = new TrieNode1[2];
        }
    }

    public MaximumXORTwoNumbers(int[] nums) {
        root = new TrieNode1();
        for(int num : nums) {
            insert(num);
        }
    }

    public void insert(int num) {
        TrieNode1 pointer = root;
        int currBit;

        for(int i=31; i>=0; i--) {
            currBit = (num >> i) & 1;
            if(pointer.next[currBit] == null) {
                pointer.next[currBit] = new TrieNode1();
            }
            pointer = pointer.next[currBit];
        }
    }

    public int findMaxXOR(int[] nums) {
        int maxXor = 0;
        int curr = 0;
        int currBit;
        TrieNode1 pointer = root;

        for(int num : nums) {
            curr = 0;
            pointer = root;
            for(int i=31; i>=0; i--) {
                currBit = ((num >> i) & 1) == 0 ? 1 : 0;
                curr <<= 1;
                if(pointer.next[currBit] != null) {
                    curr |= 1;
                    pointer = pointer.next[currBit];
                } else {
                    curr |= 0;
                    pointer = pointer.next[currBit == 0 ? 1 : 0];
                }
            }
            maxXor = Math.max(curr, maxXor);
        }

        return maxXor;
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{25, 10, 2, 8, 5, 3};
        MaximumXORTwoNumbers obj = new MaximumXORTwoNumbers(nums);
        System.out.println(obj.findMaxXOR(nums));
    }
}
