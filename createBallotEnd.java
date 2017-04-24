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

class createBallotEnd extends JFrame implements ActionListener{

	PrintWriter pwOut;
	BufferedReader brIn;
	Ballot ballot;
	Socket sock;
	String username;
	createBallotEnd(){}
	createBallotEnd(PrintWriter pwOut, BufferedReader brIn, Ballot ballot,String username){
		this.username = username;
		System.out.println("End:"+username);
		JPanel pnlMain = new JPanel();
		GroupLayout layout = new GroupLayout(pnlMain);
		Color bgColor = new Color(176,196,222);

		JFrame frame = new JFrame("Ballot Information");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel container = new JPanel();
		JPanel pTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lTitle = new JLabel("Resulting Ballot Information:");
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		pTitle.add(lTitle);
		frame.add(container);
		container.add(pTitle);
		pTitle.setBackground(bgColor);


		JPanel arrayPanel[] = new JPanel[ballot.raceNames.size()];

		JPanel pEli = new JPanel();
		JLabel lEligibility = new JLabel("Eligibility:");
		JLabel rank1 = new JLabel(" Freshman: "+ballot.eligibility.get(0));
		JLabel rank2 = new JLabel(" Sophomore: "+ballot.eligibility.get(1));
		JLabel rank3 = new JLabel(" Junior: "+ballot.eligibility.get(2));
		JLabel rank4 = new JLabel(" Senior: "+ballot.eligibility.get(3));
		JLabel rank5 = new JLabel(" Graduate: "+ballot.eligibility.get(4));
		JLabel rank6 = new JLabel(" Professional: "+ballot.eligibility.get(5));
		JLabel college1 = new JLabel(" Engineering: "+ballot.eligibility.get(6));
		JLabel college2 = new JLabel(" Medical: "+ballot.eligibility.get(7));
		JLabel club = new JLabel(" Club/Organization: "+ballot.eligibility.get(8));

		pEli.add(lEligibility);
		pEli.add(rank1);
		pEli.add(rank2);
		pEli.add(rank3);
		pEli.add(rank4);
		pEli.add(rank5);
		pEli.add(rank6);
		pEli.add(college1);
		pEli.add(college2);
		pEli.add(club);

		container.add(pEli);

		JPanel pResults = new JPanel();
		JLabel lResultDisplayed = new JLabel("Results Displayed:");
		JLabel rawCount = new JLabel(" Raw Count: " + ballot.results.get(0));
		JLabel winner = new JLabel(" Winner: "+ ballot.results.get(1));
		JLabel tOutStats = new JLabel(" Turn Out Stats: " + ballot.results.get(2));

		pResults.add(lResultDisplayed);
		pResults.add(rawCount);
		pResults.add(winner);
		pResults.add(tOutStats);

		container.add(pResults);

		for(int i = 0; i < ballot.raceNames.size(); i++){
			arrayPanel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));

			arrayPanel[i].add(new JLabel("Race : " + ballot.raceNames.get(i) + "    "));
			if(ballot.numOfCandidates.get(i) >= 0){
				arrayPanel[i].add(new JLabel("Number of Candidates: " + ballot.numOfCandidates.get(i)));
			}else{
				arrayPanel[i].add(new JLabel("No Candidates"));
			}

			container.add(arrayPanel[i]);
			arrayPanel[i].setBackground(bgColor);
		}

		frame.setSize(800,500);
		frame.setVisible(true);
		run();


	}

	private void run(){
		try{
			sock = new Socket("127.0.0.2", 50000);
			brIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pwOut = new PrintWriter(sock.getOutputStream(),true);
		}catch(IOException e){
			System.out.println("IOException");
		}catch(NullPointerException npe){
			System.out.println("Null");
		}
	}

	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){

		}
	}

	public static void main(String args[]){
		new createBallotEnd();
	}


}
