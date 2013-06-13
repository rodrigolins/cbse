package de.tudresden.business.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.tudresden.business.businessobjects.User;

@Stateful
public class UserManagementBeanImpl implements UserManagementBean
{
	@PersistenceContext
	private EntityManager em;
	
	List<User> users;
	
	public UserManagementBeanImpl()
	{
		System.out.println("UserManagementBean Initialized!");
		users = new ArrayList<User>();
	}
	
	public Boolean addUser(User user)
	{
		System.out.println("Trying to persist user: " + user);
		try
		{
			em.persist(user);
			System.out.println("Data persisted successfuly");
		}
		catch(Exception e)
		{
			System.out.println("It was not possible to persist the data");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	public Boolean removeUser(User user)
	{
		System.out.println("Trying to persist user: " + user);
		try
		{
			em.remove(user);
			System.out.println("User persisted successfuly");
			return Boolean.TRUE;
		}
		catch (Exception e)
		{
			System.out.println("Fail to remove user");
			return Boolean.FALSE;
		}
	}
	
	public Boolean updateUser(User user)
	{
		System.out.println("Trying to update user: " + user);
		try
		{
			em.merge(user);
			System.out.println("User updated successfuly");
			return Boolean.TRUE;
		}
		catch (Exception e)
		{
			System.out.println("Fail to update user");
			return Boolean.FALSE;
		}
	}

	public User getUserById(Integer id)
	{
		System.out.println("Trying to retrieve an user with id: " + id);
		User user = em.find(User.class, id);
		System.out.println("Trying to retrieved: " + user);
		return user;
	}

	public User getUserByUsernameAndPassword(String userName, String password)
	{
		System.out.println("Trying to retrieve user using userName: " + userName + " | password: " + password);
		Query query = em.createQuery("SELECT u FROM User u WHERE u.userName = ?1 and u.password = ?2");
		query.setParameter(1, userName);
		query.setParameter(2, password);
		@SuppressWarnings("unchecked")
		List<User> result = (List<User>) query.getResultList();
		System.out.println("result size: " + result.size());
		if(result.size() != 1)
		{
			System.out.println("More than one user or no users at all with this username and password");
			return null;
		}
		
		User user = result.get(0);
		return user;

	}
	
	@Override
	public List<User> getAllUsers()
	{
		Query q = em.createQuery("select u from User u");
		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();
		return users;
	}

}
