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

class createBallot4 extends JFrame implements ActionListener{
	PrintWriter pwOut;
	BufferedReader brIn;
	private final String numCandidates = "[0-9]+";
	//String numberOfRaces;
	Socket sock;
	//String numberCandidates;
	Ballot ballot;
	ArrayList<Candidate> currentRaceCandidates;
	JTextField txtCandidateName[];
	JTextField txtCandidateID[];
	String username;
	createBallot4(){}
	createBallot4(PrintWriter pwOut, BufferedReader brIn, Ballot ballot,String username){
		this.pwOut = pwOut;
		this.brIn = brIn;
		this.ballot = ballot;
		this.username = username;
		currentRaceCandidates = new ArrayList<Candidate>();
		//this.numberOfRaces = numberOfRaces;
		//this.numberCandidates = numberCandidates;
		JPanel pnlMain = new JPanel();
		GroupLayout layout = new GroupLayout(pnlMain);
		Color bgColor = new Color(176,196,222);


		JFrame frame = new JFrame("Input Candidate Information");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel container = new JPanel();
		JPanel pTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lTitle = new JLabel("Add Candidates to the Race");
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		pTitle.add(lTitle);
		frame.add(container);
		container.add(pTitle);
		pTitle.setBackground(bgColor);

		int i = 0;
		JPanel arrayPanels[] = new JPanel[ballot.currentNumberOfCandidates];
		txtCandidateName = new JTextField[ballot.currentNumberOfCandidates];
		txtCandidateID = new JTextField[ballot.currentNumberOfCandidates];

	while(i<ballot.currentNumberOfCandidates){
			arrayPanels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));

			arrayPanels[i].add(new JLabel("Candidate Name"));
			txtCandidateName[i] = new JTextField(20);
			arrayPanels[i].add(txtCandidateName[i]);

			arrayPanels[i].add(new JLabel("Candidate Student ID"));
			txtCandidateID[i] = new JTextField(20);
			arrayPanels[i].add(txtCandidateID[i]);

			container.add(arrayPanels[i]);
			arrayPanels[i].setBackground(bgColor);
			++i;
		}
		JButton btnNext = new JButton("Next");
		btnNext.setActionCommand("Next");
		btnNext.addActionListener(this);
		container.add(btnNext);

		frame.setSize(800,500);
		frame.setVisible(true);
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
					new createBallot3(pwOut, brIn, ballot,username);
				}else if(strIn.equals("<CreateBallotEnd>")){
					setVisible(false);
					new createBallotEnd(pwOut,brIn,ballot,username);
				}else{
					JOptionPane.showMessageDialog(this,strIn, "Error1",JOptionPane.PLAIN_MESSAGE);
				}
			}
		}catch(IOException e){
			System.out.println("IOException");
		}catch(NullPointerException npe){
			System.out.println("null");
		}
	}

	public void actionPerformed(ActionEvent e){
		Candidate current;
		Student temp;
		switch(e.getActionCommand()){
			case "Next":
				for(int i = 0; i < ballot.currentNumberOfCandidates; i++){
					temp = new Student(txtCandidateName[i].getText(), txtCandidateID[i].getText());
					current = new Candidate(temp);
					currentRaceCandidates.add(current);
				}
				ballot.raceCandidates.add(currentRaceCandidates);
				if(ballot.numOfRaces > 0){
					System.out.println("<CreateBallot3>");
					pwOut.println("<CreateBallot3>");
				}else{
					//Goes to end page
					//End page should contain all of the information concerning the Ballot
					System.out.println("<CreateBallotEnd>");
					pwOut.println("<CreateBallotEnd>");
				}

			break;
		}

	}

	public static void main(String args[]){
		new createBallot4();
	}

}
