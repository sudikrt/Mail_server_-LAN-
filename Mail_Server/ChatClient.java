import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Vector;
import java.util.Iterator;

//CREATE THE GUI OF THE CLIENT SIDE//
public class ChatClient extends JFrame implements ActionListener,ItemListener,KeyListener
{
	
	Container pane;
	List usernames;
	JTextField tf;
	TextArea message;
	JButton button,clr;
	JButton lout;
	JScrollPane scrollpane1;
	JLabel label,fromid,toid,from,to;
	Socket socket,socket1,socket2;
	DataInputStream din;
	DataOutputStream dout;
	DataOutputStream dlout;
	DataInputStream din1;
	DataOutputStream dout1;
	String name;
	ChatClient(String name)throws IOException
	{
		this.name=name;
		setTitle("Chat Room");
		
		tf=new JTextField();
		tf.setFont(new Font("Comic Sans MS",Font.BOLD,12));
		tf.setForeground(Color.black);
		tf.addActionListener(this);
		tf.addKeyListener(this);
		message=new TextArea();
		message.setFont(new Font("Courier New",Font.BOLD,12));
		message.setForeground(Color.black);
		message.setEditable(false);
		label=new JLabel("Message");
		label.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		label.setForeground(Color.black);
		fromid=new JLabel("From:");
		fromid.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		fromid.setForeground(Color.black);
		toid=new JLabel("To:");
		toid.setFont(new Font("Comic Sans MS",Font.BOLD,14));
		toid.setForeground(Color.black);
		from=new JLabel(name);
		from.setFont(new Font("Courier New",Font.ITALIC,18));
		from.setForeground(Color.blue);
		to=new JLabel();
		to.setFont(new Font("Courier New",Font.ITALIC,18));
		to.setForeground(Color.blue);
		usernames=new List();
		usernames.setFont(new Font("Courier New",Font.BOLD,14));
		usernames.setForeground(Color.black);
		usernames.addItemListener(this);
		button=new JButton("Send");
		clr=new JButton("Clear");
		lout=new JButton("Exit");
		JPanel panel=new JPanel();
		button.addActionListener(this);
		clr.addActionListener(this);
		lout.addActionListener(this);
		setResizable(false);
		pane=getContentPane();
		pane.setLayout(new BorderLayout());
		setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		pane.add(fromid);
		pane.add(toid);
		pane.add(from);
		pane.add(to);
		pane.add(tf);
		pane.add(button);
		pane.add(clr);
		pane.add(message);
		pane.add(label);
		pane.add(lout);
		pane.add(usernames);
		
		pane.setLayout(null);
		fromid.setBounds(10,10,40,30);
		toid.setBounds(520,10,25,30);
		from.setBounds(60,10,150,30);
		to.setBounds(550,10,150,30);
		message.setBounds(10,50,490,250);
		usernames.setBounds(520,50,180,250);
		label.setBounds(10,310,80,30);
		tf.setBounds(80,310,380,30);
		button.setBounds(480,310,70,30);
		clr.setBounds(560,310,70,30);
		lout.setBounds(640,310,70,30);
		try{
			String st1=Client.getip();
			st1=st1.trim();
			socket=new Socket(st1,1236);
			socket1=new Socket(st1,1236);
			socket2=new Socket(st1,1236);
			
		}
		catch(ConnectException uhe)
		{
			
			JOptionPane.showMessageDialog(null,"Server not exist");
			this.setVisible(false);
			MainMenu s1=new MainMenu(Client.getCurrentUserName());
                	Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                	final int HEIGHT=590;
                	final int WIDTH=820;
                	s1.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                	s1.setVisible(true);
			
		}
			din1=new DataInputStream(socket2.getInputStream());
			din=new DataInputStream(socket.getInputStream());
			dout=new DataOutputStream(socket.getOutputStream());
			dout.writeUTF("All" + " " + name + " has Logged in");
			dout.flush();
			dlout=new DataOutputStream(socket1.getOutputStream());
			dout1=new DataOutputStream(socket2.getOutputStream());
		
		My1 m1=new My1(dout1,name,din1);
		Thread t1=new Thread(m1);
		t1.start();
		My m=new My(din,message);
		Thread t=new Thread(m);
		t.start();
	}
	public void itemStateChanged(ItemEvent ie)
	{
		to.setText(usernames.getSelectedItem());
	}
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			Toolkit.getDefaultToolkit().beep();
			if(tf.getText().equals("") || to.getText().equals("")) return;
			String recipient=usernames.getSelectedItem();
			String str="";
			str=name+":] "+tf.getText();
			try
			{
				dout.writeUTF(recipient + " " +str.toString());
				System.out.println(str);
				dout.flush();
				if(!recipient.equals(name) && !recipient.equals("All"))
				message.append(str+"\n");
				show();
				tf.setText("");
			}
			catch(IOException ae)
			{
				System.out.println(ae);
			}
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		//sending the message//
		if(e.getSource()==button)
		{
			if(tf.getText().equals("") || to.getText().equals("")) return;
			String recipient=usernames.getSelectedItem();
			String str="";
			str=name+":] "+tf.getText();
			try
			{
				dout.writeUTF(recipient + " " +str.toString());
				System.out.println(str);
				dout.flush();
				if(!recipient.equals(name) && !recipient.equals("All"))
				message.append(str+"\n");
				show();
				tf.setText("");
			}
			catch(IOException ae)
			{
				System.out.println(ae);
			}
		}
		
