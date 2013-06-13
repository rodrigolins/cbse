package de.tudresden.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import de.tudresden.business.beans.ScheduleManagementBean;
import de.tudresden.business.businessobjects.Appointment;
import de.tudresden.business.businessobjects.AppointmentType;
import de.tudresden.business.businessobjects.Schedule;
import de.tudresden.business.businessobjects.User;

@ManagedBean(name="showappointments")
@SessionScoped
public class ShowAppointmentBean
{
	@EJB
	ScheduleManagementBean scheduleManagement;
	
	private User user;
	private List<Appointment> appointments;
	
	@ManagedProperty(value="#{login}")
	private LoginBean loginBean;
	
	public void init() throws IOException
	{
		User user = loginBean.getUser();
		System.out.println("Init ShowAppointmentBean");
		
		if(user == null)
		{
			System.out.println("User not logged in");
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		}
		else
		{
			System.out.println("User logged sucessfully");
			FacesContext.getCurrentInstance().getExternalContext().redirect("tasklist.xhtml");
		}
		
//		User aUser = new User();
//		aUser.setUserName("user");
//		aUser.setPassword("user");
//		aUser.setEmail("user@user.com");
//		Schedule schedule = new Schedule();
//		schedule.setUser(aUser);
//		Appointment appointment = new Appointment();
//		appointment.setAppointmentType(AppointmentType.AWAY);
//		appointment.setTitle("Kindergarten");
//		appointment.setEndDate(new Date(2013, month, date, hrs, min))
		
	}
	
	public ShowAppointmentBean()
	{
//		scheduleManagement.getAllUserAppointments(user);
		
		appointments = new ArrayList<Appointment>();
		Date currentDate = Calendar.getInstance().getTime();
		for (int i = 0; i < 10; i++) {
			Appointment testApp = new Appointment(i, currentDate, currentDate,
					"Test title" + i, "test description" + i,
					AppointmentType.BLOCKED, false);

			appointments.add(testApp);
		}
		System.out.println("Added appointment to List");
	}
	
	public List<Appointment> getUserAppointments() {
		return this.appointments;
	}
	
	public void deleteAppointment(Integer appId)
	{
		System.out.println("Deleting Appointment " + appId.toString());
		for (int i = 0; i < this.appointments.size(); i++) {
			Appointment app = appointments.get(i);
			if (app.getId() == appId) {
				System.out.println("Found Now Deleting");
				appointments.remove(i);
				return;
			}
		}
	}
	
	/*********************************/
	/******** Getters & Setters ******/
	/*********************************/
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	public LoginBean getLoginBean() {
		return loginBean;
	}
}
