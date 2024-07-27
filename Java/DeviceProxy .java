public class DeviceProxy implements Device {
    private Device device;

    public DeviceProxy(Device device) {
        this.device = device;
    }

    @Override
    public void turnOn() {
        System.out.println("Turning on " + device.getType() + " " + device.getId());
        device.turnOn();
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off " + device.getType() + " " + device.getId());
        device.turnOff();
    }

    @Override
    public String getStatus() {
        return device.getStatus();
    }

    @Override
    public int getId() {
        return device.getId();
    }

    @Override
    public String getType() {
        return device.getType();
    }
}
