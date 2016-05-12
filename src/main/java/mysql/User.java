package mysql;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Artem on 12.05.2016.
 */
@Entity
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;
    private String name;
    private int age;
    @Basic
    @Column(name = "isAdmin", nullable = false)
    private boolean admin;


    private Timestamp createDate;


    public User() {
    }

    public User(String name, int age, boolean admin, Timestamp createDate) {
        this.name = name;
        this.age = age;
        this.admin = admin;
        this.createDate = createDate;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Basic
    @Column(name = "createDate", nullable = false)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (age != user.age) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (createDate != null ? !createDate.equals(user.createDate) : user.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
