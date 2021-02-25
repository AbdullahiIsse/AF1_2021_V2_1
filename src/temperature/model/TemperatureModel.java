package temperature.model;

public interface TemperatureModel extends NamedPropertyChangeSubject
{
  void addTemperature(String id, double temperature);

  Temperature getLastInsertedTemperature();

  Temperature getLastInsertedTemperature(String id);



  void turnUp();
  void turnDown();
  int getPower();

}
