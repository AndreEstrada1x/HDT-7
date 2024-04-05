import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Crear árboles para cada idioma
        BinaryTree<String, String> englishTree = new BinaryTree<>();
        BinaryTree<String, String> spanishTree = new BinaryTree<>();
        BinaryTree<String, String> frenchTree = new BinaryTree<>();

        // Leer el archivo diccionario.txt y construir los árboles
        try (BufferedReader br = new BufferedReader(new FileReader("resources/diccionario.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String englishWord = parts[0];
                String spanishWord = parts[1];
                String frenchWord = parts[2];

                // Insertar las asociaciones en los árboles correspondientes
                englishTree.insert(englishWord, spanishWord + "," + frenchWord);
                spanishTree.insert(spanishWord, englishWord + "," + frenchWord);
                frenchTree.insert(frenchWord, englishWord + "," + spanishWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leer el archivo texto.txt y realizar las traducciones
        try (BufferedReader br = new BufferedReader(new FileReader("resources/texto.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Dividir la línea en palabras
                String[] words = line.split(" ");

                // Traducir cada palabra y mostrar el resultado
                for (String word : words) {
                    String translation = translateWord(word, "english", englishTree, spanishTree, frenchTree);
                    System.out.print(translation + " ");
                }
                System.out.println(); // Agregar una nueva línea después de cada línea de texto
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para traducir una palabra según el idioma especificado
    private static String translateWord(String word, String language, BinaryTree<String, String> englishTree,
            BinaryTree<String, String> spanishTree, BinaryTree<String, String> frenchTree) {
        String translation = "*" + word + "*"; // Por defecto, si no se encuentra la traducción, se devuelve la palabra original

        // Dependiendo del idioma especificado, buscamos la traducción en el árbol correspondiente
        switch (language.toLowerCase()) {
            case "english":
                if (englishTree.search(word)) {
                    translation = englishTree.get(word);
                }
                break;
            case "spanish":
                if (spanishTree.search(word)) {
                    translation = spanishTree.get(word);
                }
                break;
            case "french":
                if (frenchTree.search(word)) {
                    translation = frenchTree.get(word);
                }
                break;
            default:
                // Si el idioma especificado no es válido, se devuelve la palabra original
                break;
        }

        return translation;
    }
}

