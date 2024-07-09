package fxml_helloworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField login_username;
    @FXML
    private TextField login_showPassword;
    @FXML
    private PasswordField login_password;
    @FXML
    private CheckBox login_selecShowPassword;
    @FXML
    private Button login_Button;
    @FXML
    private Hyperlink login_registerAccount;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    private File xmlFile = new File("users.xml");

    @FXML
    private void handleLogin(ActionEvent event) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        String username = login_username.getText().trim();
        String password = login_password.getText().trim();

        System.out.println("Attempting login with Username: " + username + ", Password: " + password);

        if (authenticate(username, password)) {
            showAlert("Success", "Login successful");
            root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Main");
            stage.show();
        } else {
            showAlert("Error", "Invalid username or password");
        }
    }

    private boolean authenticate(String username, String password) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        if (!xmlFile.exists() || isFileEmptyOrCorrupted(xmlFile)) {
            return false;
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList users = doc.getElementsByTagName("User");

        for (int i = 0; i < users.getLength(); i++) {
            Element userElement = (Element) users.item(i);
            String storedUsername = userElement.getElementsByTagName("Username").item(0).getTextContent();
            String storedPassword = userElement.getElementsByTagName("Password").item(0).getTextContent();

            System.out.println("Stored Username: " + storedUsername + ", Stored Password: " + storedPassword);

            if (storedUsername.equals(username) && storedPassword.equals(password)) {
                return true;
            }
        }

        return false;
    }

    private boolean isFileEmptyOrCorrupted(File file) {
        try {
            if (file.length() == 0) {
                return true;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dBuilder.parse(file);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void change() {
        if (login_selecShowPassword.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_password.setVisible(true);
            login_showPassword.setVisible(false);
        }
    }

    @FXML
    private void switchToRegisterPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
