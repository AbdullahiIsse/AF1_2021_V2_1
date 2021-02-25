package temperature.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import temperature.ViewModel.TemperaturViewModel2;

public class TemperatureViewController2 {

  private ViewHandler viewHandler;
  private Region root;
  private TemperaturViewModel2 viewModel;
  @FXML
  Label eventLabel;

  @FXML
  BarChart barChart;

  private XYChart.Data<String, Integer> x = new XYChart.Data<String, Integer>("Udendørs", 0);

  private XYChart.Data<String, Integer> y = new XYChart.Data<String, Integer>("Langt fra radiator", 0);

  private XYChart.Data<String, Integer> z = new XYChart.Data<String, Integer>("Tæt på radiator", 0);

  public TemperatureViewController2()
  {
  }

  public void init(ViewHandler viewHandler, TemperaturViewModel2 viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    x.YValueProperty().bind(viewModel.xProperty());
    y.YValueProperty().bind(viewModel.yProperty());
    z.YValueProperty().bind(viewModel.zProperty());

    eventLabel.textProperty().bind(viewModel.updateTimeStampProperty());

    XYChart.Series yellowSeries = new XYChart.Series();
    yellowSeries.setName("Udendørs");
    yellowSeries.getData().add(x);
    barChart.getData().add(yellowSeries);

    XYChart.Series greenSeries = new XYChart.Series();
    greenSeries.setName("Langt fra radiator");
    greenSeries.getData().add(y);
    barChart.getData().add(greenSeries);

    XYChart.Series redSeries = new XYChart.Series();
    redSeries.setName("Tæt på radiator");
    redSeries.getData().add(z);
    barChart.getData().add(redSeries);

  }


  public Region getRoot() {
    return root;
  }


  public void back(ActionEvent actionEvent) {

    viewHandler.openView("temperature");



  }
}
