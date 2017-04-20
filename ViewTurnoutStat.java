import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

class ViewTurnoutStat extends JFrame implements ActionListener
{
   ViewTurnoutStat()
	 {
    JFrame frame = new JFrame("View Turnout Statistics");
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
		JButton arrayOfButtons[] = new JButton[numElections];

		while(i<numElections)
		{
			//Create Panel
			arrayPanels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));

			//Create Buttons to go into Panel
			arrayOfButtons[i] = new JButton("View Turnout Statistics");
			arrayOfButtons[i].setActionCommand("stat");
      arrayOfButtons[i].addActionListener(this);

			//Create Labels to go into Panel
			arrayPanels[i].add(new JLabel("Election Name Here"));
			arrayPanels[i].add(arrayOfButtons[i]);

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
			case "stat":
							JOptionPane.showMessageDialog(this, "Total number of voters: ", "Turnout Statistics", JOptionPane.PLAIN_MESSAGE);
							break;
							}
    }
    public static void main(String args[]){
        new ViewTurnoutStat();
    }
}
