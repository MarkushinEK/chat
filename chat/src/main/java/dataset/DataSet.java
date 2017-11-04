package dataset;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class DataSet implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(unique = true, updatable = false)
    public String login;

    @Column
    public String password;

    public DataSet() {}

    public DataSet(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public long getUserId() {
        return id;
    }
}
