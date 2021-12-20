package ec.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="SOCCER_STATS_USER")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;
    private String password;
    private Integer role;
    
    public User() { }
    public User(String name) {
       this.name = name;
    }
    public User(Integer id, String name, String password, Integer role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) {  this.name = name; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) {  this.password = password; }
    
    public Integer getRole() { return role; }
    public void setRole(Integer role) { this.role = role; }
    
    @Override
    public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n");	
		buffer.append("\n");	
		buffer.append("id:"+id+"\n");	
		buffer.append("username:"+name+"\n");
		buffer.append("password:"+password+"\n");
		buffer.append("role:"+ role +"\n");
		return buffer.toString();
    }
}