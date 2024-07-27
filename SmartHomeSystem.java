import java.util.*;

// Observer Pattern
interface Observer {
    void update(Device device);
}

class DeviceStatusObserver implements Observer {
    @Override
    public void update(Device device) {
        System.out.println("Device status updated: " + device.getStatus());
    }
}

// Device Interface
interface Device {
    void turnOn();
    void turnOff();
    String getStatus();
    int getId();
    String getType();
}

// Concrete Devices
class Light implements Device {
    private int id;
    private String status;

    public Light(int id) {
        this.id = id;
        this.status = "off";
    }

    @Override
    public void turnOn() {
        status = "on";
    }

    @Override
    public void turnOff() {
        status = "off";
    }

    @Override
    public String getStatus() {
        return "Light " + id + " is " + status + ".";
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getType() {
        return "Light";
    }
}

class Thermostat implements Device {
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

class DoorLock implements Device {
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

// Device Proxy (Proxy Pattern)
class DeviceProxy implements Device {
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

// Factory Method (Creational Pattern)
interface DeviceFactory {
    Device createDevice(int id);
}

class LightFactory implements DeviceFactory {
    @Override
    public Device createDevice(int id) {
        return new Light(id);
    }
}

class ThermostatFactory implements DeviceFactory {
    @Override
    public Device createDevice(int id) {
        return new Thermostat(id);
    }
}

class DoorLockFactory implements DeviceFactory {
    @Override
    public Device createDevice(int id) {
        return new DoorLock(id);
    }
}

// Scheduler
class Scheduler {
    private List<ScheduledTask> tasks = new ArrayList<>();

    public void addTask(Device device, String time, String command) {
        tasks.add(new ScheduledTask(device, time, command));
    }

    public void checkSchedules() {
        // Implement the logic to check schedules
    }

    public void printTasks() {
        for (ScheduledTask task : tasks) {
            System.out.println("Device: " + task.getDevice().getType() + ", Time: " + task.getTime() + ", Command: " + task.getCommand());
        }
    }
}

class ScheduledTask {
    private Device device;
    private String time;
    private String command;

    public ScheduledTask(Device device, String time, String command) {
        this.device = device;
        this.time = time;
        this.command = command;
    }

    public Device getDevice() {
        return device;
    }

    public String getTime() {
        return time;
    }

    public String getCommand() {
        return command;
    }
}

// Trigger
class Trigger {
    private String condition;
    private Runnable action;

    public Trigger(String condition, Runnable action) {
        this.condition = condition;
        this.action = action;
    }

    public String getCondition() {
        return condition;
    }

    public void executeAction() {
        action.run();
    }
}

// Main Class
public class SmartHomeSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Device> devices = new HashMap<>();
        List<Trigger> triggers = new ArrayList<>();
        Scheduler scheduler = new Scheduler();

        // Initialize Devices
        DeviceFactory lightFactory = new LightFactory();
        DeviceFactory thermostatFactory = new ThermostatFactory();
        DeviceFactory doorLockFactory = new DoorLockFactory();

        Device light = new DeviceProxy(lightFactory.createDevice(1));
        Device thermostat = new DeviceProxy(thermostatFactory.createDevice(2));
        Device doorLock = new DeviceProxy(doorLockFactory.createDevice(3));

        devices.put(1, light);
        devices.put(2, thermostat);
        devices.put(3, doorLock);

        // Observer
        Observer observer = new DeviceStatusObserver();

        // Menu
        String command;
        do {
            System.out.println("Smart Home System Menu:");
            System.out.println("1. View Device Status");
            System.out.println("2. Turn On Device");
            System.out.println("3. Turn Off Device");
            System.out.println("4. Schedule Task");
            System.out.println("5. View Scheduled Tasks");
            System.out.println("6. Add Trigger");
            System.out.println("7. View Triggers");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    for (Device device : devices.values()) {
                        System.out.println(device.getStatus());
                    }
                    break;
                case "2":
                    System.out.print("Enter Device ID to turn on: ");
                    int deviceIdToOn = Integer.parseInt(scanner.nextLine());
                    devices.get(deviceIdToOn).turnOn();
                    observer.update(devices.get(deviceIdToOn));
                    break;
                case "3":
                    System.out.print("Enter Device ID to turn off: ");
                    int deviceIdToOff = Integer.parseInt(scanner.nextLine());
                    devices.get(deviceIdToOff).turnOff();
                    observer.update(devices.get(deviceIdToOff));
                    break;
                case "4":
                    System.out.print("Enter Device ID to schedule (1: Light, 2: Thermostat, 3: DoorLock): ");
                    int schedDeviceId = Integer.parseInt(scanner.nextLine());
                    Device schedDevice = devices.get(schedDeviceId);
                    System.out.print("Enter time to schedule (HH:mm): ");
                    String time = scanner.nextLine();
                    System.out.print("Enter command (Turn On/Turn Off): ");
                    String schedCommand = scanner.nextLine();
                    scheduler.addTask(schedDevice, time, schedCommand);
                    break;
                case "5":
                    scheduler.printTasks();
                    break;
                case "6":
                    System.out.print("Enter condition (e.g., temperature > 75): ");
                    String condition = scanner.nextLine();
                    System.out.print("Enter action (e.g., turnOff(1)): ");
                    String action = scanner.nextLine();
                    Runnable actionRunnable = () -> {
                        if (action.equals("turnOff(1)")) {
                            devices.get(1).turnOff();
                            observer.update(devices.get(1));
                        }
                    };
                    triggers.add(new Trigger(condition, actionRunnable));
                    break;
                case "7":
                    System.out.println("Triggers:");
                    for (Trigger trigger : triggers) {
                        System.out.println("Condition: " + trigger.getCondition() + ", Action: Executed");
                    }
                    break;
                case "8":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!command.equals("8"));

        scanner.close();
        scheduler.checkSchedules();
    }
}
