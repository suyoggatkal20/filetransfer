import java.net.*;
import java.util.*;
import java.io.*;

class MyServer
{
	public static void main(String []args) throws Exception
	{
	System.out.println("**********************WELCOME IN FILE TRANSFER TOOL***********************\n\n\n");	
    Scanner sc =new Scanner(System.in);
	
    ServerSocket so=new ServerSocket(8080);     //this will create server socket object with given port No. you can mention any free port no here.
	
	System.out.print("\n\nWaiting for connection request...");
	Socket s =so.accept();	//this function will wait until client connects the server. and then it will return socket object
    System.out.print("\n\nConnected...");
	
	System.out.print("\n\nEnter File Path with file name(with extention) :");
	String path=sc.nextLine();
	
	
	OutputStream out = new FileOutputStream(path);     //here we created the FileOutputStream object for file handling
	BufferedInputStream in = new BufferedInputStream(s.getInputStream());   // here we declared bufferedInputStream object to get input from socket
	
	byte buffer[]=new byte[512];                 //declaration and memory allocation of buffer
	int count;                                 //integer to hold no. of charactor read by input stream
	
	
	System.out.print("\n\nReceiving Data...");
	while(true)                                    // infinite while loop
	{
		count=in.read(buffer,0,512);           //this function will read data from socket and strore it into buffer.
		                                       //it return the integer that represent the no of actual bits that are read by the input stream
											   // it take 3 parameters. 1. buffer 2.starting point from where it should write in buffer 3. Max no. of byte to be read
											   //it returns -1 if no byte is read at the end of file this happens.
		if(count==-1)                             //here it will cheak End Of File. and break loop if it is end.
			break;                         
		out.write(buffer,0,count);             //write the data into file from buffer.
												//take 3 parameters 1. buffer 2. starting position in buffer from where byte to be transfered to file
												// 3. No of bytes to be transfered
												
		out.flush();                            //to flush the output stream
	}
	System.out.println("\n\nData Transfer Completed...\nThank you...");	
	in.close();                          // here  at last we close all the connections
	out.close();
	s.close();
	so.close();


}
}