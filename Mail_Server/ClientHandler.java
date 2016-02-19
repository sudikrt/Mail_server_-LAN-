import java.io.*;
import java.net.*;
import java.util.*;
class ClientHandler extends Thread
{
	private Socket client;
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	public ClientHandler(Socket socket)
	{
		client=socket;
	}
	public synchronized void run()
	{
		try
		{
			sendUserNamesToClient();
			String user=getUserNameFromClient();
			setName(user);
			char operation;
			do
			{
				objectIn=new ObjectInputStream(client.getInputStream());
				String option=(String)objectIn.readObject();
				operation=option.charAt(0);
				switch(operation)
				{
					case 'S' : recieveMessagesFromClient();
	           				break;
					case 'R' : sendMessagesToClient();
           					break;
					case 'D' : recieveMessagesFromClient1();
           					break;
					case 'M' : sendMessagesToClient1();
           					break;
					case 'E' : recieveMessagesFromClient2();
           					break;
					case 'N' : sendMessagesToClient2();
           					break;
					case 'Q' : Server.updateMsgWind("Disconnecting"+" "+getName()+"...");
           					break;
				}
			}
			while(operation!='Q');
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
		finally
		{
			try
			{
				Server.updateMsgWind("Closing down connection");
				client.close();
				Server.updateMsgWind("Client Disconneted Succesful");
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
	private synchronized void sendUserNamesToClient()throws IOException
	{
		objectOut=new ObjectOutputStream(client.getOutputStream());
		int vectorSize=Server.getUserNamesVectorSize();
		objectOut.writeObject(new Integer(vectorSize));
		for(int count=0;count<vectorSize;count++)
		{
			objectOut.writeObject((User)Server.getUser(count));
		}
		objectOut.flush();
	}
	private synchronized String getUserNameFromClient()throws IOException,ClassNotFoundException
	{
		objectIn=new ObjectInputStream(client.getInputStream());
		User user=(User)objectIn.readObject();
		Boolean objectNewUser=(Boolean)objectIn.readObject();
		boolean newUser=objectNewUser.booleanValue();
		Server.updateMsgWind("..."+user.getUserName()+"is now connected to server");
		if(newUser==true)
		{
			Server.addUser(user);
		}
		return(user.getUserName());
	}
	//RECIEVE MESSAGE FROM CLIENT//
	private synchronized void recieveMessagesFromClient()
	{
		try
		{
			Email email=(Email)objectIn.readObject();
			Server.addMail(email);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}
	//SEND MESSAGE TO CLIENT FROM SERVER//
	private synchronized void sendMessagesToClient()
	{
		try
		{
			objectOut=new ObjectOutputStream(client.getOutputStream());
			int vectorSize=Server.getMailSize();
			for(int count=0;count<vectorSize;count++)
			{
				if(getName().equals(Server.getEmail(count).getRecipient()))
				{
					objectOut.writeObject("SENDING");
					objectOut.flush();
					objectOut.writeObject(Server.getEmail(count));
					objectOut.flush();
					Server.deleteMail(count);
					count--;
					vectorSize--;
				}
			}
			objectOut.writeObject("END");
			objectOut.flush();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	//RECIEVE DRAFT MESSAGE FROM CLIENT//
	private synchronized void recieveMessagesFromClient1()
	{
		try
		{
			Email email=(Email)objectIn.readObject();
			Server.addDraft(email);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}

	//SEND DRAFT MESSAGES TO CLIENT FROM SERVER//
	private synchronized void sendMessagesToClient1()
	{
		try
		{
			objectOut=new ObjectOutputStream(client.getOutputStream());
			int vectorSize=Server.getDraftSize();
			for(int count=0;count<vectorSize;count++)
			{
				if(getName().equals(Server.getDraft(count).getRecipient()))
				{
					objectOut.writeObject("DSENDING");
					objectOut.flush();
					objectOut.writeObject(Server.getDraft(count));
					objectOut.flush();
					Server.deleteDraft(count);
					count--;
					vectorSize--;
				}
			}
			objectOut.writeObject("END");
			objectOut.flush();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	//RECIEVE SENT MESSEAGES FROM CLIENT//
	private synchronized void recieveMessagesFromClient2()
	{
		try
		{
			Email email=(Email)objectIn.readObject();
			Server.addSent(email);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}
	//SEND SENT MESSAGES TO CLIENT FROM SERVER//
	private synchronized void sendMessagesToClient2()
	{
		try
		{
			objectOut=new ObjectOutputStream(client.getOutputStream());
			int vectorSize=Server.getSentSize();
			for(int count=0;count<vectorSize;count++)
			{
				if(getName().equals(Server.getSent(count).getRecipient()))
				{
					objectOut.writeObject("ESENDING");
					objectOut.flush();
					objectOut.writeObject(Server.getSent(count));
					objectOut.flush();
					Server.deleteSent(count);
					count--;
					vectorSize--;
				}
			}
			objectOut.writeObject("END");
			objectOut.flush();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}

