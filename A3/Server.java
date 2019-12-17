package tupleServer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	private static TupleSpace myTupleSpace = new TupleSpace();
	public static void main(String[] args) {
		try {
			Boolean b=true;
			int a=1766632;
			String s="ehthesham";
			myTupleSpace.writeToSpace(b, a, s);
			System.out.println("SERVER\nDefault tuple : "+myTupleSpace.toString());
			ServerSocket myServer = new ServerSocket( Integer.parseInt("1024"));
			while (true) {
				Socket clientSocket = myServer.accept();
				MyThread thread = new MyThread( clientSocket,myTupleSpace);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

