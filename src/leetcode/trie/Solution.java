package leetcode.trie;

class Node {
    boolean isLeaf = false;
    Node[] children = new Node[26];
}

public class Solution {
    Node root;
    public Solution(){
        root = new Node();
    }

    public void add(String s) {
        char[] arr = s.toCharArray();
        Node curr = root;
        for(char c: arr) {
            if(curr.children[c-'a'] == null) {
                curr.children[c-'a'] = new Node();
            }
            curr = curr.children[c-'a'];
        }
        curr.isLeaf = true;
    }

    public boolean search(String s) {
        char[] arr = s.toCharArray();
        Node curr = root;
        for(char c: arr) {
            if(curr.children[c-'a'] == null) {
                return false;
            }
            curr = curr.children[c-'a'];
        }
        return curr.isLeaf;
    }

    public boolean startsWith(String prefix) {
        Node curr =  root;
        for(char c: prefix.toCharArray()) {
            if(curr.children[c-'a'] == null) {
                return false;
            }
            curr = curr.children[c-'a'];
        }
        return true;
    }

    public static void main(String[] args) {

        Solution s = new Solution();
        s.add("flower");
        s.add("flow");
        s.add("flee");
        System.out.println(s.startsWith("flo"));
        System.out.println(s.search("flew"));
    }
}
