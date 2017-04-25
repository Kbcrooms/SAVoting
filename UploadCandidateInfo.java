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

class UploadCandidateInfo extends JFrame implements ActionListener{
	PrintWriter pwOut;
	BufferedReader brIn;
	Socket sock;
	JTextField txtName;
	JTextField txtMajor;
	JTextField txtCollege;
	JTextField txtStances;

	UploadCandidateInfo(){
		JPanel pnlMain = new JPanel();
		GroupLayout layout = new GroupLayout(pnlMain);
		Color bgColor = new Color(176,196,222);

		JLabel lName = new JLabel("Name");
		txtName = new JTextField(20);
		JLabel lMajor = new JLabel("Major");
		txtMajor = new JTextField(20);
		JLabel lCollege = new JLabel("College");
		txtCollege = new JTextField(20);
		JLabel lStances = new JLabel("Stances");
		txtStances = new JTextField(20);
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setActionCommand("submit");
		btnSubmit.addActionListener(this);

		pnlMain.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
					  layout.createSequentialGroup()
					  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						    .addGroup(layout.createParallelGroup()
							      .addComponent(lName)
							      .addComponent(txtName)
							      .addComponent(lMajor)
							      .addComponent(txtMajor)
							      .addComponent(lCollege)
							      .addComponent(txtCollege)
							      .addComponent(lStances)
							      .addComponent(txtStances)
							      .addComponent(btnSubmit)))
					  
					  );
		layout.setVerticalGroup(
					layout.createParallelGroup()
					.addGroup(layout.createParallelGroup()
						  .addGroup(layout.createSequentialGroup()
							    .addComponent(lName)
							    .addComponent(txtName)
							    .addComponent(lMajor)
							    .addComponent(txtMajor)
							    .addComponent(lCollege)
							    .addComponent(txtCollege)
							    .addComponent(lStances)
							    .addComponent(txtStances)
							    .addComponent(btnSubmit)))
					);

		setSize(650,300);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Upload Candidate Information");
		pnlMain.setBackground(bgColor);;
		getContentPane().add(pnlMain);
		//this centers the window in the screen
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		setLocation(x, y);
		//make sure you can actually see it, starts off false
	  	setVisible(true);
		//run();
		
	}

	public void actionPerformed(ActionEvent e){
	    setVisible(false);
	    new CandidateMain();
		
	}
    
	public static void main(String args[]){
		new UploadCandidateInfo();
	}
}
