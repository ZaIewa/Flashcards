package flashcards_p;

public class SetNameSingleton {
    private static final SetNameSingleton INSTANCE = new SetNameSingleton();

    private String SetName;

    private SetNameSingleton() {}

    public static SetNameSingleton getInstance() {
        return INSTANCE;
    }
    public String getSetName() {
        return SetName;
    }
    public void setSetName(String setName) {
        SetName = setName;
    }
}
