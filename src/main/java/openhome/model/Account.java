package openhome.model;

public class Account {
    private String name;
    private String password;
    private String email;
    public Account() {}

    public Account(String name, String password) {
        this.name= name;
        this.password= password;
    }

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "Account [name=" + name + ", password=" + password + ", email=" + email + "]";
    }
}
