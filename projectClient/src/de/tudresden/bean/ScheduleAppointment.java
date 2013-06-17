package de.tudresden.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.tudresden.business.beans.ScheduleManagementBean;
import de.tudresden.business.beans.UserManagementBean;
import de.tudresden.business.businessobjects.Appointment;
import de.tudresden.business.businessobjects.User;

@ManagedBean(name = "ScheduleAppointment")
@SessionScoped
public class ScheduleAppointment {

	@ManagedProperty(value = "#{login}")
	private LoginBean login;


	private String Title;
	private Date StartDateTime;
	private Date EndDateTime;
	private String Notes;
	private boolean Private;
	private String AppointmentType;
	private List<User> Invited;
	private User sessionUser;

	@EJB
	UserManagementBean userManagement;

	@EJB
	ScheduleManagementBean scheduleManagement;


	public ScheduleAppointment() {

	}

	@PostConstruct
	public void init() {
		if (login == null) {
			System.out.println("Login Object is NULL");
		} else {
			System.out.println("Login exists!!");
		}
		sessionUser = login.getUser();
		if (sessionUser != null) {
			System.out.println("Successfully retrived Loggedin user: "
					+ sessionUser.getUserName());
		} else {
			System.out.println("User Could not be retrived");
		}
	}

	public boolean CheckDateConflict() {
		Date now = new Date();
		if (StartDateTime.compareTo(EndDateTime) > 0
				|| StartDateTime.compareTo(EndDateTime) == 0) {
			return false;
		} else {
			return true;
		}

	}

	public void addAppointment() {

		System.out.println("Entered method to save appointment");

		boolean DateCheck = CheckDateConflict();
		if (!DateCheck) {
			System.out.println("DateCheck failed!!");
		}

		Appointment apt = new Appointment();
		apt.setTitle(getTitle());
		apt.setAppointmentType(getAppointmentType());
		apt.setDescription(getNotes());
		apt.setStartDate(getStartDateTime());
		apt.setEndDate(getEndDateTime());
		apt.setPrivateAppointment(getPrivate());

		System.out.println("Created and initialized appointment");
		
//		scheduleManagement.addMultipleUsersInAppointment(getInvited(), apt);

		for (User u : getInvited()) {
			System.out.println("user invited: " + u);
			scheduleManagement.addUserInAppointment(u, apt);
		}

		System.out.println("Saved appointment to users schedules");

	}

	/********** Getters and Setters-Start ***/
	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getTitle() {
		return Title;
	}

	public void setStartDateTime(Date StartDateTime) {
		this.StartDateTime = StartDateTime;
	}

	public Date getStartDateTime() {
		return StartDateTime;
	}

	public void setEndDateTime(Date EndDateTime) {
		this.EndDateTime = EndDateTime;
	}

	public Date getEndDateTime() {
		return EndDateTime;
	}

	public void setNotes(String Notes) {
		this.Notes = Notes;
	}

	public String getNotes() {
		return Notes;
	}

	public void setPrivate(boolean Private) {
		this.Private = Private;
	}

	public boolean getPrivate() {
		return Private;
	}

	public void setAppointmentType(String AppointmentType) {
		this.AppointmentType = AppointmentType;
	}

	public String getAppointmentType() {
		return AppointmentType;
	}

	public List<User> getInvited() {
		return Invited;
	}

	public void setInvited(List<User> invited) {
		Invited = invited;
	}

	public List<User> getAllUsers() {
		return userManagement.getAllUsers();
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public LoginBean getLogin() {
		return login;
	}

	/********** Getters and Setters-End ***/

}
