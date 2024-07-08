package fxml_helloworld;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class FXMLMultiSceneController implements Initializable{
    OpenScene openScene = new OpenScene();

    @FXML
    private BorderPane mainPane;

    @FXML
    public void handleButtonSceneBar(ActionEvent event) {
        try {
            // Muat file FXML menggunakan getResource
            URL fxmlUrl = getClass().getResource("/fxml_helloworld/FXMLbar.fxml");
            if (fxmlUrl != null) {
                FXMLLoader loader = new FXMLLoader(fxmlUrl);
                Pane page = loader.load();

                // Set pane baru ke dalam center dari mainPane
                mainPane.setCenter(page);
                System.out.println("Button Page 1 is clicked!");
            } else {
                System.err.println("Could not find the FXML file: /fxml_helloworld/FXMLbar.fxml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonSceneLine(ActionEvent event){
        try {
            // Muat file FXML menggunakan getResource
            URL fxmlUrl = getClass().getResource("/fxml_helloworld/FXMLLine.fxml");
            if (fxmlUrl != null) {
                FXMLLoader loader = new FXMLLoader(fxmlUrl);
                Pane page = loader.load();

                // Set pane baru ke dalam center dari mainPane
                mainPane.setCenter(page);
                System.out.println("Button Page 2 is clicked!");
            } else {
                System.err.println("Could not find the FXML file: /fxml_helloworld/FXMLLine.fxml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonScenePie(ActionEvent event){
        try {
            // Muat file FXML menggunakan getResource
            URL fxmlUrl = getClass().getResource("/fxml_helloworld/FXMLPie.fxml");
            if (fxmlUrl != null) {
                FXMLLoader loader = new FXMLLoader(fxmlUrl);
                Pane page = loader.load();

                // Set pane baru ke dalam center dari mainPane
                mainPane.setCenter(page);
                System.out.println("Button Page 3 is clicked!");
            } else {
                System.err.println("Could not find the FXML file: /fxml_helloworld/FXMLPie.fxml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}
