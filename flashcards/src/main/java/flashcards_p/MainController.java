package flashcards_p;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class MainController {

    GetSets getSet = new GetSets();
    DeleteSet deleteSet = new DeleteSet();
    Reloader reloader = new Reloader();
    CreateSet createSet = new CreateSet();
    DataSingleton dataSingleton = DataSingleton.getInstance();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ScrollPane mainPane;
    @FXML
    private VBox mainVbox;
    @FXML
    private TextField textField;

    @FXML
    public void initialize() throws SQLException {
        mainPane.setFitToWidth(true);
        // Gets names of all sets of flashcards
        String[] sets = getSet.get();
        mainVbox.getChildren().clear();

        // Creates rows with set names, edit and delete buttons
        for(int i = 0; i<sets.length; i++) {
            HBox hBoxMain = new HBox();
            hBoxMain.setPadding(new Insets(20,20,20,20));

            // Creates containers for names
            HBox hBoxNames = new HBox();
            hBoxNames.setMinWidth(140);
            hBoxNames.setSpacing(20);

            // Creates containers for buttons
            HBox hBoxButtons = new HBox();
            hBoxButtons.setMinWidth(420);
            hBoxButtons.setSpacing(10);
            hBoxButtons.setAlignment(Pos.CENTER_RIGHT);

            mainVbox.getChildren().add(hBoxMain);
            hBoxMain.getChildren().add(hBoxNames);
            hBoxMain.getChildren().add(hBoxButtons);

            Label label = new Label(sets[i]);
            hBoxNames.getChildren().add(label);

            int finalI = i;

            Button polishButton = new Button();
            polishButton.setText("Show Polish");
            polishButton.setOnAction(e -> {
                dataSingleton.setSetName(sets[finalI]);
                dataSingleton.setLanguage("Polish");
                try {
                    reloader.reload("quiz-view.fxml", (Stage)mainPane.getScene().getWindow());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            hBoxButtons.getChildren().add(polishButton);

            Button englishButton = new Button();
            englishButton.setText("Show English");
            englishButton.setOnAction(e -> {
                dataSingleton.setSetName(sets[finalI]);
                dataSingleton.setLanguage("English");
                try {
                    reloader.reload("quiz-view.fxml", (Stage)mainPane.getScene().getWindow());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            hBoxButtons.getChildren().add(englishButton);

            // Creates Edit buttons that on press change scene to edit-set-view.fxml of respective set
            Button editbutton = new Button();
            editbutton.setText("Edit");
            editbutton.setOnAction(e -> {
                try {
                    dataSingleton.setSetName(sets[finalI]);
                    reloader.reload("edit-set-view.fxml", (Stage)mainPane.getScene().getWindow());
                } catch (IOException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            hBoxButtons.getChildren().add(editbutton);

            // Creates Delete buttons that on press will delete respective set of flashcards
            Button deletebutton = new Button();
            deletebutton.setText("Delete");
            deletebutton.setOnAction(e -> {
                deleteSet.delete(sets[finalI]);
                try {
                    reloader.reload("main-view.fxml", (Stage)mainPane.getScene().getWindow());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            hBoxButtons.getChildren().add(deletebutton);

            mainVbox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        }

    }

    // Creates new set & reloads the scene to show it
    public void onAddButtonPress(ActionEvent actionEvent) throws SQLException, IOException {
        createSet.create(textField.getText());
        reloader.reload("main-view.fxml", (Stage)mainPane.getScene().getWindow());
    }

}
