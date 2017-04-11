import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.*;
import java.io.*;

class StudentMain extends JFrame implements ActionListener{
	StudentMain(){
		getContentPane().setLayout(new GridBagLayout());
		Color bgColor = new Color(176,196,222);
		getContentPane().setBackground(bgColor);
		
		JPanel panelMain = new JPanel();
        GroupLayout layout = new GroupLayout(panelMain);
        
		JButton btnVote = new JButton("Vote in Election");

		btnVote.setActionCommand("vote");
		btnVote.addActionListener(this);

		panelMain.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(btnVote))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(btnVote))
		);
		
		add(panelMain);

    	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(280,80);
		setTitle("Student: Main Menu");
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
			case "vote":
				break;
		}
	}

	public static void main(String[] args){
		new StudentMain();
	}
}
