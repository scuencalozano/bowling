package bowling;

public interface Game {

    String SEPARATOR = "\t";
    String REGEX_WHITESPACES = "\\s+";
    String LINE_SEPARATOR = System.getProperty("line.separator");

    void calculateScore();

    void showResults();

    /*
     * Evaluates if all input data are correct.
     */
    boolean isValid();
}
