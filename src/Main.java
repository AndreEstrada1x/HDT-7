import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The main class of the program that performs language detection and translation.
 * @author André Estrada
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        BinaryTree<String, String> englishTree = new BinaryTree<>();
        BinaryTree<String, String> spanishTree = new BinaryTree<>();
        BinaryTree<String, String> frenchTree = new BinaryTree<>();

        try (BufferedReader br = new BufferedReader(new FileReader("resources/diccionario.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String englishWord = parts[0];
                String spanishWord = parts[1];
                String frenchWord = parts[2];

                englishTree.insert(englishWord, spanishWord + "," + frenchWord);
                spanishTree.insert(spanishWord, englishWord + "," + frenchWord);
                frenchTree.insert(frenchWord, englishWord + "," + spanishWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String detectedLanguage = detectLanguage(englishTree, spanishTree, frenchTree);
        String targetLanguage = chooseTargetLanguage(detectedLanguage);
        translateText("resources/texto.txt", detectedLanguage, targetLanguage, englishTree, spanishTree, frenchTree);
    }

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

    private static String chooseTargetLanguage(String detectedLanguage) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("El idioma detectado es: " + detectedLanguage);
        System.out.println("Por favor, seleccione el idioma de destino (1 para español, 2 para francés, 3 para inglés): ");
        String input = scanner.nextLine();
    
        while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
            System.out.println("¡Por favor, seleccione una opción válida (1 para español, 2 para francés, 3 para inglés)!");
            input = scanner.nextLine();
        }
    
        switch (input) {
            case "1":
                return "español";
            case "2":
                return "francés";
            case "3":
                return "inglés";
            default:
                return ""; // En caso de que ocurra algún error
        }
    }

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

    public static String translateWord(String word, String targetLanguage, BinaryTree<String, String> englishTree,
        BinaryTree<String, String> spanishTree, BinaryTree<String, String> frenchTree) {
        String translation = "*" + word + "*";

        switch (targetLanguage.toLowerCase()) {
            case "español":
                if (englishTree.search(word)) {
                    String[] translations = englishTree.get(word).split(",");
                    translation = translations[0];
                } else if (frenchTree.search(word)) {
                    String[] translations = frenchTree.get(word).split(",");
                    translation = translations[0];
                }
                break;
            case "francés":
                if (englishTree.search(word)) {
                    String[] translations = englishTree.get(word).split(",");
                    translation = translations[1];
                } else if (spanishTree.search(word)) {
                    String[] translations = spanishTree.get(word).split(",");
                    translation = translations[1];
                }
                break;
            case "inglés":
                if (spanishTree.search(word)) {
                    String[] translations = spanishTree.get(word).split(",");
                    translation = translations[0];
                } else if (frenchTree.search(word)) {
                    String[] translations = frenchTree.get(word).split(",");
                    translation = translations[0];
                }
                break;
            default:
                break;
        }

        return translation;
    }
}
