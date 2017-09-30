import org.tartarus.snowball.ext.PorterStemmer;

import java.util.LinkedList;


public class Results {
    private LinkedList<Word> results = new LinkedList();

    public LinkedList<Word> getResults() {
        return results;
    }


    public static class Word implements Comparable<Word> {
        private String word;
        private int totalOccurances = 0;
        private String sentenceIndexes = "";


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Word word1 = (Word) o;
            return word != null ? word.equals(word1.word) : word1.word == null;
        }

        @Override
        public int hashCode() {
            int result = word != null ? this.getStem().hashCode() : 0;
            return result;
        }

        public String getStem() {
            PorterStemmer stemmer = new PorterStemmer();
            stemmer.setCurrent(this.word);
            stemmer.stem();
            return stemmer.getCurrent();

        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public Integer getTotalOccurances() {
            return totalOccurances;
        }

        public void setTotalOccurances(int totalOccurances) {
            this.totalOccurances = totalOccurances;
        }

        public String getSentenceIndexes() {
            return sentenceIndexes;
        }

        public void setSentenceIndexes(String sentenceIndexes) {
            this.sentenceIndexes = this.sentenceIndexes.concat(sentenceIndexes);
        }

        @Override
        public int compareTo(Word otherWord) {
            return this.word.compareTo(otherWord.getWord());
        }
    }

}