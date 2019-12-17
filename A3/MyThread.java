package tupleServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
@SuppressWarnings("rawtypes")
public class MyThread extends Thread {
	 public final static String READ = "1";
	    public final static String WRITE = "2";
	    public final static String UPDATE = "3";
	    public final static String TAKE = "4";
		public static final String View = "5";
	Socket clientSocket;
	TupleSpace myTupleSpace;
	public MyThread(Socket clientSocket,TupleSpace myTupleSpace) {
		// TODO Auto-generated constructor stub
		this.clientSocket=clientSocket;
		this.myTupleSpace=myTupleSpace;}

	public void run() {		
		try{

			BufferedReader br = new BufferedReader(new  InputStreamReader(clientSocket.getInputStream()));
			String value = br.readLine();
			PrintStream out = new PrintStream(clientSocket.getOutputStream());
			System.out.println(value);
			if(!value.isEmpty()) {
				String main[]=value.split("##");
				if(main[0].equals(WRITE)) {
					String writeData[]=main[1].split(";");

					write(writeData);
					out.println("Tuple Written to the Space");
				}
				else if(main[0].equals(READ) ){
					ArrayList<Tuple> t=	read(main);
					t.removeAll(Collections.singleton(null));

					StringBuffer sb = new StringBuffer("READ OPERATION: ");
					if (t.isEmpty()) {
						System.out.print(sb.append(" No Such Tuple Found. Critical Section did not execute. Please Write and then read").toString());
					} else {
						//System.out.println(sb.toString());
						for (Tuple temp1 : t) {
							if(temp1==null) continue;
							System.out.print(sb.append(temp1.toString()));sb.append(";");
						}sb.append("Critical Section Executed Successfully");
					}

					out.println(sb.toString());

				}
				else if(main[0].equals(UPDATE)) {
					System.out.println(main);
					out.println(update(main));
				}
				else if(main[0].equals(TAKE)) {
					out.println(take(main));
					System.out.println("tuple space now"+ myTupleSpace.toString());


				}
				else if(main[0].equals(View)) {
					out.println("Current tuples in Tuplespace:"+myTupleSpace.toString()+"\n--------------------------------");
				}


			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	private String take(String[] main) throws InterruptedException {
		// TODO Auto-generated method stub
		String[] mainn=main[1].split(";");

		String[] mainn2 = new String[mainn.length + 1];
		mainn2[0] = "0";
		System.arraycopy(mainn, 0, mainn2, 1, mainn.length);
		ArrayList<Tuple> temp = read(mainn2);

		temp.removeAll(Collections.singleton(null));
		String toreturn="";
		for(int i=0;i<temp.size();i++) {
			Tuple T=temp.get(i);

			if(T!=null) {
				synchronized(this) {
					myTupleSpace.tupleSpace.remove(T);
					myTupleSpace.toString();
				}

				//	T=myTupleSpace.takeFromSpace(T,myTupleSpace);
				toreturn+=T.toString();}}
		//if(T==null) return "No such Tuple Found";
		if(toreturn=="") return "no such tuple found, failed to execute critical section";
		return toreturn+"Succesufully taken from the space";}


	private String update(String[] main) throws InterruptedException {
		// TODO Auto-generated method stub
		String[] newdata = {null,null,null,null} ;
		String[] mainn=main[1].split(";");
		String[] mainn2 = new String[mainn.length + 1];
		mainn2[0] = "0";
		System.arraycopy(mainn, 0, mainn2, 1, mainn.length);
		for(int i=0;i<4;i++)
			newdata[i]=mainn2[i];

		ArrayList<Tuple> temp = read(newdata);
		temp.removeAll(Collections.singleton(null));
		//	for(int i=0;i<temp.size();i++) {
		if(!temp.isEmpty()) {
			Tuple T=temp.get(0);


			synchronized(this) {
				myTupleSpace.tupleSpace.remove(T);
				int j=0;
				for(int k=4;k<=6;k++)
				{
					newdata[j]=mainn2[k];
					j++;
				}

				write(newdata);
				return "succesfull updated";

				//myTupleSpace.toString();
			}

			//	T=myTupleSpace.takeFromSpace(T,myTupleSpace);
		}
		else
			return "No such Tuple Found, Update failed";


	}
	//else return "No such Tuple Found";



	private  ArrayList<Tuple> read(String[] newarr) throws InterruptedException {
		// TODO Auto-generated method stub
		Object temp = null;
		Object a=null,b=null,c=null;
		Boolean ax=false,bx=false,cx=false;
		ArrayList<Tuple> listtoreturn = new ArrayList<>();
		for(int i=1;i<newarr.length;i++) {
			//		String[] newarr= writeData[i].split(";");
			if(newarr[i].equals("true")) {
				temp=true;

			}
			else if (newarr[i].equals("false")) {
				temp=false;
			}
			else if (newarr[i].equals("CLASSBOOLEAN")) {
				temp=Boolean.class;
				if(i==1) {
					ax=true;
				}
				else if(i==2) {
					bx=true;
				}
				else if(i==3) {
					cx=true;
				}
			}
			else if(newarr[i].equals("CLASSSTRING")) {
				temp=String.class;
				if(i==1) {
					ax=true;
				}
				else if(i==2) {
					bx=true;
				}
				else if(i==3) {
					cx=true;
				}
			}
			else if(newarr[i].equals("CLASSINTEGER")) {
				temp=Integer.class;
				if(i==1) {
					ax=true;
				}
				else if(i==2) {
					bx=true;
				}
				else if(i==3) {
					cx=true;
				}
			}
			else  {
				try {
					temp=Integer.parseInt(newarr[i]);
				}
				catch(Exception e) {

					temp=newarr[i];
				}
			}
			if(i==1) {
				a=temp;
			}
			else if(i==2) {
				b=temp;
			}
			else if(i==3) {
				c=temp;
			}
		}
		if(ax && bx && cx) {
			listtoreturn.addAll(myTupleSpace.readFromSpace((Class) a, (Class) b, (Class) c));
		}
		else if(!ax && bx && cx) {
			listtoreturn.addAll(myTupleSpace.readFromSpace( a, (Class) b, (Class) c));
		}
		else if(ax && !bx && cx) {
			listtoreturn.addAll(myTupleSpace.readFromSpace((Class) a,  b, (Class) c));
		}
		else if(ax && bx && !cx) {
			listtoreturn.addAll(myTupleSpace.readFromSpace((Class) a, (Class) b,  c));
		}
		else if(!ax && !bx && !cx) {
			listtoreturn.addAll(myTupleSpace.readFromSpace( a,  b,  c));
		}
		else if(ax && !bx && !cx) {
			listtoreturn.addAll(myTupleSpace.readFromSpace((Class) a,  b,  c));
		}
		else if(!ax && bx && !cx) {
			listtoreturn.addAll(myTupleSpace.readFromSpace( a, (Class) b,  c));
		}
		else if(!ax && !bx && cx) {
			listtoreturn.addAll(myTupleSpace.readFromSpace( a, b, (Class) c));
		}
		

		return listtoreturn;
		//sb.toString();

	}
	private  void write(String[] writeData) throws InterruptedException {
		// TODO Auto-generated method stub
		//	System.out.println("main aray"+writeData);
		Object temp=null;
		ArrayList<Object> arr= new ArrayList<>();
		for(int i=0;i<writeData.length;i++) {
			if(writeData[i].equals("true")) {
				temp=true;
				arr.add(temp);
				continue;
			}
			else if(writeData[i].equals("false")) {
				temp=false;
				arr.add(temp);
				continue;
			}
			try {
				arr.add(Integer.parseInt(writeData[i]));
				continue;
			}catch(Exception e) {
				arr.add(writeData[i]);
			}
		}
		myTupleSpace.writeToSpace(arr.get(0), arr.get(1), arr.get(2));
		//	System.out.println("this is tuple "+ myTupleSpace.toString());
	}
}
