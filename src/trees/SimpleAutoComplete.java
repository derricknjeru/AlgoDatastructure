package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleAutoComplete {
    //Improvements ---> add a hit weight and print the first 3 //minheap/maxheap/priority queue
    //https://www.youtube.com/watch?v=E_IOs3buLOQ
    //https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/642-design-search-autocomplete-system.html
    TrieNode root;
    static class TrieNode {
        Map<Character,TrieNode> children;
        boolean isWord;

        TrieNode(){
            children  = new HashMap<>();
            isWord=false;
        }
    }

    static class Trie {
        TrieNode root;

        Trie(){
            root=new TrieNode();
        }
        public void insert(String word) {
            TrieNode current = root;

            for(char c : word.toCharArray()){
                TrieNode node = current.children.get(c);
                if(node==null){
                    node = new TrieNode();
                    current.children.put(c,node);
                }

                current = node;
            }
            current.isWord=true;
        }

        public List<String> autoComplete(String prefix){
            TrieNode current = root;
            List<String> result= new ArrayList<>();
            for(char c : prefix.toCharArray()){
              TrieNode node = current.children.get(c);
              if(node==null){
                  return result;
              }

              current = node;
            }

            //the prefix exist and current node is at the end of the prefix node.

            //run a dfs for all words after the prefix
            searchWords(result,prefix,current);

            return result;
        }

        private void searchWords(List<String> result, String word, TrieNode current) {
            if(current==null) return;

            if(current.isWord) result.add(word);

            Map<Character,TrieNode> map = current.children;
            for(char child : map.keySet()){
                searchWords(result,word+String.valueOf(child),map.get(child));
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        List<String> words = List.of("hello", "dog", "hell", "cat", "a", "hel","help","helps","helping","dig", "dig love");

        for (String word : words){
            trie.insert(word);
        }

        List<String> suggestedWords = trie.autoComplete("dig");

        for (String word:suggestedWords){
            System.out.println(word);
        }
    }
}
