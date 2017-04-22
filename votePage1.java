import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.*;
import java.io.*;

class votePage1 extends JFrame implements ActionListener{

	PrintWriter pwOut;
	BufferedReader brIn;
	Socket sock;
	JFrame frame;

	votePage1(){}
	
	votePage1(PrintWriter pwOut, BufferedReader brIn){
	  	this.pwOut = pwOut;
	  	this.brIn = brIn;
	  	getContentPane().setLayout(new GridBagLayout());
	  	Color bgColor = new Color(176,196,222);
	  	getContentPane().setBackground(bgColor);
	  	JPanel panelMain = new JPanel();
	    	GroupLayout layout = new GroupLayout(panelMain);
	    	JButton btnVote = new JButton("Vote in Election");
	  	JButton btnCandidateInfo = new JButton("View Candidate Information");
	  	btnVote.setActionCommand("vote");
	  	btnVote.addActionListener(this);
	  	btnCandidateInfo.setActionCommand("view");
	  	btnCandidateInfo.addActionListener(this);
	  	panelMain.setLayout(layout);
	  	layout.setAutoCreateGaps(true);
	  	layout.setAutoCreateContainerGaps(true);
	  	layout.setHorizontalGroup(
					  layout.createSequentialGroup()
					  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						    .addComponent(btnVote)
						    .addComponent(btnCandidateInfo))
					  );
		layout.setVerticalGroup(
					layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
						  .addComponent(btnVote)
						  .addComponent(btnCandidateInfo))
					);

		add(panelMain);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(350,120);
		setTitle("Election Comissioner: Main Menu");
		panelMain.setBackground(bgColor);

		getContentPane().add(panelMain);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		setLocation(x, y);
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
				if(){
					//need to fill in this if statement
					// first if statement should take the student to the ballot
					//second if statement takes student to candidate into
				}else{
					JOptionPane.showMessageDialog(this,strIn, "Error",JOptionPane.PLAIN_MESSAGE);
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
			switch(e.getActionCommand(){
				case "vote":
				//fill in action
				break;
				case "view":
				//fill in action
				break;
			}
		}else{
			JOptionPane.showMessageDialog(this, "Socket is Closed", "Error", JOptionPane.ERROR_MESSAGE);
		}


	}

	public static void main(String args[]){
		new votePage1();
	}
}
