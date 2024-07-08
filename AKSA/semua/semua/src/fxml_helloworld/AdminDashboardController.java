package fxml_helloworld;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, String> firstNameColumn;

    @FXML
    private TableColumn<User, String> lastNameColumn;

    @FXML
    private TableColumn<User, String> statusColumn;

    @FXML
    private Button verifyButton;

    @FXML
    private Button refreshButton;

    private File xmlFile = new File("users.xml");

    private ObservableList<User> usersList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadUsersData();
    }

    @FXML
    private void handleVerifyAction() {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            selectedUser.setStatus("Verified");
            updateXmlFile(selectedUser);
            loadUsersData();
            showAlert("Success", "User verified successfully");
        } else {
            showAlert("Error", "Please select a user to verify");
        }
    }

    @FXML
    private void handleRefreshAction() {
        loadUsersData();
    }

    private void loadUsersData() {
        usersList.clear();
        try {
            if (xmlFile.exists()) {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();

                NodeList nList = doc.getElementsByTagName("User");

                for (int i = 0; i < nList.getLength(); i++) {
                    Element userElement = (Element) nList.item(i);
                    String username = userElement.getElementsByTagName("Username").item(0).getTextContent();
                    String firstName = userElement.getElementsByTagName("FirstName").item(0).getTextContent();
                    String lastName = userElement.getElementsByTagName("LastName").item(0).getTextContent();
                    String status = userElement.getElementsByTagName("Status").item(0).getTextContent();

                    usersList.add(new User(firstName, lastName, username, status));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        usersTable.setItems(usersList);
    }

    private void updateXmlFile(User user) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            NodeList nList = doc.getElementsByTagName("User");

            for (int i = 0; i < nList.getLength(); i++) {
                Element userElement = (Element) nList.item(i);
                String username = userElement.getElementsByTagName("Username").item(0).getTextContent();

                if (username.equals(user.getUserName())) {
                    userElement.getElementsByTagName("Status").item(0).setTextContent(user.getStatus());
                    break;
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
