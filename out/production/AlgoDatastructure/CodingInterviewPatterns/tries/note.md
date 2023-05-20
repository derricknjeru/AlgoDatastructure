# Trie

A type of tree that stores the values for each node based on it position in the tree.

- [Tries](https://www.youtube.com/watch?v=kMt9Y5fv4Ug&ab_channel=GoogleStudents)
- [Tries book](https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6228907478220800)

### Examples

#### Does my problem match this pattern?

- Yes, if either of these conditions is fulfilled:

    - We need to compare two strings to detect partial matches, based on the initial characters of one or both strings.
    - We wish to optimize the space used to store a dictionary of words. Storing shared prefixes once allows for
      significant savings.

- No, if either of these conditions is fulfilled:
    - The problem statement restricts us from breaking down the strings into individual characters.
    - Partial matches between pairs of strings are not significant to solving the problem.

#### Real-world problems

Many problems in the real world use the trie pattern. Let’s look at some examples.

- Autocomplete system: One of the most common applications of trie is the autocomplete system in search engines, such as
  Google. This is the feature that prompts the search engine to give us some suggestions to complete our query when we
  start typing something in the search bar. These suggestions are given based on common queries that users have searched
  already that match the prefix we have typed.
- Orthographic corrector: Ever seen pop-up suggestions or red lines under a word while you’re typing a message? That’s
  an
  orthographic corrector making suggestions and pointing out spelling mistakes by searching through a dictionary. It
  uses
  a trie data structure for efficient searches and retrievals from the available database.
- Design an autocomplete system for a search engine
- Find all the words in a dictionary that start with “aba”

#### Time & space complexity

- Insertion: The time complexity of inserting a word into the Trie is O(L), where L is the length of the word. This is
  because we iterate over each character in the word, and for each character, we perform constant time operations to
  navigate the Trie or create new nodes if necessary.
- Search: The time complexity of searching for a word in the Trie is also O(L), where L is the length of the word.
  Similar to insertion, we traverse the Trie by examining each character of the word, performing constant time
  operations until either the word is found or we reach the end of the word.
- Prefix Search (startsWith): The time complexity of searching for a prefix in the Trie is O(P), where P is the length
  of the prefix. We iterate over each character of the prefix and traverse the Trie accordingly. The number of
  operations depends on the length of the prefix but is independent of the number of words stored in the Trie.
- Overall, the time complexity of Trie operations (insertion, search, and prefix search) is determined by the length of
  the word or prefix being operated on, making them efficient data structures for handling string-related operations.

``` text /**
/**
 * Time complexity
 * Insert(): The time complexity is O(l), where l is the length of the word being inserted.
 * Search(): The time complexity is O(l), where l is the length of the word being searched.
 * Search prefix(): The time complexity is O(l), where l is the length of the prefix being searched.
 * Space complexity
 * Insert(): The space complexity is O(l) because, in the worst case, we will add l nodes in the trie.
 * Search(): The space complexity isO(1) because constant space is used while searching the trie.
 * Search prefix(): The space complexity is O(1) because constant space is used while searching the trie.
 */
```


