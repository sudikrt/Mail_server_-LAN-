import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DownLoad implements Runnable{
    	public ServerSocket server;
    	public Socket socket;
    	public int port;
	byte[] att;   
	public String saveTo = "";
    	public InputStream In;
    	public FileOutputStream Out;
        	public DownLoad(String saveTo,byte[] att,String name){
        		try {
            			server = new ServerSocket(0);
            			port = server.getLocalPort();
            			this.saveTo = saveTo;
			this.att=att;
			System.out.println(saveTo);
			Out = new FileOutputStream(saveTo);
		} 
        		catch (IOException ex) {
            			System.out.println("Exception [Download : Download(...)]");
        		}
	}
	@Override
	public void run() {
		try{
			if(true)
			Out.write(att);
		}
		catch(IOException eee)
		{
		}
       	}
}