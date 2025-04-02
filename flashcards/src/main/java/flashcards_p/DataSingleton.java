package flashcards_p;

import java.util.ArrayList;

public class DataSingleton {
    private static final DataSingleton INSTANCE = new DataSingleton();

    private String SetName;
    private String language;
    private ArrayList<String[]> correctAnswers;
    private ArrayList<String[]> wrongAnswers;

    private DataSingleton() {}

    public static DataSingleton getInstance() {
        return INSTANCE;
    }
    public String getSetName() {
        return SetName;
    }
    public void setSetName(String setName) {
        SetName = setName;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public ArrayList<String[]> getCorrectAnswers() {
        return correctAnswers;
    }
    public void setCorrectAnswers(ArrayList<String[]> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
    public ArrayList<String[]> getWrongAnswers() {
        return wrongAnswers;
    }
    public void setWrongAnswers(ArrayList<String[]> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }
}
