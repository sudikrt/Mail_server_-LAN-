
import java.io.*;
class Email implements Serializable
{
	private String sender;
	private String recipient;
	private String Actrecipient;
	private String subject;
	private String content;
	private String attachmentName;
	private byte[] attachment;
	private boolean read;
	private String date;
	public Email()
	{
		sender="";
		recipient="";
		Actrecipient="";
		subject="";
		content="";
		attachmentName="";
		attachment=null;
		read=false;
		date="";
	}
	public Email(String sender,String recipient,String Actrecipient,String subject,String content,String attachmentName,byte[] attachment,String date)
	{
		this.sender=sender;
		this.recipient=recipient;
		this.Actrecipient=Actrecipient;
		this.subject=subject;
		this.content=content;
		this.attachmentName=attachmentName;
		this.attachment=attachment;
		read=false;
		this.date=date;
	}
	
	public String getSender()
	{
		return sender;
	}
	public void setSender(String sender)
	{
		this.sender=sender;
	}
	public String getRecipient()
	{
		return recipient;
	}
	public void setRecipient(String recipient)
	{
		this.recipient=recipient;
	}
	public String getActrecipient()
	{
		return Actrecipient;
	}
	public void setActRecipient(String Actrecipient)
	{
		this.Actrecipient=Actrecipient;
	}
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject=subject;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content=content;
	}
	public String getAttachmentName()
	{
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName)
	{
		this.attachmentName=attachmentName;
	}
	public byte[] getAttachment()
	{
		return attachment;
	}
	public void setAttachment(File temp)
	{
		try
		{
			FileInputStream fileIn=new FileInputStream(temp.getAbsolutePath());
			long fileLength=temp.length();
			int intFileLength=(int)fileLength;
			attachment=new byte[intFileLength];
			fileIn.read(attachment);
			fileIn.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	public void setAttachment1(byte[] attachment)
	{
		this.attachment=attachment;
	}
	public boolean getRead()
	{
		return read;
	}
	public void setRead(boolean read)
	{
		this.read=read;
	}
	public String getdate()
	{
		return date;
	}
	public void setdate(String d) 
	{
		this.date=d;
	}
}