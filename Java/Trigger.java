public class Trigger {
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