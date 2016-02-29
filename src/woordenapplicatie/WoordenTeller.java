package woordenapplicatie;

import java.text.Normalizer;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by david on 2/22/16.
 */
public class WoordenTeller {
    private String input;
    private String[] words;
    private HashMap<String, Integer> occurences;
    private final static String seperator = " ";

    public String getInput() {
        return input;
    }

    public WoordenTeller(String input) {
        // init
        occurences = new HashMap<>();

        // sanitize input
        input = input.replace("\n", " ");
        input = input.replace(".", "");
        input = input.replace(",", "");

        this.input = input;

        words = input.split(seperator);

        // Split
        for (String word : input.split(seperator)) {
            if (!word.isEmpty())
                addOccurence(word);
        }
    }

    private int addOccurence(String word) {
        String normalized = Normalizer.normalize(word, Normalizer.Form.NFKC);
        if (occurences.containsKey(normalized)) {
            occurences.put(normalized, occurences.get(normalized) + 1);
        } else {
            occurences.put(normalized, 1);
        }

        return occurences.get(normalized);
    }

    public int GetTotalWordCount() {
        return occurences.values().stream().map((count) -> count).reduce((x, y) -> x + y).get();
    }

    public int GetUniqueWordCount() {
        return occurences.keySet().size();
    }

    public Collection<String> GetReversedWordOrder() {
        List<String> collection = new LinkedList<>();

        collection.addAll(occurences.keySet());

        collection.sort((o1, o2) -> {
            // http://stackoverflow.com/questions/11176227/simple-way-to-sort-strings-in-the-case-sensitive-alphabetical-order
            int res = String.CASE_INSENSITIVE_ORDER.compare(o2, o1);
            if (res == 0) {
                res = o2.compareTo(o1);
            }
            return res;
        });

        return collection;
    }

    public HashMap<String, Integer> GetWordCount() {
        return occurences;
    }

    public Map<String, Integer> GetOrderedWordCount() {
        Map<String, Integer> result = new LinkedHashMap<>();
        Stream<Map.Entry<String, Integer>> st = occurences.entrySet().stream();

        st.sorted(Comparator.comparing(e -> 0-e.getValue()))
                .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

    public Map<String, Set<Integer>> GetConcondances() {
        Map<String, Set<Integer>> concordance = new HashMap<String, Set<Integer>>();

        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            if (s.isEmpty())
                continue;
            if (!concordance.containsKey(s)) {
                concordance.put(s, new HashSet<>());
            }
            Set<Integer> set = concordance.get(s);
            set.add(i);
        }

        return concordance;
    }
}
