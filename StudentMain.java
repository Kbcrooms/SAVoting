import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StudentMain extends JFrame implements ActionListener{

	PrintWriter pwOut;
	BufferedReader brIn;
	StudentMain(){}
	StudentMain(PrintWriter pwOut, BufferedReader brIn){
		this.pwOut = pwOut;
		this.brIn = brIn;
		JPanel panelMain = new JPanel();
    	        GroupLayout layout = new GroupLayout(panelMain);

		getContentPane().setLayout(new GridBagLayout());
		Color bgColor = new Color(176,196,222);
		getContentPane().setBackground(bgColor);

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
				setVisible(false);
				new StudentElectionsDisplay(pwOut,brIn);
				break;
		}
	}

	public static void main(String[] args){
		new StudentMain();
	}
}
