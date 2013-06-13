package de.tudresden.business.beans;

import java.util.List;

import javax.ejb.Remote;

import de.tudresden.business.businessobjects.User;

@Remote
public interface UserManagementBean
{
	public Boolean addUser(User user);
	public Boolean removeUser(User user);
	public Boolean updateUser(User user);
	public User getUserById(Integer id);
	public User getUserByUsernameAndPassword(String userName, String password);
	public List<User> getAllUsers();
}
