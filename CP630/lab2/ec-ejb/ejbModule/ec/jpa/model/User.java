package ec.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="USER")
@NamedQueries({
    @NamedQuery(name = "User.findByName", query = "SELECT a FROM User a WHERE a.name = :name")
})
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    public User() { }
    public User(String name) {
       this.name = name;
    }
    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) {  this.name = name; }
    @Override
    public String toString() {
        return "User {" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}