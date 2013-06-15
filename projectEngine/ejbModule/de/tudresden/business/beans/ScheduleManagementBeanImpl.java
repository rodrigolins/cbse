package de.tudresden.business.beans;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.tudresden.business.businessobjects.Appointment;
import de.tudresden.business.businessobjects.Schedule;
import de.tudresden.business.businessobjects.User;

/**
 * This class is an implementation of the {@link ScheduleManagementBean}
 * interface.
 * 
 * @author Arjun Naik, Rodrigo Lins de Oliveira
 * 
 */
@Stateful
public class ScheduleManagementBeanImpl implements ScheduleManagementBean {
	
	@PersistenceContext
	private EntityManager em;

	/**
	 * Used to add an appointment to a {@link User}'s schedule.
	 * 
	 * @param user
	 *            : The user to whose {@link Schedule} the appointment has to be
	 *            added.
	 * @param appointment
	 *            : The {@link Appointment} to be added to the user's schedule.
	 */
	public void createUserAppointment(User user, Appointment appointment) {
		
		if(user == null )
		{
			return;
		}
		System.out.println("1 step");
		Appointment newAppointment = new Appointment();
		newAppointment.setAppointmentType(appointment.getAppointmentType());
		newAppointment.setDescription(appointment.getDescription());
		newAppointment.setEndDate(appointment.getEndDate());
		newAppointment.setPrivateAppointment(appointment.getPrivateAppointment());
		newAppointment.setStartDate(appointment.getStartDate());
		newAppointment.setTitle(appointment.getTitle());
		System.out.println("2 Step - persisting the appointment");
		em.persist(newAppointment);
		
		System.out.println("Getting user");
		
		Schedule userSchedule ;
		System.out.println("3 Step - getting or creating the new schedule");
		if(user.getSchedule() == null)
		{
			userSchedule = new Schedule();
		}
		else
		{
			userSchedule = user.getSchedule();
		}
		userSchedule.setUser(user);
		
		System.out.println("4 Step - Adding appointment in schedule");
		System.out.println("Printing user schedule: " + userSchedule);
		userSchedule.addAppointment(newAppointment);
		System.out.println(newAppointment);
		
		System.out.println("Added new appointment to user schedule");
		em.persist(userSchedule);
		System.out.println("Sucessfully persisted the new appointment");
	}

	public void addUserInAppointment(User user, Appointment appointment) {
		createUserAppointment(user, appointment);
	}
	
	public void createScheduleForUser(User user, Schedule schedule)
	{
		schedule.setUser(user);
		em.persist(schedule);
	}

	/**
	 * This functions returns all the {@link Appointment}s for a {@link User}
	 * 
	 * @param user
	 *            : The {@link User}
	 * @return All the user's {@link Appointment}s
	 */
	public List<Appointment> getAllUserAppointments(User user) {
		return user.getSchedule().getAppointments();
	}

	@Override
	public void deleteUserAppointment(User user, Appointment appointment) {
		List<Appointment> appointments = user.getSchedule().getAppointments();
		System.out.println("Appointments for User retrived: "
				+ appointments.size());
		appointments.remove(appointment);
		System.out.println("Removed Appointment");
		user.getSchedule().setAppointments(appointments);
		System.out.println("Attempting to persist changes to schedule");
		em.persist(user.getSchedule());
		System.out.println("Persisted changes to User schedule");
	}

}
