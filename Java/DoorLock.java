public class DoorLock implements Device {
    private int id;
    private String status;

    public DoorLock(int id) {
        this.id = id;
        this.status = "locked";
    }

    @Override
    public void turnOn() {
        status = "locked";
    }

    @Override
    public void turnOff() {
        status = "unlocked";
    }

    @Override
    public String getStatus() {
        return "DoorLock " + id + " is " + status + ".";
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getType() {
        return "DoorLock";
    }
}
