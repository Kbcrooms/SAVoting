import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.*;
import java.io.*;

class ViewTurnoutStat extends JFrame implements ActionListener
{
   PrintWriter pwOut;
   BufferedReader brIn;
   Socket sock;
   JFrame frame;

   ViewTurnoutStat(){}
   ViewTurnoutStat(PrintWriter pwOut, BufferedReader brIn)
	 {
    frame = new JFrame("View Turnout Statistics");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel container = new JPanel();
		JPanel pTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lTitle = new JLabel("Closed Elections");
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		pTitle.add(lTitle);
		frame.add(container);
		container.add(pTitle);
		pTitle.setBackground(new Color(176,196,222));

   int i = 0, numElections = 2/*Actual # goes here*/;
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
			arrayPanels[i].add(new JLabel("TestElection" + (i+1)));
			arrayPanels[i].add(arrayOfButtons[i]);

			//Create Panel + Set color
			container.add(arrayPanels[i]);
			arrayPanels[i].setBackground(new Color(176,196,222));
			++i;
		}

		frame.setSize(600, 350);
		    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();       	    
		    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		    frame.setLocation(x, y);
    		frame.setVisible(true);
		run();
		
   }

    private void run(){
	try{
	    sock = new Socket("127.0.0.2",50000);
            brIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            pwOut = new PrintWriter(sock.getOutputStream(),true);
	
	    while(true){
		String strIn = brIn.readLine();
		if(strIn.equals("<HSOMain>")){	
			frame.setVisible(false);
			new HSOMain(pwOut,brIn);	
		}else{
			JOptionPane.showMessageDialog(this,strIn,"Error",JOptionPane.PLAIN_MESSAGE);
		}

	    }
	}catch(IOException e){
            System.out.println("IOException");
        }catch(NullPointerException npe){
            System.out.println("null");
        }
    }
    
    public void actionPerformed(ActionEvent e){
			switch(e.getActionCommand()){
			case "stat":
				JOptionPane.showMessageDialog(this, "Total number of voters: 1", "Turnout Statistics", JOptionPane.PLAIN_MESSAGE);
				System.out.println("<HSOMain>");
				pwOut.println("<HSOMain>");
				break;
			}
    }
    public static void main(String args[]){
        new ViewTurnoutStat();
    }
}
