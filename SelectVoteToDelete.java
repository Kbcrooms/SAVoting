import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

class SelectVoteToDelete extends JPanel implements ActionListener, ListSelectionListener
{
	private JList list;
	private JButton deleteButton;
	private DefaultListModel listModel;
  SelectVoteToDelete()
	{
		super(new BorderLayout());

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
 
    add(listScrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.PAGE_END);
		
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
			JFrame frame = new JFrame("Vote Deletion");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JComponent mainPane = new SelectVoteToDelete();

			frame.add(mainPane);	//Add list to frame
			frame.setSize(600, 350);
    	frame.setVisible(true);
    }
}
