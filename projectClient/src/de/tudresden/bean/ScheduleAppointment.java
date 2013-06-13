package de.tudresden.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.tudresden.business.beans.UserManagementBean;
import de.tudresden.business.businessobjects.User;

@ManagedBean(name = "ScheduleAppointment")
@SessionScoped
public class ScheduleAppointment {

	private String Title;
	private Date StartDateTime;
	private Date EndDateTime;
	private String Notes;
	private boolean Private;
	private String AppointmentType;
	private List<User> Invitees;
	private List<User> Invited;

	@EJB
	UserManagementBean userManagement;

	public ScheduleAppointment() {
		Invitees = userManagement.getAllUsers();
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

	public boolean Submit() {

		boolean DateCheck = CheckDateConflict();
		if (!DateCheck) {
			// Do java script alert
		}

		// Call the Bean Function and pass the parameters: Title, StartDateTime,
		// EndDateTime, Notes, Private, Appointmenttype and Invitee list
		// The function in the business object should check for invitee conflict
		// and return true or false
		// If conflictt exists.... Javascript alert
		return true;
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

	public void setInvitees(List<User> Invitees) {
		this.Invitees = Invitees;
	}

	public List<User> getInvitees() {
		return Invitees;
	}

	/********** Getters and Setters-End ***/

}
