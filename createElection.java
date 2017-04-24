import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class createElection extends JFrame implements ActionListener{
  //Create an election
  //Start Date/EndDate/Election Commisioner's ID
  //Checkboxes:
  //Results: Raw Count,Winner,Turnout Stats
  //Voter Elegibilty:Rank,College,Club/Organization
  JTextField txtElectionTitle;
  JTextField txtStartDate;
  JTextField txtEndDate;
  JTextField txtElectionComID;
  PrintWriter pwOut;
  BufferedReader brIn;
  private final String dateRegex = "([0-9]{2})/([0-9]{2})/([0-9]{4})";
  createElection(){}
  createElection(PrintWriter pwOut,BufferedReader brIn){
    this.pwOut = pwOut;
    this.brIn = brIn;
  	JPanel pnlMain = new JPanel();
  	GroupLayout layout = new GroupLayout(pnlMain);
  	Color bgColor = new Color(176,196,222);
  	txtElectionTitle = new JTextField(20);
  	txtStartDate = new JTextField(20);
  	txtEndDate = new JTextField(10);
  	txtElectionComID = new JTextField(10);

  	JLabel lElectionTitle = new JLabel("Election Title");
  	JLabel lStartDate = new JLabel("Start Date (Ex: 05/30/2017)");
  	JLabel lEndDate = new JLabel("End Date (Ex: 05/31/2017)");
  	JLabel lElectionComID = new JLabel("Election Commisioner ID");

  	JButton btnCreateElection = new JButton("Create Election");
  	btnCreateElection.setActionCommand("create");
  	btnCreateElection.addActionListener(this);


  	pnlMain.setLayout(layout);
  	layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

  	layout.setHorizontalGroup(
  	    layout.createSequentialGroup()
  	      	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
  	      		.addGroup(layout.createSequentialGroup()
  	      			.addGroup(layout.createParallelGroup()
  						  .addComponent(lElectionTitle)
  						  .addComponent(txtElectionTitle)
  						  .addComponent(lElectionComID)
  				  		  .addComponent(txtElectionComID))
  					.addGroup(layout.createParallelGroup()
  						  .addComponent(lStartDate)
  						  .addComponent(txtStartDate)
  						  .addComponent(lEndDate)
  						  .addComponent(txtEndDate)))
  			    .addComponent(btnCreateElection))
  				  );

  	layout.setVerticalGroup(
  		layout.createParallelGroup()
  			.addGroup(layout.createSequentialGroup()
  				.addGroup(layout.createParallelGroup()
  					.addGroup(layout.createSequentialGroup()
  						  .addComponent(lElectionTitle)
  						  .addComponent(txtElectionTitle)
  						  .addComponent(lElectionComID)
  				  		  .addComponent(txtElectionComID))
  					.addGroup(layout.createSequentialGroup()
  						  .addComponent(lStartDate)
  						  .addComponent(txtStartDate)
  						  .addComponent(lEndDate)
  						  .addComponent(txtEndDate)))
  				.addComponent(btnCreateElection))
  				);
        setSize(650,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create An Election");
        pnlMain.setBackground(bgColor);;
        getContentPane().add(pnlMain);
        //this centers the window in the screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        setLocation(x, y);
        //make sure you can actually see it, starts off false
  	    setVisible(true);
        try{
          while(true){
            String strIn = brIn.readLine();
            if(strIn.startsWith("<createdelection>")){
              JOptionPane.showMessageDialog(this,"Successful","Successfully Created Election",JOptionPane.PLAIN_MESSAGE);
	      setVisible(false);
	      new HSOMain(pwOut,brIn);
            }
            else{
              JOptionPane.showMessageDialog(this,strIn,"Error Creating Election",JOptionPane.PLAIN_MESSAGE);
            }
          }
        }
        catch(Exception e){
          System.out.println(e);
        }
    }


    public void actionPerformed(ActionEvent e){
      String sDate = txtStartDate.getText();
      String eDate = txtEndDate.getText();
      Pattern  datePattern = Pattern.compile(dateRegex);
      Matcher sDateMatcher = datePattern.matcher(sDate);
      Matcher dDateMatcher = datePattern.matcher(eDate);
      if(sDateMatcher.matches()&&dDateMatcher.matches()){
        switch(e.getActionCommand()){
          case "create":
            System.out.println("<election>," + txtElectionTitle.getText()+","+txtElectionComID.getText()+","+txtStartDate.getText()+","+txtEndDate.getText());
            pwOut.println("<election>," + txtElectionTitle.getText()+","+txtElectionComID.getText()+","+txtStartDate.getText()+","+txtEndDate.getText());
          break;

        }
      }
      else
        JOptionPane.showMessageDialog(this,"Invalid Date","Error Creating Election",JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String args[]){
	     new createElection();
    }

}
