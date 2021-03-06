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

class createBallot2 extends JFrame implements ActionListener{
	//Second Page for creating a ballot
	//This page simply allows the EC to enter in the number of races
	JTextField txtNumberOfRaces;
	PrintWriter pwOut;
	BufferedReader brIn;
	private final String numRace = "[0-9]+";
	public String numberOfRaces;
	Socket sock;
	Ballot ballot;
	String username;
	createBallot2(){}
	createBallot2(PrintWriter pwOut, BufferedReader brIn, Ballot ballot, String username){
		this.pwOut = pwOut;
		this.brIn= brIn;
		this.ballot = ballot;
		this.username = username;
  		JPanel pnlMain = new JPanel();
  		GroupLayout layout = new GroupLayout(pnlMain);
  		Color bgColor = new Color(176,196,222);
		txtNumberOfRaces = new JTextField(20);

		JLabel lNumberOfRaces = new JLabel("Number of Races");
		JButton btnNext = new JButton("Next");
		btnNext.setActionCommand("Next");
		btnNext.addActionListener(this);

		pnlMain.setLayout(layout);
	  	layout.setAutoCreateGaps(true);
    		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addGroup(layout.createParallelGroup()
						.addComponent(lNumberOfRaces)
						.addComponent(txtNumberOfRaces))
					.addComponent(btnNext))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
							.addGroup(layout.createSequentialGroup()
							.addComponent(lNumberOfRaces)
							.addComponent(txtNumberOfRaces)))
					.addComponent(btnNext))
		);

		setSize(300,150);
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
					new createBallot3(pwOut, brIn, ballot,username);
				}else{
					JOptionPane.showMessageDialog(this,strIn, "Error",JOptionPane.PLAIN_MESSAGE);
				}
			}
		}catch(IOException e){
			System.out.println("IOException");
		}catch(NullPointerException npe){
			System.out.println("null CB2");
		}

	}


	public void actionPerformed(ActionEvent e){
		if(!sock.isClosed()){
			String numElections = txtNumberOfRaces.getText();
			Pattern numPattern = Pattern.compile(numRace);
			Matcher numElectionMatcher = numPattern.matcher(numElections);
			numberOfRaces = numElections;
			ballot.numOfRaces = Integer.valueOf(numberOfRaces);

			if(numElectionMatcher.matches()){
				switch(e.getActionCommand()){
					case "Next":
						System.out.println("<CreateBallot3>");
						pwOut.println("<CreateBallot3>");
					break;
				}
			}else{
				JOptionPane.showMessageDialog(this, "Invalid Date", "Error with Number of Races", JOptionPane.PLAIN_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(this, "Socket is Closed", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String args[]){
		new createBallot2();
	}

}
