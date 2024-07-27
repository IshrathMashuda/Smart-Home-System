import java.util.*;

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
