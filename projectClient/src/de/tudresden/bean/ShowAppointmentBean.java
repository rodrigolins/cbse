package de.tudresden.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import de.tudresden.business.beans.ScheduleManagementBean;
import de.tudresden.business.businessobjects.Appointment;
import de.tudresden.business.businessobjects.User;

@ManagedBean(name="showappointments")
@SessionScoped
public class ShowAppointmentBean
{
	@EJB
	ScheduleManagementBean scheduleManagement;
	
	@ManagedProperty(value="#{login}")
	private LoginBean loginBean;
	
	private User sessionUser;
	private List<Appointment> appointments;
	
	@PostConstruct
	public void init() throws IOException
	{
		if (loginBean == null) {
			System.out.println("Login Object is NULL");
		} else {
			System.out.println("Login exists!!");
		}
		sessionUser = loginBean.getUser();
		if (sessionUser != null) {
			System.out.println("Successfully retrived Loggedin user: " + sessionUser.getUserName());
		} else {
			System.out.println("User Could not be retrived");
		}
	}
	
	public ShowAppointmentBean()
	{
	}
	
	public List<Appointment> getUserAppointments() {
		this.appointments = scheduleManagement.getAllUserAppointments(sessionUser);
		return this.appointments;
	}
	
	public void deleteAppointment(Integer id)
	{
		System.out.println("Trying to delete the appointment with id: " + id);
		scheduleManagement.deleteAppointmentById(id);
//		System.out.println("Deleting Appointment " + appId.toString());
//		for (int i = 0; i < this.appointments.size(); i++) {
//			Appointment app = appointments.get(i);
//			if (app.getId() == appId) {
//				System.out.println("Found Now Deleting");
//				appointments.remove(i);
//				return;
//			}
//		}
	}
	
	
	
	/*********************************/
	/******** Getters & Setters ******/
	/*********************************/
	
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	public LoginBean getLoginBean() {
		return loginBean;
	}
	public User getSessionUser() {
		return sessionUser;
	}
	public void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
}
