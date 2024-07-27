import java.util.*;

public class Scheduler {
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
