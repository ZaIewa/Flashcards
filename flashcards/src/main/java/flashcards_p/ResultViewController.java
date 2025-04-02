package flashcards_p;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultViewController {

    private ArrayList<String[]> correctAnswers;
    private ArrayList<String[]> wrongAnswers;

    DataSingleton dataSingleton = DataSingleton.getInstance();
    Reloader reloader = new Reloader();

    @FXML
    private Label percent;
    @FXML
    private Label points;
    @FXML
    private ScrollPane mainPane;
    @FXML
    private VBox correctBox;
    @FXML
    private VBox wrongBox;

    public void initialize(){
        // Gets rid of bottom scroll
        mainPane.setFitToWidth(true);

        correctAnswers = dataSingleton.getCorrectAnswers();
        wrongAnswers = dataSingleton.getWrongAnswers();
        int correctAnswerCount = correctAnswers.size();
        int wrongAnswerCount = wrongAnswers.size();
        double percentage = (correctAnswerCount * 100.0) / (wrongAnswerCount + correctAnswerCount);
        percent.setText(String.format("%.2f", percentage) + "%");
        points.setText(correctAnswerCount + "/" + (wrongAnswerCount+correctAnswerCount));

        createRows(correctAnswerCount, correctBox, correctAnswers);
        createRows(wrongAnswerCount, wrongBox, wrongAnswers);
    }

    private void createRows(int AnswerCount, VBox tBox, ArrayList<String[]> Answers) {
        for(int i = 0; i < AnswerCount; i++){
            HBox row = new HBox();
            row.setAlignment(Pos.CENTER);
            tBox.getChildren().add(row);

            Label plLabel = new Label(Answers.get(i)[0]);
            plLabel.prefWidthProperty().bind(row.widthProperty().divide(2).subtract(40));
            plLabel.setAlignment(Pos.CENTER);
            row.getChildren().add(plLabel);

            row.getChildren().add(new Separator(Orientation.VERTICAL));

            Label enLabel = new Label(Answers.get(i)[1]);
            enLabel.prefWidthProperty().bind(row.widthProperty().divide(2).subtract(40));
            enLabel.setAlignment(Pos.CENTER);
            row.getChildren().add(enLabel);
        }
    }

    public void onBackButtonPress(ActionEvent actionEvent) throws SQLException, IOException {
        reloader.reload("main-view.fxml",(Stage)mainPane.getScene().getWindow());
    }
}
