import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

class StudentVotingDisplay extends JFrame implements ActionListener{
  JPanel panelMain;
  PrintWriter pwOut;
  BufferedReader brIn;
  ArrayList<ButtonGroup> raceChoices;
  String eName;
  StudentVotingDisplay(PrintWriter pwOut, BufferedReader brIn, String eName){
    this.eName = eName;
    raceChoices = new ArrayList<ButtonGroup>();
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
      String parseLine = brIn.readLine();
      System.out.println(parseLine);
      if(parseLine.contains("<sendElectionInfoStart>")){
        String data[] = parseLine.split(",");
        try{
          parseLine = brIn.readLine();
          while(!parseLine.equals("<sendElectionInfoEnd>")){
            String[] race=parseLine.split(",");
            panelMain.add(new JLabel(race[1]));
            ButtonGroup candidates = new ButtonGroup();
            for(int i = 2; i< race.length;i=i+2){
              JButton candidateBtn = new JButton(race[i+1]);
              candidateBtn.setActionCommand(","+race[1]+","+race[i]);
              candidates.add(candidateBtn);
              candidateBtn.addActionListener(this);
              panelMain.add(candidateBtn);
            }
            raceChoices.add(candidates);
            parseLine = brIn.readLine();
          }
        }
        catch(Exception e){

        }

      }
      else
        System.out.println("Error");
    }
    catch(Exception e){

    }
    JButton submitBtn = new JButton("Submit");
    submitBtn.setActionCommand("submit");
    submitBtn.addActionListener(this);
    panelMain.add(submitBtn);
    setVisible(true);
  }
  public void actionPerformed(ActionEvent e){
    if(e.getActionCommand().equals("submit")){
      pwOut.println();
      for(int i=0;i<raceChoices.size();i++){
        pwOut.println("<vote>"+raceChoices.get(i).getSelection().getActionCommand());
        System.out.println("<vote>"+raceChoices.get(i).getSelection().getActionCommand());
      }
    }
  }
}
