import java.net.*;
import java.util.Hashtable;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.io.*;

class Server extends Thread{

    Hashtable<String,String> admins;
    Hashtable<String,String> students;
    Hashtable<String,Election> elections;
    ServerSocket ss;

    File f;
    FileOutputStream fileOut = null;
    FileInputStream fileIn = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;


    Server(){
        admins = new Hashtable<String,String>();
        students = new Hashtable<String,String>();
        elections = new Hashtable<String,Election>();
    }

    public void run(){
        try{
    	    //Reading Stored Serialized Hashtables,if they exist.
    	    f = new File("storedLoginInfo.bin");
    	    if(f.isFile() && f.canRead()){
        		FileInputStream fileIn = new FileInputStream(f);
        		ObjectInputStream in = new ObjectInputStream(fileIn);
        		admins = (Hashtable<String,String>)in.readObject();
        		students = (Hashtable<String,String>)in.readObject();
    	    }
          //Generates Test Users
          admins.put("testadmin","123");
          students.put("testuser","123");
    	    ss = new ServerSocket(50000);   //high port numbers aren't normally dedicated
    	    System.out.println("Server Started");
    	    while(true){
        		Socket client = ss.accept();
        		new ClientHandler(client,this).start();
        		System.out.println("Started ClientHandler");
    	    }
        }
        catch(SocketException f){
            System.out.println("null");

        }
        catch(IOException e){
            System.out.print("Server IOException");
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
	         System.out.print("Error reading stored data!");
	      }
      }
    public void die(){
        try{
	    //Saveing Hashtables through serialization
	    fileOut = new FileOutputStream("storedLoginInfo.bin");
	    out = new ObjectOutputStream(fileOut);
	    out.writeObject(admins);
	    out.writeObject(students);
	    out.flush();
	    out.close();
            ss.close();
        }catch(IOException e){
            //don't care, shutting down
        }
        System.exit(0);
    }

    public boolean addUser(String strUser, String strPass, String authority){
        if(authority.equals("<admin>") && !admins.containsKey(strUser)){
            admins.put(strUser,strPass);
        }else if(authority.equals("<student>") && !students.containsKey(strUser)){
            students.put(strUser,strPass);
        }else{
            return false;
        }
	return true;
    }

    public String loginUser(String strUser, String strPass){
        String tempPass;
        if(admins.containsKey(strUser)){
            tempPass = admins.get(strUser);
            if (tempPass != null && tempPass.equals(strPass)){
                return "<admin>";
            }
        }else if(students.containsKey(strUser)){
            tempPass = students.get(strUser);
            if (tempPass != null && tempPass.equals(strPass)){
                return "<student>";
            }
        }
        return "<invalid>";
    }
    public String createElection(String eName, String eComID, String eStart, String eEnd){
      if(elections.containsKey(eName)){
        return "<dupelection>";
      }
      elections.put(eName,new Election(eComID,eStart,eEnd));
      return "<createdelection>";
    }
    public static void main(String args[]){
        new Server().start();
    }

}
