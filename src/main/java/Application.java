import java.util.*;


public class Application {
    private static final String text = "Take this paragraph of text and return an alphabetized list of ALL unique words.  A unique word is any form of a word often communicated with essentially the same meaning. For example, fish and fishes could be defined as a unique word by using their stem fish. For each unique word found in this entire paragraph, determine the how many times the word appears in total. Also, provide an analysis of what sentence index position or positions the word is found. The following words should not be included in your analysis or result set: \"a\", \"the\", \"and\", \"of\", \"in\", \"be\", \"also\" and \"as\".  Your final result MUST be displayed in a readable console output in the same format as the JSON sample object shown below.";

    private static HashMap<Integer, Integer> uniqueWords = new HashMap();
    private static HashSet stems = new HashSet();


    public static void main(String[] args) {

        textAnalize(text);
    }

    private static void textAnalize(String text) {
        Map<Integer, Results.Word> unique = new HashMap();
        TreeSet<String> stems = new TreeSet<>();
        ArrayList sentences = Helper.getSentences(text);
        //sentences clean and separated in sentences text
        //Analyze each sentence
        for (int i = 0; i < sentences.size(); i++) {
            //Analyze each word in sentence
            for (String word : sentences.get(i).toString().replaceAll("[^a-zA-Z ]", "").split("\\s+")) {
                Results.Word input = new Results.Word();
                input.setWord(word);
                if (!stems.contains(input.getStem())) {
                    input.setTotalOccurances(input.getTotalOccurances() + 1);
                    input.setSentenceIndexes(" [" + i + "] ");
                    unique.put(input.getStem().hashCode(), input);
                    stems.add(input.getStem());
                } else {
                    input = unique.get(input.getStem().hashCode());
                    input.setTotalOccurances(input.getTotalOccurances() + 1);
                    input.setSentenceIndexes(" [" + i + "] ");
                    unique.put(input.getStem().hashCode(), input);
                }
            }

        }
        Results results = new Results();
        List orderedList = results.getResults();

        unique.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Results.Word::getWord)))
                .forEach(x -> orderedList.add(x.getValue()));

        Helper.printToJsonResults(results);
    }
}
