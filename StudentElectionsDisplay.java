import java.io.*;
import java.net.*;

class StudentElectionsDisplay extends JFrame implements ActionListener{
  StudentElectionsDisplay(PrintWriter pwOut, BufferedReader brIn){
    getContentPane().setLayout(new GridBagLayout());
    Jpanel panelMain = new JPanel();
    GroupLayout layout = new GroupLayout(panelMain);
    pwOut.println("<getElections>");
    String electionsPayload = brIn.readLine();
    if(electionsPayload.contains("<sendElections>")){
      String elections[] = electionsPayload.split(",");
      pnlMain.setLayout(layout);
      layout.setAutoCreateGaps(true);
      layout.setAutoCreateContainerGaps(true);
      layout.setHorizontalGroup();
      layout.setVerticalGroup();
      for(int i = 1; i < elections.size;i++){
        JButton electionBtn = new JButton(data[0]);
        electionBtn.setActionCommand(data[0]);
        electionBtn.addActionListener(this);
      }
    }
    System.out.println("Error");
  }

}
