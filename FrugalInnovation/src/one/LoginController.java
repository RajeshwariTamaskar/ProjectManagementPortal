package one;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import one.WelcomePanel;

public class LoginController {
	private EntityManager manager;
	private WelcomePanel gui;
        /*Constructor*/
	public LoginController(WelcomePanel gui, EntityManager manager) {
            this.manager = manager; 
            this.gui = gui;
        }
	/*Constructor*/
	public LoginController(WelcomePanel gui) {
		this.gui = gui;
	}
	
	public boolean verifyLogin() {
	   	String userName = this.gui.getUsernameTextField();
	   	String password = this.gui.getPasswordTextField();
	   	LoginModel lm = manager.find(LoginModel.class, userName);
		if(lm.getPassword().equals(password) && lm.getUserName().equals(userName)) {
			return true;
		}
		else {
			return false;
		}
	}
}
