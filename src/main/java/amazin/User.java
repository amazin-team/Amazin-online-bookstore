package amazin;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(){}

    public User(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public void setEmail(String email){
      this.email = email;
    }

    public String getEmail(){
      return email;
    }

    public void setPassword(String password){
      this.password = password;
    }

    public String getPassword(){
      return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                '}';
    }


    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.launch();
    }

}
