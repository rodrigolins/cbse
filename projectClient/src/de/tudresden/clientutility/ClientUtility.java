package de.tudresden.clientutility;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientUtility
{
	private static Context initialContex;
	
	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
	
	public static Context getInitialContext() throws NamingException
	{
		if(initialContex == null)
		{
			Properties properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
			initialContex = new InitialContext(properties);
		}
		return initialContex;
	}

}
