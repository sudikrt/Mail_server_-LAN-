//IMPORT LIBRARIES FOR SERIALIZABLE//
import java.io.*;
class User implements Serializable
{
	//DECLARE USER NAME AND PASSWORD VARIABLES//
	private String userName;
	private String password;

	//CLASS CONSTRUCTOR//
	public User(String name,String pass)
	{
		userName=name;
		password=pass;
	}

	//ACCESSOR METHODS//
	public String getUserName()
	{
		return userName;
	}

	public String getPassword()
	{
		return password;
	}

	//MUTATOR METHODS//
	public void setUserName(String name)
	{
		userName=name;
	}

	public void setPassword(String pass)
	{
		password=pass;
	}
}

