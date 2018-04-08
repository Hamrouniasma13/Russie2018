package entities;
import java.sql.Date;

public class User {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String email_canonical;
    private String username;
    private String username_canonical;
    private String salt;
    private String password;
    private Date last_login;
    private String confirmation_token;
    private Date password_requested_at;
    private String roles;
    private Date createdAt;
    private Date updatedAt;


    private static User instance;

    private User(User U) {
        this.id = U.id;
        this.fname = U.fname;
        this.lname = U.lname;
        this.email_canonical = U.email_canonical;
        this.username = U.username;
        this.username_canonical = U.username_canonical;
        this.salt = U.salt;
        this.password = U.password;
        this.last_login = U.last_login;
        this.confirmation_token = U.confirmation_token;
        this.password_requested_at = U.password_requested_at;
        this.roles = U.roles;
    }

    public User(int id, String fname, String lname, String email, String email_canonical, String username, String username_canonical, String password,
                String roles, Date createdAt, Date updatedAt) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.email_canonical = email_canonical;
        this.username = username;
        this.username_canonical = username_canonical;
        this.password = password;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

   
    public static User getInstance(){
        return instance;
    }

    public static void setInstance(User user){
        if(user == null){
            instance= null;
        }else{
            instance = new User(user);
        }
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
