package com.SOOHPServer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.apache.commons.io.FileUtils;

public class SOOHPServerMainGUI extends JPanel{
	public static JFrame frame = new JFrame("SOOHPServer");
	public static JPanel topHalf = new JPanel(new BorderLayout());
	public static JPanel bottomHalf = new JPanel(new BorderLayout());
	public static JLabel topLabel = new JLabel();
	public static JLabel bottomLabel = new JLabel();
	public static JLabel clPageNumberLabel = new JLabel();
	public static JLabel clSubmittedByLabel = new JLabel();
	public static JLabel clStatusLabel = new JLabel();
	public static JLabel clTimeStampLabel = new JLabel();
	public static JTextArea questionTextArea;
	
	
	public static JButton newClueLists = new JButton("New Clue Lists");
	public static JButton savedClueLists = new JButton("Saved Clue Lists");
	public static JButton newQuestion = new JButton("New Question");
	public static JButton newRule = new JButton("New Rule");
	public static JPanel clButtonPanel = new JPanel((new GridLayout(2, 2)));
	public static JButton clNext = new JButton("Next");
	public static JButton clPrevious = new JButton("Previous");
	public static JButton clSave = new JButton("Save");
	public static JButton clArchive = new JButton("Archive");
	public static JButton clBack = new JButton("Back");
	public static newClueListsButtonHandler myNewClueListsButtonHandler = new newClueListsButtonHandler();
	public static savedClueListsButtonHandler mySavedClueListsButtonHandler = new savedClueListsButtonHandler();
	public static newQuestionButtonHandler myNewQuestionButtonHandler = new newQuestionButtonHandler();
	public static newRuleButtonHandler myNewRuleButtonHandler = new newRuleButtonHandler();
	public static clNextButtonHandler myClNextButtonHandler = new clNextButtonHandler();
	public static clPreviousButtonHandler myClPreviousButtonHandler = new clPreviousButtonHandler();
	public static clSaveHandler myClSaveHandler = new clSaveHandler();
	public static clArchiveHandler myClArchiveHandler = new clArchiveHandler();
	public static clBackHandler myClBackHandler = new clBackHandler();
	
	public static String pathSubmittedClueLists = "D:\\SOOHPServer\\ClueLists\\SubmittedClueLists\\";
	public static String pathSavedClueLists = "D:\\SOOHPServer\\ClueLists\\SavedClueLists\\";
	public static String pathArchivedClueLists = "D:\\SOOHPServer\\ClueLists\\ArchivedClueLists\\";
	public static String pathUpdates = "D:\\SOOHPServer\\Updates\\";
	public static String newOrSaved;
	public static Vector<File> listOfClueListFiles = new Vector<File>();
	public static Vector<String> listOfClueListNames = new Vector<String>();
	public static int pageNumber = 0;
	
	SOOHPServerMainGUI()
	{
		//declare button handlers
		newClueLists.addMouseListener(myNewClueListsButtonHandler);
		savedClueLists.addMouseListener(mySavedClueListsButtonHandler);
		newQuestion.addMouseListener(myNewQuestionButtonHandler);
		newRule.addMouseListener(myNewRuleButtonHandler);
		clNext.addMouseListener(myClNextButtonHandler);
		clPrevious.addMouseListener(myClPreviousButtonHandler);
		clSave.addMouseListener(myClSaveHandler);
		clArchive.addMouseListener(myClArchiveHandler);
		clBack.addMouseListener(myClBackHandler);
		
		// divide the main panel into a top and a bottom
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);

		// create top panel and add 
		topHalf.setMinimumSize(new Dimension(400, 50));
		topHalf.setPreferredSize(new Dimension(450, 250));
		splitPane.add(topHalf);

