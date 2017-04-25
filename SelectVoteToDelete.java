import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.*;
import java.io.*;

class SelectVoteToDelete extends JPanel implements ActionListener, ListSelectionListener
{
	private JList list;
	private JButton deleteButton;
	private DefaultListModel listModel;
   PrintWriter pwOut;
   BufferedReader brIn;
   Socket sock;
   JFrame frame;

  SelectVoteToDelete(){}
  SelectVoteToDelete(PrintWriter pwOut, BufferedReader brIn)
	{
		super(new BorderLayout());
		this.pwOut = pwOut;
    		this.brIn = brIn;

		JFrame frame = new JFrame("Vote Deletion");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add student ID's here
		int numElections = 50/*# goes here*/;
		int i = 0;
		listModel = new DefaultListModel();
		while(i<numElections)
		{
			listModel.addElement("Person " + (i+1));	/*ID's go here*/ 
			++i;
		}
		
		list = new JList(listModel);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    list.addListSelectionListener(this);
    list.setVisibleRowCount(5);
    JScrollPane listScrollPane = new JScrollPane(list);

		deleteButton = new JButton("Delete");	//Create button + set properties
		deleteButton.setActionCommand("del");
		deleteButton.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,
                                           BoxLayout.X_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());	//For button placement
		buttonPanel.add(deleteButton);
		buttonPanel.add(Box.createHorizontalGlue());	//For button placement

    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
		buttonPanel.setBackground(new Color(176,196,222));
 
		//Add list to frame
    frame.add(listScrollPane, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.PAGE_END);
    frame.setSize(600, 350);
    frame.setVisible(true);
		
   }
    
    public void actionPerformed(ActionEvent e)
		{
			int currentIndex = list.getSelectedIndex();	//Get current selection's index
			listModel.remove(currentIndex);	//remove element @ index
			/*Do stuff to remove vote from election + recount here*/


			list.setSelectedIndex(currentIndex);	//Set cursor on next element in list
      list.ensureIndexIsVisible(currentIndex);
    }

    public void valueChanged(ListSelectionEvent e) 
		{
        if (e.getValueIsAdjusting() == false) 
				{
            if (list.getSelectedIndex() != -1) 
						{
                deleteButton.setEnabled(true);	//Enable if button selected
            } 
						else 
						{
                deleteButton.setEnabled(false);	//Disable if not selected
            }
        }
    }
    public static void main(String args[])
		{
    	 new SelectVoteToDelete();
    }
}
