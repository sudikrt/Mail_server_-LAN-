//IMPORT STANDARD GUI PACKAGES//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//IMPORT NEEDED FOR VECTOR AND STREAMS//
import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.Toolkit.*;
import java.net.*;
import java.applet.*;
public class Service extends JFrame  implements ActionListener{
	private JButton server,chats,voices;
	private JLabel title;
	private Container pane;
	public Service()
	{
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		setTitle("Service");
		try 
		{ 
		        	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
		} 
		catch(Exception e){ 
		}
		try{
			this.setIconImage(new ImageIcon(getClass().getResource("ss.PNG")).getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH));
		}
		catch(Exception execxc)
		{			 
		}
		pane=getContentPane();
		pane.setLayout(new BorderLayout());
		server=new JButton("Server");
		server.addActionListener(this);
		try{
			title=new JLabel("Server IP address: "+ Inet4Address.getLocalHost().getHostAddress());
			title.setFont(new Font("Calibri",Font.BOLD,20));
		}catch(Exception e2){
		}
		chats=new JButton("Chat Server");
		chats.addActionListener(this);
		voices=new JButton("Voice Server");
		voices.addActionListener(this);
		title.setBounds(15,20,400,50);
		server.setBounds(25,90,100,35);
		chats.setBounds(137,90,110,35);
		voices.setBounds(260,90,110,35);
 		pane.add(title);
		pane.add(server);
		pane.add(chats);
		pane.add(voices);
		pane.setLayout(null);
	}
	//ACTION LISTENER FOR GUI BUTTONS
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==server)
		{	
			try{
				Process p1=Runtime.getRuntime().exec("cmd.exe /c start java Server");
			}catch(Exception e1){}
		}
		if(e.getSource()==chats)
		{
			try{
				Process p2=Runtime.getRuntime().exec("cmd.exe /c start java ChatServer");
			}catch(Exception e1){}	
		}
		if(e.getSource()==voices)
		{
			try{
				Process p3=Runtime.getRuntime().exec("cmd.exe /c start java VoiceServer");
			}catch(Exception e1){}	
			
		}
	}
	public static void main(String ar[])throws Exception
	{
		Service s1=new Service();
                		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                		final int HEIGHT=200;
                		final int WIDTH=400;
                		s1.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                		s1.setVisible(true);
	}
}
