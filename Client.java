import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
class Client extends JFrame implements ActionListener{

    BufferedReader brIn;
    PrintWriter pwOut;
    Socket sock;
    JTextField txtName;
    JPasswordField txtPass;
    ButtonGroup btnGroupRadio;
    JPanel pnlMain;

    Client(String mode){
        pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain,BoxLayout.PAGE_AXIS));
        Color bgColor = new Color(176,196,222);
        UIManager.put("OptionPane.background", bgColor);

        JPanel pnlName = new JPanel();
        JPanel pnlPassword = new JPanel();
        JPanel pnlButtons = new JPanel();
        JPanel pnlRadios = new JPanel();

        txtName = new JTextField(30);
        JLabel lblName = new JLabel("Name");
        pnlName.add(Box.createRigidArea(new Dimension(25,0)));
        pnlName.add(lblName);
        pnlName.add(txtName);

        txtPass = new JPasswordField(30);
        JLabel lblPass = new JLabel("Password");
        pnlPassword.add(lblPass);
        pnlPassword.add(txtPass);

        JButton btnAdd = new JButton("Add User");
        btnAdd.setActionCommand("add");
        btnAdd.addActionListener(this);

        JButton btnLogin = new JButton("Login");
        btnLogin.setActionCommand("login");
        btnLogin.addActionListener(this);

        JRadioButton adminSelect = new JRadioButton("Admin");
        adminSelect.setActionCommand("<ADMIN>");
        JRadioButton studentSelect = new JRadioButton("Student");
        studentSelect.setActionCommand("<STUDENT>");

        btnGroupRadio = new ButtonGroup();
        btnGroupRadio.add(adminSelect);
        btnGroupRadio.add(studentSelect);

        JLabel lblAuthority = new JLabel("User Type:   ");
        lblAuthority.setFont(new Font(lblAuthority.getFont().getName(), Font.PLAIN, 15));
        pnlRadios.add(lblAuthority);
        pnlRadios.add(adminSelect);
        pnlRadios.add(studentSelect);

        JButton btnQuit = new JButton("Quit");
        btnQuit.setActionCommand("quit");
        btnQuit.addActionListener(this);

      	if(mode.equals("ADD_DATA")){
      	    pnlButtons.add(btnAdd);
      	    pnlMain.add(pnlRadios);
      	}
        pnlButtons.add(btnLogin);
        pnlButtons.add(btnQuit);

        pnlMain.add(pnlName);
        pnlMain.add(pnlPassword);
        pnlMain.add(pnlButtons);

        pnlName.setBackground(bgColor);
        pnlPassword.setBackground(bgColor);
        pnlButtons.setBackground(bgColor);
        pnlRadios.setBackground(bgColor);
        adminSelect.setBackground(bgColor);
        studentSelect.setBackground(bgColor);
        getContentPane().add(pnlMain);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Morgantown University Election Login");
        setSize(500,150);

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

            while (true){
                String strIn = brIn.readLine();
                strIn = strIn.trim();
                if (strIn.startsWith("<added>")){
                    JOptionPane.showMessageDialog(this,"User added","Successful",JOptionPane.PLAIN_MESSAGE);
                }
                else if (strIn.startsWith("<logged>")){
                    JOptionPane.showMessageDialog(this,"Successful Login","Successful",JOptionPane.PLAIN_MESSAGE);
                }
                else if (strIn.startsWith("<admin>")){
                    JOptionPane.showMessageDialog(this,"Successful Admin Login", "Successful", JOptionPane.PLAIN_MESSAGE);
		                setVisible(false);
                    new HSOMain(pwOut,brIn);
                }
                else if (strIn.startsWith("<student>")){
                    JOptionPane.showMessageDialog(this,"Successful Student Login", "Successful", JOptionPane.PLAIN_MESSAGE);
                    new StudentMain(pwOut, brIn);
		                setVisible(false);
                    break;
                }
                else if(strIn.startsWith("<electionCom>")){
		              JOptionPane.showMessageDialog(this,"Successful Election Comm. Login","Successful",JOptionPane.PLAIN_MESSAGE);
                  String username = strIn.split(",")[1];

		              new ECMain(pwOut,brIn,username);
		              setVisible(false);
                }
                else{
                    //JOptionPane.showMessageDialog(this,strIn,"Error",JOptionPane.PLAIN_MESSAGE);
                }
            }
        }catch(IOException e){
            System.out.println("IOException");
        }catch(NullPointerException npe){
            System.out.println("null Client");
        }

    }
    public void actionPerformed(ActionEvent e){
        if (!sock.isClosed()){
            switch (e.getActionCommand()){
                case "add":
                    System.out.println("<add>," + txtName.getText() + "," + txtPass.getText() + "," + btnGroupRadio.getSelection().getActionCommand());
                    pwOut.println("<add>," + txtName.getText() + "," + txtPass.getText() + "," + btnGroupRadio.getSelection().getActionCommand());
                break;
                case "login":
                    pwOut.println("<login>," + txtName.getText() + "," + txtPass.getText());
                    System.out.println("<login>," + txtName.getText() + "," + txtPass.getText());
                break;
                case "quit":
                    pwOut.println("<die>");
                    System.exit(0);
                break;
            }
        }else{
            JOptionPane.showMessageDialog(this,"Socket is Closed","Error",JOptionPane.ERROR_MESSAGE);
        }


    }

    public static void main(String args[]){
	if(args.length != 1){
	    System.out.println("Please enter the mode for startup as a command line argument!");
	    System.out.println("Options: NORMAL, ADD_DATA");
	}else{

	    switch(args[0]){
	    case "NORMAL":
		new Client("NORMAL");
		break;
	    case "ADD_DATA":
		new Client("ADD_DATA");
		break;
	    default:
		System.out.println("Please enter the mode for startup as a command line argument!");
		System.out.println("Options: NORMAL, ADD_DATA");
	    }
	}
    }
}
