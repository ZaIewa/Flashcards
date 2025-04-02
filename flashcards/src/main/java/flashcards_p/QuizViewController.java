package flashcards_p;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Random;

public class QuizViewController {

    private String SetName;
    private String language;
    private int iterator;
    private Random rand = new Random();
    private int randomIndex;
    private int correctAnswer = 0;
    private String[][] ar;

    DataSingleton dataSingleton = DataSingleton.getInstance();
    GetData getData = new GetData();

    @FXML
    private Label Prompt;
    @FXML
    private TextField AnswerField;

    public void initialize() throws SQLException {
        SetName = dataSingleton.getSetName();
        language = dataSingleton.getLanguage();

        ar = getData.get(SetName);
        iterator = ar[0].length;
        randomIndex = rand.nextInt(iterator);
        if (language.equals("Polish")) {
            Prompt.setText(ar[0][randomIndex]);
        }else{
            Prompt.setText(ar[1][randomIndex]);
        }
    }

    public void onSubmitButtonPress(ActionEvent actionEvent) {
        String answer = AnswerField.getText();
        if (language.equals("Polish")) {
            if (answer.equals(ar[1][randomIndex])) {
                correctAnswer += 1;
            }
        }else{
            if (answer.equals(ar[0][randomIndex])) {
                correctAnswer += 1;
            }
        }

        ar[0][randomIndex] = ar[0][iterator-1];
        ar[1][randomIndex] = ar[1][iterator-1];
        iterator--;
        if (iterator == 0) {
            Prompt.setText(String.valueOf(correctAnswer));
        }else{
            randomIndex = rand.nextInt(iterator);
            if (language.equals("Polish")) {
                Prompt.setText(ar[0][randomIndex]);
            }else{
                Prompt.setText(ar[1][randomIndex]);
            }
        }
    }
}
