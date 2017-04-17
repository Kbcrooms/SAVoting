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

class createBallot1 extends JFrame implements ActionListener{
	//First Page for creating a  ballot
	//How they would like results to be returned Raw Count, Winner, Turnout Statistics
	//voter Eligibility Rank, College, Club/Organizations

	ArrayList<JCheckBox> btnCheckResults;
  	ArrayList<JCheckBox> btnCheckVoterElegibility;
  	PrintWriter pwOut;
  	BufferedReader brIn;
	Socket sock;

	createBallot1(){}
	createBallot1(PrintWriter pwOut, BufferedReader brIn){
		this.pwOut = pwOut;
    		this.brIn = brIn;
  		JPanel pnlMain = new JPanel();
  		GroupLayout layout = new GroupLayout(pnlMain);
  		JPanel pnlResultButtons = new JPanel();
  		JPanel pnlElegibilityButtons = new JPanel();
  		Color bgColor = new Color(176,196,222);
		pnlResultButtons.setBorder(BorderFactory.createLineBorder(Color.black));
  		pnlElegibilityButtons.setBorder(BorderFactory.createLineBorder(Color.black));

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
	
  		JButton btnCreateBallot = new JButton("Continue");
  		btnCreateBallot.setActionCommand("continue");
  		btnCreateBallot.addActionListener(this);

  		pnlMain.setLayout(layout);
  		layout.setAutoCreateGaps(true);
    		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
  	      				.addGroup(layout.createSequentialGroup()
  	      					.addGroup(layout.createParallelGroup()	
							.addComponent(pnlResultButtons)
							.addComponent(pnlElegibilityButtons)
							.addComponent(btnCreateBallot))))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
							.addComponent(pnlResultButtons)
							.addComponent(pnlElegibilityButtons)
							.addComponent(btnCreateBallot))))
		);


		setSize(650,300);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Create An Ballot");
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
		run();
	}

	private void run(){
		try{
			sock = new Socket("127.0.0.2", 50000);
			brIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pwOut = new PrintWriter(sock.getOutputStream(),true);
			while(true){
				String strIn = brIn.readLine();
				if(strIn.equals("<continueCreateBallot1>")){
					setVisible(false);
					new createBallot2(pwOut, brIn);				
				}else{
					JOptionPane.showMessageDialog(this,strIn, "Error",JOptionPane.PLAIN_MESSAGE);
				}
			}		
		}catch(IOException e){
			System.out.println("IOException");
		}catch(NullPointerException npe){
			System.out.println("null");		
		}


	}

	public void actionPerformed(ActionEvent e){
		if(!sock.isClosed()){
			switch(e.getActionCommand()){
				case "continue":
					System.out.println("<continueCreateBallot1>");
					pwOut.println("<continueCreateBallot1>");
				break;
			}
		}else{
			JOptionPane.showMessageDialog(this, "Socket is Closed", "Error", JOptionPane.ERROR_MESSAGE);		
		}
	}

	public static void main(String args[]){
		new createBallot1();
	}
}

