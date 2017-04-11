import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.*;
import java.io.*;

class CandidateMain extends JFrame implements ActionListener{
	CandidateMain(){
		getContentPane().setLayout(new GridBagLayout());
		Color bgColor = new Color(176,196,222);
		getContentPane().setBackground(bgColor);
	
		JPanel panelMain = new JPanel();
        GroupLayout layout = new GroupLayout(panelMain);
				
		JButton btnVote = new JButton("Vote in Election");
		JButton btnUpload= new JButton("Upload Candidate Information");

		btnVote.setActionCommand("vote");
		btnVote.addActionListener(this);
		btnUpload.setActionCommand("upload");
		btnUpload.addActionListener(this);

		panelMain.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(btnVote)
					.addComponent(btnUpload))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(btnVote)
					.addComponent(btnUpload))
		);
		
		add(panelMain);	

    	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(315,120);
		setTitle("Candidate: Main Menu");
		panelMain.setBackground(bgColor);
		getContentPane().add(panelMain);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();       	    
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		setLocation(x, y);
		setVisible(true);

	}
	public void actionPerformed(ActionEvent evt){
		switch(evt.getActionCommand()){
			case "upload":
				break;
			case "vote":
				break;
		}
	}

	public static void main(String[] args){
		new CandidateMain();
	}
}
