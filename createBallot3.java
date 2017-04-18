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
	JTextField txtNameOfRace;
	ButtonGroup btnGroupTypeOfRace;
	PrintWriter pwOut;
	BufferedReader brIn;
	String numberOfRaces;
	Socket sock;

	createBallot3(){
		//this.pwOut = pwOut;
		//this.brIn = brIn;
		//this.numberOfRaces = numberOfRaces;
		JPanel pnlMain = new JPanel();
		GroupLayout layout = new GroupLayout(pnlMain);
		JPanel pnlButtons = new JPanel();
		JPanel pnlCandidateInfo = new JPanel();
		Color bgColor = new Color(176,196,222);

		txtNameOfRace = new JTextField(20);
		JLabel lNameOfRace = new JLabel("Name of Race");
		txtNameofCandidate = new JTextField(20);

		
		txt

	}
}
