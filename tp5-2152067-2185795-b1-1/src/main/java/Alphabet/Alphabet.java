package Alphabet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Alphabet {

    /**
     * TODO
     * From the words contained in the dictionary of a fictitious language, find the lexical order of
     * the symbols composing the language.
     *
     * @param dictionary Contains all the word of a language
     * @return The lexicalOrder of the symbols composing this language
     */
    public static ArrayList<Character> lexicalOrder(String[] dictionary) {
        ArrayList<Character> lexicalOrder = new ArrayList<>();
        Graph<Character> graph = new Graph<Character>();
        Queue<Vertex<Character>> queue = new LinkedList<>();

        // Itérer à travers chaque mot du dictionary
        for (int i = 0 ; i < dictionary.length - 1; i++ ) {
             String firstWord = dictionary[i];
             String secondWord = dictionary[i+1];
             int minLength = Math.min(firstWord.length(), secondWord.length());
            // Itérer à travers chaque caractère de chaque mot dans dictionary
            // pour en créer un graphe
             for (int j = 0; j < minLength ; j++) {
                 char firstChar = firstWord.charAt(j);
                 char secondChar = secondWord.charAt(j);
                 if (firstChar != secondChar) {
                    graph.connect(firstChar, secondChar);
                    break;
                 }
             }
        }

        // Créer une linked list avec les vertices du graphe créé
        for (Vertex<Character> vertex : graph.vertices) {
            if (vertex.indegree == 0)
                queue.add(vertex);
        }

        // Créer ordre topologique
        while (!queue.isEmpty()) {
            Vertex<Character> vertex = queue.poll();
            lexicalOrder.add(vertex.label);

            for (Vertex<Character> toVertex : vertex.toVertex) {
                toVertex.indegree -= 1;
                if (toVertex.indegree == 0)
                    queue.add(toVertex);
            }
        }
        return lexicalOrder;
    }
}
