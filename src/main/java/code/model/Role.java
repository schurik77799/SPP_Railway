package code.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
@Entity
public class Role {

    @Min(value = 0, message = "Id field can't be negative!")
    private int id;
    @Size(min = 1, max = 50, message = "Role name must be between {min} and {max} characters long!")
    private String name;
    private Collection<User> users;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "rl_id", nullable = true, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rl_name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "role")
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
