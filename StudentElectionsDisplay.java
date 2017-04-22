import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

class StudentElectionsDisplay extends JFrame implements ActionListener{
  StudentElectionsDisplay(PrintWriter pwOut, BufferedReader brIn){
    getContentPane().setLayout(new GridBagLayout());
    JPanel panelMain = new JPanel();
    setLayout(new BorderLayout());
    panelMain.setLayout(new FlowLayout());
    pwOut.println("<getElections>");
    try{
      String electionsPayload = brIn.readLine();
      if(electionsPayload.contains("<sendElections>")){
        String elections[] = electionsPayload.split(",");
        add(panelMain,BorderLayout.CENTER);
        setSize(640,480);
        setVisible(true);
        for(int i = 1; i < elections.length;i++){
          JButton electionBtn = new JButton(elections[i]);
          electionBtn.setActionCommand(elections[i]);
          electionBtn.addActionListener(this);
          add(electionBtn, BorderLayout.SOUTH);
        }
      }
    }
    catch(Exception e){

    }
    System.out.println("Error");
  }
  public void actionPerformed(ActionEvent e){

  }
}
