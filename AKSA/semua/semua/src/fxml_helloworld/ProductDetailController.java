package fxml_helloworld;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ProductDetailController {

    @FXML
    private Button btnPembayaran;

    @FXML
    void belanja(ActionEvent event) throws Exception{
        URL fxmlUrl = getClass().getResource("/fxml_helloworld/PembayaranBerhasil.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        // Dapatkan stage dari event yang diterima
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Atur scene baru ke stage
        stage.setScene(new Scene(root, 700, 500));
        stage.setTitle("Pembayaran");
        // Tampilkan scene baru
        stage.show();
    }

}
