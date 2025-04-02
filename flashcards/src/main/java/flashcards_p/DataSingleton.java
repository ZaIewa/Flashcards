package flashcards_p;

public class DataSingleton {
    private static final DataSingleton INSTANCE = new DataSingleton();

    private String SetName;
    private String language;

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
}
