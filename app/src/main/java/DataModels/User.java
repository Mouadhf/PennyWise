package DataModels;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;

    public User(){}
    public User(int userId, String name, String email, String linkedBankAccountId) {
        this.id = userId;
        this.username = name;
        this.email = email;
    }
    public void setId(int newId){
        this.id = newId;
    }
    public void setUsername(String usern){
        this.username = usern;
    }
    public void setEmail(String newemail) {
        this.email = newemail;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public String getUsername(){
        return username;
    }

    public String getEmail() {
        return email;
    }
}
