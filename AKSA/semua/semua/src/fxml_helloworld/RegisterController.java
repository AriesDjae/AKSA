package fxml_helloworld;

import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RegisterController {

    @FXML
    private Label label;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField username;

    @FXML
    private TextField showPassword;

    @FXML
    private TextField showcPassword;

    @FXML
    private PasswordField password;
    
    @FXML
    private PasswordField cpassword;

    @FXML
    private CheckBox selecShowPassword;  
    
    @FXML
    private CheckBox selecShowPassword1; 

    @FXML
    private Button ButtonRegister;

    // File XML untuk menyimpan data pengguna
    private File xmlFile = new File("users.xml");

    @FXML
    private void handleButtonRegisterAction(ActionEvent event) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        String firstName = fname.getText().trim();
        String lastName = lname.getText().trim();
        String userName = username.getText().trim();
        String passWord = password.getText().trim();
        String cPassWord = cpassword.getText().trim();

        // Log untuk memastikan password diambil dengan benar
        System.out.println("Password diambil dari field: " + passWord);

        // Pengecekan field kosong
        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || passWord.isEmpty() || cPassWord.isEmpty()) {
            showAlert("Error", "Input Semua Data Terlebih Dahulu!");
            return;
        }

        // Pengecekan kesamaan password
        if (!passWord.equals(cPassWord)) {
            showAlert("Error", "Passwords tidak sama!");
            return;
        }

        // Cek jika username sudah ada
        if (isUsernameTaken(userName)) {
            showAlert("Error", "Username sudah digunakan!");
            return;
        }

        // Simpan pengguna ke file XML
        User newUser = new User(firstName, lastName, userName, passWord);
        saveUserToXml(newUser);

        // Tampilkan pesan sukses dan pindah ke halaman login
        showAlert("Success", "Registrasi Berhasil");
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void saveUserToXml(User user) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc;

        if (!xmlFile.exists() || isFileEmptyOrCorrupted(xmlFile)) {
            doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("Users");
            doc.appendChild(rootElement);
        } else {
            doc = dBuilder.parse(xmlFile);
        }

        Element root = doc.getDocumentElement();

        Element userElement = doc.createElement("User");

        Element firstNameElement = doc.createElement("FirstName");
        firstNameElement.appendChild(doc.createTextNode(user.getFirstName() != null ? user.getFirstName() : ""));
        userElement.appendChild(firstNameElement);

        Element lastNameElement = doc.createElement("LastName");
        lastNameElement.appendChild(doc.createTextNode(user.getLastName() != null ? user.getLastName() : ""));
        userElement.appendChild(lastNameElement);

        Element usernameElement = doc.createElement("Username");
        usernameElement.appendChild(doc.createTextNode(user.getUserName() != null ? user.getUserName() : ""));
        userElement.appendChild(usernameElement);

        Element passwordElement = doc.createElement("Password");
        passwordElement.appendChild(doc.createTextNode(user.getPassword() != null ? user.getPassword() : ""));
        userElement.appendChild(passwordElement);

        Element statusElement = doc.createElement("Status");
        statusElement.appendChild(doc.createTextNode("Unverified"));
        userElement.appendChild(statusElement);

        root.appendChild(userElement);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);

        // Log untuk memastikan data tersimpan
        System.out.println("User saved: " + user.getUserName() + ", Password: " + user.getPassword());
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

    private boolean isUsernameTaken(String username) throws ParserConfigurationException, IOException, SAXException {
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
            if (storedUsername.equals(username)) {
                return true;
            }
        }

        return false;
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
        if (selecShowPassword.isSelected()) {
            showPassword.setText(password.getText());
            showPassword.setVisible(true);
            password.setVisible(false);
        } else {
            password.setText(showPassword.getText());
            password.setVisible(true);
            showPassword.setVisible(false);
        }
    }

    @FXML
    private void change1() {
        if (selecShowPassword1.isSelected()) {
            showcPassword.setText(cpassword.getText());
            showcPassword.setVisible(true);
            cpassword.setVisible(false);
        } else {
            cpassword.setText(showcPassword.getText());
            cpassword.setVisible(true);
            showcPassword.setVisible(false);
        }
    }
}
