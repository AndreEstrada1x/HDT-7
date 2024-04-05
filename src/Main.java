import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

        // Leer el archivo texto.txt y detectar el idioma
        String detectedLanguage = detectLanguage(englishTree, spanishTree, frenchTree);

        // Solicitar al usuario que seleccione el idioma de destino
        String targetLanguage = chooseTargetLanguage(detectedLanguage);

        // Leer el archivo texto.txt y realizar las traducciones
        translateText("resources/texto.txt", detectedLanguage, targetLanguage, englishTree, spanishTree, frenchTree);
    }

    // Método para detectar el idioma del texto
    private static String detectLanguage(BinaryTree<String, String> englishTree, BinaryTree<String, String> spanishTree,
            BinaryTree<String, String> frenchTree) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        wordCountMap.put("english", 0);
        wordCountMap.put("spanish", 0);
        wordCountMap.put("french", 0);

        try (BufferedReader br = new BufferedReader(new FileReader("resources/texto.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (englishTree.search(word)) {
                        wordCountMap.put("english", wordCountMap.get("english") + 1);
                    } else if (spanishTree.search(word)) {
                        wordCountMap.put("spanish", wordCountMap.get("spanish") + 1);
                    } else if (frenchTree.search(word)) {
                        wordCountMap.put("french", wordCountMap.get("french") + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Obtener el idioma con el mayor número de palabras coincidentes
        int maxCount = -1;
        String detectedLanguage = null;
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                detectedLanguage = entry.getKey();
            }
        }

        return detectedLanguage;
    }

    // Método para solicitar al usuario que seleccione el idioma de destino
    private static String chooseTargetLanguage(String detectedLanguage) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("El idioma detectado es: " + detectedLanguage);
        System.out.println("Por favor, seleccione el idioma de destino (1 para español, 2 para francés): ");
        String input = scanner.nextLine();

        while (!input.equals("1") && !input.equals("2")) {
            System.out.println("¡Por favor, seleccione una opción válida (1 para español, 2 para francés)!");
            input = scanner.nextLine();
        }

        return input.equals("1") ? "español" : "francés";
    }


    // Método para traducir el texto al idioma seleccionado por el usuario
    private static void translateText(String filePath, String detectedLanguage, String targetLanguage,
            BinaryTree<String, String> englishTree, BinaryTree<String, String> spanishTree,
            BinaryTree<String, String> frenchTree) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    String translation = translateWord(word, targetLanguage, englishTree, spanishTree, frenchTree);
                    System.out.print(translation + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para traducir una palabra al idioma seleccionado
    private static String translateWord(String word, String targetLanguage, BinaryTree<String, String> englishTree,
        BinaryTree<String, String> spanishTree, BinaryTree<String, String> frenchTree) {
        String translation = "*" + word + "*"; // Por defecto, si no se encuentra la traducción, se devuelve la palabra original

        switch (targetLanguage) {
            case "español":
                if (englishTree.search(word)) {
                    String[] translations = englishTree.get(word).split(",");
                    translation = translations[0]; // Tomar la traducción al español
                }
                break;
            case "francés":
                if (englishTree.search(word)) {
                    String[] translations = englishTree.get(word).split(",");
                    translation = translations[1]; // Tomar la traducción al francés
                }
                break;
            default:
                // No hacer nada, mantener la palabra original como traducción
                break;
        }

        return translation;
    }
}
