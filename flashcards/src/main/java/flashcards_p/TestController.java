package flashcards_p;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TestController {
    CreateSet createSet = new CreateSet();

    @FXML
    private TextField text;

    @FXML
    protected void onTestButtonClick() {
        createSet.create(text.getText());
    }
}
