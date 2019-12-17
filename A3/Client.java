package tupleClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

//import tupleServer.Constants;

class Client {
	 public final static String READ = "1";
	    public final static String WRITE = "2";
	    public final static String UPDATE = "3";
	    public final static String TAKE = "4";
	    public final static String View="5";
    private static Scanner reader = null;
    public static void main(String[] args) {
        reader = new Scanner(System.in);  // Reading from System.in
        callMainMenu();
        reader.close();
    }

    private static void callMainMenu() {
        StringBuffer sb = new StringBuffer();
        System.out.println("CLIENT\nEnter which operation to perform ");
        System.out.println("1 for Read");
        System.out.println("2 for Write");
        System.out.println("3 for Update");
        System.out.println("4 for Take");
        System.out.println("5 to View TupleSpace");

        int serviceNumber = Integer.parseInt(getInput("INT"));

        String data = "";
        int counter = 0;
        switch (serviceNumber) {
            case 1:
            	System.out.println();
                data = read(sb.append(READ), counter);
               // System.out.println(data);
                break;
            case 2:
            	System.out.println();
                data = write(sb.append(WRITE).append("##"), counter);
                break;
            case 3:
            	System.out.println();
                data = updated(sb.append(UPDATE).append("##"), counter);
                break;
            case 4:
            	System.out.println();
                data = take(sb.append(TAKE).append("##"), counter);
                break;
            case 5:
            	System.out.println();
            	data=sb.append(View).append("##").toString();

            	break;
            default:
                System.out.println("Wrong option, please try again!!!");
                callMainMenu();
                break;
        }
        callServer(data);
    }

	private static String write(StringBuffer sb, int counter) {
        if (counter == 3)
            return sb.substring(0, sb.toString().length() - 1);

        System.out.println("Choose the type of data to be written: ");
        System.out.println("1) Boolean: true");
        System.out.println("2) Boolean: false");
        System.out.println("3) Integer: Enter the value");
        System.out.println("4) String: Enter String Below");
        System.out.println("5) Change operation");


        int serviceNumber = Integer.parseInt(getInput("INT"));
        switch (serviceNumber) {
            case 1:
                sb.append("true").append(";");
                counter++;
                write(sb, counter);
                break;
            case 2:
                sb.append("false").append(";");
                counter++;
                write(sb, counter);
                break;
            case 3:
                sb.append(getInput("INT")).append(";");
                counter++;
                write(sb, counter);
                break;
            case 4:
                sb.append(getInput("STRING")).append(";");
                counter++;
                write(sb, counter);
                break;
            case 5:
                callMainMenu();
                break;
            default:
                System.out.println("Wrong option, please try again!!!");
                write(sb, counter);
                break;
        }
        return sb.substring(0, sb.toString().length() - 1);
    }

    private static String updated(StringBuffer sb, int counter) {
        if (counter == 6) return sb.substring(0, sb.toString().length() - 1);
        if(counter <3)
        System.out.println("Choose " +(counter+1) + " element of the old tuple");
        if(counter>3)
            System.out.println("Choose updated elements");

        System.out.println("1) Boolean: true");
        System.out.println("2) Boolean: false");
        System.out.println("3) Integer: Enter the value");
        System.out.println("4) String: Enter String Below");
        System.out.println("5) Change operation");


        int serviceNumber = Integer.parseInt(getInput("INT"));
        switch (serviceNumber) {
            case 1:
                sb.append("true").append(";");
                counter++;
                updated(sb, counter);
                break;
            case 2:
                sb.append("false").append(";");
                counter++;
                updated(sb, counter);
                break;
            case 3:
                sb.append(getInput("INT")).append(";");
                counter++;
                updated(sb, counter);
                break;
            case 4:
                sb.append(getInput("STRING")).append(";");
                counter++;
                updated(sb, counter);
                break;
            case 5:
                callMainMenu();
                break;
            default:
                System.out.println("Wrong option, please try again!!!");
                write(sb, counter);
                break;
        }
        return sb.substring(0, sb.toString().length() - 1);
    }

    private static String take(StringBuffer sb, int counter) {
        if (counter == 3) return sb.substring(0, sb.toString().length() - 1);

        System.out.println("Choose the type of data to be taken: ");
        System.out.println("1) Boolean: true");
        System.out.println("2) Boolean: false");
        System.out.println("3) Integer: Enter the value");
        System.out.println("4) String: Enter String Below");
        System.out.println("5) Change operation");


        int serviceNumber = Integer.parseInt(getInput("INT"));
        switch (serviceNumber) {
            case 1:
                sb.append("true").append(";");
                counter++;
                take(sb, counter);
                break;
            case 2:
                sb.append("false").append(";");
                counter++;
                take(sb, counter);
                break;
            case 3:
                sb.append(getInput("INT")).append(";");
                counter++;
                take(sb, counter);
                break;
            case 4:
                sb.append(getInput("STRING")).append(";");
                counter++;
                take(sb, counter);
                break;
            case 5:
                callMainMenu();
                break;
            default:
                System.out.println("Wrong option, please try again!!!");
                take(sb, counter);
                break;
        }
        return sb.substring(0, sb.toString().length() - 1);
    }

    private static String read(StringBuffer sb, int counter) {
        if (counter == 3) return sb.toString();
        System.out.println("Choose the type of data to be read: ");
        System.out.println("1) Boolean: true");
        System.out.println("2) Boolean: false");
        System.out.println("3) Boolean class");
        System.out.println("4) Integer: Enter the value");
        System.out.println("5) Integer class");
        System.out.println("6) String: Enter String Below");
        System.out.println("7) String class");
        System.out.println("8) Change operation");

        int serviceNumber = Integer.parseInt(getInput("INT"));
        switch (serviceNumber) {
            case 1:
                sb.append("##").append("true");
                counter++;
                read(sb, counter);
                break;
            case 2:
                sb.append("##").append("false");
                counter++;
                read(sb, counter);
                break;
            case 3:
                sb.append("##").append("CLASSBOOLEAN");
                counter++;
                read(sb, counter);
                break;
            case 4:
                sb.append("##").append(getInput("INT"));
                counter++;
                read(sb, counter);
                break;
            case 5:
                sb.append("##").append("CLASSINTEGER");
                counter++;
                read(sb, counter);
                break;
            case 6:
                sb.append("##").append(getInput("STRING"));
                counter++;
                read(sb, counter);
                break;
            case 7:
                sb.append("##").append("CLASSSTRING");
                counter++;
                read(sb, counter);
                break;
            case 8:
                callMainMenu();
                break;
            default:
                System.out.println("Wrong option, please try again!!!");
                read(sb, counter);
                break;
        }

        return sb.toString();
    }

    private static void callServer(String data) {
        try {
            Socket clientSocket = new Socket("localhost", 1024);
            PrintStream ps = new PrintStream(clientSocket.getOutputStream());
            ps.println(data);
            System.out.println("--------------------------------");
            System.out.println("waiting for server to respond. ");
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            String output = br.readLine();
            

            System.out.println(output);
            System.out.println("--------------------------------");
            System.out.println("operation Completed.");
            System.out.println();
            clientSocket.close();
            callMainMenu();

        } catch (IOException e) {
            e.printStackTrace();
            callMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
            callMainMenu();
        }
    }

    private static String getInput(String type) {
        String returnValue = "";
        if (type.equals("INT")) {
            int n = reader.nextInt();
            returnValue = String.valueOf(n);
            reader.nextLine();
        } else {
            returnValue = reader.nextLine();
        }
        return returnValue;
    }

}