		// create bottom panel and add 
		bottomHalf.setMinimumSize(new Dimension(400, 50));
		bottomHalf.setPreferredSize(new Dimension(450, 250));
		splitPane.add(bottomHalf);
		
		
		
		
		showMenuScreen();
	}
	  public void createAndShowGUI()
	  {
		//set up the main window
			this.frame.setDefaultCloseOperation(3);
			setOpaque(true);
			frame.setContentPane(this);
			frame.pack();
			frame.setLocationRelativeTo(null); 
			frame.setVisible(true);
		  
		
		  
	  }
	  
	// then have methods to call the different screens
	  public static void showMenuScreen() {
		  topHalf.removeAll();
		  topHalf.repaint();
		  topHalf.setVisible(true);
		  topLabel.setText("Clue Lists");
		  topHalf.setLayout((new GridLayout(3, 3)));
		  bottomLabel.setText("Questions & Rules");
		  bottomHalf.setLayout((new GridLayout(3, 3)));
		  
		  topHalf.add(topLabel, BorderLayout.NORTH);
		  topHalf.add(newClueLists, BorderLayout.EAST);
		  topHalf.add(savedClueLists, BorderLayout.WEST);
		  
		  bottomHalf.removeAll();
		  bottomHalf.repaint();
		  bottomHalf.setVisible(true);
		  bottomHalf.add(bottomLabel, BorderLayout.NORTH);
		  bottomHalf.add(newQuestion, BorderLayout.EAST);
		  bottomHalf.add(newRule, BorderLayout.WEST);
		  
		  frame.setVisible(true);
	  }
	  
	  public static void showClueListScreen(){
		  topLabel.setText(newOrSaved + " Clue Lists");
		  //bottomLabel.setText(newOrSaved);
		  
		  //clear everything out
		  topHalf.removeAll();
		  topHalf.add(topLabel, BorderLayout.NORTH);
		  topHalf.setLayout((new GridLayout(5, 1)));
		  topHalf.repaint();
		  topHalf.setVisible(true);
		  bottomHalf.removeAll();
		  //bottomHalf.add(bottomLabel, BorderLayout.NORTH);
		  bottomHalf.setLayout((new GridLayout(2, 1)));
		  bottomHalf.repaint();
		  bottomHalf.setVisible(true);

		  // add labels
		    
			clSubmittedByLabel.setText("Submitted by: ");
			clStatusLabel.setText("Status: ");
			clTimeStampLabel.setText("Timestamp: ");
			clPageNumberLabel.setText("Date Submitted: ");
		    topHalf.add(clPageNumberLabel);
		    topHalf.add(clSubmittedByLabel);
		    topHalf.add(clStatusLabel);
		    topHalf.add(clTimeStampLabel);

		    
		  //setup question display area
			questionTextArea = new JTextArea(1, 10);
			questionTextArea.setEditable(false);
			questionTextArea.setFont(new Font("Serif", Font.ITALIC, 20));
			questionTextArea.setLineWrap(true);
			questionTextArea.setWrapStyleWord(true);
			JScrollPane questionTextPane = new JScrollPane(questionTextArea,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			bottomHalf.add(questionTextPane);
			
			//add the clue list buttons
			clButtonPanel.add(clNext);
			clButtonPanel.add(clPrevious);
			clButtonPanel.add(clSave);
			clButtonPanel.add(clArchive);
			clButtonPanel.add(clBack);
			bottomHalf.add(clButtonPanel);
			
			getClueLists();
			nextClueList();
			
		  frame.setVisible(true);
	  }
	  
	  public static void showNewQuestionScreen() {
		  topLabel.setText("Clue Lists");
		  bottomLabel.setText("Questions & Rules");
		  
		  topHalf.add(topLabel, BorderLayout.NORTH);
		  topHalf.add(newClueLists, BorderLayout.EAST);
		  topHalf.add(savedClueLists, BorderLayout.WEST);
		  
		  bottomHalf.add(bottomLabel, BorderLayout.NORTH);
		  bottomHalf.add(newQuestion, BorderLayout.EAST);
		  bottomHalf.add(newRule, BorderLayout.WEST);
		  
		  frame.setVisible(true);
	  }
	  
	  public static void showNewRuleScreen() {
		  topLabel.setText("Clue Lists");
		  bottomLabel.setText("Questions & Rules");
		  
		  topHalf.add(topLabel, BorderLayout.NORTH);
		  topHalf.add(newClueLists, BorderLayout.EAST);
		  topHalf.add(savedClueLists, BorderLayout.WEST);
		  
		  bottomHalf.add(bottomLabel, BorderLayout.NORTH);
		  bottomHalf.add(newQuestion, BorderLayout.EAST);
		  bottomHalf.add(newRule, BorderLayout.WEST);
		  
		  frame.setVisible(true);
		  
	  }
	  
	//then the button action handlers 
		public static class newClueListsButtonHandler extends MouseAdapter {
			public void mouseReleased(MouseEvent e) {
				newOrSaved = "New";
				showClueListScreen();
				}
			}
	 
		public static class savedClueListsButtonHandler extends MouseAdapter {
			public void mouseReleased(MouseEvent e) {
				newOrSaved = "Saved";
				showClueListScreen();
				}
			}
	 
		public static class newQuestionButtonHandler extends MouseAdapter {
			public void mouseReleased(MouseEvent e) {
				showNewQuestionScreen();
				}
			}
	 
		public static class newRuleButtonHandler extends MouseAdapter {
			public void mouseReleased(MouseEvent e) {
				showNewRuleScreen() ;
				}
			}

		public static class clNextButtonHandler extends MouseAdapter {
			public void mouseReleased(MouseEvent e) {
				
				if ((pageNumber+1) == listOfClueListFiles.size())
				{
					pageNumber = pageNumber - (listOfClueListFiles.size()-1);
				}else{
					pageNumber++;
				}	
				nextClueList();
				}
			}
		
		public static class clPreviousButtonHandler extends MouseAdapter {
			public void mouseReleased(MouseEvent e) {
				
				System.out.println("Page number d : "+pageNumber);
				if (pageNumber == 0)
				{
					pageNumber = pageNumber + (listOfClueListFiles.size()-1);
					System.out.println("Page number e : "+pageNumber);
				}	
				else {
					pageNumber--;
					System.out.println("Page number f : "+pageNumber);
				}
				previousClueList();
				}
			}
		
		public static class clSaveHandler extends MouseAdapter {
			public void mouseReleased(MouseEvent e) {
				if (listOfClueListFiles.size()>0){
					moveToSaved();
				}
				
				}
			}
		
		public static class clArchiveHandler extends MouseAdapter {
			public void mouseReleased(MouseEvent e) {
				if (listOfClueListFiles.size()>0){
					moveToArchived();
				}
				}
			}
		
		public static class clBackHandler extends MouseAdapter {
			public void mouseReleased(MouseEvent e) {
				showMenuScreen();
				}
			}

		//then other methods

		public static void getClueLists(){
			File savedFolder = new File(pathSavedClueLists);
			File submittedFolder = new File(pathSubmittedClueLists);
			File activeFolder = new File(pathSubmittedClueLists);
			if(newOrSaved =="Saved"){
				activeFolder = new File(pathSavedClueLists);
			}
			Vector<File> tempFileArray = new Vector<File>();
			Iterator iter =  FileUtils.iterateFiles(activeFolder, new String[]{"txt","java"}, true);
			while (iter.hasNext()){
				File newFile = (File) iter.next();
				tempFileArray.add(newFile);
			}
			SOOHPServerMainGUI.listOfClueListFiles	= tempFileArray;
		}
		
		public static void nextClueList(){
			if(listOfClueListFiles.isEmpty()){
				//TODO this line has to be replaced
				questionTextArea.setText("No more clue lists");
				System.out.println("No more clue lists");
			}else{
				try {
				Scanner clueListScanner = new Scanner(listOfClueListFiles.get(pageNumber)).useDelimiter("\n");
				listOfClueListNames=(scanLine(clueListScanner.next()));	
				System.out.println("clNames"+listOfClueListNames);
				clStatusLabel.setText("Status: "+clueListScanner.next());
				clTimeStampLabel.setText("Timestamp: "+clueListScanner.next());
				clSubmittedByLabel.setText("Submitted by: "+clueListScanner.next()); 
				clPageNumberLabel.setText((Integer.toString(pageNumber+1))+" of "+listOfClueListFiles.size());
				clueListScanner.close();
				} catch (IOException ioe) {
					System.out.println(ioe.getMessage());
				}
			}
			
		}
		
		public static void previousClueList(){
			if(listOfClueListFiles.isEmpty()){
				//TODO this line has to be replaced
				System.out.println("No more clue lists");
			}else{
				try {
				Scanner clueListScanner = new Scanner(listOfClueListFiles.get(pageNumber)).useDelimiter("\n");
				listOfClueListNames=(scanLine(clueListScanner.next()));	
				System.out.println("clNames"+listOfClueListNames);
				clStatusLabel.setText("Status: "+clueListScanner.next());
				clTimeStampLabel.setText("Timestamp: "+clueListScanner.next());
				clSubmittedByLabel.setText("Submitted by: "+clueListScanner.next()); 
				clPageNumberLabel.setText((Integer.toString(pageNumber+1))+" of "+listOfClueListFiles.size());
				clueListScanner.close();
				} catch (IOException ioe) {
					System.out.println(ioe.getMessage());
				}
			}
		}
		
		//this is the line scanner used by the question scanner
		public static Vector scanLine(String line) {
			@SuppressWarnings("resource")
			Vector<String> returnVector = new Vector<>();
			Scanner lineScanner = new Scanner(line);
			lineScanner.useDelimiter(",");
			questionTextArea.setText(null);
			while(lineScanner.hasNext()){
				String nextLine = new String(lineScanner.next());
				returnVector.add(nextLine);
				questionTextArea.append(nextLine+"\n");
			}
			lineScanner.close();
			return returnVector;
		}
		

		

		
		public static void moveToSaved(){
			File savedFolder = new File(pathSavedClueLists);
			try {
				FileUtils.moveFileToDirectory(listOfClueListFiles.get(pageNumber), savedFolder, false);
				if ((pageNumber+1) == listOfClueListFiles.size())
				{
					pageNumber = pageNumber - (listOfClueListFiles.size()-1);
				}else{
					pageNumber++;
				}	
				getClueLists();
				pageNumber=0;
				nextClueList();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void moveToArchived(){
			File archivedFolder = new File(pathArchivedClueLists);
			try {
				FileUtils.moveFileToDirectory(listOfClueListFiles.get(pageNumber), archivedFolder, false);
				if ((pageNumber+1) == listOfClueListFiles.size())
				{
					pageNumber = pageNumber - (listOfClueListFiles.size()-1);
				}else{
					pageNumber++;
				}	
				getClueLists();
				pageNumber=0;
				nextClueList();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
}
