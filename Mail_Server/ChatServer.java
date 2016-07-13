import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer
{
	ServerSocket ss;
	Socket s,s1,s2;
	Vector al=new Vector();
	Vector al1=new Vector();
	Vector al2=new Vector();
	Vector alname=new Vector();
	public ChatServer()throws IOException
	{
		ss=new ServerSocket(1236);
		//create server socket 
		while(true)
		{
			s=ss.accept();    //accept the client socket s1=ss.accept();
			s1=ss.accept();
			s2=ss.accept();
			al.add(s);   //add the Client socket in Vector
			al1.add(s1);
			al2.add(s2);
			System.out.println("Client is Connected");
			MyThread2 m=new MyThread2(s2,al2,alname); //new thread maintanig the list of user name
			Thread t2=new Thread(m);
			t2.start();
			MyThread r=new MyThread(s,al,alname);//new thread for receive and sending the messages
			Thread t=new Thread(r);
			t.start();
			MyThread1 my=new MyThread1(s1,al1,s,s2); //new thread for update the list of user name 
			Thread t1= new Thread(my);
			t1.start();
		}
	}
	public static void main(String args[])
	{
		try
		{
			new ChatServer();
		}
		catch(IOException e)
		{}
	}
}

//class is used to update the list of user name
class MyThread1 implements Runnable
{
	Socket s1,s,s2;
	static Vector al1;
	DataInputStream ddin;
	String sname;
	MyThread1(Socket s1,Vector al1,Socket s,Socket s2)
	{
		this.s1=s1;
		this.al1=al1;
		this.s=s;
		this.s2=s2;
	}
	public void run()
	{
		try
		{
			ddin=new
			DataInputStream(s1.getInputStream());
			while(true)
			{
				sname=ddin.readUTF();
				System.out.println("Exit:"+sname);
				MyThread2.alname.remove(sname); //remove the logout user name from arraylist
				MyThread2.every();
				al1.remove(s1);
				MyThread.al.remove(s);
				MyThread2.al2.remove(s2);
			}
		}
		catch(Exception ie)
		{}
	}
}

//class is used to maintain the list of all online users
class MyThread2 implements Runnable
{
	Socket s2;
	static Vector al2;
	static Vector alname;
	static DataInputStream din1;
	static DataOutputStream dout1;
	MyThread2(Socket s2,Vector al2,Vector alname)
	{
		this.s2=s2;
		this.al2=al2;
		this.alname=alname;
	}
	public void run()
	{
		try
		{
			din1=new DataInputStream(s2.getInputStream());
			alname.add(din1.readUTF()); //store the user name in arraylist 
			every();
		}
		catch(Exception oe)
		{
			System.out.println("Main expression"+oe);
		}
	}
	//send the list of user name to all client
	static void every()throws Exception
	{
		Iterator i1=al2.iterator();
		Socket st1;
		while(i1.hasNext())
		{
			st1=(Socket)i1.next();
			dout1=new DataOutputStream(st1.getOutputStream());
			ObjectOutputStream obj=new ObjectOutputStream(dout1);
			obj.writeObject(alname);
			//write the list of user in stream of all clients
			dout1.flush();
			obj.flush();
		}
	}
}

//class is used to receive the massage and send it to all clients
class MyThread implements Runnable
{
	Socket s;
	static Vector al;
	static Vector alname;
	DataInputStream din;
	DataOutputStream dout;
	MyThread(Socket s, Vector al,Vector alname)
	{
		this.s=s;
		this.al=al;
		this.alname=alname;
	}
	public void run()
	{
		int i=1;
		try
		{
			din=new DataInputStream(s.getInputStream());
		}
		catch(Exception e)
		{}
		while(i==1)
		{
			try
			{
				String msgFromClient=new String();
				msgFromClient=din.readUTF(); //read the message
				StringTokenizer st=new StringTokenizer(msgFromClient);
				String Recipient=st.nextToken();
				String msg="";
				while(st.hasMoreTokens())
				{
					msg=msg+" "+st.nextToken();
				}
				if(Recipient.equals("All"))
				{
					Iterator it=al.iterator();
					Socket soc;
					while(it.hasNext())
					{
						soc=(Socket)it.next();
						dout=new DataOutputStream(soc.getOutputStream());
						dout.writeUTF(msg);
						dout.flush();
					}
				}
				else
				{
					for(int iCount=0;iCount<alname.size();iCount++)
					{
						if(alname.elementAt(iCount).equals(Recipient))
						{
							Socket tSoc=(Socket)al.elementAt(iCount);
							DataOutputStream tdout=new DataOutputStream(tSoc.getOutputStream());
							tdout.writeUTF(msg);
							tdout.flush();
						}
					}
				}
			}
			catch(IOException e)
			{}
		}
	}
}







