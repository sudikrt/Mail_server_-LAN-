//IMPORT STANDARD GUI PACKAGES//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//IMPORT NEEDED FOR VECTOR AND STREAMS//
import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.Toolkit.*;
;public class SignInWindow extends JFrame  implements ActionListener,KeyListener{
	private JButton login,signup,exit;
	private ImageIcon signin,loginicon;
	private JLabel title,signinimg,signinlabel,signuplabel,labelPassword,labelUsername;
	private JTextField txtUsername,txtPassword;
	private Container pane;
	private ImageIcon icon=new ImageIcon("img/18.JPG");
	private static ObjectOutputStream objectOut;
	
	
	public SignInWindow()
	{
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				Client.closeDown();
			}
		});
		setTitle("Sign In");
	

		 try 
	    { 
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
	    } 
	    catch(Exception e){ 
	    }
		 try{
			 this.setIconImage(new ImageIcon(getClass().getResource("img/admin.PNG")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
		 }
		 catch(Exception execxc)
		 {
			 
		 }
		Image imglbl = icon.getImage();  
		Image newimglb = imglbl.getScaledInstance(400, 400,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon newIconlb = new ImageIcon(newimglb);
		JLabel imglb=new JLabel(newIconlb);
		imglb.setSize(400,400);
		
		pane=getContentPane();
		pane.setLayout(new BorderLayout());
		
		pane.setBackground(Color.LIGHT_GRAY);
		
		title=new JLabel("LAN CHAT AND MAIL SERVER");
		title.setFont(new Font("Calibri",Font.BOLD,25));
		title.setForeground(Color.white);
		
		login=new JButton("Sign in");
		login.addActionListener(this);

		signup=new JButton("Sign up");
		signup.addActionListener(this);
		
		exit=new JButton("Exit");
		exit.addActionListener(this);

		loginicon=new ImageIcon("img/admin.PNG");
		Image img = loginicon.getImage();  
		Image newimg = img.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon newIcon = new ImageIcon(newimg);
		signinimg=new JLabel(newIcon);
		
				
		signinlabel=new JLabel("Sign in to continue to ");
		signinlabel.setFont(new Font("Calibri",Font.BOLD,22));
		signinlabel.setForeground(Color.gray);

		signuplabel=new JLabel("New User ?");
		signuplabel.setFont(new Font("Calibri",Font.BOLD,20));
		signuplabel.setForeground(Color.gray);
		
		labelPassword=new JLabel("Password");
		labelUsername=new JLabel("Username");
		labelPassword.setFont(new Font("Calibri",Font.BOLD,17));
		labelPassword.setForeground(Color.white);
		labelUsername.setFont(new Font("Calibri",Font.BOLD,17));
		labelUsername.setForeground(Color.white);
		
		txtUsername=new JTextField(10);
		txtPassword=new JPasswordField(10);
		txtPassword.addKeyListener(this);

		
		title.setBounds(18,140,400,50);
		signinimg.setBounds(150,40,70,70);
		signinlabel.setBounds(85,90,250,60);
		labelUsername.setBounds(40,200,90,30);
		txtUsername.setBounds(125,200,180,30);
		labelPassword.setBounds(40, 240, 90, 30);
		txtPassword.setBounds(125,240,180,30);
		login.setBounds(40,300,80,35);
		//signuplabel.setBounds(120,300,150,50);
		signup.setBounds(150,300,80,35);
		exit.setBounds(260,300,80,35);
 		pane.add(signuplabel);
		pane.add(title);
		pane.add(signinimg);
		pane.add(signinlabel);
		pane.add(labelUsername);
		pane.add(txtUsername);
		pane.add(labelPassword);
		pane.add(txtPassword);
		pane.add(login);
		pane.add(signup);
		pane.add(exit);
		pane.add(imglb);

		pane.setLayout(null);
	}
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			Toolkit.getDefaultToolkit().beep();
			try{
			//DECLARES THE VARIABLES LOCAL TO THE METHOD
			String userName=txtUsername.getText();
			String Password=txtPassword.getText();
			
			//CHECK IF THE USER HAS ENTERED THE VALID USERNAME AND PASSWORD
			boolean validUser=checkUserExists(userName,Password);
			
			//IF VALID USERNAME AND PASSWORD LOG ON TO THE APPLICATION
			if(validUser==true)
			{
				//SEND THE USERNAME AND PASSWORD TO CLIENT AND SERVER
				sendUserNameToClient(userName,Password);
				sendUserNameToServer(userName,Password);
				setVisible(false);
				MainMenu mailWindow=new MainMenu(Client.getCurrentUserName());
				
				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
				final int HEIGHT=590;
				final int WIDTH=820;
				mailWindow.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
				mailWindow.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(pane, "Incorrect Login");
				txtUsername.setText("");
				txtPassword.setText("");
			}
			}catch(Exception ee){}
		}
	}
	//ACTION LISTENER FOR GUI BUTTONS
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==login)
		{	
			try{
			//DECLARES THE VARIABLES LOCAL TO THE METHOD
			String userName=txtUsername.getText();
			String Password=txtPassword.getText();
			
			//CHECK IF THE USER HAS ENTERED THE VALID USERNAME AND PASSWORD
			boolean validUser=checkUserExists(userName,Password);
			
			//IF VALID USERNAME AND PASSWORD LOG ON TO THE APPLICATION
			if(validUser==true)
			{
				//SEND THE USERNAME AND PASSWORD TO CLIENT AND SERVER
				sendUserNameToClient(userName,Password);
				sendUserNameToServer(userName,Password);
				setVisible(false);
				MainMenu mailWindow=new MainMenu(Client.getCurrentUserName());
				
				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
				final int HEIGHT=590;
				final int WIDTH=820;
				mailWindow.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
				mailWindow.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(pane, "Incorrect Login");
				txtUsername.setText("");
				txtPassword.setText("");
			}
			}catch(Exception ee){}
		}
		if(e.getSource()==signup)
		{
			//CREATE SIGNUP GUI
			SignUpWindow sign=new SignUpWindow();
			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
			final int HEIGHT=400;
			final int WIDTH=400;
			sign.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
			sign.setVisible(true);
			setVisible(false);//HIDE SIGNIN WINDOW
		}
		if(e.getSource()==exit)
		{
			Client.closeDown();
		}
	}
	//CHECK ABOUT THE PERTICULAR USER THE EXISTANSE AND CORRECTNESS OF USERNAME AND PASSWORD
	public boolean checkUserExists(String userName,String Password)
	{
		//MAKE A COPY OF USERNAME FROM SERVER
		Vector userNames=Client.getUserNames();
		int vectorSize=userNames.size();
		int vectorPosition;
		String euser,epassword;
		boolean exists=false;
			
		//SEARCH THROUGH VECTOR TO CHECK ALL USERNAMES
		for(vectorPosition=0;vectorPosition<vectorSize;vectorPosition++)
		{
			
			//RETRIVE THE FIRST OBJECT FROM THE VECTOR
			User temp=(User)userNames.elementAt(vectorPosition);
			
			//EXTRACT USERNAME AND PASSWORD FROM THE USER OBJECT
			euser=temp.getUserName();
			epassword=temp.getPassword();
			//System.out.println(euser+" "+epassword);
			//System.out.println(vectorSize);
			//CHECK IF THE EXTRACTED INFO MATCHES PASSED INFO
			if(euser.equals(userName)&& epassword.equals(Password))
			{
					//IF USER EXISTS SET TO TRUE AND QUIT LOOP
					exists=true;
					break;
			}								
		}
		
		return exists;
	}
		
		//SEND LOGGED IN USER NAME TO SERVER//
		private void sendUserNameToServer(String userName,String Password)
		{
			try
			{
				//SET USER STATUS TO TRUE FOR EXISTING USER//
				Boolean userStatus=Boolean.FALSE;
				
				//SET UP OBJECT OUTPUT STREAM//
				objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
				
				//CREATE NEW USER FROM ENTERED USER//
				User user=new User(userName,Password);
		
				//SEND INFORMATION TO SERVER//
				objectOut.writeObject(user);
				objectOut.writeObject(userStatus);
		
				//FLUSH STREAMS//
				objectOut.flush();
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		
		//SEND UESR NAME TO CLIENT//
		private void sendUserNameToClient(String userName,String password)
		{
		User user=new User(userName,password);
		Client.setUser(user);
		}
		
	
	
	
}
