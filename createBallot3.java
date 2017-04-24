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

class createBallot3 extends JFrame implements ActionListener{
	ButtonGroup btnGroupTypeOfRace;
	PrintWriter pwOut;
	BufferedReader brIn;
	//String numberOfRaces;
	Socket sock;
	private final String numCandidates = "[0-9]+";
	JRadioButton writeIn;
	JRadioButton candidate;
	JTextField txtNumCandidates;
	JTextField txtNameOfRace;
	String myNumCandidates;
	Ballot ballot;
	

	createBallot3(){}
	createBallot3(PrintWriter pwOut, BufferedReader brIn, Ballot ballot){
		this.pwOut = pwOut;
		this.brIn = brIn;
		this.ballot = ballot;
		//this.numberOfRaces = numberOfRaces;
		JPanel pnlMain = new JPanel();
		GroupLayout layout = new GroupLayout(pnlMain);
		JPanel pnlButtons = new JPanel();
		Color bgColor = new Color(176,196,222);

		txtNameOfRace = new JTextField(20);
		JLabel lNameOfRace = new JLabel("Name of Race");
	    txtNumCandidates = new JTextField(20);
		JLabel lNumCandidates = new JLabel("Number of Candidates");


		JLabel lblTypeOfElection = new JLabel("Type of Election:    ");
		btnGroupTypeOfRace = new ButtonGroup();
		writeIn = new JRadioButton("Write In Election (No Candidates)");
		writeIn.setActionCommand("writeIn");
		btnGroupTypeOfRace.add(writeIn);
		candidate = new JRadioButton("Candidate Election");
		candidate.setActionCommand("candidate");
		btnGroupTypeOfRace.add(candidate);
		pnlButtons.add(lblTypeOfElection);
		pnlButtons.add(writeIn);
		pnlButtons.add(candidate);

		JButton btnNext = new JButton("Next");
		btnNext.setActionCommand("Next");
		btnNext.addActionListener(this);

		pnlMain.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lNameOfRace)
					.addComponent(txtNameOfRace)
					.addComponent(pnlButtons)
					.addComponent(lNumCandidates)
					.addComponent(txtNumCandidates)
					.addComponent(btnNext))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(lNameOfRace)
					.addComponent(txtNameOfRace)
					.addComponent(pnlButtons)
					.addComponent(lNumCandidates)
					.addComponent(txtNumCandidates)
					.addComponent(btnNext))

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
		run();		

	}
	
	private void run(){
		try{
			sock = new Socket("127.0.0.2", 50000);
			brIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pwOut = new PrintWriter(sock.getOutputStream(),true);
			while(true){
				String strIn = brIn.readLine();
				if(strIn.equals("<CreateBallot3>")){
					setVisible(false);
					new createBallot3(pwOut, brIn, ballot);		
				}else if(strIn.equals("<CreateBallot4>")){
					setVisible(false);
					new createBallot4(pwOut, brIn, ballot); //, myNumCandidates);
				}else if(strIn.equals("<CreateBallotEnd>")){
					setVisible(false);
					//new createBallotEnd(pwOut, brIn, ballot);
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
				case "Next":
					if(btnGroupTypeOfRace.getSelection().getActionCommand().equals("writeIn")){
					//need to set Candidate Information to NULL
						ballot.raceNames.add(txtNameOfRace.getText());
						ballot.numOfCandidates.add(-1);
						ballot.numOfRaces--;
						if(ballot.numOfRaces >= 0){
							System.out.println("<CreateBallot3>");
							pwOut.println("<CreateBallot3>");	
						}else{
							//Goes to end page
							//End page should contain all of the information  concerning the Ballot
							System.out.println("<CreateBallotEnd>");
							pwOut.println("<CreateBallotEnd>");
						}
					}
					if(btnGroupTypeOfRace.getSelection().getActionCommand().equals("candidate")){
						myNumCandidates = txtNumCandidates.getText();
						ballot.raceNames.add(txtNameOfRace.getText());
						Pattern numPattern = Pattern.compile(numCandidates);
						Matcher numElectionMatcher = numPattern.matcher(myNumCandidates);
						if(numElectionMatcher.matches()){
							ballot.numOfCandidates.add(Integer.valueOf(myNumCandidates));
							ballot.currentNumberOfCandidates = Integer.valueOf(myNumCandidates);
							ballot.numOfRaces--;
							if(ballot.numOfRaces >= 0){
								System.out.println("<CreateBallot4>");
								pwOut.println("<CreateBallot4>");
							}else{
							//Goes to end page
							//End page should contain all of the information concerning the Ballot
								System.out.println("<CreateBallotEnd>");
								pwOut.println("<CreateBallotEnd>");
							}
						}else{
							JOptionPane.showMessageDialog(this, "Invalid Number of Candidates", "Error with Number of Races", JOptionPane.PLAIN_MESSAGE); 		
						}
					}
				break;
			}
		}else{
			JOptionPane.showMessageDialog(this, "Socket is Closed", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
		


	public static void main(String args[]){
		new createBallot3();
	}
}
