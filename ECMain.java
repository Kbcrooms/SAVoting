import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.*;
import java.io.*;

class ECMain extends JFrame implements ActionListener{
  PrintWriter pwOut;
  BufferedReader brIn;
  Socket sock;
  ECMain(){}
  ECMain(PrintWriter pwOut, BufferedReader brIn){
	this.pwOut = pwOut;
	this.brIn = brIn;
	getContentPane().setLayout(new GridBagLayout());
	Color bgColor = new Color(176,196,222);
	getContentPane().setBackground(bgColor);
	JPanel panelMain = new JPanel();
  GroupLayout layout = new GroupLayout(panelMain);
  JButton btnVote = new JButton("Vote in Election");
	JButton btnCreateBallot = new JButton("Create a Ballot");
	btnVote.setActionCommand("vote");
	btnVote.addActionListener(this);
	btnCreateBallot.setActionCommand("create");
	btnCreateBallot.addActionListener(this);
	panelMain.setLayout(layout);
	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);
	layout.setHorizontalGroup(
				  layout.createSequentialGroup()
				  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					    .addComponent(btnVote)
					    .addComponent(btnCreateBallot))
				  );
	layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					  .addComponent(btnVote)
					  .addComponent(btnCreateBallot))
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
				if(strIn.equals("<createBallot>")){
					setVisible(false);
					new createBallot1(pwOut, brIn);
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

	public void actionPerformed(ActionEvent evt){
		if(!sock.isClosed()){
			switch(evt.getActionCommand()){
				case "create":
					System.out.println("<createBallot>");
					pwOut.println("<createBallot>");
					break;
				case "vote":
					break;
			}
		}else{
			JOptionPane.showMessageDialog(this, "Socket is Closed", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args){
		new ECMain();
	}
}
