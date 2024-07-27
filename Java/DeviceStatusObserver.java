public class DeviceStatusObserver implements Observer {
    @Override
    public void update(Device device) {
        System.out.println("Device status updated: " + device.getStatus());
    }
}
