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
	String numberOfRaces;
	Socket sock;
	String numberCandidates = "5";

	createBallot4(){
		//this.pwOut = pwOut;
		//this.brIn = brIn;
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
		JPanel arrayPanels[] = new JPanel[Integer.valueOf(numberCandidates)];
		JTextField txtCandidateName[] = new JTextField[Integer.valueOf(numberCandidates)];
		JTextField txtCandidateID[] = new JTextField[Integer.valueOf(numberCandidates)];

		while(i<Integer.valueOf(numberCandidates)){
			arrayPanels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));

			arrayPanels[i].add(new JLabel("Candidate Name"));
			txtCandidateName[i] = new JTextField(20);
			arrayPanels[i].add(txtCandidateName[i]);
		
			arrayPanels[i].add(new JLabel("Candidate Student ID"));
			txtCandidateName[i] = new JTextField(20);
			arrayPanels[i].add(txtCandidateID[i]);
			
			container.add(arrayPanels[i]);
			arrayPanels[i].setBackground(bgColor);
			++i;
		}

		frame.setSize(650,300);
		setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e){

	}

	public static void main(String args[]){
		new createBallot4();
	}

}
