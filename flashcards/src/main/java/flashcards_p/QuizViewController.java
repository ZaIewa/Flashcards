package flashcards_p;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class QuizViewController {

    private String SetName;
    private String language;
    private int iterator;
    private Random rand = new Random();
    private int randomIndex;
    private String[][] ar;
    private ArrayList<String[]> correctAnswers = new ArrayList<String[]>();
    private ArrayList<String[]> wrongAnswers = new ArrayList<String[]>();

    DataSingleton dataSingleton = DataSingleton.getInstance();
    GetData getData = new GetData();
    Reloader reloader = new Reloader();

    @FXML
    private AnchorPane mainPane;
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

    public void onSubmitButtonPress(ActionEvent actionEvent) throws SQLException, IOException {
        String answer = AnswerField.getText();
        if (language.equals("Polish")) {
            if (answer.equals(ar[1][randomIndex])) {
                correctAnswers.add(new String[]{ar[0][randomIndex], ar[1][randomIndex]});
            } else{
                wrongAnswers.add(new String[]{ar[0][randomIndex], ar[1][randomIndex]});
            }
        }else{
            if (answer.equals(ar[0][randomIndex])) {
                correctAnswers.add(new String[]{ar[0][randomIndex], ar[1][randomIndex]});
            } else{
                wrongAnswers.add(new String[]{ar[0][randomIndex], ar[1][randomIndex]});
            }
        }

        ar[0][randomIndex] = ar[0][iterator-1];
        ar[1][randomIndex] = ar[1][iterator-1];
        iterator--;
        if (iterator == 0) {
            dataSingleton.setCorrectAnswers(correctAnswers);
            dataSingleton.setWrongAnswers(wrongAnswers);
            reloader.reload("result-view.fxml", (Stage)mainPane.getScene().getWindow());
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
