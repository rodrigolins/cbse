package de.tudresden.business.businessobjects;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="appointment")
public class Appointment implements Serializable
{
	private static final long serialVersionUID = 4848631804499480212L;
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;
	@Column(name="startdate")
	private Date startDate;
	@Column(name="enddate")
	private Date endDate;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="appointmenttype")
	private String appointmentType;
	@Column(name="privateappointment")
	private Boolean privateAppointment;
	
	@ManyToMany(fetch = FetchType.EAGER ,mappedBy="appointments")
	private List<Schedule> schedules;

	public Appointment() {
//		this.schedules = new ArrayList<Schedule>();
	}
	
	public Appointment(Integer id, Date startDate, Date endDate, String title, String description, String appointmentType, Boolean privateAppointment, List<Schedule> schedules)
	{
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.privateAppointment = privateAppointment;
		this.schedules = schedules;
	}
	
	public Appointment(Integer id, Date startDate, Date endDate, String title, String description, String appointmentType, Boolean privateAppointment)
	{
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.privateAppointment = privateAppointment;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public Boolean getPrivateAppointment() {
		return privateAppointment;
	}

	public void setPrivateAppointment(Boolean privateAppointment) {
		this.privateAppointment = privateAppointment;
	}
	
	public List<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	
	public String getPrivateText()
	{
		if(this.privateAppointment)
		{
			return "True";
		}
		return "False";
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", title=" + title
				+ ", description=" + description + ", appointmentType="
				+ appointmentType + ", privateAppointment="
				+ privateAppointment + ", schedules=" + schedules + "]";
	}


}