package de.tudresden.business.beans;

import java.util.ArrayList;

import javax.ejb.Remote;

import de.tudresden.business.businessobjects.Appointment;
import de.tudresden.business.businessobjects.User;

@Remote
public interface ScheduleManagementBean
{
	public void createUserAppointment(User user, Appointment appointment);
	public void deleteUserAppointment(User user, Appointment appointment);
	public void addUserInAppointment(User user, Appointment appointment);
	public ArrayList<Appointment> getAllUserAppointments(User user);
}
