package com.example.flashcards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class MainController {
    @FXML
    private Label leftLabel;
    @FXML
    private Label rightLabel;
    @FXML
    private TextField leftField;
    @FXML
    private TextField rightField;

    InsertData insertData = new InsertData();
    GetData getData = new GetData();

    @FXML
    protected void onSaveButtonClick() {
        insertData.insert(leftField.getText(), rightField.getText());
    }

    @FXML
    protected void onShowButtonClick() throws SQLException {
        String[][] ar = getData.get();
        String pl = String.join(",", ar[0]);
        String ang = String.join(",", ar[1]);
        leftLabel.setText(pl);
        rightLabel.setText(ang);
    }
}