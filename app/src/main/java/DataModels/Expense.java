package DataModels;

import java.sql.Date;

public class Expense {
    private int id;
    private String title;
    private double amount;
    private Date date;
    private String description;
    private double limit;

    public Expense(int id, String title, double amount, Date date, String description, double limit){
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.limit = limit;
    }
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public double getAmount(){
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
