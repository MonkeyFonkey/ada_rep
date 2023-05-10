import java.util.List;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hello");
        trie.insert("world");
        trie.insert("hi");
        trie.insert("there");

        List<String> words = trie.getAllWords();
        for (String word : words) {
            System.out.println(word);
        }
    }
}