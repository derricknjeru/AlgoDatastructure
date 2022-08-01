package trees;

import java.util.*;

public class AutoCompleteWithWeight {
    //Improvements ---> add a hit weight and print the first 3 //minheap/maxheap/priority queue
    //https://www.youtube.com/watch?v=E_IOs3buLOQ
    //https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/642-design-search-autocomplete-system.html
    TrieNode root;
    static class TrieNode {
        Map<Character,TrieNode> children;
        boolean isWord;

        int count;

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
        public void insert(String word,int count) {
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
            current.count=count;
        }

        public List<String> autoComplete(String prefix){
            TrieNode current = root;

            HashMap<String,Integer> mapResult = new HashMap<>();

            List<String> result= new ArrayList<>(mapResult.keySet());


            for(char c : prefix.toCharArray()){
                TrieNode node = current.children.get(c);
                if(node==null){
                    return null;
                }

                current = node;
            }

            //the prefix exist and current node is at the end of the prefix node.

            //run a dfs for all words after the prefix
            searchWords(mapResult,prefix,current);

            List<Map.Entry<String, Integer> > list
                    = new LinkedList<>(
                    mapResult.entrySet());

            Collections.sort(
                    list,
                    (a,
                     b) -> b.getValue().compareTo(a.getValue()));


            for (Map.Entry<String, Integer> entry : list) {
               result.add(entry.getKey());
            }

            return result;
        }

        private void searchWords(Map<String,Integer> mapResult, String word, TrieNode current) {
            if(current==null) return;

            if(current.isWord) {
                mapResult.put(word,current.count);
            }

            Map<Character,TrieNode> map = current.children;
            for(char child : map.keySet()){
                searchWords(mapResult, word+ child,map.get(child));
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        Map<String,Integer> map = new HashMap<>();

        map.put("I am Mike",9);
        map.put("I am dev",7);
        map.put("Hell am Mike",9);
        map.put("Hell am James",5);
        map.put("Hell am Ken",7);
        map.put("Not am dev",7);

        for(Map.Entry<String,Integer> entry:map.entrySet()) {
                trie.insert(entry.getKey(),entry.getValue());
        }

        List<String> suggestedWords = trie.autoComplete("Hell");

        for (String word:suggestedWords){
            System.out.println(word);
        }
    }
}
