import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.*;
import java.io.*;

class HSOMain extends JFrame implements ActionListener{
   PrintWriter pwOut;
   BufferedReader brIn;
   Socket sock;
   HSOMain(){}
   HSOMain(PrintWriter pwOut, BufferedReader brIn){
    this.pwOut = pwOut;
    this.brIn = brIn;
    JPanel panelMain = new JPanel();
    GroupLayout layout = new GroupLayout(panelMain);

    JButton btnCreateElection = new JButton("Create an Election");
    JButton btnCertifyElection = new JButton("Certify an Election");
    JButton btnStatistics = new JButton("View Turnout Statistics");
    JButton btnRecount = new JButton("Recount an Election");
    JButton btnDelete = new JButton("Delete A Vote");

    btnCreateElection.setActionCommand("create");
    btnCreateElection.addActionListener(this);
    btnCertifyElection.setActionCommand("certify");
    btnCertifyElection.addActionListener(this);
    btnStatistics.setActionCommand("stats");
    btnStatistics.addActionListener(this);
    btnRecount.setActionCommand("recount");
    btnRecount.addActionListener(this);
    btnDelete.setActionCommand("delete");
    btnDelete.addActionListener(this);

    panelMain.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    
    layout.setHorizontalGroup(
	layout.createSequentialGroup()
		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(btnCreateElection)
			.addComponent(btnCertifyElection)
			.addComponent(btnStatistics)
			.addComponent(btnRecount)
			.addComponent(btnDelete))
		            
    );
    layout.setVerticalGroup(
	layout.createParallelGroup()
		.addGroup(layout.createSequentialGroup()
			.addComponent(btnCreateElection)
			.addComponent(btnCertifyElection)
			.addComponent(btnStatistics)
			.addComponent(btnRecount)
			.addComponent(btnDelete))
		          
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
    run();
}

private void run(){
	try{
	    sock = new Socket("127.0.0.1",50000);
            brIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            pwOut = new PrintWriter(sock.getOutputStream(),true);
	
	    while(true){
		String strIn = brIn.readLine();
		if(strIn.equals("<createElection>")){	
			setVisible(false);
			new createElection(pwOut,brIn);	
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

public void actionPerformed(ActionEvent evt){
	if(!sock.isClosed()){
	    	switch(evt.getActionCommand()){
		      case "create":
			  System.out.println("<createElection>");
			  pwOut.println("<createElection>");
			  break;
		      case "certify":
			  break;
		      case "stats":
			  break;
		      case "recount":
			  break;

		   }
	}else{
            JOptionPane.showMessageDialog(this,"Socket is Closed","Error",JOptionPane.ERROR_MESSAGE);
        }
}

public static void main(String[] args){
   new HSOMain();
}
}
