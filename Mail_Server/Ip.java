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
public class Ip extends JFrame  implements ActionListener,KeyListener
{
	private JButton connect;
	private JLabel title,lip;
	private JTextField tip;
	static Container pane;
	public Ip()
	{
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		setTitle("LAN Chat And Mail Server");
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
		title=new JLabel("LAN CHAT AND MAIL SERVER");
		title.setFont(new Font("Calibri",Font.BOLD,25));
		lip=new JLabel("Enter the Host Ip:");
		lip.setFont(new Font("Arial",Font.BOLD,18));
		connect=new JButton("Connect");
		connect.addActionListener(this);
		tip=new JTextField(15);
		tip.addKeyListener(this);
		tip.setText("192.168.0.14");
		title.setBounds(18,4,400,50);
		lip.setBounds(30,40,160,50);
		tip.setBounds(200,55,150,26);
		connect.setBounds(140,90,100,30);
		setResizable(false);
		pane.add(title);
		pane.add(connect);
		pane.add(lip);
		pane.add(tip);
		pane.setLayout(null);
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			Toolkit.getDefaultToolkit().beep();
			if(tip.getText()=="")
			{
				JOptionPane.showMessageDialog(pane,"Enter the host ip address");
			}
			else if(tip.getText().equals("localhost"))
			{
				try{
					String str=tip.getText().trim();
					new Client(str.trim());
					setVisible(false);
				}catch(NullPointerException e12)
				{
					JOptionPane.showMessageDialog(null,"Server Not Exist");	
					tip.setText("");
					setVisible(false);
					Ip s1=new Ip();
               				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
               				final int HEIGHT=160;
                			final int WIDTH=400;
                			s1.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                			s1.setVisible(true);
				 }
				catch(IOException e23){}
			}
			
			
			else 
			{
				try{
					String str=tip.getText().trim();
					new Client(str.trim());
					setVisible(false);
				}catch(NullPointerException e12)
				{
					JOptionPane.showMessageDialog(null,"Server Not Exist");	
					tip.setText("");
					setVisible(false);
					Ip s1=new Ip();
               				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
               				final int HEIGHT=160;
                			final int WIDTH=400;
                			s1.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                			s1.setVisible(true);
				 }
				catch(IOException e23){}
			}
		}
	}
	//ACTION LISTENER FOR GUI BUTTONS
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==connect)
		{	
			if(tip.getText()=="")
			{
				JOptionPane.showMessageDialog(pane,"Enter the host ip address");
			}
			else if(tip.getText().equals("localhost"))
			{
				try{
					String str=tip.getText().trim();
					new Client(str.trim());
					setVisible(false);
				}catch(NullPointerException e12)
				{
					JOptionPane.showMessageDialog(null,"Server Not Exist");	
					tip.setText("");
					setVisible(false);
					Ip s1=new Ip();
               				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
               				final int HEIGHT=160;
                			final int WIDTH=400;
                			s1.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                			s1.setVisible(true);
				 }
				catch(IOException e23){}
			}
			
			else 
			{
				try{
					String str=tip.getText().trim();
					new Client(str.trim());
					setVisible(false);
				}catch(NullPointerException e12)
				{
					JOptionPane.showMessageDialog(null,"Server Not Exist");	
					tip.setText("");
					setVisible(false);
					Ip s1=new Ip();
               				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
               				final int HEIGHT=160;
                			final int WIDTH=400;
                			s1.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                			s1.setVisible(true);
				 }
				catch(IOException e23){}
			}
		}
		
	}
	public static void main(String ar[])throws Exception
	{
		Ip s1=new Ip();
              	Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
             	final int HEIGHT=160;
            	final int WIDTH=400;
             	s1.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
            	s1.setVisible(true);
	}
}