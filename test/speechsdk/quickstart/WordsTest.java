package speechsdk.quickstart;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class WordsTest {

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    private Words words;

    @Before
    public void setUp() throws IOException {
        // Fixture dictionary: a few ASCII words plus one Cyrillic word (matching real usage).
        // Written as UTF-8 to match how Words reads the shipped dictionary.
        File dict = tmp.newFile("dict.txt");
        try (OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(dict), StandardCharsets.UTF_8)) {
            w.write("damn\n");
            w.write("hell\n");
            w.write("сука\n"); // "сука"
            w.write("\n");                          // blank line must be ignored
            w.write("  spaced  \n");                // surrounding whitespace must be trimmed
        }
        words = new Words();
        words.loadProfanityDictionary(dict.getAbsolutePath());
    }

    @Test
    public void detectsBadWordInPhrase() {
        assertTrue(words.containsBadWord("that was damn good"));
    }

    @Test
    public void detectsCyrillicBadWord() {
        assertTrue(words.containsBadWord("ты сука")); // "ты сука"
    }

    @Test
    public void matchingIsCaseInsensitive() {
        assertTrue(words.containsBadWord("DAMN it"));
    }

    @Test
    public void punctuationIsStrippedBeforeMatching() {
        assertTrue(words.containsBadWord("damn!!!"));
    }

    @Test
    public void trimsWhitespaceAroundDictionaryEntries() {
        assertTrue(words.containsBadWord("that was spaced out"));
    }

    @Test
    public void matchesWholeTokensNotSubstrings() {
        // "damnation" contains "damn" as a substring but is a different token.
        assertFalse(words.containsBadWord("damnation follows"));
    }

    @Test
    public void cleanPhraseIsNotFlagged() {
        assertFalse(words.containsBadWord("perfectly clean sentence"));
    }

    @Test
    public void emptyPhraseIsNotFlagged() {
        assertFalse(words.containsBadWord(""));
    }

    @Test
    public void nullPhraseIsNotFlagged() {
        assertFalse(words.containsBadWord(null));
    }

    @Test
    public void blankDictionaryLinesAreIgnored() {
        // The blank line in the fixture must not become an entry that matches "" tokens.
        assertFalse(words.containsBadWord("two  spaces")); // double space yields an empty token
    }

    @Test
    public void noMatchesBeforeDictionaryLoaded() {
        Words empty = new Words();
        assertFalse(empty.containsBadWord("damn"));
    }

    @Test
    public void missingDictionaryFileDoesNotThrow() {
        Words w = new Words();
        w.loadProfanityDictionary(tmp.getRoot().getAbsolutePath() + "/does-not-exist.txt");
        assertFalse(w.containsBadWord("damn"));
    }
}
