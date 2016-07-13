
//INCLUDE LIBARRIES NEEDED FOR GUI SETUP//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//INCLUDE LIBARRIES NEEDED FOR OPERTION OF THE SERVER//
import java.io.*;
import java.net.*;
import java.util.*;

	public class Server extends JFrame{
	private static ServerSocket serverSocket;
	private static final int PORT=1234;
	private static Vector userNames;
	private static Vector mail;
	private static Vector draft;
	private static Vector sent;
	
	private static ObjectOutputStream fileOut;
	private static ObjectInputStream fileIn;
	private static ObjectInputStream objectOut;
	private static ObjectInputStream ObjectIn;
	
	private static JTextArea messageWindow;
	
	//CONSTRUCTOR FOR SERVER GUI//
	public Server()
	{
		addWindowListener(
				new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
					{
						shutDownServer();
					}
				}
				);
		setTitle("E-Mail Server Monitoring");
		messageWindow=new JTextArea(38,40);
		
		messageWindow.setWrapStyleWord(true);
		messageWindow.setLineWrap(true);
		messageWindow.setEditable(false);
		
		getContentPane().add(messageWindow,BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(messageWindow));
	}
	//MAIN METHOD FOR SERVER
	public static void main(String args[])throws IOException 
	{
		//CREATE NEW INSTANCE OF SERVER STORAGE VECTOR
		userNames=new Vector();
		mail=new Vector();
		draft=new Vector();
		sent=new Vector();
		
		Server serverGUI=new Server();
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		final int HEIGHT=500;
		final int WIDTH=400;
		serverGUI.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
		serverGUI.setVisible(true);
		
		startUpServer();
		//SETUP A SOCKET TO ACCEPT A CONNECTION
		try
		{
			serverSocket=new ServerSocket(PORT);
		}
		catch(IOException ioe)
		{
			updateMsgWind("Unable to set up PORT. ");
			System.exit(1);
		}
		do
		{
			
			//ACCEPT NEW CLIENT
			Socket client=serverSocket.accept();
			updateMsgWind("Accepting incoming connections");
			
			//ALLOCATE NEW CLIENT HANDLER THREAD//
			ClientHandler handler=new ClientHandler(client);
			handler.start();
			
		}while(true);
	}
	private static void startUpServer()
	{
		try
		{
			readInUserNameFromServerFile();
			readInMailFromServerFile();
            readInDraftFromServerFile();
            readInSentFromServerFile();
		}
		catch(IOException ioe)
		{
			updateMsgWind("Error in reading from file");
		}
	}
	
	
	//GETS A VECTOR OF EMAILS FROM PERSISTENT STORAGE
	private static void readInUserNameFromServerFile()throws IOException
	{
		//SET UP FILE INPUT STREAM FROM PERSISTENT SERVER FILE
		fileIn=new ObjectInputStream(new FileInputStream("Usernames.dat"));
		try
		{
			do
			{
				//READ OBJECT FROM FILE AND ADD IT TO SERVER VECTOR
				User temp=(User)fileIn.readObject();
				addUser(temp);
			}while(true);
		}
		catch(EOFException eofe)
		{
			//DISPLAY THE MESSAGE AND CLOSE STREAM
			updateMsgWind("UserNames read sucessfully");
			fileIn.close();
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}
	
	//GET VECTOR MAILS FROM PERSISTENT STORAGE
	private static void readInMailFromServerFile()throws IOException
	{
		fileIn=new ObjectInputStream(new FileInputStream("Mail.dat"));
		try
		{
			do
			{
				//READ OBJECT FROM FILE AND ADD TO SERVER VECTOR
				Email temp=(Email)fileIn.readObject();
				addMail(temp);
			}while(true);
		}
		catch(EOFException eofe)
		{
			updateMsgWind("Mail read sucessfully");
			fileIn.close();
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}
	
	//GETS A VECTOR OF EMAILS FROM PERSISTENT STORAGE
	private static void readInDraftFromServerFile()throws IOException
	{
		fileIn=new ObjectInputStream(new FileInputStream("Mail.dat"));
		try
		{
			do
			{
				//READ OBJECT FROM FILE AND ADD IT TO SERVER
				Email temp=(Email)fileIn.readObject();
                addDraft(temp);
            }while(true);
		}
		catch(EOFException eofe)
		{
			updateMsgWind("Draft-Mail read sucessfully");
			fileIn.close();
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}
	
	//GETS A VECTOR OF EMAILS FROM PERSISTENT STORAGE//
	private static void readInSentFromServerFile() throws IOException
    {
		fileIn=new ObjectInputStream(new FileInputStream("Sent.dat"));
	    try
	    {
	    	do
	        {
	    		//READ OBJECT FROM FILE AND ADD TO SERVER VECTOR
	            Email temp=(Email)fileIn.readObject();
	            addSent(temp);
	         }while(true);
	     }
	     catch(EOFException eofe)
	     {
	    	 updateMsgWind("Sent Read Succesfully");
	         fileIn.close();
	     }
	     catch(ClassNotFoundException cnfe)
	     {
	    	 cnfe.printStackTrace();
	     }
	}
	
	
	private static void shutDownServer()
	{
		try
		{
			writeOutMailToServer();
			writeOutUserToServer();
			writeOutDraftToServer();
			writeOutSentToServer();
		}
		catch(IOException ioe)
        {
             updateMsgWind("Eror Writing To File");
        }
        System.exit(0);
        
	}
	
	//SENDS ALL THE SERVER USERNAMES TO FILE
	private static void writeOutUserToServer()throws IOException
	{
		fileOut=new ObjectOutputStream(new FileOutputStream("Usernames.dat"));
		int vectorSize=getUserNamesVectorSize();
		if(vectorSize>0)
		{
			updateMsgWind("Adding user to premenent storage");
			for(int count=0;count<vectorSize;count++)
			{
				User user=(User)getUser(count);
				fileOut.writeObject(user);
			}
			fileOut.close();
		}
		else
		{
			updateMsgWind("No current users");
		}
	}
	
	//SENDS ALL SERVER MAIL TO A FILE
	private static void writeOutMailToServer() throws IOException
	{

	    fileOut=new ObjectOutputStream(new FileOutputStream("Mail.dat"));
        int vectorSize=getMailSize();
        if(vectorSize>0)
		{
        	updateMsgWind("Adding Mail To Permament Storage");
           	for(int count=0; count<vectorSize;count++)
	        {
	          	Email mail=(Email)getEmail(count);
	            fileOut.writeObject(mail);
	        }
            fileOut.close();
	   	}
	    else
	   	{
	        updateMsgWind("No Current Mail");
	    }
	}  

	//SENDS ALL SERVER SENT MAIL TO A FILE//
	private static void writeOutDraftToServer() throws IOException
	{
		fileOut=new ObjectOutputStream(new FileOutputStream("Draft.dat"));
		int vectorSize=getDraftSize();
		if(vectorSize>0)
	    {
			updateMsgWind("Adding Mail To Premament Storage");
			for(int count=0; count<vectorSize; count++)
	        {
				Email mail=(Email)getDraft(count);
	            fileOut.writeObject(mail);
	         }
            fileOut.close();
         }
	     else
	     {
             updateMsgWind("No Current Mail!");
	     }
	  }
	
	
	//SENDA ALL SERVER SENT MAIL TO A FILE//
	private static void writeOutSentToServer()throws IOException
	{

	   fileOut=new ObjectOutputStream(new FileOutputStream("Sent.dat"));
      int vectorSize=getSentSize();
      if(vectorSize>0)
	  {
    	  updateMsgWind("adding Mail To Permament Storage");
          for(int count=0; count<vectorSize;count++)
	      {
	         Email mail=(Email)getSent(count);
	         fileOut.writeObject(mail);
	      }
          fileOut.close();
	  }
	  else
	  {
		  updateMsgWind("No Current Mail!");
	  }
	}

	//ACCESSOR METHODS//
	        public static User getUser(int position)
	        {
	            return(User)userNames.elementAt(position);
	        }

	        public static int getUserNamesVectorSize()
	        {
	            return userNames.size();
	        }


	        public static Email getEmail(int position)
	        {
	            return(Email)mail.elementAt(position);
	        }
	        
		public static int getMailSize()
	        {
	            return mail.size();
	        }

	        public static Email getDraft(int position)
	        {
	            return(Email)draft.elementAt(position);
	        }


	        public static int getDraftSize()
	        {
	            return draft.size();
	        }


	        public static Email getSent(int position)
	        {
	            return(Email)sent.elementAt(position);
	        }


	        public static int getSentSize()
	        {
	            return sent.size();
	        }


	//MUTATOR METHOD//
	        public static void addUser(User newUser)
	        {
	            userNames.add(newUser);
	        }


	        public static void addMail(Email newMail)
	        {
	            mail.add(newMail);
	        }


	        public static void deleteMail(int position)
	        {
	            mail.removeElementAt(position);
	        }


	        public static void addDraft(Email newMail)
	        {
	            draft.add(newMail);
	        }


	        public static void deleteDraft(int position)
	        {
	            draft.removeElementAt(position);
	        }


	        public static void addSent(Email newMail)
	        {
	           sent.add(newMail);
	        }


	        public static void deleteSent(int position)
	        {
	           sent.removeElementAt(position);
	        }


	        public static void updateMsgWind(String info)
	        {
	        	messageWindow.setText(messageWindow.getText()+"\n"+info);
		}
	   }




	


