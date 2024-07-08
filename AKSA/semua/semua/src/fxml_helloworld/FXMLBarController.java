package fxml_helloworld;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class FXMLBarController implements Initializable {
    XYChart.Series data = new XYChart.Series<>();

    @FXML
    private BarChart bcCar;

    @FXML
    private void addHandleAction(ActionEvent event) {
        data.getData().add(new XYChart.Data<>("Alat tulis", 400));
        System.out.println("AddButton is clicked");
    }

    @FXML
    private void subtractHandleAction(ActionEvent event) {
        data.getData().remove(3);
        System.out.println("SubtractButton is clicked");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.getData().add(new XYChart.Data("Dekorasi", 300));
        data.getData().add(new XYChart.Data("Mainan", 200));
        data.getData().add(new XYChart.Data("Alat keseharian", 500));
        bcCar.getData().add(data);
    }
}
