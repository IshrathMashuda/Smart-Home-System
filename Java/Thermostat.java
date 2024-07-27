public class Thermostat implements Device {
    private int id;
    private int temperature;

    public Thermostat(int id) {
        this.id = id;
        this.temperature = 70;
    }

    @Override
    public void turnOn() {
        // Thermostat-specific logic for turning on
    }

    @Override
    public void turnOff() {
        // Thermostat-specific logic for turning off
    }

    @Override
    public String getStatus() {
        return "Thermostat " + id + " is set to " + temperature + " degrees.";
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getType() {
        return "Thermostat";
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }
}
