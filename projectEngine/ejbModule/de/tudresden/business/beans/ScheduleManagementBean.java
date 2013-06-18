package de.tudresden.business.beans;

import java.util.List;

import javax.ejb.Remote;

import de.tudresden.business.businessobjects.Appointment;
import de.tudresden.business.businessobjects.User;

@Remote
public interface ScheduleManagementBean {
	public void createUserAppointment(User user, Appointment appointment);

	public void deleteAppointment(Appointment appointment);
	
	public void deleteAppointmentById(Integer id);

	public void addUserInAppointment(User user, Appointment appointment);
	
	public List<Appointment> getAllUserAppointments(User user);
	
}
