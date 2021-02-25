package temperature.model;

public class ModelFactory {

    private TemperatureModel temperatureModel;

    public TemperatureModel getTemperatureModel() {
        if(temperatureModel == null) {
            temperatureModel = new TemperatureModelManager();
        }
        return temperatureModel;
    }

}
