package flashcards_p;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class AddShowController {

    @FXML
    private TextField leftField;
    @FXML
    private TextField rightField;
    @FXML
    private VBox leftColumn;
    @FXML
    private VBox rightColumn;

    InsertData insertData = new InsertData();
    GetData getData = new GetData();


    @FXML
    protected void onSaveButtonClick() {
        insertData.insert(leftField.getText(), rightField.getText());
    }

    @FXML
    protected void onShowButtonClick() throws SQLException {
        String[][] ar = getData.get();
        leftColumn.getChildren().clear();
        rightColumn.getChildren().clear();
        for(int i = 0; i<ar[0].length; i++) {
            leftColumn.getChildren().add(new Label(ar[0][i]));
            leftColumn.getChildren().add(new Separator(Orientation.HORIZONTAL));
            rightColumn.getChildren().add(new Label(ar[1][i]));
            rightColumn.getChildren().add(new Separator(Orientation.HORIZONTAL));
        }
    }
}