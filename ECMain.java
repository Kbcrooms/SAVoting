import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.*;
import java.io.*;

class ECMain extends JFrame implements ActionListener{
	ECMain(){
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
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(btnVote)
					.addComponent(btnCreateBallot))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(btnVote)
					.addComponent(btnCreateBallot))
		);

	
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300,200);
        setTitle("Head of Student Organizations: Main Menu");
        getContentPane().add(panelMain);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();       	    
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        setLocation(x, y);
        setVisible(true);
	}
	
	public void actionPerformed(ActionEvent evt){
		switch(evt.getActionCommand()){
			case "create":
				break;
			case "vote":
				break;
		}
	}

	public static void main(String[] args){
		new ECMain();
	}
}
