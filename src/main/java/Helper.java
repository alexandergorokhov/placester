import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.icu.text.BreakIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Helper {
    private static List<String> toRemove = Arrays.asList("a", "the", "and", "of", "in", "be", "also", "as");

    private static String cleanUpText(String text) {
        text = text.replaceAll("[^a-zA-Z. ]", "").toLowerCase();
        String delimiter = " ";
        text = Pattern.compile(delimiter).splitAsStream(text)
                .filter(s -> !toRemove.contains(s))
                .collect(Collectors.joining(delimiter));

        return text;

    }

    public static ArrayList getSentences(String text) {
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        ArrayList<String> sentences = new ArrayList();
        String source = text;
        iterator.setText(source);
        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {
            sentences.add(cleanUpText(source.substring(start, end)));
        }
        return sentences;
    }

    public static void printToJsonResults(Results results) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(results));


    }
}
