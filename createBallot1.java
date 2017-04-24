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
	JPanel pnlResultButtons;
	JPanel pnlElegibilityButtons;
	Ballot ballot;
	

	createBallot1(){}
	createBallot1(PrintWriter pwOut, BufferedReader brIn){
		this.pwOut = pwOut;
    	this.brIn = brIn;
  		JPanel pnlMain = new JPanel();
  		GroupLayout layout = new GroupLayout(pnlMain);
  		pnlResultButtons = new JPanel();
  		pnlElegibilityButtons = new JPanel();
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
	  	JCheckBox chkRank1 = new JCheckBox("Rank: Freshman");
	  	chkRank1.setActionCommand("Rank1");
		JCheckBox chkRank2 = new JCheckBox("Rank: Sophomore");
	  	chkRank2.setActionCommand("Rank2");
		JCheckBox chkRank3 = new JCheckBox("Rank: Junior");
	  	chkRank3.setActionCommand("Rank3");
		JCheckBox chkRank4 = new JCheckBox("Rank: Senior");
	  	chkRank4.setActionCommand("Rank4");
		JCheckBox chkRank5 = new JCheckBox("Rank: Graduate");
	  	chkRank5.setActionCommand("Rank5");
		JCheckBox chkRank6 = new JCheckBox("Rank: Professional");
	  	chkRank6.setActionCommand("Rank6");
	  	JCheckBox chkCollege1 = new JCheckBox("College: Engineering");
	  	chkCollege1.setActionCommand("College1");
		JCheckBox chkCollege2 = new JCheckBox("College: Medical");
	  	chkCollege2.setActionCommand("College2");
	  	JCheckBox chkClub = new JCheckBox("Club/Organization");
	  	chkClub.setActionCommand("Club");

	  	chkRank1.setBackground(bgColor);
	  	chkRank5.setBackground(bgColor);
	  	chkRank4.setBackground(bgColor);
	  	chkRank3.setBackground(bgColor);
	  	chkRank2.setBackground(bgColor);
	  	chkCollege1.setBackground(bgColor);
		  chkCollege2.setBackground(bgColor);
		  chkClub.setBackground(bgColor);

	  	btnCheckVoterElegibility = new ArrayList<JCheckBox>();
	  	btnCheckVoterElegibility.add(chkRank1);
		  btnCheckVoterElegibility.add(chkRank2);
		  btnCheckVoterElegibility.add(chkRank3);
		  btnCheckVoterElegibility.add(chkRank4);
		  btnCheckVoterElegibility.add(chkRank5);
		  btnCheckVoterElegibility.add(chkRank6);	  	
		  btnCheckVoterElegibility.add(chkCollege1);		
		  btnCheckVoterElegibility.add(chkCollege2);
	  	btnCheckVoterElegibility.add(chkClub);

	    pnlElegibilityButtons.add(lblVoterElegibility);
	  	pnlElegibilityButtons.add(chkRank1);
	  	pnlElegibilityButtons.add(chkRank2);
	  	pnlElegibilityButtons.add(chkRank3);
	  	pnlElegibilityButtons.add(chkRank4);
	  	pnlElegibilityButtons.add(chkRank5);
	  	pnlElegibilityButtons.add(chkCollege1);
	  	pnlElegibilityButtons.add(chkCollege2);
	  	pnlElegibilityButtons.add(chkClub);
	
  		JButton btnCreateBallot = new JButton("Continue");
  		btnCreateBallot.setActionCommand("continue");
  		btnCreateBallot.addActionListener(this);

  		pnlMain.setLayout(layout);
  		layout.setAutoCreateGaps(true);
    		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
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


		setSize(1450,300);
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
				if(strIn.equals("<CreateBallot2>")){
					setVisible(false);
					new createBallot2(pwOut, brIn, createBallotObj());				
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
					System.out.println("<CreateBallot2>");
					pwOut.println("<CreateBallot2>");
				break;
			}
		}else{
			JOptionPane.showMessageDialog(this, "Socket is Closed", "Error", JOptionPane.ERROR_MESSAGE);		
		}
	}

	public Ballot createBallotObj(){
		Iterator<JCheckBox> iterCheckButtonsR = btnCheckResults.iterator();
		Iterator<JCheckBox> iterCheckButtonsE = btnCheckVoterElegibility.iterator();
		ArrayList<Boolean> resultDisplayed = new ArrayList<Boolean>();
		ArrayList<Boolean> eligibility = new ArrayList<Boolean>();

		while(iterCheckButtonsR.hasNext()){
	        JCheckBox nextBox = iterCheckButtonsR.next();
	        if(nextBox.isSelected()){
	            resultDisplayed.add(true);               
	        }else{
	        	resultDisplayed.add(false);
	        }
	    }

	    while(iterCheckButtonsE.hasNext()){
	        JCheckBox nextBox = iterCheckButtonsE.next();
	        if(nextBox.isSelected()){
	            eligibility.add(true);               
	        }else{
	        	eligibility.add(false);
	        }
	    }

	    Ballot ballot = new Ballot();
	    ballot.results = resultDisplayed;
	    ballot.eligibility = eligibility;
	    return ballot;

	}
}

