import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

class StudentElectionsDisplay extends JFrame implements ActionListener{
  JPanel panelMain;
  PrintWriter pwOut;
  BufferedReader brIn;
  Socket sock;
  StudentElectionsDisplay(PrintWriter pwOut, BufferedReader brIn){
    JPanel panelMain = new JPanel();
    getContentPane().add(panelMain,BorderLayout.CENTER);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(640,480);
    setVisible(true);
    this.pwOut = pwOut;
    this.brIn = brIn;
    run();
  }
  private void run(){
    try{
      pwOut.println("<getElections>");
      System.out.println("Waiting");
      String electionsPayload = brIn.readLine();
      System.out.println(electionsPayload);
      if(electionsPayload.contains("<sendElections>")){
        String elections[] = electionsPayload.split(",");

        for(int i = 1; i < elections.length;i++){
          JButton electionBtn = new JButton(elections[i]);
          electionBtn.setActionCommand(elections[i]);
          electionBtn.addActionListener(this);
          panelMain.add(electionBtn);
        }
      }
      else{
        System.out.println("Error");
      }
    }
    catch(Exception e){

    }
  }
  public void actionPerformed(ActionEvent e){
  }
  public static void main(String args[]){
  }

}
