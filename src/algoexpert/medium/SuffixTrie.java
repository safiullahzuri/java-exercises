package algoexpert.medium;


import java.util.*;

public class SuffixTrie {

    TrieNode root;
    char endSymbol = '*';

    public SuffixTrie(String str){
        root = new TrieNode();

        populateSuffixTrie(str);
    }

    public void populateSuffixTrie(String str){
        for (int i=0; i<str.length(); i++){
            insertSubstringStartingAt(i, str);
        }
    }

    public void insertSubstringStartingAt(int i, String str){
        TrieNode node = root;
        for (int j=i; j<str.length(); j++){
            char letter = str.charAt(j);
            if (!node.nodeChildren.containsKey(letter)){
                TrieNode newNode = new TrieNode();
                node.nodeChildren.put(letter, newNode);
            }
            node = node.nodeChildren.get(letter);
        }
        node.nodeChildren.put(endSymbol, null);
    }

    public boolean contains(String str){
        TrieNode node = root;

        for (int i=0; i<str.length(); i++){
            char letter = str.charAt(i);

            if (!node.nodeChildren.containsKey(letter)){
                return false;
            }
            node = node.nodeChildren.get(letter);
        }
        return node.nodeChildren.containsKey(endSymbol);
    }


    public static void main(String[] args) {
        int[] nums = {12,23,2,3,21};
    }









    class TrieNode{
        Map<Character, TrieNode> nodeChildren = new HashMap<>();
    }






}
