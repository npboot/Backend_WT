package WTproject.boekenWT.models;

import jakarta.persistence.*;

@Entity
@Table(name="USERINFO")
public class User {

//    public User(String name, String password, UserType usertype) {
//        this.name = name;
//        this.password = password;
//        this.userType = usertype;
//    }

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private int age;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    // fech = Eager to always get the userType when loding the user
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userTypeId")
    private UserType userType;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }



}
