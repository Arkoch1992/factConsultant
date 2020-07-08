package factconsultant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Admin {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
Long id;
String userName;
String password;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
	
}
