import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Client
{
	private static final int PORT=1234;
	private static Socket link;
	private static Vector inbox;
	private static Vector draft;
	private static Vector sent;
	private static Vector userNames;
	private static User user;
	private static ObjectInputStream objectIn;
	private static ObjectInputStream objectOut;
	private static String st;

	public Client(String ip)throws IOException
	{
		try
        	{
			st=ip.trim();
                	//SET UP CONNECTION INFORMATION//
                	link=new Socket(st,PORT);
                	//SET UP CLIENT DATA STORAGE//
                	userNames=new Vector();
                	inbox=new Vector();
                	draft=new Vector();
                	sent=new Vector();
		
                	//GET PERMITED USER NAMES FROM  SERVER//
                	getUserNamesFromServer();
                	SignInWindow s1=new SignInWindow();
                	Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                	final int HEIGHT=400;
                	final int WIDTH=400;
                	s1.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                	s1.setVisible(true);
        	}
        	//EXCEPTION HANDLING ROUTINES AND CLASSES//
        	catch(ConnectException uhe)
        	{
                	System.out.println("Host ID Not found");
			JOptionPane.showMessageDialog(null,"server not exist");
			Ip.pane.setVisible(false);
			Ip s1=new Ip();
                	Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                	final int HEIGHT=160;
                	final int WIDTH=400;
                	s1.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                	s1.setVisible(true);
        	}
        	catch(IOException ioe)
        	{
                	ioe.printStackTrace();
        	}
	}
        //RECEIVES VECTOR OF PERMITTED USER NAMES FROM SERVER//
	private static void getUserNamesFromServer() throws IOException
        {
		try
                {
                        objectIn=new ObjectInputStream(link.getInputStream());
                        Integer vectorSizeText=(Integer)objectIn.readObject();
                        int vectorSize=vectorSizeText.intValue();
                        for(int count=0;count<vectorSize;count++)
                        {
                                userNames.add((User)objectIn.readObject());
                                User temp=(User)userNames.elementAt(count);
                        }
                }
                catch(ClassNotFoundException cnfe)
                {
                        cnfe.printStackTrace();
                }
        }
	
        //SHUTS DOWN THE CLIENT CORRECTLY WHEN FINISHED//
	public static void closeDown()
        {
		try
                {
                        //SENDS UNDELETED MAIL BACK TO SERVER FOR STORAGE//
                        for(int count=0;count<inbox.size();count++)
                        {
                                returnMail(getMail(count));
                                inbox.removeElementAt(count);
                                count--;
                        }
                        //SENDS UNDELETED DRAFT MAIL BACK OF SERVER FOR STORAGE//
                        for(int count=0;count<draft.size();count++)
                        {
                                returnDraft(getDraft(count));
                                draft.removeElementAt(count);
                                count--;
                        }
                        //SENDS UNDELETED SENT MAIL BACK OF SERVER FOR STORAGE//
                        for(int count=0;count<sent.size();count++)
                        {
                                returnSent(getSent(count));
                                sent.removeElementAt(count);
                                count--;
                        }
			ObjectOutputStream objectOut=new ObjectOutputStream(link.getOutputStream());
                        String option="QUITTING";
                        objectOut.writeObject(option);
                        objectOut.flush();
                        link.close();
                        System.exit(0);
                }
                catch(IOException ioe)
                {
                	ioe.printStackTrace();
                }
        }
        //SENDS ANY MAIL IN CLIENT INBOX BACK TO SERVER//
	private static void returnMail(Email mail)
        {
		try
		{
			ObjectOutputStream objectOut=new ObjectOutputStream(link.getOutputStream());
			String option="SENDING";
			objectOut.writeObject(option);
			objectOut.flush();
			objectOut.writeObject(mail);
			objectOut.flush();
		}
		catch(IOException ioe)
		{
 			ioe.printStackTrace();
		}
	}
	//SENDS ANY DRAFT MAIL IN CLIENT INBOX BACK TO SERVER//
	private static void returnDraft(Email mail)
   	{
         	try
        	{
                    	ObjectOutputStream objectOut=new ObjectOutputStream(link.getOutputStream());
                    	String option="DSENDING";
                	objectOut.writeObject(option);
                 	objectOut.flush();
                  	objectOut.writeObject(mail);
                	objectOut.flush();
           	}
            	catch(IOException ioe)
              	{
                 	ioe.printStackTrace();
             	}
   	}
    	//SENDS ANY SENT MAIL IN CLIENT INBOX BACK TO SERVER//
	private static void returnSent(Email mail)
	{
         	try
		{
                    	ObjectOutputStream objectOut=new ObjectOutputStream(link.getOutputStream());
                   	String option="ESENDING";
                 	objectOut.writeObject(option);
                	objectOut.flush();
                      	objectOut.writeObject(mail);
                     	objectOut.flush();
        	}
            	catch(IOException ioe)
           	{
                     	ioe.printStackTrace();
              	}
	}
       	//ACCESSOR METHOD//
	public static String getCurrentUserName()
       	{
		return user.getUserName();
  	}
	public static Vector getUserNames()
	{
 		return userNames;
 	}
 	public static int getUserNamesSize()
 	{
  		return userNames.size();
 	}
 	public static String getName(int position)
	{
 		User temp=(User)userNames.elementAt(position);
        	return temp.getUserName();
	}
       	public static Email getMail(int position)
   	{
            	return(Email)inbox.elementAt(position);
     	}
      	public static Email getDraft(int position)
     	{
             	return(Email)draft.elementAt(position);
    	}
      	public static Email getSent(int position)
     	{
             	return(Email)sent.elementAt(position);
       	}
     	public static Socket getLink()
       	{
            	return link;
  	}
    	public static Vector getMailVector()
     	{
             	return inbox;
 	}
     	public static int inboxSize()
     	{
            	return inbox.size();
    	}
    	public static Vector getDraftVector()
     	{
            	return draft;
    	}
       	public static int draftSize()
     	{
             	return draft.size();
     	}
   	public static Vector getsentVector()
     	{
                		return sent;
    	}
 	public static int sentSize()
   	{
             	return sent.size();
    	}
    	//MUTATOR METHODS//
    	public static void setUser(User newUser)
    	{
             	user=newUser;
   	}
      	public static void addUser(User newUser)
     	{
               	userNames.add(newUser);
     	}
    	public static void addMail(Email email)
    	{
             	inbox.add(email);
  	}
    	public static void setInbox(Vector vector)
     	{
             	inbox=vector;
  	}
      	public static void deleteMail(int position)
    	{
             	inbox.removeElementAt(position);
     	}
     	public static void addDraft(Email email)
     	{	
             	draft.add(email);
  	}
     	public static void setDraft(Vector vector)
       	{
             	draft=vector;
      	}
    	public static void deleteDraft(int position)
     	{
              	draft.removeElementAt(position);
       	}
    	public static void addSent(Email email)
    	{
            	sent.add(email);
      	}
     	public static void setsent(Vector vector)
      	{
             	sent=vector;
     	}
    	public static void deleteSent(int position)
      	{
              	sent.removeElementAt(position);
     	}
	public static String  getip()
	{
		return st;
	}
      	public static void close()
	{
             	try
               	{
			//SENDS UNDELETED MAIL BACK TO SERVER FOR STORAGE//
			for(int count=0;count<inbox.size();count++)
 			{
                 		returnMail(getMail(count));
                   		inbox.removeElementAt(count);
                         	count--;
                  	}
            		//SENDS UNDELETED DRAFT MAIL BACK OF SERVER FOR STORAGE//
               		for(int count=0;count<draft.size();count++)
                  	{
              			returnDraft(getDraft(count));
                   		draft.removeElementAt(count);
               			count--;
               		}
            		//SENDS UNDELETED SENT MAIL BACK OF SERVER FOR STORAGE//
              		for(int count=0;count<sent.size();count++)
                   	{
                     		returnSent(getSent(count));
                   		sent.removeElementAt(count);
                		count--;
             		}
           		ObjectOutputStream objectOut=new ObjectOutputStream(link.getOutputStream());
                  	String option="QUITTING";
               		objectOut.writeObject(option);
                  	objectOut.flush();
                	link.close();
   			try
             		{
             			//SET UP CONNECTION INFORMATION//
              			link=new Socket(st,PORT);
                   		//SET UP CLIENT DATA STORAGE//
               			userNames=new Vector();
              			inbox=new Vector();
               			draft=new Vector();
                   		sent=new Vector();
                     		//GET PERMITED USER NAMES FROM  SERVER//
              			getUserNamesFromServer();
                      		SignInWindow s2=new SignInWindow();
           			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                 		final int HEIGHT=400;
              			final int WIDTH=400;
          			s2.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
              			s2.setVisible(true);
                  	}
                 	//EXCEPTION HANDLING ROUTINES AND CLASSES//
                  	catch(UnknownHostException uhe)
           		{
                            	System.out.println("Host ID Not found");
                 	}
                      	catch(IOException ioe)
                  	{
                              	ioe.printStackTrace();
                   	}
           	}
              	catch(IOException ioe)
               	{
                     	ioe.printStackTrace();
               	}
        }

}
                





                        






















