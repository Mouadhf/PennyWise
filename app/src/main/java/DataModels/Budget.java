package DataModels;

import java.sql.Date;

public class Budget {
    private int id;
    private String title;
    private double amount;
    private Date startDate;
    private Date endDate;
    private double limit;
    private String description;

    public Budget(int id, String title, double amount, Date startDate, Date endDate, double limit, String description) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.limit = limit;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getLimit() {
        return limit;
    }

    public String getDescription() {
        return description;
    }


}