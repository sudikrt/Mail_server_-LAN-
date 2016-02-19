import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.Timer;
import java.io.*;
import java.util.*;
import java.net.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public  class MainMenu extends JFrame implements ChangeListener,ActionListener,MouseListener
{
		Container pane;
        	JTabbedPane tab;
        	JPanel jp1,jp2,jp3,jp5,jp4,jp6,jp7,jp8,jp9;
        
		//Compose
        	JButton comSave,comSend,comAttach;
        	JLabel label1,label2,label3;
       		JComboBox comboTo;
        	JTextField txtSubject,txtAttach;
       		JTextArea txtArea1;
        	JScrollPane scrollpane;
        	private String recipient,cdate,ddate,ssdate,fdate,fddate,rdate,rrdate;
        	private String[] names;
        	private Email email;
			
		//Inbox 
        	JList emailList;
        	JLabel inTitle,ilabel;
        	JTextArea txtArea2;
        	JButton inDown,inDelete,inForward,inReply,inAttach;
        	Vector inDescriptor;
        	int inSelMailNumber;
       		JScrollPane inscrollpane1,inscrollpane2;
		JLabel rsvLabel,csvLabel,csdLabel,rsdLabel,fsdLabel,dsdLabel,ssdLabel,indLabel,sndLabel,dfdLabel;
        
		//DRAFT
        	JList draftList;
        	JLabel draftTitle,dlabel;
        	JTextArea txtArea3;
        	JButton draftDelete,draftForward,draftReply,draftAttach;
        	Vector draftDescriptor;
        	int draftSelMailNumber;
        	JScrollPane draftscrollpane1,draftscrollpane2;
		
        	//Sent
        	JList sentList;
        	JLabel sentTitle,slabel;
        	JTextArea txtArea4;
        	JButton sentDelete,sentForward,sentAttach;
        	Vector sentDescriptor;
        	int sentSelMailNumber;
        	JScrollPane sentscrollpane1,sentscrollpane2;

        	//Reply
        	JButton reSend,reSave,reAttach,reCancel;
        	JLabel relabTo,relabSubject,relabAttach;
        	JTextField retxtTo,retxtSubject,retxtAttach;
        	JTextArea txtArea5;
        	JScrollPane rescrollpane;
        	Email remail;
        	String reRecipient;
        
		//Forward
        	JButton forSend,fordraftSend,fordraftAttach,inCancel,draftCancel,sentCancel;
        	JLabel forlabTo,forlabSubject,forlabAttach;
        	JTextField fortxtTo,fortxtSubject,fortxtAttach;
        	JTextArea txtArea6;
        	JScrollPane forscrollpane;
        	private String forrecipient,dforrecipient,oldUser;
        	Email femail,fdemail;
        
		//DECLARATION STREAM NEEDED FOR TRANSMISSION
        	private static ObjectInputStream objectIn;
        	private static ObjectOutputStream objectOut;
        	JLabel headlbl;
        	private String name;

        	public MainMenu(String name)
        	{
                	addWindowListener(new WindowAdapter()
                	{
                        	public void windowClosing(WindowEvent e)
                        	{
                               	Client.close();
                        	}
                }
        	);
	       	addWindowListener(new WindowAdapter()
        	{
                	public void windowOpened(WindowEvent e)
                	{
                        		try
                        		{
                                		//Recieve Mail From Server
                                		recieveMessagesFromServer();
                                		recieveMessagesFromServer1();
                               	 		recieveMessagesFromServer2();
                        		}	
                        		catch(ClassNotFoundException cnfe)
                        		{
                                	cnfe.printStackTrace();
                        		}
                	}
        	}
        	);
        	this.name=name;
         	try 
         	{ 
		  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
		} 
 		catch(Exception e){}
		setTitle("E-mail(Compose)");
        	email=new Email();
        	names=new String[Client.getUserNamesSize()];
        	for(int count=0;count<names.length;count++)
        	{
                	names[count]=Client.getName(count);
        	}
        	recipient=names[0];
        	pane=getContentPane();
        	pane.setLayout(new BorderLayout());
        	pane.setBackground(Color.white);
        	tab=new JTabbedPane();
        	tab.addChangeListener(this);
        	jp1=new JPanel();
        	jp2=new JPanel();
        	jp3=new JPanel();
        	jp4=new JPanel();
        	jp5=new JPanel();
        	jp6=new JPanel();
	       	jp7=new JPanel();
        	jp8=new JPanel();
	       	jp9=new JPanel();
   		Font f=new Font("Cordia New",Font.BOLD,20);
        	setForeground(Color.black);
        
		//compose
       		comSend=new JButton("Send");
        	comSend.setFont(f);
        	comSend.setForeground(Color.black);
        	comSave=new JButton("Save");
        	comSave.setFont(f);
        	comSave.setForeground(Color.black);
        	comAttach=new JButton("Choose file");
        	comAttach.setFont(f);
        	comAttach.setForeground(Color.black);
        	comSave.addActionListener(this);
        	comSend.addActionListener(this);
        	comAttach.addActionListener(this);
        	label1=new JLabel("To:");
        	label1.setFont(f);
        	label1.setForeground(Color.black);
        	label2=new JLabel("Subject:");
        	label2.setFont(f);
        	label2.setForeground(Color.black);
        	label3=new JLabel("Attachment:");
        	label3.setFont(f);
        	label3.setForeground(Color.black);
		csdLabel=new JLabel();
		csdLabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		csdLabel.setText("<html><body bgcolor=rgb(250,250,120)>Your mail has been sent successfully.</body></html>");
		
		rsdLabel=new JLabel();
		rsdLabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		rsdLabel.setText("<html><body bgcolor=rgb(250,250,120)>Your mail has been sent successfully.</body></html>");

		fsdLabel=new JLabel();
		fsdLabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		fsdLabel.setText("<html><body bgcolor=rgb(250,250,120)>Your mail has been sent successfully.</body></html>");
		
		csvLabel=new JLabel();
		csvLabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		csvLabel.setText("<html><body bgcolor=rgb(250,250,120)>Your mail has been saved successfully .</body></html>");

		dsdLabel=new JLabel();
		dsdLabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		dsdLabel.setText("<html><body bgcolor=rgb(250,250,120)>Your mail has been sent successfully .</body></html>");

   		ssdLabel=new JLabel();
		ssdLabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		ssdLabel.setText("<html><body bgcolor=rgb(250,250,120)>Your mail has been sent successfully .</body></html>");
		
		indLabel=new JLabel();
		indLabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		indLabel.setText("<html><body bgcolor=rgb(250,250,120)>Mail has been deleted successfully .</body></html>");
		
		sndLabel=new JLabel();
		sndLabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		sndLabel.setText("<html><body bgcolor=rgb(250,250,120)>Mail has been deleted successfully .</body></html>");
		
		dfdLabel=new JLabel();
		dfdLabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		dfdLabel.setText("<html><body bgcolor=rgb(250,250,120)>Mail has been deleted successfully .</body></html>");
		comboTo=new JComboBox(names);
        	comboTo.setFont(f);
        	comboTo.setForeground(Color.black);
        	txtSubject=new JTextField();
        	txtSubject.setFont(f);
        	txtSubject.setForeground(Color.black);
        	txtAttach=new JTextField();
        	txtAttach.setFont(f);
        	txtAttach.setForeground(Color.black);
        	txtAttach.setEditable(false);
        	txtArea1=new JTextArea();
        	txtArea1.setColumns(20);
        	txtArea1.setRows(5);
        	txtArea1.setFont(f);
        	txtArea1.setForeground(Color.black);
        	scrollpane=new JScrollPane();
        	scrollpane.setViewportView(txtArea1);
        	jp1.add(label1);
        	jp1.add(comboTo);
        	jp1.add(label2);
        	jp1.add(txtSubject);
        	jp1.add(label3);
		jp1.add(csdLabel);
		jp1.add(csvLabel);
		jp1.add(txtAttach);
        	jp1.add(comAttach);
        	jp1.add(scrollpane);
        	jp1.add(comSend);
        	jp1.add(comSave);
       	 	label1.setBounds(120,40,20,20);
        	comboTo.setBounds(150,40,200,25);
        	label2.setBounds(90,90,50,20);
        	txtSubject.setBounds(150,90,200,20);
        	label3.setBounds(65,140,80,20);
		
		csdLabel.setBounds(180,2,420,20);
		csdLabel.setVisible(false);
		
		csvLabel.setBounds(180,2,420,20);
		csvLabel.setVisible(false);
        	txtAttach.setBounds(150,140,200,20);
        	comAttach.setBounds(360,140,100,20);
		scrollpane.setBounds(20,210,650,240);
     		comSend.setBounds(30,465,80,30);
        	comSave.setBounds(130,465,80,30);
        	
		//Inbox
		inDescriptor=new Vector();
        	inSelMailNumber=0;
        	inDown=new JButton("Download attachment");
        	inDown.setFont(f);
       		inDown.setForeground(Color.black);
        	inDelete=new JButton("Delete");
        	inDelete.setFont(f);
        	inDelete.setForeground(Color.black);
		ilabel=new JLabel();
		ilabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		ilabel.setText("<html><body bgcolor=rgb(250,250,120)>No current Mail.</body></html>");
        	inForward=new JButton("Forward");
        	inForward.setFont(f);
        	inForward.setForeground(Color.black);
        	inReply=new JButton("Reply");
        	inReply.setFont(f);
        	inReply.setForeground(Color.black);
        	inAttach=new JButton("Attach");
        	inAttach.setFont(f);
        	inAttach.setForeground(Color.black);
        	inTitle=new JLabel();
        	inTitle.setFont(f);
        	inTitle.setForeground(Color.black);
        	inDown.addActionListener(this);
        	inDelete.addActionListener(this);
        	inForward.addActionListener(this);
        	inReply.addActionListener(this);
        	inAttach.addActionListener(this);
        	emailList=new JList();
        	emailList.setFont(f);
        	emailList.setForeground(Color.black);
        	emailList.setVisibleRowCount(33);
        	emailList.setFixedCellWidth(320);
        	emailList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        	MouseListener mouseListener = new MouseAdapter() 
		{
        		public void mouseClicked(MouseEvent mouseEvent) 
			{
        			JList theList = (JList) mouseEvent.getSource();
        			if (mouseEvent.getClickCount() == 2) 
				{
					if((emailList.isSelectionEmpty())==true)return;
                			Email selectedMail=Client.getMail(inSelMailNumber);
                			selectedMail.setRead(true);
                			txtArea2.setText(selectedMail.getContent());
               				refreshListContents();
				}
			}
		};
		emailList.addMouseListener(mouseListener);
        	txtArea2=new JTextArea();
       		txtArea2.setFont(f);
       		txtArea2.setForeground(Color.black);
       		txtArea2.setEditable(false);
      		inscrollpane1=new JScrollPane();
       		inscrollpane2=new JScrollPane();
       		inscrollpane1.setViewportView(emailList);
      		inscrollpane2.setViewportView(txtArea2);
       		jp2.add(inTitle);
       		jp2.add(inscrollpane1);
      		jp2.add(inscrollpane2);
       		jp2.add(inDown);
        	jp2.add(inReply);
		jp2.add(ilabel);
      		jp2.add(inAttach);
       		jp2.add(inDelete);
     		jp2.add(inForward);
		jp2.add(indLabel);		
		indLabel.setBounds(180,2,420,20);
		indLabel.setVisible(false);	


     		inTitle.setBounds(20,15,150,20);
		inscrollpane1.setBounds(20,80,650,180);
      		inscrollpane2.setBounds(20,270,650,230);
     		inDown.setBounds(390,40,170,27);
     		inReply.setBounds(100,40,70,27);
		ilabel.setBounds(220,2,320,20);
		ilabel.setVisible(false);
    		inAttach.setBounds(280,40,100,27);
    		inDelete.setBounds(20,40,73,27);
     		inForward.setBounds(180,40,90,27);
 	       
		//Draft
        	draftTitle=new JLabel();
        	draftTitle.setFont(f);
        	draftTitle.setForeground(Color.black);
        	draftDescriptor=new Vector();
        	draftSelMailNumber=0;     
        	draftDelete=new JButton("Delete");
        	draftDelete.setFont(f);
        	draftDelete.setForeground(Color.black);
		dlabel=new JLabel();
		dlabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		dlabel.setText("<html><body bgcolor=rgb(250,250,120)>No current Mail.</body></html>");
        	draftForward=new JButton("Send");
        	draftForward.setFont(f);
        	draftForward.setForeground(Color.black);
        	draftAttach=new JButton("Attachment");
        	draftAttach.setFont(f);
        	draftAttach.setForeground(Color.black);
		draftDelete.addActionListener(this);
        	draftForward.addActionListener(this);
        	draftAttach.addActionListener(this);
        	draftList=new JList();
        	draftList.setVisibleRowCount(33);
        	draftList.setFixedCellWidth(320);
        	draftList.setFont(f);
        	draftList.setForeground(Color.black);
        	draftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
        	MouseListener draftListener = new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent mouseEvent) 
			{
        			JList theList = (JList) mouseEvent.getSource();
        			if (mouseEvent.getClickCount() == 2) 
				{
		 			if((draftList.isSelectionEmpty())==true)return;
                			Email selectedMail=Client.getDraft(draftSelMailNumber);
                			selectedMail.setRead(false);
                			txtArea3.setText(selectedMail.getContent());
               	 			refreshListContents1();
				}
			}
		};
		draftList.addMouseListener(draftListener);
		txtArea3=new JTextArea();
        	txtArea3.setFont(f);
       		txtArea3.setForeground(Color.black);
        	txtArea3.setEditable(false);
        	draftscrollpane1=new JScrollPane();
        	draftscrollpane2=new JScrollPane();
        	draftscrollpane1.setViewportView(draftList);
        	draftscrollpane2.setViewportView(txtArea3);
        	jp3.add(draftTitle);
        	jp3.add(draftscrollpane1);
        	jp3.add(draftscrollpane2);
                jp3.add(draftAttach);
		jp3.add(dlabel);
        	jp3.add(draftDelete);
       		jp3.add(draftForward);
		jp3.add(dfdLabel);
		dfdLabel.setBounds(180,2,420,20);
		dfdLabel.setVisible(false);
        	draftTitle.setBounds(20,20,150,20);
        	draftscrollpane1.setBounds(20,80,650,180);
        	draftscrollpane2.setBounds(20,270,650,230);
                draftAttach.setBounds(100,40,110,27);
		dlabel.setBounds(220,2,320,20);
		dlabel.setVisible(false);
        	draftDelete.setBounds(220,40,90,27);
        	draftForward.setBounds(20,40,70,27);
        
		//Sent
        	sentTitle=new JLabel();
        	sentTitle.setFont(f);
        	sentTitle.setForeground(Color.black);
        	sentDescriptor=new Vector();
        	sentSelMailNumber=0;
                sentDelete=new JButton("Delete");
        	sentDelete.setFont(f);
        	sentDelete.setForeground(Color.black);
		slabel=new JLabel();
		slabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		slabel.setText("<html><body bgcolor=rgb(250,250,120)>No current Mail.</body></html>");
        	sentForward=new JButton("Forward");
        	sentForward.setFont(f);
        	sentForward.setForeground(Color.black);
        	sentAttach=new JButton("Attachment");
        	sentAttach.setFont(f);
        	sentAttach.setForeground(Color.black);
              	sentDelete.addActionListener(this);
        	sentForward.addActionListener(this);
        	sentAttach.addActionListener(this);
        	sentList=new JList();
        	sentList.setFont(f);
        	sentList.setForeground(Color.black);
        	sentList.setVisibleRowCount(33);
        	sentList.setFixedCellWidth(320);

		MouseListener sentListener = new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent mouseEvent) 
			{
        			JList theList = (JList) mouseEvent.getSource();
        			if (mouseEvent.getClickCount() == 2) 
				{
					if((sentList.isSelectionEmpty())==true)return;
                				Email selectedMail1=Client.getSent(sentSelMailNumber);
                				selectedMail1.setRead(false);
                				txtArea4.setText(selectedMail1.getContent());
                				refreshListContents2();
				}
			}
		};


		sentList.addMouseListener(sentListener);
        	sentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       		txtArea4=new JTextArea();
        	txtArea4.setFont(f);
        	txtArea4.setForeground(Color.black);
        	txtArea4.setEditable(false);
		
        	sentscrollpane1=new JScrollPane();
        	sentscrollpane2=new JScrollPane();
        	sentscrollpane1.setViewportView(sentList);
        	sentscrollpane2.setViewportView(txtArea4);
        	jp4.add(sentTitle);
        	jp4.add(sentscrollpane1);
       		jp4.add(sentscrollpane2);
         	jp4.add(sentAttach);
		jp4.add(slabel);
        	jp4.add(sentDelete);
       		jp4.add(sentForward);
		jp4.add(sndLabel);
		sndLabel.setBounds(180,2,420,20);
		sndLabel.setVisible(false);
        	sentTitle.setBounds(20,20,150,20);
        	sentscrollpane1.setBounds(20,80,650,180);
        	sentscrollpane2.setBounds(20,270,650,230);
                sentAttach.setBounds(200,40,110,27);
		slabel.setBounds(220,2,320,20);
		slabel.setVisible(false);
        	sentDelete.setBounds(20,40,73,27);
        	sentForward.setBounds(100,40,90,27);
        
		//Reply
        	remail=new Email();
        	reSend=new JButton("Send");
        	reSend.setFont(f);
        	reSend.setForeground(Color.black);
        	reSave=new JButton("Save");
        	reSave.setFont(f);
        	reSave.setForeground(Color.black);
        	reSend.setToolTipText("To send mail");
        	reSave.setToolTipText("To save mail");
        	reAttach=new JButton("Choose file");
        	reAttach.setFont(f);
        	reAttach.setForeground(Color.black);
        	reCancel=new JButton("Cancel");
        	reCancel.setFont(f);
        	reCancel.setForeground(Color.black);    
        	reSend.addActionListener(this);
        	reSave.addActionListener(this);
        	reAttach.addActionListener(this);
        	reCancel.addActionListener(this);
        	relabTo=new JLabel("To:");
        	relabTo.setFont(f);
        	relabTo.setForeground(Color.black);
        	relabSubject=new JLabel("Subject:");
        	relabSubject.setFont(f);
        	relabSubject.setForeground(Color.black);
        	relabAttach=new JLabel("Attachment:");
        	relabAttach.setFont(f);
        	relabAttach.setForeground(Color.black);
        	retxtTo=new JTextField();
        	retxtTo.setFont(f);
        	retxtTo.setForeground(Color.black);
        	retxtTo.setEditable(false);
        	retxtSubject=new JTextField();
        	retxtSubject.setFont(f);
        	retxtSubject.setForeground(Color.black);
        	retxtAttach=new JTextField();
        	retxtAttach.setEditable(false);
        	retxtAttach.setFont(f);
        	retxtAttach.setForeground(Color.black);
        	txtArea5=new JTextArea();
        	txtArea5.setColumns(20);
        	txtArea5.setRows(5);
        	txtArea5.setFont(f);
        	txtArea5.setForeground(Color.black);
        	rescrollpane=new JScrollPane();
        	rescrollpane.setViewportView(txtArea5);
		rsvLabel=new JLabel();
		rsvLabel.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		rsvLabel.setText("<html><body bgcolor=rgb(250,250,120)>Your mail has been saved successfully.</body></html>");
        	jp5.add(relabTo);
        	jp5.add(retxtTo);
        	jp5.add(relabSubject);
        	jp5.add(retxtSubject);
        	jp5.add(relabAttach);
        	jp5.add(retxtAttach);
        	jp5.add(reAttach);
        	jp5.add(rescrollpane);
        	jp5.add(reSend);
        	jp5.add(reSave);
        	jp5.add(reCancel);
		jp5.add(rsvLabel);
		jp5.add(rsdLabel);
        	relabTo.setBounds(120,40,20,20);
        	retxtTo.setBounds(150,40,200,20);
        	relabSubject.setBounds(90,90,50,20);
        	retxtSubject.setBounds(150,90,200,20);
        	relabAttach.setBounds(70,140,70,20);
        	retxtAttach.setBounds(150,140,200,20);
        	reAttach.setBounds(360,140,100,20);
        	rescrollpane.setBounds(20,200,650,200);
        	reSend.setBounds(30,420,80,30);
        	reSave.setBounds(130,420,80,30);
        	reCancel.setBounds(230,420,80,30);
		rsdLabel.setBounds(180,2,420,20);
		rsdLabel.setVisible(false);
		rsvLabel.setBounds(180,2,420,20);
		rsvLabel.setVisible(false);
        
		//Forward
        	femail=new Email();
        	fdemail=new Email();
        	forSend=new JButton("Send");
        	forSend.setToolTipText("To send mail");
        	fordraftSend=new JButton("Send");
        	fordraftSend.setToolTipText("To forward mail");
        	retxtAttach.setFont(f);
        	retxtAttach.setForeground(Color.black);
        	fordraftAttach=new JButton("Edit Attach");
        	fordraftAttach.setToolTipText("Edit Attachment");
        	inCancel=new JButton("Cancel");
        	draftCancel=new JButton("Cancel");
        	sentCancel=new JButton("Cancel");   
        	forSend.addActionListener(this);
        	fordraftSend.addActionListener(this);
        	fordraftAttach.addActionListener(this);
        	inCancel.addActionListener(this);
        	draftCancel.addActionListener(this);
        	sentCancel.addActionListener(this);
        	forlabTo=new JLabel("To:");
        	forlabSubject=new JLabel("Subject:");
        	forlabAttach=new JLabel("Attachment:");
        	fortxtTo=new JTextField();
        	fortxtTo.setVisible(false);
       		fortxtTo.setEditable(false);
      		fortxtTo.setFont(f);
       		fortxtTo.setForeground(Color.black);
        	fortxtSubject=new JTextField();
        	fortxtSubject.setEditable(false);
        	fortxtSubject.setFont(f);
        	fortxtSubject.setForeground(Color.black);
        	fortxtAttach=new JTextField();
        	fortxtAttach.setEditable(false);
        	fortxtAttach.setFont(f);
       		fortxtAttach.setForeground(Color.black);
        	txtArea6=new JTextArea();
        	txtArea6.setFont(f);
        	txtArea6.setForeground(Color.black);
        	txtArea6.setEditable(false);
        	txtArea6.setColumns(20);
        	txtArea6.setRows(5);
        	forscrollpane=new JScrollPane();
        	forscrollpane.setViewportView(txtArea6);
		jp6.add(forlabTo);
        	jp6.add(fortxtTo);
		jp6.add(forlabSubject);
        	jp6.add(fortxtSubject);
        	jp6.add(forlabAttach);
        	jp6.add(fortxtAttach);
        	jp6.add(fordraftAttach);
        	jp6.add(forscrollpane);
        	jp6.add(forSend);
        	jp6.add(fordraftSend);
        	jp6.add(inCancel);
        	jp6.add(draftCancel);
        	jp6.add(sentCancel);
		jp6.add(fsdLabel);
		jp6.add(dsdLabel);
		jp6.add(ssdLabel);
		fsdLabel.setBounds(180,2,420,20);
		fsdLabel.setVisible(false);
		dsdLabel.setBounds(180,2,420,20);
		dsdLabel.setVisible(false);
		ssdLabel.setBounds(180,2,420,20);
		ssdLabel.setVisible(false);
		forlabTo.setBounds(120,40,20,20);
        	fortxtTo.setBounds(150,40,200,20);
        	forlabSubject.setBounds(90,90,50,20);
        	fortxtSubject.setBounds(150,90,200,20);
        	forlabAttach.setBounds(70,140,70,20);
        	fortxtAttach.setBounds(150,140,200,20);
        	fordraftAttach.setBounds(360,140,100,20);
        	forscrollpane.setBounds(20,200,650,200);
        	forSend.setBounds(30,420,80,30);
        	fordraftSend.setBounds(30,420,80,30);
        	inCancel.setBounds(130,420,80,30);
       	 	draftCancel.setBounds(130,420,80,30);
        	sentCancel.setBounds(130,420,80,30);
        	tab.addTab("Compose",new ImageIcon(((new ImageIcon("img/compose.JPG")).getImage()).getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)),jp1,"Compose new mail" );
        	tab.addTab("     Inbox  ",new ImageIcon(((new ImageIcon("img/inbox.png")).getImage()).getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)),jp2,"Received mail" );
        	tab.addTab(" Draft Mail",new ImageIcon(((new ImageIcon("img/draft.jpg")).getImage()).getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)),jp3,"Saved mail" );
        	tab.addTab(" Sent Mail",new ImageIcon(((new ImageIcon("img/sent.JPG")).getImage()).getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)),jp4,"Sent mail" );
       		tab.addTab("TextChat",new ImageIcon(((new ImageIcon("img/Chat.JPG")).getImage()).getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)),jp7,"Text Chat" );
		tab.addTab("AudioChat",new ImageIcon(((new ImageIcon("img/voice.JPG")).getImage()).getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)),jp9,"Audio Chat");
		tab.addTab("  Log out",new ImageIcon(((new ImageIcon("img/exit.jpg")).getImage()).getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)),jp8,"Logout");
	
		jp1.setLayout(null);
        	jp2.setLayout(null);
       		jp3.setLayout(null);
        	jp4.setLayout(null);
		jp5.setLayout(null);
        	jp6.setLayout(null);
		jp7.setLayout(null);
		jp8.setLayout(null);
		jp9.setLayout(null);
	
		tab.setTabPlacement(JTabbedPane.LEFT);
		JPanel header=new JPanel();
		headlbl=new JLabel();
		String nm=Client.getCurrentUserName();
		headlbl.setText("<html><body>Hai "+"<font color=rgb(128,0,255)>"+nm+"</font>"+"</body></html>");
		headlbl.setFont(new Font("Arial",Font.BOLD,18));
		header.add(headlbl);
		JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,header,tab);
		jsp.setResizeWeight(0.03);
		jsp.setEnabled(false);
                pane.add(jsp);
		setResizable(false);
        	ComboHandler handler=new ComboHandler();
        	comboTo.addItemListener(handler);
        	ListSelectionHandler handler1=new ListSelectionHandler();
		emailList.addListSelectionListener(handler1);
        	draftList.addListSelectionListener(handler1);
        	sentList.addListSelectionListener(handler1);
		
	}

 	public void mouseClicked(MouseEvent e) 
	{
		if (e.getClickCount() == 2) 
		{
			if((sentList.isSelectionEmpty())==true)return;
                		Email selectedMail=Client.getSent(sentSelMailNumber);
                		selectedMail.setRead(false);
               	 		txtArea4.setText(selectedMail.getContent());
                		refreshListContents2();
		}	
	}
	public void mouseEntered(MouseEvent ee1) 
	{}   
	public void mouseExited(MouseEvent ee2) 
	{}   
	public void mouseReleased(MouseEvent ee3) 
	{}
	public void mousePressed(MouseEvent ee4) 
	{}
	public void stateChanged(ChangeEvent e)
	{
        	int index=tab.getSelectedIndex();
		if(index==0)
			setTitle("E-Mail(Compose)");
        		if(index==1)
        		{
				setTitle("E-Mail(Inbox)");
                		inTitle.setText("Recieved Email:"+" "+Client.inboxSize());
                		try
                		{
                        		recieveMessagesFromServer();
                		}
                		catch(ClassNotFoundException cnfe)
                		{
                        		cnfe.printStackTrace();
                		}
                		if(Client.inboxSize()==0)
				{
					ilabel.setVisible(true);
					Timer t=new Timer(1000, new ActionListener()
					{
						public void actionPerformed(ActionEvent e1)
						{
						
							ilabel.setVisible(false);	
						}
					}); 
					t.setRepeats(false);
					t.start();
                		}
				else
                			refreshListContents();
       			}
			if(index==2)
       			{
				setTitle("E-Mail(Draft)");
                		draftTitle.setText("Saved Email:"+" "+Client.draftSize());
                		if(Client.draftSize()==0)
				{
					dlabel.setVisible(true);
					Timer t=new Timer(1000, new ActionListener()
					{
						public void actionPerformed(ActionEvent e1)
						{
						
							dlabel.setVisible(false);	
						}
					}); 
					t.setRepeats(false);
					t.start();
                		}	
				else
          	            		refreshListContents1();
       			}
       			if(index==3)
       			{
				setTitle("E-Mail(Sent)");
                		sentTitle.setText("Sent Email:"+" "+Client.sentSize());
                		if(Client.sentSize()==0)
				{
					slabel.setVisible(true);
					Timer t=new Timer(1000, new ActionListener()
					{
						public void actionPerformed(ActionEvent e1)
						{
						
							slabel.setVisible(false);	
						}
					}); 
					t.setRepeats(false);
					t.start();
                		}
				else
                       			refreshListContents2();
       			}
			try
			{
				if(index==4)
				{
					setVisible(false);
					ChatClient cc=new ChatClient(Client.getCurrentUserName());
					Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
					final int HEIGHT=400;
					final int WIDTH=730;
					cc.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
					cc.setVisible(true);
				}
			}
			catch(IOException eee)
			{}
			if(index==5)
			{
				try
				{
					setVisible(false);
					VoiceChat s1=new VoiceChat(Client.getCurrentUserName());
               	 			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                			final int HEIGHT=460;
                			final int WIDTH=625;
                			s1.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                			s1.setVisible(true);
				}
				catch(Exception ee){}
			}	
			if(index==6)
       			{
        			setVisible(false);
				Client.close();
			}
		}

		public void actionPerformed(ActionEvent e)
		{
        		if(e.getSource()==comSend)
        		{
                		
					
				cdate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
				sendEmail();
				sentEmail();
                		csdLabel.setVisible(true);
				Timer t=new Timer(1500, new ActionListener()
				{
					public void actionPerformed(ActionEvent e1)
					{
						
						csdLabel.setVisible(false);
						setVisible(false);
                				MainMenu main=new MainMenu(Client.getCurrentUserName());
                				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                				final int HEIGHT=590;
                				final int WIDTH=820;
                				main.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                				main.setVisible(true);		
					}
				}); 
				t.setRepeats(false);
				t.start();
                						
			}
        		if(e.getSource()==comSave)
        		{
                		ssdate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
                		saveEmail();
				csvLabel.setVisible(true);
				Timer t=new Timer(1000, new ActionListener()
				{
					public void actionPerformed(ActionEvent e1)
					{
						
						csvLabel.setVisible(false);
						setVisible(false);
                				MainMenu main=new MainMenu(Client.getCurrentUserName());
                				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                				final int HEIGHT=590;
                				final int WIDTH=820;
                				main.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                				main.setVisible(true);	
					}
				}); 
				t.setRepeats(false);
				t.start();
				/*setVisible(false);
                		MainMenu main=new MainMenu(Client.getCurrentUserName());
                		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                		final int HEIGHT=590;
                		final int WIDTH=820;
                		main.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                		main.setVisible(true);*/
        		}
        		if(e.getSource()==comAttach)
        		{
                		JFileChooser chooser=new JFileChooser();
                		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                		int result=chooser.showOpenDialog(this);
                		if(result==JFileChooser.CANCEL_OPTION)
                			return;
                			File temp=chooser.getSelectedFile();
                		if(temp==null || temp.getName().equals(""))
                		{
                        		JOptionPane.showMessageDialog(this,"Invalid File Name","Invalid file name",JOptionPane.ERROR_MESSAGE);
                		}
                		else
                		{
                        		email.setAttachment(temp);
                        		email.setAttachmentName(temp.getName());
                        		txtAttach.setText(temp.getName());
                		}
         		}
         		if(e.getSource()==inDown)
         		{
				if((emailList.isSelectionEmpty())==true)return;
					Email selectedMail=Client.getMail(inSelMailNumber);
					String name=selectedMail.getAttachmentName();
					if(name.equals(""))
			 			JOptionPane.showMessageDialog(pane,"Sorry No attachments to download");
		 			else
		 			{
                		 		JFileChooser jf=new JFileChooser();
			 			jf.setSelectedFile(new File(selectedMail.getAttachmentName()));
			 			int returnVal = jf.showSaveDialog(MainMenu.this);
			 			String saveTo=jf.getSelectedFile().getPath();
                   		 		if(saveTo != null )
			 			{
				 			DownLoad dwn = new DownLoad(saveTo,selectedMail.getAttachment(),selectedMail.getAttachmentName());
				 			Thread t = new Thread(dwn);
				 			t.start();
			 			}
					}
         		}
         		if(e.getSource()==inReply)
         		{
				setTitle("E-Mail(Reply)");
                		if((emailList.isSelectionEmpty())==true)return;
                			Email selectedMail=Client.getMail(inSelMailNumber);
                			retxtTo.setText(selectedMail.getSender());
                			tab.add("Reply",jp5);
             				tab.setSelectedIndex(7);
					tab.setEnabledAt(0,false);
					tab.setEnabledAt(1,false);
					tab.setEnabledAt(2,false);
					tab.setEnabledAt(3,false);
					tab.setEnabledAt(4,false);
					tab.setEnabledAt(5,false);
					tab.setEnabledAt(6,false);
         		}	
         		if(e.getSource()==inDelete)
         		{
				 if((emailList.isSelectionEmpty())==true)return;
					Client.deleteMail(inSelMailNumber);
				 	refreshListContents();
					txtArea2.setText("");
		 			inTitle.setText("Recieved Email:"+" "+Client.inboxSize());
					indLabel.setVisible(true);
				Timer t=new Timer(1500, new ActionListener()
				{
					public void actionPerformed(ActionEvent e1)
					{
						
						indLabel.setVisible(false);
								
					}
				}); 
				t.setRepeats(false);
				t.start();
              		}
         		if(e.getSource()==inForward)
         		{
                		setTitle("E-Mail(Forward)");
				if((emailList.isSelectionEmpty())==true)return;
                			Email inselectedMail=Client.getMail(inSelMailNumber);
                			femail=inselectedMail;
                			oldUser=inselectedMail.getSender();
                			fortxtSubject.setText(inselectedMail.getSubject());
                			fortxtAttach.setText(inselectedMail.getAttachmentName());
                			txtArea6.setText(inselectedMail.getContent());
                			forSend.setVisible(true);
                			fortxtTo.setVisible(false);
                			fordraftSend.setVisible(false);
                			fordraftAttach.setVisible(false);
                			inCancel.setVisible(true);
                			draftCancel.setVisible(false);
                			sentCancel.setVisible(false);
                			jp6.add(comboTo);
                			tab.add("Forward",jp6);
                        		tab.setSelectedIndex(7);
                        		tab.setEnabledAt(0,false);
                       		 	tab.setEnabledAt(1,false);
                        		tab.setEnabledAt(2,false);
                       			tab.setEnabledAt(3,false);
                       			tab.setEnabledAt(4,false);
                        		tab.setEnabledAt(5,false);
                        		tab.setEnabledAt(6,false);
                	}
                	if(e.getSource()==inAttach)
                	{
                		if((emailList.isSelectionEmpty())==true)return;
                			Email selectedMail=Client.getMail(inSelMailNumber);
                			String name=selectedMail.getAttachmentName();
                			name=name.toLowerCase();
                			if(name.endsWith(".txt"))
                			{
                        			ViewTextAttachment attachment=new ViewTextAttachment(selectedMail.getAttachment(),selectedMail.getAttachmentName());
                        			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                        			final int HEIGHT=500;
                        			final int WIDTH=500;
                        			attachment.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                        			attachment.setVisible(true);
                			}
                			if(name.endsWith(".gif") || name.endsWith(".jpg") || name.endsWith(".jpeg"))
                			{
                        			ViewGraphicsAttachment attachment=new ViewGraphicsAttachment(selectedMail.getAttachment());
                        			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                        			final int HEIGHT=800;
                        			final int WIDTH=800;
                        			attachment.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                        			attachment.setVisible(true);
                			}
		
         		}
         		if(e.getSource()==forSend)
         		{
                		
				fdate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
				fsendEmail();
                		fsentEmail();
				fsdLabel.setVisible(true);
				Timer t=new Timer(1500, new ActionListener()
				{
					public void actionPerformed(ActionEvent e1)
					{
						
						fsdLabel.setVisible(false);
						setVisible(false);
                				MainMenu main=new MainMenu(Client.getCurrentUserName());
                				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                				final int HEIGHT=590;
                				final int WIDTH=820;
                				main.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                				main.setVisible(true);		
					}
				}); 
				t.setRepeats(false);
				t.start();
                		/*setVisible(false);
                		MainMenu main=new MainMenu(Client.getCurrentUserName());
                		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                		final int HEIGHT=590;
                		final int WIDTH=820;
                		main.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                		main.setVisible(true);
				*/
				
          		}
         		if(e.getSource()==reSend)
         		{
                		
				rdate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
				rsendEmail();
                		rsentEmail();
				rsdLabel.setVisible(true);
				Timer t=new Timer(1500, new ActionListener()
				{
					public void actionPerformed(ActionEvent e1)
					{
						
						rsdLabel.setVisible(false);
						setVisible(false);
                				MainMenu main=new MainMenu(Client.getCurrentUserName());
                				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                				final int HEIGHT=590;
                				final int WIDTH=820;
                				main.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                				main.setVisible(true);		
					}
				}); 
				t.setRepeats(false);
				t.start();
				/*setVisible(false);
                		MainMenu main=new MainMenu(Client.getCurrentUserName());
                		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                		final int HEIGHT=590;
                		final int WIDTH=820;
                		main.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                		main.setVisible(true);*/
                		
          		}	
         		if(e.getSource()==reSave)
         		{
                		rrdate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
				rsaveEmail();
                		rsvLabel.setVisible(true);
				Timer t=new Timer(1000, new ActionListener()
				{
					public void actionPerformed(ActionEvent e1)
					{
						
						rsvLabel.setVisible(false);
						setVisible(false);
                				MainMenu main=new MainMenu(Client.getCurrentUserName());
                				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                				final int HEIGHT=590;
                				final int WIDTH=820;
                				main.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                				main.setVisible(true);	
					}
				}); 
				t.setRepeats(false);
				t.start();
          		}
        		if(e.getSource()==reAttach)
        		{
                		JFileChooser chooser=new JFileChooser();
                		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                		int result=chooser.showOpenDialog(this);
                		if(result==JFileChooser.CANCEL_OPTION)
                		return;
                		File temp=chooser.getSelectedFile();
                		if(temp==null || temp.getName().equals(""))
                                     	JOptionPane.showMessageDialog(this,"Invalid File Name","Invalid file name",JOptionPane.ERROR_MESSAGE);	
               			else
                		{
                        		remail.setAttachment(temp);
                        		remail.setAttachmentName(temp.getName());
                        		retxtAttach.setText(temp.getName());
                		}
         		}
                	if(e.getSource()==draftDelete)
         		{
                		if((draftList.isSelectionEmpty())==true)return;
                			Client.deleteDraft(draftSelMailNumber);
                			refreshListContents1();
                			txtArea3.setText("");
                			draftTitle .setText("Saved Email:"+" "+Client.draftSize());
					dfdLabel.setVisible(true);
				Timer t=new Timer(1500, new ActionListener()
				{
					public void actionPerformed(ActionEvent e1)
					{
						
						dfdLabel.setVisible(false);
						                			
					}
				}); 
				t.setRepeats(false);
				t.start();
         		}
         		if(e.getSource()==draftAttach)
         		{
                		if((draftList.isSelectionEmpty())==true)return;
                			Email selectedMail=Client.getDraft(draftSelMailNumber);
                			String name=selectedMail.getAttachmentName();
                			name=name.toLowerCase();
                			if(name.endsWith(".txt"))
                			{
                        			ViewTextAttachment attachment=new ViewTextAttachment(selectedMail.getAttachment(),selectedMail.getAttachmentName());
                        			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                        			final int HEIGHT=500;
                        			final int WIDTH=500;
                        			attachment.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                        			attachment.setVisible(true);
                			}
                			if(name.endsWith(".gif") || name.endsWith(".jpg") || name.endsWith(".jpeg"))
                			{
                        			ViewGraphicsAttachment attachment=new ViewGraphicsAttachment(selectedMail.getAttachment());
                        			Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                        			final int HEIGHT=800;
                        			final int WIDTH=800;
                        			attachment.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                        			attachment.setVisible(true);
                			}
         		}
         		if(e.getSource()==draftForward)
         		{
				setTitle("E-Mail(Forward)");
                		if((draftList.isSelectionEmpty())==true)return;
                			fdemail=new Email();
                			Email selectedMail=Client.getDraft(draftSelMailNumber);
                			fdemail=selectedMail;
                			dforrecipient=selectedMail.getActrecipient();
                			fortxtTo.setText(dforrecipient);
					fortxtTo.setVisible(false);
                			fortxtSubject.setText(selectedMail.getSubject());
                			fortxtAttach.setText(selectedMail.getAttachmentName());
                			txtArea6.setText(selectedMail.getContent());
                			forSend.setVisible(false);
                			fortxtSubject.setEditable(true);
                			txtArea6.setEditable(true);
                			fortxtTo.setVisible(true);
                			fordraftSend.setVisible(true);
                			fordraftAttach.setVisible(true);
                			inCancel.setVisible(false);
                			draftCancel.setVisible(true);
                			sentCancel.setVisible(false);
					tab.add("Forward",jp6);
                			tab.setSelectedIndex(7);
                			tab.setEnabledAt(0,false);
                			tab.setEnabledAt(1,false);
                			tab.setEnabledAt(2,false);
                			tab.setEnabledAt(3,false);
                			tab.setEnabledAt(4,false);
					tab.setEnabledAt(5,false);
					tab.setEnabledAt(6,false);
         		}
         		if(e.getSource()==fordraftSend)
         		{
                		
				fddate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
				fordsendEmail();
                		fordsentEmail();
                		Client.deleteDraft(draftSelMailNumber);
                		dsdLabel.setVisible(true);
				Timer t=new Timer(1500, new ActionListener()
				{
					public void actionPerformed(ActionEvent e1)
					{
						
						dsdLabel.setVisible(false);
						setVisible(false);
                				MainMenu main=new MainMenu(Client.getCurrentUserName());
                				Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                				final int HEIGHT=590;
                				final int WIDTH=820;
                				main.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                				main.setVisible(true);		
					}
				}); 
				t.setRepeats(false);
				t.start();
         		}	
        		if(e.getSource()==fordraftAttach)
        		{
                		JFileChooser chooser=new JFileChooser();
                		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                		int result=chooser.showOpenDialog(this);
                		if(result==JFileChooser.CANCEL_OPTION)
                			return;
                			File temp=chooser.getSelectedFile();
                		if(temp==null || temp.getName().equals(""))    
                        		JOptionPane.showMessageDialog(this,"Invalid File Name","Invalid file name",JOptionPane.ERROR_MESSAGE);
                		else
                		{
                        		fdemail.setAttachment(temp);
                        		fdemail.setAttachmentName(temp.getName());
                        		fortxtAttach.setText(temp.getName());
                		}
         		}
         		if(e.getSource()==sentForward)
         		{
                		setTitle("E-Mail(Forward)");
				if((sentList.isSelectionEmpty())==true)return;
                			femail=new Email();
                			Email sselectedMail=Client.getSent(sentSelMailNumber);
                			femail=sselectedMail;
                			oldUser=sselectedMail.getActrecipient();
                			fortxtSubject.setText(sselectedMail.getSubject());
                			fortxtAttach.setText(sselectedMail.getAttachmentName());
                			txtArea6.setText(sselectedMail.getContent());
                			forSend.setVisible(true);
                			fortxtTo.setVisible(false);
                			fordraftSend.setVisible(false);
                			fordraftAttach.setVisible(false);
                			inCancel.setVisible(false);
                			sentCancel.setVisible(true);
                			draftCancel.setVisible(false);
                			jp6.add(comboTo);
                			tab.add("Forward",jp6);
                			tab.setSelectedIndex(7);
                			tab.setEnabledAt(0,false);
                			tab.setEnabledAt(1,false);
                			tab.setEnabledAt(2,false);
                			tab.setEnabledAt(3,false);
                			tab.setEnabledAt(4,false);
					tab.setEnabledAt(5,false);
					tab.setEnabledAt(6,false);
         		}
       	 		if(e.getSource()==sentDelete)
	 		{         
	  			if((sentList.isSelectionEmpty())==true)return;
                			Client.deleteSent(sentSelMailNumber);
                			refreshListContents2();
                			txtArea4.setText("");
                			sentTitle .setText("Sent Email:"+" "+Client.sentSize());
					sndLabel.setVisible(true);
				Timer t=new Timer(1500, new ActionListener()
				{
					public void actionPerformed(ActionEvent e1)
					{
						
						sndLabel.setVisible(false);
								
					}
				}); 
				t.setRepeats(false);
				t.start();
         		}
         		if(e.getSource()==sentAttach)
         		{
                		if((sentList.isSelectionEmpty())==true)return;
                			Email selectedMail=Client.getSent(sentSelMailNumber);
                			String name=selectedMail.getAttachmentName();
                			name=name.toLowerCase();
                		if(name.endsWith(".txt"))
                		{
                        		ViewTextAttachment attachment=new ViewTextAttachment(selectedMail.getAttachment(),selectedMail.getAttachmentName());
                        		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                        		final int HEIGHT=500;
                        		final int WIDTH=500;
                        		attachment.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                        		attachment.setVisible(true);
                		}
                		if(name.endsWith(".gif") || name.endsWith(".jpg") || name.endsWith(".jpeg"))
                		{
                        		ViewGraphicsAttachment attachment=new ViewGraphicsAttachment(selectedMail.getAttachment());
                        		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
                        		final int HEIGHT=800;
                        		final int WIDTH=800;
                       			attachment.setBounds(((screenSize.width/2)-(WIDTH/2)),((screenSize.height/2)-(HEIGHT/2)),WIDTH,HEIGHT);
                        		attachment.setVisible(true);
                		}
                	}
                	if(e.getSource()==reCancel)
                	{
                        	tab.setSelectedIndex(1);
                        	retxtTo.setText("");
                        	retxtSubject.setText("");
                        	retxtAttach.setText("");
                        	txtArea5.setText("");
                        	tab.remove(7);
                        	tab.setEnabledAt(0,true);
                        	tab.setEnabledAt(1,true);
                        	tab.setEnabledAt(2,true);
                        	tab.setEnabledAt(3,true);
                        	tab.setEnabledAt(4,true);
				tab.setEnabledAt(5,true);
				tab.setEnabledAt(6,true);
                	}
                	if(e.getSource()==inCancel)
                	{
                        	jp1.add(comboTo);
                        	fortxtSubject.setText("");
                        	fortxtAttach.setText("");
                        	txtArea6.setText("");
                        	tab.setSelectedIndex(1);
                        	tab.remove(7);
                        	tab.setEnabledAt(0,true);
                        	tab.setEnabledAt(1,true);
                        	tab.setEnabledAt(2,true);
                        	tab.setEnabledAt(3,true);
                        	tab.setEnabledAt(4,true);
				tab.setEnabledAt(5,true);
				tab.setEnabledAt(6,true);
                	}
                	if(e.getSource()==draftCancel)
                	{
                        	fortxtTo.setText("");
                        	fortxtSubject.setText("");
                        	fortxtAttach.setText("");
                        	txtArea6.setText("");
                        	tab.setSelectedIndex(2);
                        	tab.remove(7);
                        	fortxtTo.setVisible(false);
                        	comboTo.setVisible(true);
                        	tab.setEnabledAt(0,true);
                        	tab.setEnabledAt(1,true);
                        	tab.setEnabledAt(2,true);
                        	tab.setEnabledAt(3,true);
                        	tab.setEnabledAt(4,true);
				tab.setEnabledAt(5,true);
				tab.setEnabledAt(6,true);
                	}
                	if(e.getSource()==sentCancel)
                	{
                        	jp1.add(comboTo);
                        	fortxtSubject.setText("");
                        	fortxtAttach.setText("");
                        	txtArea6.setText("");
                        	tab.setSelectedIndex(3);
                        	tab.remove(7);
                        	tab.setEnabledAt(0,true);
                        	tab.setEnabledAt(1,true);
                        	tab.setEnabledAt(2,true);
                        	tab.setEnabledAt(3,true);
                        	tab.setEnabledAt(4,true);
				tab.setEnabledAt(5,true);
				tab.setEnabledAt(6,true);
                	}
	}
	private class ComboHandler implements ItemListener
	{
                public void itemStateChanged(ItemEvent IE)
                {
                        	recipient=(names[comboTo.getSelectedIndex()]);
                }
	}
	private class ListSelectionHandler implements ListSelectionListener
	{
                public void valueChanged(ListSelectionEvent e)
                {
                        	inSelMailNumber=emailList.getSelectedIndex();
                        	draftSelMailNumber=draftList.getSelectedIndex();
                        	sentSelMailNumber=sentList.getSelectedIndex();
                }
	}

	//RECIEVE CLIENT MAIL SERVER
	private void recieveMessagesFromServer()throws ClassNotFoundException
	{
                try
                {
                	//SET UPSTREAM FOR OBJECT OUTPUT
                       	objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
	
			//SENDING MESSAGE TO SERVER
                       	String command="RECIEVING";
                       	objectOut.writeObject(command);
                       	objectOut.flush();
                        
			//SET UP STREAM FOR RECIEVING OBJECT
                       	objectIn=new ObjectInputStream(Client.getLink().getInputStream());
                       
			//GET INSTRUCTION FROM SERVER
                       	String option=(String)objectIn.readObject();
                        
			//GET ALL MAILS FROM SERVER
                      	while(option.equals("SENDING"))
                       	{
                               	Email email=(Email)objectIn.readObject();
                                Client.addMail(email);
                                option=(String)objectIn.readObject();
                        }
                 }
                 catch(IOException ioe)
                 {
                        ioe.printStackTrace();
                 }
	}

	private void recieveMessagesFromServer1()throws ClassNotFoundException
	{
                try
                {
                        //SET UPSTREAM FOR OBJECT OUTPUT
                        objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
                        
			//SENDING MESSAGE TO SERVER
                        String command="MRECIEVING";
                        objectOut.writeObject(command);
                        objectOut.flush();
                        
			//SET UP STREAM FOR RECIEVING OBJECTS
                        objectIn=new ObjectInputStream(Client.getLink().getInputStream());
                        
			//GET INSRUCTION FROM SERVER
                        String option=(String)objectIn.readObject();
                        
			//GET ALL MAILS FROM SERVER
                        while(option.equals("DSENDING"))
                        {
                                Email email=(Email)objectIn.readObject();
                                Client.addDraft(email);
                                option=(String)objectIn.readObject();
                        }
                 }
                 catch(IOException ioe)
                 {
                        ioe.printStackTrace();
                 }
	}

	private void recieveMessagesFromServer2()throws ClassNotFoundException
	{
                 try
                 {
                 	//SET UPSTREAM FOR OBJECT OUTPUT
                        objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
                                
			//SENDING MESSAGE TO SERVER
                        String command="NRECIEVING";
                        objectOut.writeObject(command);
                        objectOut.flush();

                        //SET UP STREAM FOR RECIEVING OBJECT
                        objectIn=new ObjectInputStream(Client.getLink().getInputStream());
                                 
			//GET INSTRUCTION FROM SERVER
                        String option=(String)objectIn.readObject();

                        //GET ALL MAILS FROM SERVER
                        while(option.equals("ESENDING"))
                        {
                        	Email email=(Email)objectIn.readObject();
                              	Client.addSent(email);
                                option=(String)objectIn.readObject();
                    	}
		}
 		catch(IOException ioe)
                {
                	ioe.printStackTrace();
		}
	}

	private void sendEmail()
	{
                try
                {
                        	objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
                        	String option="SENDING";
                        	objectOut.writeObject(option);
                        	objectOut.flush();
                        	email.setSender(Client.getCurrentUserName());
                        	email.setRecipient(recipient);
                        	email.setSubject(txtSubject.getText());
                        	email.setContent(txtArea1.getText());
                        	email.setRecipient(recipient);
				email.setdate(cdate);
                        	objectOut.writeObject(email);
                        	objectOut.flush();
                 }
                 catch(IOException ioe)
                 {
                        ioe.printStackTrace();
                 }
	}

	private void sentEmail()
	{
                try
                {
                        	objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
                        	String option="ESENDING";
                        	objectOut.writeObject(option);
                        	objectOut.flush();
                        	email.setSender(Client.getCurrentUserName());
                        	email.setRecipient(Client.getCurrentUserName());
                        	email.setSubject(txtSubject.getText());
                        	email.setContent(txtArea1.getText());
                        	email.setActRecipient(recipient);
				email.setdate(cdate);
                        	objectOut.writeObject(email);
                        	objectOut.flush();
                 }
                 catch(IOException ioe)
                 {
                        ioe.printStackTrace();
                 }
	}

	private void saveEmail()
	{
                try
                {
                        	objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
                        	String option="DSENDING";
                        	objectOut.writeObject(option);
                        	objectOut.flush();
                        	email.setSender(Client.getCurrentUserName());
                        	email.setRecipient(Client.getCurrentUserName());
                        	email.setSubject(txtSubject.getText());
                        	email.setContent(txtArea1.getText());
                        	email.setActRecipient(recipient);
				email.setdate(ssdate);
                        	objectOut.writeObject(email);
                        	objectOut.flush();
                 }
                 catch(IOException ioe)
                 {
                        ioe.printStackTrace();
                 }
	}

	private void rsendEmail()
	{
                try
                {
                        	objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
                        	String option="SENDING";
                        	objectOut.writeObject(option);
                        	objectOut.flush();
                        	remail.setSender(Client.getCurrentUserName());
                        	remail.setRecipient(retxtTo.getText());
                        	remail.setSubject(retxtSubject.getText());
                        	remail.setContent(txtArea5.getText());
				remail.setdate(rdate);
                        	objectOut.writeObject(remail);
                        	objectOut.flush();
                 }
                 catch(IOException ioe)
                 {
                        ioe.printStackTrace();
                 }
	}

	private void rsentEmail()
	{
                try
                {
                        	objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
                        	String option="ESENDING";
                        	objectOut.writeObject(option);
                        	objectOut.flush();
                        	remail.setSender(Client.getCurrentUserName());
        	               	remail.setRecipient(Client.getCurrentUserName());
                        	remail.setSubject(retxtSubject.getText());
                        	remail.setContent(txtArea5.getText());
                        	remail.setActRecipient(retxtTo.getText());
				remail.setdate(rdate);
                        	objectOut.writeObject(remail);
                        	objectOut.flush();
                 }
                 catch(IOException ioe)
                 {
                        ioe.printStackTrace();
                 }

	}

	private void rsaveEmail()
	{
                try
                {
                        	objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
                        	String option="DSENDING";
                        	objectOut.writeObject(option);
                        	objectOut.flush();
                        	remail.setSender(Client.getCurrentUserName());
                        	remail.setRecipient(Client.getCurrentUserName());
                        	remail.setActRecipient(retxtTo.getText());
                        	remail.setSubject(retxtSubject.getText());
                        	remail.setContent(txtArea5.getText());
				remail.setdate(rrdate);
                        	objectOut.writeObject(remail);
                        	objectOut.flush();
                 }
                 catch(IOException ioe)
                 {
                        ioe.printStackTrace();
                 }

	}

	private void fordsendEmail()
	{
                try
                {
                        	objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
                        	String option="SENDING";
                        	objectOut.writeObject(option);
                        	objectOut.flush();
                        	fdemail.setSender(Client.getCurrentUserName());
                        	fdemail.setSubject(fortxtSubject.getText());
                        	fdemail.setRecipient(fortxtTo.getText());
                        	fdemail.setContent(txtArea6.getText()); 
				fdemail.setdate(fddate);   
                        	objectOut.writeObject(fdemail);
                        	objectOut.flush();
                 }
                 catch(IOException ioe)
                 {
                        ioe.printStackTrace();
                 }
	}
	private void fordsentEmail()
	{
                try
                {
                        	objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
                        	String option="ESENDING";
                        	objectOut.writeObject(option);
                        	objectOut.flush();
                        	fdemail.setSender(Client.getCurrentUserName());
                        	fdemail.setRecipient(Client.getCurrentUserName());
                        	fdemail.setSubject(fortxtSubject.getText());
                        	fdemail.setContent(txtArea6.getText());
                        	fdemail.setActRecipient(fortxtTo.getText());
				fdemail.setdate(fddate);
                        	objectOut.writeObject(fdemail);
                        	objectOut.flush();
                 }
                 catch(IOException ioe)
                 {
                        ioe.printStackTrace();
                 }

	}
	private void fsendEmail()
	{
                try
                {
                        	objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
                        	String option="SENDING";
                        	objectOut.writeObject(option);
                        	objectOut.flush();
                        	femail.setSender(Client.getCurrentUserName());
                        	femail.setRecipient(recipient);
                        	femail.setSubject(fortxtSubject.getText());
                        	femail.setContent(txtArea6.getText());
                        	femail.setRead(false);
				femail.setdate(fdate);
                        	objectOut.writeObject(femail);
                        	objectOut.flush();
                 }
                 catch(IOException ioe)
                 {
                        ioe.printStackTrace();
                 }
	}

	private void fsentEmail()
	{
                try
                {
                        	objectOut=new ObjectOutputStream(Client.getLink().getOutputStream());
                        	String option="ESENDING";
                        	objectOut.writeObject(option);
                        	objectOut.flush();
                        	femail.setSender(Client.getCurrentUserName());
                        	femail.setRecipient(Client.getCurrentUserName());
        	               	femail.setSubject(fortxtSubject.getText());
                        	femail.setContent(txtArea6.getText());
                        	femail.setActRecipient(recipient);
				femail.setdate(fdate);
                        	objectOut.writeObject(femail);
                        	objectOut.flush();
                        	femail.setRead(true);
                        	femail.setSender(oldUser);
                        	femail.setActRecipient(oldUser);
                 }
                 catch(IOException ioe)
                 {
                        ioe.printStackTrace();
                 }

	}

	public void refreshListContents()
	{
              	inDescriptor.removeAllElements();
                Email mail;
                String description;
                for(int count=0;count<Client.inboxSize();count++)
                {
                        	String slno=String.valueOf(count);
                        	mail=Client.getMail(count);
                        	if(mail.getRead()==true)
                        	{
                                	description=("       "+"  From:   "+mail.getSender()+"      "+" Re:"+mail.getSubject()+"    "+"  Attachment:"+mail.getAttachmentName()+"   "+" Date & Time:"+mail.getdate());
                        	}
                        	else
                        	{
                                	description=("NEW"+"From:"+mail.getSender()+"    "+"   Re:  "+mail.getSubject()+"  "+"  Attachment: "+mail.getAttachmentName()+"   "+" Date & Time:"+mail.getdate());
                        	}
                        	inDescriptor.add(description);
                }
                emailList.setListData(inDescriptor);
	}

	public void refreshListContents1()
	{
                draftDescriptor.removeAllElements();
                Email mail;
                String description2;
                for(int count=0;count<Client.draftSize();count++)
                {
                        	mail=Client.getDraft(count);
                        	description2=("       "+"  To:   "+mail.getActrecipient()+"      "+" Re:"+mail.getSubject()+"    "+"  Attachment:"+mail.getAttachmentName()+"   "+" Date & Time:"+mail.getdate());
                        	draftDescriptor.add(description2);
	
		}
                draftList.setListData(draftDescriptor);
	}

	public void refreshListContents2()
	{
                sentDescriptor.removeAllElements();
                Email mail;
                String description3;
                for(int count=0;count<Client.sentSize();count++)
                {
                        	mail=Client.getSent(count);
                        	description3=("       "+"  To:   "+mail.getActrecipient()+"      "+" Re:"+mail.getSubject()+"    "+"  Attachment:"+mail.getAttachmentName()+"   "+" Date & Time:"+mail.getdate());
                       	 sentDescriptor.add(description3);
                }
                sentList.setListData(sentDescriptor);
                }
	}
