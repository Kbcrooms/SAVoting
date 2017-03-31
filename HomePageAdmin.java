import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

class HomePageAdmin extends JFrame implements ActionListener{

    //Create an election
    //Start Date/EndDate/Election Commisioner's ID
    //Checkboxes:
          //Results: Raw Count,Winner,Turnout Stats
          //Voter Elegibilty:Rank,College,Club/Organization

    JTextField txtElectionTitle;
    JTextField txtStartDate;
    JTextField txtEndDate;
    JTextField txtElectionComID;
    ArrayList<JCheckBox> btnCheckResults;
    ArrayList<JCheckBox> btnCheckVoterElegibility;
    
    HomePageAdmin(){
	JPanel pnlMain = new JPanel();
	GroupLayout layout = new GroupLayout(pnlMain);
	JPanel pnlResultButtons = new JPanel();
	JPanel pnlElegibilityButtons = new JPanel();
	Color bgColor = new Color(176,196,222);
	pnlResultButtons.setBorder(BorderFactory.createLineBorder(Color.black));
	pnlElegibilityButtons.setBorder(BorderFactory.createLineBorder(Color.black));

	txtElectionTitle = new JTextField(20);
	txtStartDate = new JTextField(20);
	txtEndDate = new JTextField(10);
	txtElectionComID = new JTextField(10);

	JLabel lElectionTitle = new JLabel("Election Title");
	JLabel lStartDate = new JLabel("Start Date");
	JLabel lEndDate = new JLabel("End Date");
	JLabel lElectionComID = new JLabel("Election Commisioner ID");

	JLabel lblResults = new JLabel("Results Displayed:   ");
	lblResults.setFont(new Font(lblResults.getFont().getName(), Font.PLAIN, 20));
	JCheckBox chkRawCount = new JCheckBox("Raw Count");
	chkRawCount.setActionCommand("Raw Count");
	JCheckBox chkWinner = new JCheckBox("Winner");
	chkWinner.setActionCommand("Winner");
	JCheckBox chkTurnoutStats = new JCheckBox("Turnout Statistics");
	chkTurnoutStats.setActionCommand("Turnout Statistics");
	
	chkRawCount.setBackground(bgColor);
	chkWinner.setBackground(bgColor);
	chkTurnoutStats.setBackground(bgColor);

	btnCheckResults = new ArrayList<JCheckBox>();
	btnCheckResults.add(chkRawCount);
	btnCheckResults.add(chkWinner);
	btnCheckResults.add(chkTurnoutStats);

	pnlResultButtons.add(lblResults);
	pnlResultButtons.add(chkRawCount);
	pnlResultButtons.add(chkWinner);
	pnlResultButtons.add(chkTurnoutStats);

	JLabel lblVoterElegibility = new JLabel("Voter Eligibility:   ");
	lblVoterElegibility.setFont(new Font(lblVoterElegibility.getFont().getName(), Font.PLAIN, 20));
	JCheckBox chkRank = new JCheckBox("Rank");
	chkRank.setActionCommand("Rank");
	JCheckBox chkCollege = new JCheckBox("College");
	chkCollege.setActionCommand("College");
	JCheckBox chkClub = new JCheckBox("Club/Organization");
	chkClub.setActionCommand("Club");
	
	chkRank.setBackground(bgColor);
	chkCollege.setBackground(bgColor);
	chkClub.setBackground(bgColor);

	btnCheckVoterElegibility = new ArrayList<JCheckBox>();
	btnCheckVoterElegibility.add(chkRank);
	btnCheckVoterElegibility.add(chkCollege);
	btnCheckVoterElegibility.add(chkClub);

    pnlElegibilityButtons.add(lblVoterElegibility);
	pnlElegibilityButtons.add(chkRank);
	pnlElegibilityButtons.add(chkCollege);
	pnlElegibilityButtons.add(chkClub);

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
				.addComponent(pnlResultButtons)
				.addComponent(pnlElegibilityButtons)
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
				.addComponent(pnlResultButtons)
				.addComponent(pnlElegibilityButtons)
				.addComponent(btnCreateElection))
				);

	setSize(650,300);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setTitle("Create An Election");

	pnlMain.setBackground(bgColor);
	pnlResultButtons.setBackground(bgColor);
	pnlElegibilityButtons.setBackground(bgColor);
	getContentPane().add(pnlMain);

	//this centers the window in the screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        setLocation(x, y);
        
        //make sure you can actually see it, starts off false
	setVisible(true); 
    }


    public void actionPerformed(ActionEvent e){
       
    }

    public static void main(String args[]){
	new HomePageAdmin();
    }

}
