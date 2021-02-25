package temperature.ViewModel;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import temperature.model.TemperatureList;
import temperature.model.TemperatureModel;

import java.beans.PropertyChangeEvent;

public class TemperaturViewModel2 {

    private final TemperatureModel model;
    private TemperatureList temperatureList;

    private DoubleProperty x;
    private DoubleProperty y;
    private DoubleProperty z;

    private StringProperty updateTimeStamp;

    public TemperaturViewModel2(TemperatureModel model) {
        this.model = model;
        x = new SimpleDoubleProperty();
        y = new SimpleDoubleProperty();
        z = new SimpleDoubleProperty();
        updateTimeStamp = new SimpleStringProperty("Last update: ");
        model.addListener("AddTemperature", evt -> updateBarChart(evt));
        model.addListener("AddTemperature", evt -> timeStampUpdated(evt));

    }

    public StringProperty updateTimeStampProperty() {
        return updateTimeStamp;
    }

    public void updateBarChart(PropertyChangeEvent evt) {
        if (model.getLastInsertedTemperature("0")== null||model.getLastInsertedTemperature("1")==null||model.getLastInsertedTemperature("2")==null)
            return;
        try {
            Platform.runLater(() -> {
                x.setValue(model.getLastInsertedTemperature("0").getValue());
                y.setValue(model.getLastInsertedTemperature("1").getValue());
                z.setValue(model.getLastInsertedTemperature("2").getValue());
            });
        } catch (NullPointerException e) {

            //
        }
    }

    private void timeStampUpdated(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            updateTimeStamp.setValue("Last updated: " + evt.getNewValue());
        });
    }


    public ObservableValue xProperty() {
        return x;
    }

    public ObservableValue yProperty() {
        return y;
    }

    public ObservableValue zProperty() {
        return z;
    }
}

