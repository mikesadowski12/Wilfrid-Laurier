package ec.jpa;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "model")
@NamedQueries({
    @NamedQuery(name = "Model.findByName", query = "SELECT a FROM Model a WHERE a.name = :name")
})
public class Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
	private String name;
	private String classname;
	@Lob
    private byte[] object;
	private Timestamp date;

	public Model() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public byte[] getObject() {
		return object;
	}

	public void setObject(byte[] object) {
		this.object = object;
	}
	
	public String toString() {
		return this.getClassname();
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
