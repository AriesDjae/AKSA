package fxml_helloworld;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String status;

    // Konstruktor dengan username dan password saja
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.status = "Unverified"; // Default status
    }

    // Konstruktor lengkap
    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.status = "Unverified";
    }

    public User(String firstName, String lastName, String userName, String password, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
