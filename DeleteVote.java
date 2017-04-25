import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.*;
import java.io.*;

class DeleteVote extends JFrame implements ActionListener
{

   PrintWriter pwOut;
   BufferedReader brIn;
   Socket sock;
   JFrame frame;

   DeleteVote(){}
   DeleteVote(PrintWriter pwOut, BufferedReader brIn)
	 {
    this.pwOut = pwOut;
    this.brIn = brIn;
    JFrame frame = new JFrame("Delete a Vote");
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
			arrayOfButtons[i] = new JButton("View Votes");
			arrayOfButtons[i].setActionCommand("vote");
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
		if(strIn.equals("<DeleteSingleVote>")){	
			setVisible(false);
			new SelectVoteToDelete(pwOut,brIn);	
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
	if(!sock.isClosed()){
		switch(e.getActionCommand()){
			case "vote":
			  System.out.println("<DeleteSingleVote>");
			  pwOut.println("<DeleteSingleVote>");
			break;			
		}
	}else{
            JOptionPane.showMessageDialog(this,"Socket is Closed","Error",JOptionPane.ERROR_MESSAGE);
        }

    }
    public static void main(String args[]){
        new DeleteVote();
    }
}