		if(e.getSource()==clr)
		{
			message.setText("");
		}
		//CLIENT LOGOUT//
		if(e.getSource()==lout)
		{
			try
			{
				//Sending the message for logout//
				dout.writeUTF("All" + " " + name +" has logged out");
				dout.flush();
				dlout.writeUTF(name);
				dlout.flush();
				Thread.currentThread().sleep(1000);
			}
			catch(Exception oe)
			{}
			setVisible(false);
			MainMenu sub=new MainMenu(name);
			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
			final int HEIGHT=590;
			final int WIDTH=820;
			sub.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
			sub.setVisible(true);
		}
	}

	public void windowClosing(WindowEvent w)
	{
		try
		{
			dlout.writeUTF(name);
			dlout.flush();
			Thread.currentThread().sleep(1000);
			System.exit(1);
		}
		catch(Exception oe)
		{}
	}

	//class is used to maintaing the list of user name//
	class My1 implements Runnable
	{
		DataOutputStream dout1;
		DataInputStream din1;
		String name,lname;
		Vector alname=new Vector(); //stores the list of user name//
		ObjectInputStream obj; //read the list of user names//
		int i=0;
		My1(DataOutputStream dout1,String name,DataInputStream din1)
		{
			this.dout1=dout1;
			this.name=name;
			this.din1=din1;
		}
		public void run()
		{
			try
			{
				dout1.writeUTF(name); //write the user name in output stream//
				while(true)
				{
					obj=new ObjectInputStream(din1); //read the list of user names//
					alname=(Vector)obj.readObject();
					if(i>0)
					usernames.removeAll();
					Iterator i1=alname.iterator();
					System.out.println(alname);
					usernames.add("All");
					while(i1.hasNext())
					{
						lname=(String)i1.next();
						i++;
						if(lname.equals(name))
							lname="*"+name+"*";
						
						//add the user names in the list box//
						usernames.add(lname);
					}
				}
			}
			catch(Exception oe)
			{}
		}
	}

	//CLASS IS USED TO RECEIVE THE MESSAGES//
	class My implements Runnable
	{
		DataInputStream din;
		TextArea message;
		My(DataInputStream din,TextArea message)
		{
			this.din=din;
			this.message=message;
		}
		public void run()
		{
			String str1="";
			while(true)
			{
				try
				{
					str1=din.readUTF();//RECEIVE THE MESSAGE//
					//add the list msg in list box//
					message.append(str1+"\n");
				}
				catch(Exception e)
				{}
			}
		}
	}
}


