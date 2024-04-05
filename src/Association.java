/**
 * The Association class represents a word association between an English word, a Spanish word, and a French word.
 */
public class Association {
    private String englishWord;
    private String spanishWord;
    private String frenchWord;

    /**
     * Constructs an Association object with the specified English, Spanish, and French words.
     *
     * @param englishWord the English word
     * @param spanishWord the Spanish word
     * @param frenchWord the French word
     */
    public Association(String englishWord, String spanishWord, String frenchWord) {
        this.englishWord = englishWord;
        this.spanishWord = spanishWord;
        this.frenchWord = frenchWord;
    }

    /**
     * Returns the English word of the association.
     *
     * @return the English word
     */
    public String getEnglishWord() {
        return englishWord;
    }

    /**
     * Returns the Spanish word of the association.
     *
     * @return the Spanish word
     */
    public String getSpanishWord() {
        return spanishWord;
    }

    /**
     * Returns the French word of the association.
     *
     * @return the French word
     */
    public String getFrenchWord() {
        return frenchWord;
    }
}
