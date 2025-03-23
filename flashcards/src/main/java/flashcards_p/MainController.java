package flashcards_p;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class MainController {
    GetSets getSet = new GetSets();

    @FXML
    private ScrollPane mainPane;
    @FXML
    private VBox mainVbox;

    @FXML
    public void initialize() throws SQLException {
        mainPane.setFitToWidth(true);
        String[] sets = getSet.get();
        mainVbox.getChildren().clear();
        for(int i = 0; i<sets.length; i++) {
            HBox hBoxMain = new HBox();
            hBoxMain.setPadding(new Insets(20,20,20,20));

            HBox hBoxNames = new HBox();
            hBoxNames.setMinWidth(280);
            hBoxNames.setSpacing(20);

            HBox hBoxButtons = new HBox();
            hBoxButtons.setMinWidth(280);
            hBoxButtons.setSpacing(20);
            hBoxButtons.setAlignment(Pos.CENTER_RIGHT);

            mainVbox.getChildren().add(hBoxMain);
            hBoxMain.getChildren().add(hBoxNames);
            hBoxMain.getChildren().add(hBoxButtons);

            Label label = new Label(sets[i]);
            hBoxNames.getChildren().add(label);

            Button editbutton = new Button();
            editbutton.setText("Edit");
            //editbutton.setOnAction(e -> {})
            hBoxButtons.getChildren().add(editbutton);
            Button deletebutton = new Button();
            deletebutton.setText("Delete");
            //deletebutton.setOnAction(e -> {})
            hBoxButtons.getChildren().add(deletebutton);

            mainVbox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        }

    }


}
