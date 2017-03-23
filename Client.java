import javax.swing.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.*;
import java.io.*;

class Client extends JFrame implements ActionListener{
    
    BufferedReader brIn;
    PrintWriter pwOut;
    Socket sock;
    JTextField txtName;
    
    
    Client(){
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain,BoxLayout.PAGE_AXIS));
        
        JPanel pnlName = new JPanel();
        JPanel pnlPassword = new JPanel();
        JPanel pnlButtons = new JPanel();
        
        txtName = new JTextField(15);
        JLabel lblName = new JLabel("Student ID");
        pnlName.add(lblName);
        pnlName.add(txtName);
                                
        JButton btnLogin = new JButton("Login");
        btnLogin.setActionCommand("login");
        btnLogin.addActionListener(this);
        
        JButton btnQuit = new JButton("Quit");
        btnQuit.setActionCommand("quit");
        btnQuit.addActionListener(this);
        
        pnlButtons.add(btnLogin);
        pnlButtons.add(btnQuit);
        
        pnlMain.add(pnlName);
        pnlMain.add(pnlButtons);
        
        getContentPane().add(pnlMain);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Morgantown University Election Login");        
        setSize(400,100);
        
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
                if (strIn.startsWith("<logged>")){
                    JOptionPane.showMessageDialog(this,"Successful Login","Successful",JOptionPane.PLAIN_MESSAGE);
                    
                }
                else
                    JOptionPane.showMessageDialog(this,strIn,"???",JOptionPane.PLAIN_MESSAGE);
            }
        }catch(IOException e){
            System.out.println("IOException");
        }catch(NullPointerException npe){
            System.out.println("null");
        }
        
    }
    public void actionPerformed(ActionEvent e){
        if (!sock.isClosed()){
            switch (e.getActionCommand()){
                case "login":
                    pwOut.println("<login>," + txtName.getText());
                    System.out.println("<login>," + txtName.getText());
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
        new Client();
    }
}
