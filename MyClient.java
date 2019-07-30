import java.util.*;
import java.net.*;
import java.io.*;
public class MyClient
{  
	public static void main(String[] args) throws Exception
	{  
		try
		{
			Scanner sc=new Scanner(System.in);
			

			System.out.println("**********************WELCOME IN FILE TRANSFER TOOL***********************\n\n\n");
						//here we are going to take all needed info from user
			System.out.print("Enter the ip address of machine that u want to connect : ");
			String ip=sc.nextLine();
			System.out.print("\nEnter the port No. of server : ");
			int port=sc.nextInt();
			sc.nextLine();              // this is to remove newline char after nextInt function
		
		    // here we are creating the socket with provided ip and port
			Socket s=new Socket(ip,port);
			if(s!=null)
				System.out.println("\n\nconnected...");
			
				System.out.println("\n\nEnter the file path to be transfered : ");
				String path=sc.nextLine();
		
			
			//here we are going to create the objects of FileInputStream and BufferedOutputStream to take input from file and send it to server through socket  
			BufferedOutputStream dout=new BufferedOutputStream(s.getOutputStream()); 
			InputStream in = new FileInputStream(path);
		
			byte buffer[] = new byte[512];   // buffer declaration and memory allocation
			int count;                        // count variable to store actual count of read bytes

			System.out.println("\n\nSending data...");			
			while(true)                     //infinite loop
			{
				count=in.read(buffer,0,512);    //this function will read data from file and strore it into buffer.
		                                       //it return the integer that represent the no of actual bits that are read by the input stream
											   // it take 3 parameters. 1. buffer 2.starting point from where it should write in buffer 3. Max no. of byte to be read
											   //it returns -1 if no byte is read at the end of file this happens.
				if(count==-1)                    //here it will cheak End Of File. and break loop if it is end.
					break;
				dout.write(buffer,0,count);	         //send the data to socket from buffer.
												//take 3 parameters 1. buffer 2. starting position in buffer from where byte to be transfered to socket
												// 3. No of bytes to be transfered
				dout.flush();                    //to flush the output stream
			}  
			System.out.println("\n\nData Transfer Completed...\nThank you...");	
			dout.close();                         //here at last we will close all connections
			in.close();  
			s.close();  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}  
	}  
} 