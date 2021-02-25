package temperature.ViewModel;

import StatePattern.RadiatorState;
import StatePattern.offState;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import temperature.model.TemperatureList;
import temperature.model.TemperatureModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperaturViewModel implements PropertyChangeListener
{


    private final TemperatureModel model;
    private TemperatureList temperatureList;
    private StringProperty Temperatur;
    private StringProperty id;
    private StringProperty filterlabel;
    private String thermometerId;
    private StringProperty MAX;
    private StringProperty MIN;

    private RadiatorState radiatorState;

    private StringProperty temperatur1;
    private StringProperty temperatur2;
    private StringProperty getPower;
    private offState offState;
    private StringProperty getoverheat;
    private double min;
    private double max;


    /*public void getLastTemp() {
    getLastTemp() = temperatureList.getLastTemperature();
        }*/

        public TemperaturViewModel(TemperatureModel model) {
            this.model=model;
            Temperatur = new SimpleStringProperty();
            id = new SimpleStringProperty();
            filterlabel = new SimpleStringProperty();
            temperatur1 = new SimpleStringProperty();
            temperatur2 = new SimpleStringProperty();
            getPower = new SimpleStringProperty();
            getoverheat = new SimpleStringProperty();
            MAX=new SimpleStringProperty();
            MIN=new SimpleStringProperty();
            model.addListener("radiator",this);
            model.addListener("AddTemperature",this);
        }

    public StringProperty temperaturProperty() {
        return Temperatur;
    }
    public StringProperty temperaturProperty1() {
        return temperatur1;
    }
    public StringProperty temperaturProperty2() { return temperatur2;}
    public StringProperty getGetoverheat(){return getoverheat;}

//public StringProperty overheat1(){return overheat();}
    public StringProperty getPowerProperty(){return getPower;}


    public StringProperty filterProperty() {
        return id;
    }

    public StringProperty filterLabelProperty() {
        return filterlabel;
    }
    public StringProperty MAXProperty(){return MIN;}
    public StringProperty MINProperty(){return MAX;}

    public String getMAX()
    {
        return MAX.get();
    }

    public void maxtemp(){
        String oldValue = MIN.getValue();
        model.getLastInsertedTemperature("2") ;
        if (thermometerId.isEmpty())
        {
            thermometerId = null;
            filterlabel.setValue("All");
        }
        else
        {
            filterlabel.setValue(thermometerId);
        }
        id.setValue(null);
        update();

    }

    public void overheat()
    {
    if ( model.getLastInsertedTemperature("2").getValue() > max){getoverheat.setValue("skru ned nu!!!");}
    else if (model.getLastInsertedTemperature("1").getValue()<min){
        getoverheat.setValue("Skru op jeg fryser");
    }
    else getoverheat.setValue("Det hele er okay");
        }


        public void gem(){

         min = Double.parseDouble(MIN.getValue());
         max = Double.parseDouble(MAX.getValue());

        }



    public void update() {
            if (model.getLastInsertedTemperature("0")== null||model.getLastInsertedTemperature("1")==null||model.getLastInsertedTemperature("2")==null)
                return;
        try{
            Platform.runLater(()->{
              Temperatur.setValue(("Undendørs") + ": " + model.getLastInsertedTemperature("0").toString());
                temperatur1.setValue(("Langt fra radiator") + ": " + model.getLastInsertedTemperature("1").toString());
                temperatur2.setValue(("tæt på radiator") + ": " + model.getLastInsertedTemperature("2").toString());
                getPower.setValue("Stages: " + model.getPower());
                overheat();
            });

        }
        catch (NullPointerException e)
        {

            unFilter();
        }

    }

    public void unFilter() {
        String oldValue = filterlabel.getValue();
       // if (oldValue.equals("All"))
        //{
        //    oldValue = null;
        //}
        thermometerId = id.getValue();
        if (thermometerId.isEmpty())
        {
            thermometerId = null;
            filterlabel.setValue("All");
        }
        else
        {
            filterlabel.setValue(thermometerId);
        }
        id.setValue(null);
        update();
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        update();
    }

    public void turnUp(){
           this.model.turnUp();
        System.out.println("hej");
    }

    public void turnDown(){
        this.model.turnDown();
    }

    public int getPower(){
            return
            this.model.getPower();
    }


}