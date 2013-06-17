package de.tudresden.business.businessobjects;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="schedule")
public class Schedule implements Serializable
{
	private static final long serialVersionUID = 5548947585904393593L;
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		      name="schedule_appointment",
		      joinColumns={@JoinColumn(name="schedule_id", referencedColumnName="id")},
		      inverseJoinColumns={@JoinColumn(name="appointment_id", referencedColumnName="id")})
	List<Appointment> appointments;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy="schedule")
	private User user;
	
	public Schedule() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Appointment> getAppointments()
	{
		return appointments;
	}
	
	public void setAppointments(List<Appointment> appointments)
	{
		this.appointments = appointments;
	}

	public void addAppointment(Appointment appointment) {
		this.appointments.add(appointment);
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", appointments=" + appointments
				+ ", user=" + user + "]";
	}
	
	
}
