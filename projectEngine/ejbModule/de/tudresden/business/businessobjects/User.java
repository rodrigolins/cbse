package de.tudresden.business.businessobjects;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable
{
	private static final long serialVersionUID = -6229994188219468966L;
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	
//	@OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
//	@JoinColumn(name="schedule_id")
	@OneToOne
	private Schedule schedule;
	
	public User()
	{
	}
	
	public User(Integer id, String userName, String password, String email, Schedule schedule)
	{
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.schedule = schedule;
	}
	
	public User(String userName, String password, String email)
	{
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	public Integer getId()
	{
		return id;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", email=" + email + "]";
	}
}