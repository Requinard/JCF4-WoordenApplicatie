package woordenapplicatie;

import org.junit.Before;
import org.junit.Test;
import woordenapplicatie.gui.WoordenController;

import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by david on 2/22/16.
 */
public class WoordenTellerTest {
    WoordenTeller teller;
    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "Heb je dan geen hoedje meer\n" +
            "Maak er één van bordpapier\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "En als het hoedje dan niet past\n" +
            "Zetten we 't in de glazenkas\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier";

    @Before
    public void setUp() {
        teller = new WoordenTeller(DEFAULT_TEXT);
    }


    @org.junit.Test
    public void testGetTotalWordCount() throws Exception {
        assertEquals(teller.GetTotalWordCount(), 16);
    }

    @Test
    public void testGetUniqueWordCount() throws Exception {
        assertEquals(teller.GetUniqueWordCount(), 7);
    }

    @Test
    public void testGetReversedWordOrder() throws Exception {
        Collection<String> collection = teller.GetReversedWordOrder();

        collection.stream().forEach((word) -> System.out.println(word));

        assertTrue(collection.toArray()[0].toString().startsWith("Zetten"));
        assertTrue(collection.toArray()[collection.size() -1].toString().startsWith("Een"));
    }


    @Test
    public void testGetWordCount() throws Exception {
        HashMap<String, Integer> wordCount = teller.getWordCount();

        assertTrue(wordCount.get("Een, twee, drie, vier") == 6);
    }
}