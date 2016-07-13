import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
class ViewGraphicsAttachment extends JFrame
{
     	private static JLabel picLabel;
     	private static ImageIcon pictureAttachment;
     	private static Container pane;
     	private static  byte[] graphicsAttachment;

     	public ViewGraphicsAttachment(byte[] graphicsAttachment)
     	{
          		this.graphicsAttachment=graphicsAttachment;
          		try
          		{
               			getGraphicsAttachmentContents();
          		}
          		catch(IOException e)
          		{
               			e.printStackTrace();
          		}
          		//SET WINDOW TITLE//
          		setTitle("View Graphics Attachments");
         		//ADD COMPONENTS TO CONTENT PANEL//
          		pane=getContentPane();
          		pane.add(picLabel,BorderLayout.CENTER);
     	}
     	//GET IMAGE FROM BYTE ARRAY ATTACHMENT//
     	public void getGraphicsAttachmentContents()throws IOException
     	{
          		pictureAttachment=new ImageIcon(graphicsAttachment);
          		picLabel=new JLabel(pictureAttachment);
     	}
}

