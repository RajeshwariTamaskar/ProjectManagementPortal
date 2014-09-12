package one;

import javax.persistence.*;
import java.io.*;

@Entity(name = "LOGINMODEL")
public class LoginModel implements Serializable {
	@Id
	@Column(name = "username")
	private String username;	
		
	@Column(name = "password")
	private String password;
	
	public String getUserName() {
		return username;
	}
	
//	public void setFirstName(String name) {
//		this.first_name = name;
//	}
	
	public String getPassword() {
		return password;
	}
	
//	public void setLastName(String name) {
//		this.last_name = name;
//	}
	
	// return number of columns in the table
	public int getNumberOfColumns() {
		return 2;
	}
	
	
	@Override
	public String toString() {
		return "LoginModel [Username =" + username + ", "
		+ " Password =" + password + ","
		+ "]";
	}
}
