package fxml_helloworld;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.collections.ObservableList;

public class FXMLPieController implements Initializable {
    ObservableList<Data> data = FXCollections.observableArrayList();

    @FXML
    private PieChart pcCar;

    @FXML
    private void addHandleAction(ActionEvent event) {
        data.add(new PieChart.Data("Alat tulis", 40));
        System.out.println("AddButton is clicked");
    }

    @FXML
    private void subtractHandleAction(ActionEvent event) {
        data.remove(3);
        System.out.println("SubtractButton is clicked");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.add(new PieChart.Data("Dekorasi", 50));
        data.add(new PieChart.Data("Mainan", 30));
        data.add(new PieChart.Data("Alat keseharian", 20));

        pcCar.setData(data);
    }
}
