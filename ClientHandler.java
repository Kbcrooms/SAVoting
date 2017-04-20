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
                case "<getElections>":
                  getElections();
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
      }
      else
        pwOut.println("<error>");
    }
    private void getElections(){
      String electionsPayload = "<sendElections>";
      ArrayList<Election> elections = server.elections;
      for(int i = 0; i< elections.size();i++){
        electionsPayload+= "," + elections.get(i).eName;
      }
      pwOut.println(electionsPayload);
    }
    private void createBallot(){

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
