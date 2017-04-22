import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.*;
import java.io.*;

class VoterDemographics extends JFrame implements ActionListener
{
   PrintWriter pwOut;
   BufferedReader brIn;
   Socket sock;
   JFrame frame;

   VoterDemographics(){}
   VoterDemographics(PrintWriter pwOut, BufferedReader brIn){
    this.pwOut = pwOut;
    this.brIn = brIn;
    JFrame frame = new JFrame("View Election Demographics");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel container = new JPanel();
		JPanel pTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lTitle = new JLabel("Closed Elections");
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		pTitle.add(lTitle);
		frame.add(container);
		container.add(pTitle);
		pTitle.setBackground(new Color(176,196,222));

   int i = 0, numElections = 7/*Actual # goes here*/;
		JPanel arrayPanels[] = new JPanel[numElections];
		JLabel electionNames[] = new JLabel[numElections];
		JButton demoButtons[] = new JButton[numElections];

		while(i<numElections)
		{
			//Create Panel
			arrayPanels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));

			//Create Buttons to go into Panel
			demoButtons[i] = new JButton("View Demographics");
			demoButtons[i].setActionCommand("demo");
      demoButtons[i].addActionListener(this);

			//Create Labels to go into Panel
			arrayPanels[i].add(new JLabel("Election Name Here"));
			arrayPanels[i].add(demoButtons[i]);

			//Create Panel + Set color
			container.add(arrayPanels[i]);
			arrayPanels[i].setBackground(new Color(176,196,222));
			++i;
		}

		frame.setSize(600, 350);
    frame.setVisible(true);
		
   }
    
    public void actionPerformed(ActionEvent e){
			switch(e.getActionCommand()){
			case "demo":
							JOptionPane.showMessageDialog(this, "Males:  " /*+ # here*/ + "\nFemales: "  /*# here*/ + "\nFreshman: "  /*# here*/ + "\nSophmore: "  /*# here*/+ "\nJunior: "  /*# here*/+ "\nSenior: "  /*# here*/+ "\nGraduate: "  /*# here*/+ "\nProfessional: " /*# here*/ + "\nMedical College: " /*# here*/ + "\nEngineering College: " /*# here*/ + "\nClub/Organization: " /*# here*/, "Demographics Information", JOptionPane.PLAIN_MESSAGE);
			break;
							}
    }
    public static void main(String args[]){
        new VoterDemographics();
    }
}
