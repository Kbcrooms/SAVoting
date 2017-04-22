import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.*;
import java.io.*;

class CertifyElection extends JFrame implements ActionListener
{

   PrintWriter pwOut;
   BufferedReader brIn;
   Socket sock;
   JFrame frame;

   CertifyElection(){}
   CertifyElection(PrintWriter pwOut, BufferedReader brIn){
    this.pwOut = pwOut;
    this.brIn = brIn;
    frame = new JFrame("Certify an Election");
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
		JButton certifyButtons[] = new JButton[numElections];

		while(i<numElections)
		{
			//Create Panel
			arrayPanels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));

			//Create Buttons to go into Panel
			arrayOfButtons[i] = new JButton("View Information");
			arrayOfButtons[i].setActionCommand("info");
      arrayOfButtons[i].addActionListener(this);
			certifyButtons[i] = new JButton("Certify");
			certifyButtons[i].setActionCommand("cert");
      certifyButtons[i].addActionListener(this);

			//Create Labels to go into Panel
			arrayPanels[i].add(new JLabel("Election Name Here"));
			arrayPanels[i].add(arrayOfButtons[i]);
			arrayPanels[i].add(certifyButtons[i]);

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
			case "info":
							JOptionPane.showMessageDialog(this, "Total number of voters: ", "Election Information", JOptionPane.PLAIN_MESSAGE);
							break;
			case "cert":
				JOptionPane.showMessageDialog(this, "Certification Complete", "Certification Confirmation", JOptionPane.PLAIN_MESSAGE);
				System.out.println("<HSOMain>");
				pwOut.println("<HSOMain>");
			break;
			}
    }
    public static void main(String args[]){
        new CertifyElection();
    }
}
