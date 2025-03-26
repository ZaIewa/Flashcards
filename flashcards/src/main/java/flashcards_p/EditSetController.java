package flashcards_p;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class EditSetController {

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


    @FXML
    protected void onAddButtonClick() {
        insertData.insert(leftField.getText(), rightField.getText());
    }

    @FXML
    protected void initialize() throws SQLException {
        String[][] ar = getData.get();
        mainVBox.getChildren().clear();

        for(int i = 0; i<ar[0].length; i++) {
            mainPane.setFitToWidth(true);
            HBox row = new HBox();
            mainVBox.getChildren().add(row);

            Label plLabel = new Label(ar[0][i]);
            plLabel.prefWidthProperty().bind(row.widthProperty().divide(2).subtract(40));
            plLabel.setAlignment(Pos.CENTER);
            row.getChildren().add(plLabel);

            row.getChildren().add(new Separator(Orientation.VERTICAL));

            Label enLabel = new Label(ar[1][i]);
            enLabel.setAlignment(Pos.CENTER);
            enLabel.prefWidthProperty().bind(row.widthProperty().divide(2).subtract(40));
            row.getChildren().add(enLabel);

            Button deleteButton = new Button("Delete");
            int finalI = i;
            deleteButton.setOnAction(event -> {
                try {
                    deleteData.delete("fiszki", ar[0][finalI], ar[1][finalI]);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            deleteButton.prefWidthProperty().set(60);
            row.getChildren().add(deleteButton);
            /*
            row.getChildren().add(new Label(ar[0][i]));
            row.getChildren().add(new Separator(Orientation.VERTICAL));
            row.getChildren().add(new Label(ar[1][i]));
            row.getChildren().add(new Separator(Orientation.VERTICAL));
            row.getChildren().add(new Button("Delete"));*/

            mainVBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        }
    }
}