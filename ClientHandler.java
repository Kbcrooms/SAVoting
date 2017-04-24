import java.io.*;
import java.net.*;
import java.util.ArrayList;

class ClientHandler extends Thread{

    BufferedReader brIn;
    PrintWriter pwOut;
    Server server;
    Socket socket;

    ClientHandler(Socket sock, Server serv){
        try{
            brIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            pwOut = new PrintWriter(sock.getOutputStream(),true);
            server = serv;
            socket = sock;
        }catch(Exception e){
            System.out.println("Error initiating ClientHandler");
        }
    }

    public void run(){
        String line = new String();
        try{
            while (true){
              line = brIn.readLine();
              System.out.println(line);
              String [] data = line.split(",");
              switch (data[0].trim()){
                case "<add>":
                  addUser(data);
                break;
                case "<login>":
                  loginUser(data);
                break;
                case "<election>":
                  createElection(data);
                break;
                case "<createElection>":
                  pwOut.println("<createElection>");
                break;
                case "<createBallot>":
                 pwOut.println("<createBallot>");
                break;
                case "<CreateBallot2>":
                 pwOut.println("<CreateBallot2>");
                break;
                case "<CreateBallot3>":
                 pwOut.println("<CreateBallot3>");
                break;
                case "<CreateBallot4>":
                 pwOut.println("<CreateBallot4>");
                break;

                case "<CreateBallotEnd>":
                  pwOut.println("<CreateBallotEnd>");
                  break;
                case "<getElections>":
                  System.out.println("Get Elections Case");
                  pwOut.println(getElections());
                break;
                case "<getElectionInfo>":
                  pwOut.println(getElectionInfo(data[1].trim()));
                break;
            		case "<certifyElection>":
            		  pwOut.println("<certifyElection>");
            		break;
            		case "<HSOMain>":
            		  pwOut.println("<HSOMain>");
            		break;
            		case "<recountElection>":
            		  pwOut.println("<recountElection>");
            		break;
            		case "<turnoutStatistics>":
            		  pwOut.println("<turnoutStatistics>");
            		break;
            		case "<deleteVote>":
            		  pwOut.println("<deleteVote>");
            		break;
                case "<startBallot>":
                  createBallot(data);
                break;
            		case "<die>" :
                 die();
                break;
                default:
                 pwOut.println("<error>");
              }
            }
        }
        catch(SocketException x){
            System.out.println("socket disconnected");
        }
        catch(Exception e){
            System.out.print("Error: " + line);
            e.printStackTrace();
        }
    }
    private void addUser(String [] data){
        if (data.length == 4){
            if(server.addUser(data[1],data[2],data[3],data[4],data[5],data[6])){
                pwOut.println("<added>");
            }
            else{
                pwOut.println("<error>");
            }
        }
    }
    private void loginUser(String [] data){
        String userType;
        if (data.length == 3){
            userType = server.loginUser(data[1].trim(),data[2].trim());
            pwOut.println(userType);
        }else{
            pwOut.println("<error>");
        }
    }
    private void createElection(String [] data){
      if(data.length == 5){
        pwOut.println(server.createElection(data[1].trim(),data[2].trim(),data[3].trim(),data[4]).trim());
	      pwOut.println("<createdelection>");
      }
      else
        pwOut.println("<error>");
    }
    private String getElections(){
      String electionsPayload = "<sendElections>";
      ArrayList<Election> elections = server.elections;
      for(int i = 0; i< elections.size();i++){
        electionsPayload+= "," + elections.get(i).eName;
      }
      return electionsPayload;
    }
    private String getElectionInfo(String name){
      String electionInfo = "<sendElectionInfo>";
      ArrayList<Election> elections = server.elections;
      Election e = null;
      for(int i = 0; i< elections.size();i++){
        if(elections.get(i).eName.equals(name)){
          e = elections.get(i);
        }
      }
      if(e==null)
        return "error";
      else{
        electionInfo+=","+e.sDate;
        electionInfo+=","+e.eDate;
        return electionInfo;
      }
    }
    private void createBallot(String[] data){
      String username = data[0];
      Ballot ballot = new Ballot();
      for(int i=2;i<data.length;i++){
        if(data[i].equals("true"))
          ballot.eligibility.add(true);
        else
          ballot.eligibility.add(false);
      }
      try{
        String parseLine = brIn.readLine();
        while(!parseLine.equals("<endBallot>")){
          String[] race=parseLine.split(",");
          ballot.raceNames.add(race[0]);
          ArrayList<Candidate> candidates = new ArrayList<Candidate>();
          for(int i = 1; i< race.length;i=i+2){
            //candidates.add(new Candidate(new Student(race[i],race[i+1])));
          }
          ballot.raceCandidates.add(candidates);
          parseLine = brIn.readLine();
        }
      }
      catch(Exception e){

      }
      ArrayList<Election> elections = server.elections;
      for(int i =0; i< elections.size(); i++){
        if(elections.get(i).eComID.equals(username))
          elections.get(i).ballot = ballot;
        System.out.println("Added ballot to election");
      }
    }
    private void die(){
        try{
            socket.close();
            server.die();
        }catch(Exception e){
            //shutting down
        }
        System.exit(0);
    }
}
