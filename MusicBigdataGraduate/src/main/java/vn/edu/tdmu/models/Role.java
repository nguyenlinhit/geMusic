package vn.edu.tdmu.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by NguyenLinh on 1/26/2018.
 *
 */
@Entity
@Table(name = "roles")
public class Role extends BaseEntity{
    private static final long serialVersionUID = 1L;

    private static final int MAX_LENGTH_ROLE = 15;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = MAX_LENGTH_ROLE)
    @Column(name = "type", length = MAX_LENGTH_ROLE, unique = true, nullable = false)
    private String type;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(String type, String description, Set<User> users) {
        this.type = type;
        this.description = description;
        this.users = users;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void update(String newType, String newDescription){
        this.type = newType;
        this.description = newDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Genre)){
            return false;
        }

        Role other = (Role) obj;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("type", this.type)
                .append("description", this.description)
                .toString();
    }
}
