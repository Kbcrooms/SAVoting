import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

class StudentVotingDisplay extends JFrame implements ActionListener{
  JPanel panelMain;
  PrintWriter pwOut;
  BufferedReader brIn;
  StudentVotingDisplay(PrintWriter pwOut, BufferedReader brIn, String eName){
    JPanel panelMain = new JPanel();
    getContentPane().add(panelMain,BorderLayout.CENTER);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(640,480);
    //int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    //int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
    this.pwOut = pwOut;
    this.brIn = brIn;
    pwOut.println("<getElectionInfo>,"+eName);
    try{
      String electionInfo = brIn.readLine();
      System.out.println(electionInfo);
      if(electionInfo.contains("<sendElectionInfo>")){
        String data[] = electionInfo.split(",");

      }
      else
        System.out.println("Error");
    }
    catch(Exception e){

    }
  }
  public void actionPerformed(ActionEvent e){

  }
}
