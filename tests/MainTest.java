import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testTranslateWordFromEnglishToSpanish() {
        BinaryTree<String, String> englishTree = new BinaryTree<>();
        englishTree.insert("dog", "perro,chien");

        String translatedWord = Main.translateWord("dog", "español", englishTree, null, null);
        assertEquals("perro", translatedWord);
    }

    @Test
    public void testTranslateWordFromEnglishToFrench() {
        BinaryTree<String, String> englishTree = new BinaryTree<>();
        englishTree.insert("dog", "perro,chien");

        String translatedWord = Main.translateWord("dog", "francés", englishTree, null, null);
        assertEquals("chien", translatedWord);
    }

    @Test
    public void testTranslateWordFromSpanishToEnglish() {
        BinaryTree<String, String> spanishTree = new BinaryTree<>();
        spanishTree.insert("perro", "dog,chien");

        String translatedWord = Main.translateWord("perro", "inglés", null, spanishTree, null);
        assertEquals("dog", translatedWord);
    }

    @Test
    public void testTranslateWordFromSpanishToFrench() {
        BinaryTree<String, String> spanishTree = new BinaryTree<>();
        spanishTree.insert("perro", "dog,chien");

        BinaryTree<String, String> englishTree = new BinaryTree<>();
        englishTree.insert("dog", "perro,chien");

        String translatedWord = Main.translateWord("perro", "francés", englishTree, spanishTree, null);
        assertEquals("chien", translatedWord);
    }

    @Test
    public void testInsertAndSearch() {
        BinaryTree<Integer, String> tree = new BinaryTree<>();
        
        // Insertar asociaciones
        tree.insert(5, "cinco");
        tree.insert(3, "tres");
        tree.insert(8, "ocho");
        tree.insert(2, "dos");
        tree.insert(4, "cuatro");
        tree.insert(7, "siete");
        tree.insert(9, "nueve");
        
        // Buscar asociaciones existentes
        assertTrue(tree.search(5));
        assertTrue(tree.search(3));
        assertTrue(tree.search(8));
        assertTrue(tree.search(2));
        assertTrue(tree.search(4));
        assertTrue(tree.search(7));
        assertTrue(tree.search(9));
        
        // Buscar asociaciones no existentes
        assertFalse(tree.search(1));
        assertFalse(tree.search(6));
        assertFalse(tree.search(10));
    }

}
