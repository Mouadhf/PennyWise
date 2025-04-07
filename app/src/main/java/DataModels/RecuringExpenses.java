package DataModels;
public class RecuringExpenses {
    private String id;
    private String userId;
    private double amount;
    private String category;
    private String frequency;
    private boolean isActive;

    public RecuringExpenses(String id, String userId, double amount, String category, String frequency, boolean isActive) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.frequency = frequency;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}