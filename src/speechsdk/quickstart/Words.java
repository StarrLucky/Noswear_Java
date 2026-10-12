package speechsdk.quickstart;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class Words {
    // Relative to the working directory (project root). The dictionary ships in the repo.
    private static final String DICTIONARY_PATH = "dict/profanity_dic.txt";

    private final Set<String> profanity = new HashSet<>();

    public void loadProfanityDictionary() {
        loadProfanityDictionary(DICTIONARY_PATH);
    }

    // Package-private overload so tests can load a fixture instead of the shipped dictionary.
    void loadProfanityDictionary(String dictionaryPath) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(dictionaryPath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim().toLowerCase();
                if (!word.isEmpty()) {
                    profanity.add(word);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load profanity dictionary from " + dictionaryPath + ": " + e.getMessage());
        }
    }

    /** Returns true if any token in the phrase matches the profanity dictionary. */
    public boolean containsBadWord(String phrase) {
        if (phrase == null || phrase.isEmpty()) {
            return false;
        }

        boolean found = false;
        String[] tokens = phrase.replaceAll("\\pP", "").split("\\s+");
        for (String token : tokens) {
            String word = token.toLowerCase();
            if (!word.isEmpty() && profanity.contains(word)) {
                System.out.println("(Распознано: " + word + ")");
                found = true;
            }
        }
        return found;
    }
}
