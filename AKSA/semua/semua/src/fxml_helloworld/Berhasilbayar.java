package fxml_helloworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Berhasilbayar {

    @FXML
    private Button btnKembali;

    @FXML
    private Button btnSelanjutnya;
    @FXML
    private Stage stage;
    private Scene scene;

    @FXML
    void keHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainBeliView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void keScenekurir(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Paket.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
