//IMPORT LIBRARIES NEED FOR GUI//
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//INCLUDE LIBRARIES NEEDED FOR FILESYSTEM//
import java.io.*;
class ViewTextAttachment extends JFrame
{
     	private static BufferedReader fileIn;
     	private static JTextArea attachmentWindow;
     	private static byte[] textAttachment;
     	private static Container pane;
     	private static FileOutputStream fileOut;
     	private static String name;

     	//CONSTRUCTOR FOR SERVER GUI//
    	 public ViewTextAttachment(byte[] textAttachment,String name)
     	{
          		this.textAttachment=textAttachment;
          		this.name=name;
         		setTitle("View Text Attachment");
          		attachmentWindow=new JTextArea(30,50);
          		attachmentWindow.setWrapStyleWord(true);
          		attachmentWindow.setLineWrap(true);
          		attachmentWindow.setEditable(false);
          		pane=getContentPane();
          		pane.add(attachmentWindow,BorderLayout.CENTER);
          		pane.add(new JScrollPane(attachmentWindow));
          		try
          		{
               			getTextAttachmentContents();
          		}
          		catch(IOException ioe)
          		{
               			JOptionPane.showMessageDialog(null,"Error Reading From File");
          		}	
      	}
      	public void getTextAttachmentContents()throws IOException
      	{
          		fileOut=new FileOutputStream(name);
          		fileOut.write(textAttachment);
          		File temp=new File(name);
          		fileIn=new BufferedReader(new FileReader(temp));
          		String input;
          		String text="";
          		input=fileIn.readLine();
          		while(input!=null)
          		{
               			text=(text+input+"\n");
               			input=fileIn.readLine();
          		}
          		fileIn.close();
          		attachmentWindow.setText(text);
     	}
}




