import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	static Font defaultFont = new Font("Arial", Font.PLAIN, 16);
	
	public MenuBar() {
		JMenu menu;
		JMenuItem menuItem;
		JMenu help;
		JMenuItem helpItem_Instructions;
		JMenuItem helpItem_About;
	
		
		menu = new JMenu("File");
		menu.setFont(defaultFont);
		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		//NewActionListener newAction = new NewActionListener(PaintModel.getPaintModel());
		//menuItem.addActionListener(newAction);
		
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menu.add(menuItem);

		this.add(menu);

		menu = new JMenu("Edit");
		menu.setFont(defaultFont);
		
		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		//CutActionListener cut = new CutActionListener(); 
		//menuItem.addActionListener(cut);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		//CopyActionListener copy = new CopyActionListener();
		//menuItem.addActionListener(copy);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		//PasteActionListener paste = new PasteActionListener();
		//menuItem.addActionListener(paste);
		menu.add(menuItem);		
		
		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		//UndoActionListener undo = new UndoActionListener(undostack,PaintModel.getPaintModel());
		//menuItem.addActionListener(undo);
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		//RedoActionListener redo = new RedoActionListener(undostack,PaintModel.getPaintModel());
		//menuItem.addActionListener(redo);
		menu.add(menuItem);
		
		this.add(menu);
		
		//Create a help option to the JMenu
		help = new JMenu("Help");
		help.setFont(defaultFont);
		//a group of JMenuItems
		helpItem_Instructions = new JMenuItem("Instructions");
		//HelpActionListener help_instructions = new HelpActionListener();		
		//helpItem_Instructions.addActionListener(help_instructions);
		
		helpItem_About = new JMenuItem("About");
		//HelpActionListener help_about = new HelpActionListener();		
		//helpItem_About.addActionListener(help_about);
		
		help.add(helpItem_Instructions);
		help.add(helpItem_About);
		this.add(help);
	}
}
