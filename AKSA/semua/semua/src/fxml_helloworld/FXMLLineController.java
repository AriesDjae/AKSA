package fxml_helloworld;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class FXMLLineController implements Initializable {
    XYChart.Series data = new XYChart.Series<>();

    @FXML
    private LineChart lcCar;

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
        data.getData().add(new XYChart.Data("Dekorasi", 200));
        data.getData().add(new XYChart.Data("Mainan", 400));
        data.getData().add(new XYChart.Data("Alat kesharian", 100));
        
        lcCar.getData().addAll(data);
    }
}

