package de.tudresden.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import de.tudresden.business.beans.ScheduleManagementBean;
import de.tudresden.business.beans.UserManagementBean;
import de.tudresden.business.businessobjects.Appointment;
import de.tudresden.business.businessobjects.Schedule;
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

		System.out.println("Retriving and creating a new appointment object");
		Appointment apt = new Appointment();
		apt.setTitle(getTitle());
		apt.setAppointmentType(getAppointmentType());
		apt.setDescription(getNotes());
		apt.setStartDate(getStartDateTime());
		apt.setEndDate(getEndDateTime());
		apt.setPrivateAppointment(getPrivate());
		
		// Private appointments are only allowed for one user
		System.out.println("Checking if it is a private appointment and there is only one user");
		if(apt.getPrivateAppointment() && getInvited().size() > 1)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Appointment ", "It is not possible to crete a private appointe for more than one person"));
			return;
		}

		System.out.println("Created and initialized appointment");
		
		System.out.println("Checking if the new appointment overlap some other appointments");
		for (User u : getInvited())
		{
			System.out.println("user invited: " + u);
			Schedule schedule = u.getSchedule();
			List<Appointment> appointments = schedule.getAppointments();
			if(appointments != null)
			{
				List<Appointment> appointmentsResultList = new ArrayList<Appointment>();
				for(Appointment appointment : appointments)
				{
					Date startDate = appointment.getStartDate();
					Date endDate = appointment.getEndDate();
					if(apt.getStartDate().before(startDate) && apt.getEndDate().after(endDate))
					{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Appointment", "Created Successfuly"));
					}
					else if(apt.getEndDate().after(startDate) && apt.getEndDate().before(endDate))
					{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Appointment", "Created Successfuly"));
					}
					else if(apt.getStartDate().after(startDate) && apt.getStartDate().before(endDate))
					{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Appointment", "Created Successfuly"));
					}
					else if(apt.getStartDate().after(startDate) && apt.getEndDate().before(endDate))
					{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Appointment", "Created Successfuly"));
					}
					else
					{
						// Can add
					}
				}
			}
			else
			{
				scheduleManagement.addUserInAppointment(u, apt);
			}
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Appointment", "Created Successfuly"));

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
