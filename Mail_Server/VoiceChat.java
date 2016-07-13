import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Vector;
import java.util.Iterator;

public class VoiceChat extends JFrame implements ActionListener,ItemListener
{

        	private ImageIcon icon=new ImageIcon("img/audio.JPG");
       	Container pane;
        	JLabel vclabel,userList,msg,to,ton;
        	static JButton request,start,back,stop;
       	 static JTextArea txArea;
        	List usernames;
        	JSplitPane jsp;
        	public Socket s,s1,s2;
        	DataInputStream din;
        	DataOutputStream dout;
        	DataOutputStream dlout;
       	JScrollPane jp1;
        	DataInputStream din1;
        	DataOutputStream dout1;
        	public static String name,str1,recipient;
		
	VoiceChat(String name)throws Exception
	{	
	
	        addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
			 	MainMenu sub=new MainMenu(Client.getCurrentUserName());
                         			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                         			final int HEIGHT=590;
                         			final int WIDTH=820;
                         			sub.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                         			sub.setVisible(true);;
			}
		});
                		this.name=name;
		setTitle("Audio Chat");
		Image imglbl = icon.getImage();  
		Image newimglb = imglbl.getScaledInstance(374,232,java.awt.Image.SCALE_SMOOTH);  
		ImageIcon newIconlb = new ImageIcon(newimglb);
		JLabel imglb=new JLabel(newIconlb);
		imglb.setSize(479,240);
		
                		vclabel=new JLabel();
		userList=new JLabel();
		userList.setText("<html><body><u>List of users:-</u></body></html>");
		msg=new JLabel();
		msg.setText("<html><body><u>Message box:</u></body></html>");
		Font f=new Font("Arial",Font.BOLD,30);
		vclabel.setFont(f);
		
		vclabel.setText("<html><body><u><Font color=rgb(249,6,176)>Audio Chat</Font></u></body></html>");
		to=new JLabel("To:");
		ton=new JLabel();	
			
		 back=new JButton("Back");
		Font f1=new Font("Arial",Font.BOLD,15);
		msg.setFont(f1);
		to.setFont(f1);
		ton.setFont(f1);
		userList.setFont(f1);
		
		stop=new JButton("Stop");
		stop.setEnabled(false);
                		request=new JButton("Request");
                		start=new JButton("Start");
              		jp1=new JScrollPane();
                		txArea=new JTextArea();
                		usernames=new List();
		usernames.setFont(f1);
		
		pane=getContentPane();

		setResizable(false);
		pane.setLayout(new BorderLayout());
			
		vclabel.setBounds(135,20,200,40);	
		userList.setBounds(460,35,120,50);

 		request.setBounds(49,240,80,35);
                		start.setBounds(149,240,80,35);
               		stop.setBounds(249,240,80,35);
		
		back.setBounds(349,240,80,35);
		txArea.setFont(f1);
		to.setBounds(462,64,30,40);
		ton.setBounds(493,64,100,40);
		msg.setBounds(60,270,120,50);
                		usernames.setBounds(450,100,135,180);
		jp1.setBounds(49,313,380,90);
		jp1.setViewportView(txArea);
		txArea.setEditable(false);
		
		start.setEnabled(false);
		request.addActionListener(this);
		start.addActionListener(this);
		back.addActionListener(this);
		stop.addActionListener(this);
		
		usernames.addItemListener(this);
                		pane.add(request);
               		pane.add(msg);
		pane.add(userList);
                		pane.add(start);
                		pane.add(jp1);
		pane.add(vclabel);
		pane.add(stop);
		
		pane.add(to);
		pane.add(ton);	
		pane.add(imglb);	
		pane.add(back);
                		pane.add(usernames);
		pane.setLayout(null);
		try
		{
			String st1=Client.getip();
			st1=st1.trim();
			s=new Socket(st1,1235);
			s1=new Socket(st1,1235);
			s2=new Socket(st1,1235);
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
                		din=new DataInputStream(s.getInputStream());
                		dout=new DataOutputStream(s.getOutputStream());		
				dout.flush();
                		dlout=new DataOutputStream(s1.getOutputStream());
                		dout1=new DataOutputStream(s2.getOutputStream());
                		din1=new DataInputStream(s2.getInputStream());
                		My1 m1=new My1(dout1,name,din1);
                		Thread t1=new Thread(m1);
                			t1.start();
		 My m=new My(din,txArea);
		Thread t2=new Thread(m);
                			t2.start();   
      	}
	public void itemStateChanged(ItemEvent ie)
	{	
		ton.setText(usernames.getSelectedItem());
	}
	 //CLIENT IS USED TO MAINTAINING THE LIST OF USER NAME
         	class My1 implements Runnable
         	{	
               		DataOutputStream dout1;
                		DataInputStream din1;
                		String name,lname;
                		Vector alname=new Vector();//Stores the list of user name//
                		ObjectInputStream obj;
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
                                	dout1.writeUTF(name);
					while(true)
                                	{
                                        	obj=new ObjectInputStream(din1);//Read the list of user//
                                        	alname=(Vector)obj.readObject();
		 	 			if(i>0)
							usernames.removeAll();
                                        	Iterator i1=alname.iterator();
                                        	
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
                          {
                          }
                	}
           	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==request)
		{
			try
			{	stop.setEnabled(true);
			        	start.setEnabled(false);
				recipient=usernames.getSelectedItem();	
				String ip=Inet4Address.getLocalHost().getHostAddress();
				String str="";
                       		 	str=name+":>"+ip;
				try
                        			{
                                				dout.writeUTF(recipient+" "+str.toString());
                                				System.out.println(str);
                                				dout.flush();
                               				if(!recipient.equals(name) && !recipient.equals("All"))
                                				txArea.setText(str+"\n");
                                				show();
                               				snd s1=new snd();
					s1.start();
                         			}
                         			catch(IOException a4e)
                         			{
                                				System.out.println(a4e);
                         			}
				request.setEnabled(false);
			}
			catch(Exception e){
			}	
		 }
		if(ae.getSource()==start)
		{
			try
			{		
			 	Tx tx = new Tx(str1.substring(str1.indexOf('>')+1));
  			}
			catch(Exception e){
			}
			request.setEnabled(false);
	        		start.setEnabled(false);
			stop.setEnabled(true);
			stop.setVisible(true);
		}
		if(ae.getSource()==back)
		{
			try{
				dout.flush();
				dlout.writeUTF(name);
				dlout.flush();
			}
			catch(Exception aqe)
			{
			}
			back b1=new back();
			b1.start();
		}
		if(ae.getSource()==stop)
		{
			request.setEnabled(true);	
			Tx.sClose();
			sender.sClose();
			start.setEnabled(false);
			stop.setEnabled(false);
		}
		
        	}
	 class My implements Runnable
             	{
                		DataInputStream din;
                		JTextArea txArea;
                		My(DataInputStream din,JTextArea txArea)
                		{
                        		this.din=din;
                        		this.txArea=txArea;
                		}
                		public void run()
                		{
                        		str1="";
                        		while(true)
                  	    		{
                                		try
                                		{
                                        		str1=din.readUTF();//RECEIVE THE MEASSAGE//
                                        		//add the  msg in message box//
                                       		txArea.append(str1+"\n");
				txArea.setText(str1.substring(0,str1.indexOf(':'))+"  is requesting"+"\n");
				txArea.append("Click start button"+"\n");	
				start.setEnabled(true);
				stop.setEnabled(false);
				request.setEnabled(false);
				dout.flush();
                                		}
                               		catch(Exception e)
                                		{
                                		}
                         	}
                 	}
          	}	
	class snd extends Thread
	{
		public void run()
		{	
			try{
				sender s2=new sender();
			}
			catch(Exception ee){
			}
		}
	}
	class back extends Thread
	{
		public void run()
		{	
			try{
				Tx.sClose();
				sender.sClose();		
				setVisible(false);
				MainMenu sub=new MainMenu(Client.getCurrentUserName());
                         			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                         			final int HEIGHT=590;
                         			final int WIDTH=820;
                         			sub.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                         			sub.setVisible(true);
			}
			catch(Exception er){
			}
		}
	}
}