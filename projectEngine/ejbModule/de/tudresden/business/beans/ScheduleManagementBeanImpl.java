package de.tudresden.business.beans;

import java.util.ArrayList;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.tudresden.business.businessobjects.Appointment;
import de.tudresden.business.businessobjects.User;

@Stateful
public class ScheduleManagementBeanImpl implements ScheduleManagementBean
{
	@PersistenceContext
	private EntityManager em;

	public void createUserAppointment(User user, Appointment appointment) {
		// TODO Auto-generated method stub

	}

	public void addUserInAppointment(User user, Appointment appointment) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Appointment> getAllUserAppointments(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void helloWorld(String helloWorld)
	{
		System.out.println("ScheduleManagementBeanImpl says: " + helloWorld);
	}

	@Override
	public void deleteUserAppointment(User user, Appointment appointment) {
		
	}

}
