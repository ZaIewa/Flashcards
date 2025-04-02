package flashcards_p;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class Reloader {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Requires fxml file as sceneName, and Stage usually from "(Stage)mainPane.getScene().getWindow()" line. Loads different scene
    public void reload(String sceneName, Stage _stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneName));
        root = fxmlLoader.load();
        stage = _stage;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
