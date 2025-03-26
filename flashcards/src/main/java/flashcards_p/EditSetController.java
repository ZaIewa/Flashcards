package flashcards_p;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class EditSetController {

    private String SetName;

    @FXML
    private ScrollPane mainPane;
    @FXML
    private TextField leftField;
    @FXML
    private TextField rightField;
    @FXML
    private VBox mainVBox;

    InsertData insertData = new InsertData();
    GetData getData = new GetData();
    DeleteData deleteData = new DeleteData();

    public void setSetName(String SetName) {
        this.SetName = SetName;
    }

    // On button press adds data from text fields into current table.
    @FXML
    protected void onAddButtonClick() {
        insertData.insert(SetName,leftField.getText(), rightField.getText());
    }

    // Initializes the whole scene. Didn't use initialize function because had to set the name of a set of flashcards before loading the scene.
    protected void loadEditSet() throws SQLException {

        // Gets all rows of Polish and English words from given table
        String[][] ar = getData.get(SetName);
        mainVBox.getChildren().clear();

        // Creates rows of Polish and English words with separators and button to delete a row
        for(int i = 0; i<ar[0].length; i++) {
            mainPane.setFitToWidth(true);
            HBox row = new HBox();
            mainVBox.getChildren().add(row);

            // Adds Polish word as 1st object
            Label plLabel = new Label(ar[0][i]);
            plLabel.prefWidthProperty().bind(row.widthProperty().divide(2).subtract(40));
            plLabel.setAlignment(Pos.CENTER);
            row.getChildren().add(plLabel);

            row.getChildren().add(new Separator(Orientation.VERTICAL));

            // Adds English word as 3rd object
            Label enLabel = new Label(ar[1][i]);
            enLabel.setAlignment(Pos.CENTER);
            enLabel.prefWidthProperty().bind(row.widthProperty().divide(2).subtract(40));
            row.getChildren().add(enLabel);

            // Adds button that will delete corresponding row from table/set.
            Button deleteButton = new Button("Delete");
            int finalI = i;
            deleteButton.setOnAction(event -> {
                try {
                    deleteData.delete(SetName, ar[0][finalI], ar[1][finalI]);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            deleteButton.prefWidthProperty().set(60);
            row.getChildren().add(deleteButton);

            mainVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        }
    }
}