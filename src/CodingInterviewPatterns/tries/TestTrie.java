package CodingInterviewPatterns.tries;

public class TestTrie {
    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insert words into the Trie
        trie.insert("apple");
        trie.insert("banana");
        trie.insert("cat");
        trie.insert("car");

        // Delete words from the Trie
        // trie.delete("banana");
        // trie.delete("car");

        // Search for words
        System.out.println(trie.search("apple"));   // true
        System.out.println(trie.search("banana"));  // false
        System.out.println(trie.search("cat"));     // true
        System.out.println(trie.search("car"));     // false

        System.out.println(trie.startsWith("car"));
    }
}
