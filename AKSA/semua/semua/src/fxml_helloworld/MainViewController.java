package fxml_helloworld;

import java.io.File;
import java.net.URL;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.layout.BorderPane;

public class MainViewController {
    @FXML
    private Button btncermin;

    
    @FXML
    private BorderPane mainPane;
    
    @FXML
    private ImageView InfoBelanja;
    
    @FXML
    private Button btnChart;
    
    @FXML
    private Button btnUploadfoto;
    
    @FXML
    void detailCermin(ActionEvent event) throws Exception {
        URL fxmlUrl = getClass().getResource("/fxml_helloworld/ProductDetail.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        // Dapatkan stage dari event yang diterima
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Atur scene baru ke stage
        stage.setScene(new Scene(root, 700, 500));
        stage.setTitle("Detail Produk");
        // Tampilkan scene baru
        stage.show();
    }

    @FXML
    void keMultiScene(ActionEvent event) throws IOException{
        URL fxmlUrl = getClass().getResource("/fxml_helloworld/FXMLMultiScene.fxml");
        
        // Pastikan URL file FXML ditemukan
        if (fxmlUrl == null) {
            throw new IOException("File FXML tidak ditemukan");
        }

        // Load FXML
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        // Dapatkan stage dari event yang diterima
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Atur scene baru ke stage
        stage.setScene(new Scene(root, 700, 500));
        stage.setTitle("CharBelanja");
        // Tampilkan scene baru
        stage.show();
    }



    @FXML
    void uploadFoto(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
        
        // Atur judul dialog
        fileChooser.setTitle("Pilih File");

        // Set filter jenis file (opsional)
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"),
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        // Dapatkan Stage dari button atau elemen apapun di scene
        Stage stage = (Stage) btnUploadfoto.getScene().getWindow();

        // Tampilkan dialog dan ambil file yang dipilih
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // Lakukan sesuatu dengan file yang dipilih, misalnya menampilkan nama file
            System.out.println("File yang dipilih: " + selectedFile.getAbsolutePath());
    }
    }
}
