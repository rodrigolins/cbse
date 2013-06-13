
package de.tudresden.bean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import de.tudresden.business.beans.UserManagementBean;
import de.tudresden.business.businessobjects.Appointment;
import de.tudresden.business.businessobjects.AppointmentType;
import de.tudresden.business.businessobjects.User;

@ManagedBean(name="login")
@SessionScoped
public class LoginBean
{
	private User user;
	private boolean logged;
	
	private String userName;
	private String password;
	private String repeatPassword;
	private String email;
	
	@EJB
	UserManagementBean userManagement;

	private List<Appointment> appList;

	public LoginBean() {
		appList = new ArrayList<Appointment>();
		Date currentDate = Calendar.getInstance().getTime();
		for (int i = 0; i < 10; i++) {
			Appointment testApp = new Appointment(i, currentDate, currentDate,
					"Test title" + i, "test description" + i,
					AppointmentType.BLOCKED, false);

			appList.add(testApp);
		}
		System.out.println("Added appointment to List");
	}
	
	public void login() throws IOException {
		User currentUser = userManagement.getUserByUsernameAndPassword(userName, password);
		if (currentUser != null) {
			System.out.println("Here goes nothing!!");
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("tasklist.xhtml");
			this.setUser(currentUser);
		} else {
			System.out.println("Returned User is null");
		}
	}

	
	public boolean register()
	{
		Boolean registered = Boolean.FALSE;
		
		if(!("".equals(userName)) && !("".equals(password)) && !("".equals(repeatPassword)) && password.equals(repeatPassword))
		{
			User user = new User(userName, password, email);
			registered = userManagement.addUser(user);
		}
		
		if (registered)
		{
			System.out.println("Registered successfuly");
		}
		else
		{
			System.out.println("Not registered");
		}
		
		return registered;
	}
	
	public List<User> getUserList()
	{
		return userManagement.getAllUsers();
	}

	public List<Appointment> getUserAppointments() {
		return this.appList;
	}

	public void deleteAppointment(Integer appId) {
		System.out.println("Deleting Appointment " + appId.toString());
		for (int i = 0; i < this.appList.size(); i++) {
			Appointment app = appList.get(i);
			if (app.getId() == appId) {
				System.out.println("Found Now Deleting");
				appList.remove(i);
				return;
			}
		}
	}
	
	/*********************************/
	/******** Getters & Setters ******/
	/*********************************/
	
	public void setUser(User user)
	{
		this.user = user;
	}
	public User getUser()
	{
		return user;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return password;
	}
	public boolean isLogged()
	{
		return logged;
	}
	public String getRepeatPassword()
	{
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword)
	{
		this.repeatPassword = repeatPassword;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
}