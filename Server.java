import java.net.*;
import java.util.Hashtable;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.io.*;
import java.util.ArrayList;

class Server extends Thread{

    Hashtable<String,String> admins;
    Hashtable<String,Student> students;
    Hashtable<String,Student> electionCom;
    ArrayList<Election> elections;
    Election testElection;
    ServerSocket ss;
    Student testCan;

    File f;
    FileOutputStream fileOut = null;
    FileInputStream fileIn = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    File electionsFile;

    Server(){
        admins = new Hashtable<String,String>();
        students = new Hashtable<String,Student>();
	testCan = new Student("testCandidate","password");
	      electionCom = new Hashtable<String,Student>();
        elections = new ArrayList<Election>();
        testElection = new Election();
        electionsFile = new File("elections.bin");
    }

    public void run(){
        try{
    	    //Reading Stored Serialized Hashtables,if they exist.
    	    f = new File("storedLoginInfo.bin");
    	    if(f.isFile() && f.canRead()){
        		FileInputStream fileIn = new FileInputStream(f);
        		ObjectInputStream in = new ObjectInputStream(fileIn);
        		admins = (Hashtable<String,String>)in.readObject();
        		students = (Hashtable<String,Student>)in.readObject();
    	    }
          //Generates Test Users
    	    admins.put("testadmin","123");
    	    students.put("testuser",new Student("testuser","123","Computer Science", "Junior", "Statler College of Mineral Resources"));
    	    electionCom.put("testc", new Student("testc","password","Computer Science", "Senior", "Statler College of Mineral Resources"));
    	    electionCom.put("testc1", new Student("testc","123","Computer Science", "Senior", "Statler College of Mineral Resources"));
          //Generates Test Election
          elections.add(new Election("testElection","testc","Today","Tomorrow"));
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
        //Saving Hashtables through serialization
        fileOut = new FileOutputStream("storedLoginInfo.bin");
        out = new ObjectOutputStream(fileOut);
        out.writeObject(admins);
        out.writeObject(students);
        out.flush();
        out.close();
        ss.close();
      }
      catch(IOException e){
            //don't care, shutting down
      }
      System.exit(0);
    }
    public boolean addUser(String strUser, String strPass, String authority, String major, String rank, String college){
        if(authority.equals("<admin>") && !admins.containsKey(strUser)){
            admins.put(strUser,strPass);
        }
        else if(authority.equals("<student>") && !students.containsKey(strUser)){
            students.put(strUser,new Student(strUser,strPass,major,rank,college));
        }
        else{
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
        }
        else if(electionCom.containsKey(strUser)){
	        tempPass = electionCom.get(strUser).password;
          if(tempPass != null && tempPass.equals(strPass)){
		          return "<electionCom>," +strUser;
	        }
        }
        else if(students.containsKey(strUser)){
          tempPass = students.get(strUser).password;
          if (tempPass != null && tempPass.equals(strPass)){
            return "<student>";
          }
        }else if(testCan.username.equals(strUser)){
	    return "<candidate>";
	}
        return "<invalid>";
    }
    public String createElection(String eName, String eComID, String eStart, String eEnd){
      for(int i=0; i<elections.size();i++){
        if(elections.get(i).eName.equals(eName))
          return "<dupelection>";
      }
      if(electionCom.containsKey(eComID)){
        elections.add(new Election(eName,eComID,eStart,eEnd));
        return "<createdelection>";
      }
      return "<invalidcomid>";
    }
    public static void main(String args[]){
        new Server().start();
    }

}
