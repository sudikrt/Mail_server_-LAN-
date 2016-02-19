//IMPORT LIBRARIES NEEDE FOR GUI//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//IMPORT LIBRARIES NEEDE FOR VECTOR//
import java.util.*;
//IMPORT LIBRARIES NEEDE FOR STREAMS//
import java.io.*;
class SignUpWindow extends JFrame implements ActionListener,KeyListener
{
        private JButton create,back;
        private JLabel title,signinimg,labelPassword,labelUsername,lblCon;
        private JTextField textUsername,textPassword,txtcPassword;
        private Container pane;
        private ImageIcon signin,loginicon;
        private ImageIcon icon=new ImageIcon("img/18.JPG");
        private static ObjectOutputStream objectOut;

        //DECLARE CONSTRUCTOR USED FOR GUI//
        public SignUpWindow()
        {
                addWindowListener(new WindowAdapter()
                {
                        public void windowClosing(WindowEvent e)
                        {
                                SignInWindow mainLogin=new SignInWindow();
                                Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                                final int HEIGHT=400;
                                final int WIDTH=400;
                                mainLogin.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                                mainLogin.setVisible(true);
                        }
                }

       );

       setTitle("Sign Up");
		 try 
		    { 
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
		    } 
		    catch(Exception e){ 
		    }
		 try{
			 this.setIconImage(new ImageIcon(getClass().getResource("img/upsign.png")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
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
     

       title=new JLabel("Create Your account");
       title.setFont(new Font("Arial",Font.BOLD,22));
       title.setForeground(Color.gray);
       create=new JButton("Sign up");
       create.addActionListener(this);
	back=new JButton("Back");
       back.addActionListener(this);
	setResizable(false);
       labelPassword=new JLabel("Password");
       labelUsername=new JLabel("Username");
		lblCon=new JLabel("Confirm Password");
		
		loginicon=new ImageIcon("img/upsign.png");
		Image img = loginicon.getImage();  
		Image newimg = img.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon newIcon = new ImageIcon(newimg);
		signinimg=new JLabel(newIcon);

       textPassword=new JPasswordField(10);
		txtcPassword=new JPasswordField(10);
		txtcPassword.addKeyListener(this);
       textUsername=new JTextField(10);
	labelUsername.setFont(new Font("Calibri",Font.BOLD,17));
	labelPassword.setFont(new Font("Calibri",Font.BOLD,17));
	lblCon.setFont(new Font("Calibri",Font.BOLD,17));
	labelUsername.setForeground(Color.white);
	labelPassword.setForeground(Color.white);
	lblCon.setForeground(Color.white);
	
	title.setBounds(90,90,220,60);
       signinimg.setBounds(150,40,70,70);
       labelUsername.setBounds(40,158,90,30);
       textUsername.setBounds(190,158,180,30);
       labelPassword.setBounds(40,202,90,30);
       textPassword.setBounds(190,202,180,30);
	lblCon.setBounds(35,242,160,30);
	txtcPassword.setBounds(190,242,180,30);
       create.setBounds(76,303,90,35);
	back.setBounds(230,303,80,35);

       pane.add(title);
       pane.add(signinimg);
       pane.add(labelUsername);
       pane.add(textUsername);
       pane.add(labelPassword);
       pane.add(textPassword);
       pane.add(create);
	pane.add(back);
			pane.add(lblCon);
			pane.add(txtcPassword);
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
			//DECLARE VARIABLES LOCAL TO METHOD//
			String userName=textUsername.getText();
			String Password=new String(textPassword.getText());
			String cPassword=new String(txtcPassword.getText());

			//CHECK IF THE USER HAS ENTERED A VALID PASSWORD AND USERNAME//
			boolean userExists=checkUserExists(userName,Password);

			//CHECK IF THE FIELDS ARE BLANK//
			boolean blank=checkBlank(userName,Password);

			//IF FIELDS ARE NOT BLANK//
			if(blank==false)
			{
				//IF VALID USERNAME LOG INTO SYSTEM//
				if(userExists==false)
				{	if(Password.equals(cPassword))
					{
						//SENDS USERNAMES AND PASSWORD TO CLIENT AND SERVER//
						sendUserNameToServer(userName,Password);
						sendUserNameToClient(userName,Password);
						setVisible(false);
						MainMenu sub=new MainMenu(Client.getCurrentUserName());
						Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
						final int HEIGHT=590;
						final int WIDTH=820;
						sub.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
						sub.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(pane,"Password Miss match");
						txtcPassword.setText("");
						textPassword.setText("");
					}

				}
				else
				{
					JOptionPane.showMessageDialog(pane,"Sorry user Already Exists");
					txtcPassword.setText("");
					textPassword.setText("");
				}
			}
				//CHECK IF THE FIELDS ARE KEPT BLANK
			else
			{
				JOptionPane.showMessageDialog(pane,"Please fill the user details");
				textUsername.setText("");
				textPassword.setText("");
				txtcPassword.setText("");
			}
		}
	}
			
	//ACTION LISTENER FOR GUI BUTTONS//
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==back)
		{	
				setVisible(false);
				 SignInWindow mainLogin=new SignInWindow();
                                Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                                final int HEIGHT=400;
                                final int WIDTH=400;
                                mainLogin.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                                mainLogin.setVisible(true);
		}
		if(e.getSource()==create)
		{
			//DECLARE VARIABLES LOCAL TO METHOD//
			String userName=textUsername.getText();
			String Password=new String(textPassword.getText());
			String cPassword=new String(txtcPassword.getText());

			//CHECK IF THE USER HAS ENTERED A VALID PASSWORD AND USERNAME//
			boolean userExists=checkUserExists(userName,Password);

			//CHECK IF THE FIELDS ARE BLANK//
			boolean blank=checkBlank(userName,Password);

			//IF FIELDS ARE NOT BLANK//
			if(blank==false)
			{
				//IF VALID USERNAME LOG INTO SYSTEM//
				if(userExists==false)
				{	if(Password.equals(cPassword))
					{
						//SENDS USERNAMES AND PASSWORD TO CLIENT AND SERVER//
						sendUserNameToServer(userName,Password);
						sendUserNameToClient(userName,Password);
						setVisible(false);
						MainMenu sub=new MainMenu(Client.getCurrentUserName());
						Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
						final int HEIGHT=590;
						final int WIDTH=820;
						sub.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
						sub.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(pane,"Password Miss match");
						txtcPassword.setText("");
						textPassword.setText("");
					}

				}
				else
				{
					JOptionPane.showMessageDialog(pane,"Sorry user Already Exists");
					txtcPassword.setText("");
					textPassword.setText("");
				}
			}
				//CHECK IF THE FIELDS ARE KEPT BLANK
			else
			{
				JOptionPane.showMessageDialog(pane,"Please fill the user details");
				textUsername.setText("");
				textPassword.setText("");
				txtcPassword.setText("");
			}
		}
	}
    //CHECK IF THE PARTICULAR USER EXISTS
    public static boolean checkUserExists(String userName,String Password)
    {
          //MAKE A COPY OF USERNAMES FROM THE SERVER
          Vector userNames=Client.getUserNames();

          //DECLARE VARIABLE LOCAL TO METHOD
          int vectorSize=userNames.size();
          int vectorPosition;
          String eUser;
          String ePassword;
          boolean exists=false;

          //SEARCH THROUGH VECTOR TO CHECK ALL USERNAMES
          for(vectorPosition=0;vectorPosition<vectorSize;vectorPosition++)
          {
               //RETRIVE FIRST OBJECT FROM THE VECTOR
               User temp=(User)userNames.elementAt(vectorPosition);
               //EXTRACT USER NAME AND PASSWORD FROM USER OBJECT
               eUser=temp.getUserName();
               ePassword=temp.getPassword();
              // CHECK IF THE EXTRACTED INFO MATCHES PASSED INFO
               if(eUser.equals(userName))
               {
                    //IF USER EXISTS SET TO TRUE AND QUIT LOOP
                    exists=true;
                    break;
               }
        }
        return exists;
  }
  public static boolean checkBlank(String userName,String password)
  {
     boolean blank=false;
     if(userName.equals("")||password.equals(""))
     {
          blank=true;
     }
     return blank;
 }

 //SEND LOGGED IN USER NAME TO SERVER//
 private static void sendUserNameToServer(String userName,String Password)
 {
     try
     {
          //SET USER STATUS TO TRUE FOR EXISTING USER//
          Boolean userStatus=Boolean.TRUE;

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

//SEND USER NAME TO CLIENT//
private void sendUserNameToClient(String userName,String password)
{
     User user=new User(userName,password);
     Client.setUser(user);
     Client.addUser(user);
}
	
}









