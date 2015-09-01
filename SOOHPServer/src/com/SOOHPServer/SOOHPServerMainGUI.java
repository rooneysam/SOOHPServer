package com.SOOHPServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.apache.commons.io.FileUtils;

///import com.SOOHP.SOOHPMain;
///import com.SOOHP.SOOHPMain.SOOHPUI;
///import com.SOOHP.SOOHPMain.selectButtonHandler;
//import com.SOOHP.SOOHPMain.ComboListener;
import com.SOOHPServer.Question;

public class SOOHPServerMainGUI extends JPanel {
	public static JFrame frame = new JFrame("SOOHPServer");
	public static JPanel topHalf = new JPanel(new BorderLayout());
	public static JPanel bottomHalf = new JPanel(new BorderLayout());
	public static JLabel topLabel = new JLabel();
	public static JLabel questionTypeLabel = new JLabel();
	public static JLabel chooseWordsLabel = new JLabel();
	public static JLabel questionTextLabel = new JLabel();
	public static JLabel addConditionsLabel = new JLabel();
	public static JLabel middleLabel = new JLabel();
	public static JLabel bottomLabel = new JLabel();
	public static JLabel clPageNumberLabel = new JLabel();
	public static JLabel clSubmittedByLabel = new JLabel();
	public static JLabel clStatusLabel = new JLabel();
	public static JLabel clTimeStampLabel = new JLabel();
	public static JLabel blankLabel = new JLabel("");
	public static JLabel blankLabel2 = new JLabel("");
	// public static JLabel Label1 = new JLabel("1");
	// public static JLabel Label2 = new JLabel("2");
	// public static JLabel Label3 = new JLabel("3");
	public static JTextArea questionTextArea;
	public static JTextArea conditionTextArea;
	public static JTextArea word1;
	public static JTextArea word2;
	public static JTextArea word3;
	static Vector<Question> allQuestions = new Vector<Question>();
	public static Vector<String> questionTypes = new Vector<String>();
	public static Vector<String> questionTextVector = new Vector<String>();

	public static JComboBox typeChoice = new JComboBox();
	public static JComboBox questionChoice = new JComboBox();
	public static JComboBox nrYesNoBox = new JComboBox();
	public static String SelectedType;
	public static JPanel wordPanel = new JPanel(new BorderLayout());
	public static JPanel nqButtonPanel = new JPanel(new GridLayout(1, 2));
	public static JPanel nrButtonPanel = new JPanel(new GridLayout(1, 2));

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
	public static JButton selectButton = new JButton("Select");
	public static JButton nqSave = new JButton("Save");
	public static JButton nrSave = new JButton("Save");
	public static JButton nrAddCondition = new JButton("Add");
	
	public static newClueListsButtonHandler myNewClueListsButtonHandler = new newClueListsButtonHandler();
	public static savedClueListsButtonHandler mySavedClueListsButtonHandler = new savedClueListsButtonHandler();
	public static newQuestionButtonHandler myNewQuestionButtonHandler = new newQuestionButtonHandler();
	public static newRuleButtonHandler myNewRuleButtonHandler = new newRuleButtonHandler();
	public static clNextButtonHandler myClNextButtonHandler = new clNextButtonHandler();
	public static clPreviousButtonHandler myClPreviousButtonHandler = new clPreviousButtonHandler();
	public static clSaveHandler myClSaveHandler = new clSaveHandler();
	public static clArchiveHandler myClArchiveHandler = new clArchiveHandler();
	public static clBackHandler myClBackHandler = new clBackHandler();
	public static selectButtonHandler mySelectButtonHandler = new selectButtonHandler();
	public static nqSaveHandler myNqSaveHandler = new nqSaveHandler();
	public static nrSaveHandler myNrSaveHandler = new nrSaveHandler();
	public static nrAddConditionHandler myNrAddConditionHandler = new nrAddConditionHandler();
	
	public static String pathSubmittedClueLists = "D:\\SOOHPServer\\ClueLists\\SubmittedClueLists\\";
	public static String pathSavedClueLists = "D:\\SOOHPServer\\ClueLists\\SavedClueLists\\";
	public static String pathArchivedClueLists = "D:\\SOOHPServer\\ClueLists\\ArchivedClueLists\\";
	public static String pathUpdates = "D:\\SOOHPServer\\Updates\\";
	public static String pathApplication = "D:\\SOOHPServer\\Application\\";
	public static String newOrSaved;
	public static Vector<File> listOfClueListFiles = new Vector<File>();
	public static Vector<String> listOfClueListNames = new Vector<String>();
	public static int pageNumber = 0;
	public static String selectedType = new String();
	public static String thisQuestion = new String();


	SOOHPServerMainGUI() {
		// declare button handlers
		newClueLists.addMouseListener(myNewClueListsButtonHandler);
		savedClueLists.addMouseListener(mySavedClueListsButtonHandler);
		newQuestion.addMouseListener(myNewQuestionButtonHandler);
		newRule.addMouseListener(myNewRuleButtonHandler);
		clNext.addMouseListener(myClNextButtonHandler);
		clPrevious.addMouseListener(myClPreviousButtonHandler);
		clSave.addMouseListener(myClSaveHandler);
		nqSave.addMouseListener(myNqSaveHandler);
		nrSave.addMouseListener(myNrSaveHandler);
		nrAddCondition.addMouseListener(myNrAddConditionHandler);
		clArchive.addMouseListener(myClArchiveHandler);
		clBack.addMouseListener(myClBackHandler);
		selectButton.addMouseListener(mySelectButtonHandler);

		// divide the main panel into a top and a bottom
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);

		// create top panel and add
		topHalf.setMinimumSize(new Dimension(500, 150));
		topHalf.setPreferredSize(new Dimension(550, 350));
		splitPane.add(topHalf);

		// create bottom panel and add
		bottomHalf.setMinimumSize(new Dimension(500, 150));
		bottomHalf.setPreferredSize(new Dimension(550, 350));
		splitPane.add(bottomHalf);

		scanQuestions();
		System.out.println(allQuestions.size());

		showMenuScreen();
	}

	public void createAndShowGUI() {
		// set up the main window
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

	public static void showClueListScreen() {
		topLabel.setText(newOrSaved + " Clue Lists");
		// bottomLabel.setText(newOrSaved);

		// clear everything out
		topHalf.removeAll();
		topHalf.add(topLabel, BorderLayout.NORTH);
		topHalf.setLayout((new GridLayout(5, 1)));
		topHalf.repaint();
		topHalf.setVisible(true);
		bottomHalf.removeAll();
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

		// setup question display area
		questionTextArea = new JTextArea(1, 10);
		questionTextArea.setEditable(false);
		questionTextArea.setFont(new Font("Serif", Font.ITALIC, 20));
		questionTextArea.setLineWrap(true);
		questionTextArea.setWrapStyleWord(true);
		JScrollPane questionTextPane = new JScrollPane(questionTextArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		bottomHalf.add(questionTextPane);

		// add the clue list buttons
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

		// clear everything out
		topHalf.removeAll();
		topHalf.add(topLabel, BorderLayout.NORTH);
		topHalf.setLayout((new GridLayout(5, 1)));
		topHalf.repaint();
		topHalf.setVisible(true);
		bottomHalf.removeAll();
		bottomHalf.setLayout((new GridLayout(2, 1)));
		bottomHalf.repaint();
		bottomHalf.setVisible(true);
		wordPanel.removeAll();

		// set up labels
		topLabel.setText("Choose a question type");
		middleLabel.setText("Describe the question in three words");
		bottomLabel.setText("Question text");
		bottomHalf.setLayout((new GridLayout(3, 1)));
		// the type choice combo box pulls the list of question types from
		// the questionTypes Vector
		typeChoice.removeAllItems();
		for (int b = 0; b < questionTypes.size(); b++) {
			if (!questionTypes.get(b).equals("Test")) {
				typeChoice.addItem(questionTypes.get(b));
			}
		}
		ComboListener myComboListener = null;
		myComboListener = new ComboListener();
		typeChoice.addActionListener(myComboListener);

		// setup question display area
		questionTextArea = new JTextArea(1, 10);
		questionTextArea.setEditable(true);
		questionTextArea.setFont(new Font("Serif", Font.ITALIC, 20));
		questionTextArea.setLineWrap(true);
		questionTextArea.setWrapStyleWord(true);
		JScrollPane questionTextPane = new JScrollPane(questionTextArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		word1 = new JTextArea(1, 3);
		word1.setEditable(true);
		word1.setFont(new Font("Serif", Font.ITALIC, 15));
		word1.setBorder(BorderFactory.createLineBorder(Color.black));

		word2 = new JTextArea(1, 3);
		word2.setEditable(true);
		word2.setFont(new Font("Serif", Font.ITALIC, 15));
		word2.setBorder(BorderFactory.createLineBorder(Color.black));

		word3 = new JTextArea(1, 3);
		word3.setEditable(true);
		word3.setFont(new Font("Serif", Font.ITALIC, 15));
		word3.setBorder(BorderFactory.createLineBorder(Color.black));
		wordPanel.setLayout((new GridLayout(1, 3)));
		wordPanel.add(word1);
		wordPanel.add(word2);
		wordPanel.add(word3);

		nqSave.setEnabled(false);
		nqSave.setVisible(false);

		nqButtonPanel.add(clBack);
		nqButtonPanel.add(nqSave);

		// add components
		topHalf.add(topLabel, BorderLayout.NORTH);
		topHalf.add(typeChoice);
		topHalf.add(middleLabel);
		topHalf.add(wordPanel, BorderLayout.SOUTH);

		bottomHalf.add(bottomLabel, BorderLayout.NORTH);
		bottomHalf.add(questionTextPane, BorderLayout.CENTER);
		bottomHalf.add(nqButtonPanel, BorderLayout.SOUTH);

		// /test

		frame.setVisible(true);
	}

	public static void showNewRuleScreen() {
		// clear everything out
		topHalf.removeAll();
		topHalf.setLayout((new GridLayout(1, 3)));
		topHalf.repaint();
		topHalf.setVisible(true);
		bottomHalf.removeAll();
		bottomHalf.setLayout((new GridLayout(1, 3)));
		bottomHalf.repaint();
		bottomHalf.setVisible(true);
		wordPanel.removeAll();

		// set up the question type area (questionTypePanel)
		questionTypeLabel.setText("Please fill out the following fields in order: 1.Choose a question type");
	
		JPanel questionTypePanel = new JPanel();
		questionTypePanel.setLayout((new GridLayout(2, 1)));
		// the type choice combo box pulls the list of question types from
		// the questionTypes Vector
		typeChoice.removeAllItems();
		for (int b = 0; b < questionTypes.size(); b++) {
			if (!questionTypes.get(b).equals("Test")) {
				typeChoice.addItem(questionTypes.get(b));
			}
		}
		ComboListener myComboListener = null;
		myComboListener = new ComboListener();
		typeChoice.addActionListener(myComboListener);
		questionTypePanel.add(questionTypeLabel);
		questionTypePanel.add(typeChoice);

		// set up the word choice area (wordPanel)
		JPanel wordPanel2 = new JPanel();
		chooseWordsLabel.setText("2.Describe the question in three words");
		word1 = new JTextArea(1, 1);
		word1.setEditable(true);
		word1.setFont(new Font("Serif", Font.ITALIC, 15));
		word1.setBorder(BorderFactory.createLineBorder(Color.black));
		word2 = new JTextArea(1, 1);
		word2.setEditable(true);
		word2.setFont(new Font("Serif", Font.ITALIC, 15));
		word2.setBorder(BorderFactory.createLineBorder(Color.black));
		word3 = new JTextArea(1, 1);
		word3.setEditable(true);
		word3.setFont(new Font("Serif", Font.ITALIC, 15));
		word3.setBorder(BorderFactory.createLineBorder(Color.black));
		wordPanel.setLayout((new GridLayout(2, 1)));
		wordPanel2.setLayout((new GridLayout(1, 3)));
		wordPanel.add(chooseWordsLabel);
		wordPanel.add(wordPanel2);
		wordPanel2.add(word1);
		wordPanel2.add(word2);
		wordPanel2.add(word3);

		// set up the add conditions area (nrAddConditionsPanel1,nrAddConditionsPanel2 )
		JPanel nrAddConditionsPanel1 = new JPanel();
		JPanel nrAddConditionsPanel2 = new JPanel();
		nrAddConditionsPanel1.setLayout((new GridLayout(3, 1)));
		nrAddConditionsPanel2.setLayout((new GridLayout(2, 4)));
		addConditionsLabel.setText("3.Choose the conditions for the rule");
		questionChoice.removeAllItems();
		for (int b = 0; b < questionTextVector.size(); b++) {
			questionChoice.addItem(questionTextVector.get(b));
		}
		ComboListener questionComboListener = null;
		questionComboListener = new ComboListener();
		questionChoice.addActionListener(questionComboListener);
		nrYesNoBox.removeAllItems();
		nrYesNoBox.addItem("Yes");
		nrYesNoBox.addItem("No");
		ComboListener nrYesNoBoxComboListener = null;
		nrYesNoBoxComboListener = new ComboListener();
		nrYesNoBox.addActionListener(nrYesNoBoxComboListener);
		
		nrAddConditionsPanel1.add(addConditionsLabel);
		nrAddConditionsPanel1.add(questionChoice);
		nrAddConditionsPanel1.add(nrYesNoBox);
		
		
		
		// setup condition display area
		conditionTextArea = new JTextArea(1, 10);
		conditionTextArea.setEditable(true);
		conditionTextArea.setFont(new Font("Serif", Font.ITALIC, 20));
		conditionTextArea.setLineWrap(true);
		conditionTextArea.setWrapStyleWord(true);
		JScrollPane conditionTextPane = new JScrollPane(questionTextArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		
		
		nrAddConditionsPanel2.add(nrAddCondition);
		nrAddConditionsPanel2.add(conditionTextPane);
		
		
		// set up the test text area (questionTextPanel)
		JPanel questionTextPanel = new JPanel();
		questionTextPanel.setLayout((new GridLayout(2, 1)));
		questionTextLabel.setText("Test text");
		questionTextArea = new JTextArea(1, 10);
		questionTextArea.setEditable(true);
		questionTextArea.setFont(new Font("Serif", Font.ITALIC, 20));
		questionTextArea.setLineWrap(true);
		questionTextArea.setWrapStyleWord(true);
		JScrollPane questionTextPane = new JScrollPane(questionTextArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		questionTextPanel.add(questionTextLabel);
		questionTextPanel.add(questionTextArea);

		// set up the button area (nrButtonPanel)
		nrSave.setEnabled(false);
		nrSave.setVisible(false);

		nrButtonPanel.add(clBack);
		nrButtonPanel.add(nrSave);

		// add components
		topHalf.setLayout((new GridLayout(4, 1)));
		// topHalf.add(Label1);
		// topHalf.add(Label2);
		// topHalf.add(Label3);
		topHalf.add(questionTypePanel);
		topHalf.add(wordPanel);
		topHalf.add(nrAddConditionsPanel1);
		topHalf.add(nrAddConditionsPanel2);
		
		bottomHalf.setLayout((new GridLayout(3, 1)));
		bottomHalf.add(questionTextPanel);
		bottomHalf.add(nrButtonPanel);
		// /test

		frame.setVisible(true);
	}

	// then the button action handlers
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
			showNewRuleScreen();
		}
	}

	public static class clNextButtonHandler extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {

			if ((pageNumber + 1) == listOfClueListFiles.size()) {
				pageNumber = pageNumber - (listOfClueListFiles.size() - 1);
			} else {
				pageNumber++;
			}
			nextClueList();
		}
	}

	public static class clPreviousButtonHandler extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {

			System.out.println("Page number d : " + pageNumber);
			if (pageNumber == 0) {
				pageNumber = pageNumber + (listOfClueListFiles.size() - 1);
				System.out.println("Page number e : " + pageNumber);
			} else {
				pageNumber--;
				System.out.println("Page number f : " + pageNumber);
			}
			previousClueList();
		}
	}

	public static class clSaveHandler extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			if (listOfClueListFiles.size() > 0) {
				moveToSaved();
			}

		}
	}

	public static class clArchiveHandler extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			if (listOfClueListFiles.size() > 0) {
				moveToArchived();
			}
		}
	}

	public static class clBackHandler extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			showMenuScreen();
		}
	}

	public static class nqSaveHandler extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			System.out.println("nqSave");

			// if the three word boxes don't contain illegal characters and have
			// text
			if (!((validateWordText(word1.getText()) == true)
					&& (validateWordText(word2.getText()) == true) && (validateWordText(word3
						.getText()) == true))) {
				middleLabel
						.setText("only letters and numbers allowed no spaces or symbols");
			}
			// if the question text doesn't contain illegal characters and has
			// text
			else if (!(validateQuestionText(questionTextArea.getText()) == true)) {
				bottomLabel.setText("dont put a , or a ; in here");
			}
			// append the question to allQuestions.csv in the application folder
			// TODO and in the updates folder
			else {

				PrintWriter writer;
				try {
					// the file name contains whether there was a successful
					// fix, and the date and time
					writer = new PrintWriter(new FileWriter(pathApplication
							+ "allQuestions.csv", true));

					thisQuestion = new String(selectedType);
					writer.println(thisQuestion + "," + thisQuestion + "."
							+ word1.getText() + "." + word2.getText() + "."
							+ word3.getText() + ","
							+ questionTextArea.getText());
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}
	}

	public static class nrSaveHandler extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			System.out.println("nqSave");
		}
	}
	
	
	public static class nrAddConditionHandler extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			System.out.println("nqSave");
		}
	}

	// /Listens to the combo box
	public static class ComboListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			selectedType = (String) typeChoice.getSelectedItem();
			System.out.println((String) typeChoice.getSelectedItem() + "1");
			nqSave.setEnabled(true);
			nqSave.setVisible(true);
		}
	}

	// then other methods

	public static boolean validateWordText(String inputText) {
		boolean match = inputText.matches("[a-zA-Z0-9]+");
		if (inputText.isEmpty()) {
			match = false;
		}
		return match;
	}

	public static boolean validateQuestionText(String inputText) {
		boolean match = !(inputText.contains(",") || inputText.contains(";"));
		if (inputText.isEmpty()) {
			match = false;
		}
		return match;
	}

	public static void getClueLists() {
		File savedFolder = new File(pathSavedClueLists);
		File submittedFolder = new File(pathSubmittedClueLists);
		File activeFolder = new File(pathSubmittedClueLists);
		if (newOrSaved == "Saved") {
			activeFolder = new File(pathSavedClueLists);
		}
		Vector<File> tempFileArray = new Vector<File>();
		Iterator iter = FileUtils.iterateFiles(activeFolder, new String[] {
				"txt", "java" }, true);
		while (iter.hasNext()) {
			File newFile = (File) iter.next();
			tempFileArray.add(newFile);
		}
		SOOHPServerMainGUI.listOfClueListFiles = tempFileArray;
	}

	public static void nextClueList() {
		if (listOfClueListFiles.isEmpty()) {
			// TODO this line has to be replaced
			questionTextArea.setText("No more clue lists");
			System.out.println("No more clue lists");
		} else {
			try {
				Scanner clueListScanner = new Scanner(
						listOfClueListFiles.get(pageNumber)).useDelimiter("\n");
				listOfClueListNames = (scanLine(clueListScanner.next()));
				System.out.println("clNames" + listOfClueListNames);
				clStatusLabel.setText("Status: " + clueListScanner.next());
				clTimeStampLabel
						.setText("Timestamp: " + clueListScanner.next());
				clSubmittedByLabel.setText("Submitted by: "
						+ clueListScanner.next());
				clPageNumberLabel.setText((Integer.toString(pageNumber + 1))
						+ " of " + listOfClueListFiles.size());
				clueListScanner.close();
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}

	}

	public static void previousClueList() {
		if (listOfClueListFiles.isEmpty()) {
			// TODO this line has to be replaced
			System.out.println("No more clue lists");
		} else {
			try {
				Scanner clueListScanner = new Scanner(
						listOfClueListFiles.get(pageNumber)).useDelimiter("\n");
				listOfClueListNames = (scanLine(clueListScanner.next()));
				System.out.println("clNames" + listOfClueListNames);
				clStatusLabel.setText("Status: " + clueListScanner.next());
				clTimeStampLabel
						.setText("Timestamp: " + clueListScanner.next());
				clSubmittedByLabel.setText("Submitted by: "
						+ clueListScanner.next());
				clPageNumberLabel.setText((Integer.toString(pageNumber + 1))
						+ " of " + listOfClueListFiles.size());
				clueListScanner.close();
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
	}

	// this is the line scanner used by the question scanner
	public static Vector scanLine(String line) {
		@SuppressWarnings("resource")
		Vector<String> returnVector = new Vector<>();
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		questionTextArea.setText(null);
		while (lineScanner.hasNext()) {
			String nextLine = new String(lineScanner.next());
			returnVector.add(nextLine);
			questionTextArea.append(nextLine + "\n");
		}
		lineScanner.close();
		return returnVector;
	}

	public static void moveToSaved() {
		File savedFolder = new File(pathSavedClueLists);
		try {
			FileUtils.moveFileToDirectory(listOfClueListFiles.get(pageNumber),
					savedFolder, false);
			if ((pageNumber + 1) == listOfClueListFiles.size()) {
				pageNumber = pageNumber - (listOfClueListFiles.size() - 1);
			} else {
				pageNumber++;
			}
			getClueLists();
			pageNumber = 0;
			nextClueList();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void moveToArchived() {
		File archivedFolder = new File(pathArchivedClueLists);
		try {
			FileUtils.moveFileToDirectory(listOfClueListFiles.get(pageNumber),
					archivedFolder, false);
			if ((pageNumber + 1) == listOfClueListFiles.size()) {
				pageNumber = pageNumber - (listOfClueListFiles.size() - 1);
			} else {
				pageNumber++;
			}
			getClueLists();
			pageNumber = 0;
			nextClueList();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// this method scans in all the questions from a file to the allQuestions
	// array
	public static void scanQuestions() {
		// /test read in questions from file
		try {
			@SuppressWarnings("resource")
			Scanner questionScanner = new Scanner(new File(pathApplication
					+ "allQuestions.csv")).useDelimiter("\n");
			while (questionScanner.hasNext()) {
				// /this bit passes each line to a separate scanner which turns
				// it into a question
				allQuestions.add(scanQLine(questionScanner.next()));
			}
			for (int x = 0; x < allQuestions.size(); x++) {
				if (!(questionTypes.contains(allQuestions.get(x)
						.getQuestionType()))) {
					questionTypes.add(allQuestions.get(x).getQuestionType());
				}
			}
			// /add questionTextVector scanner here
			for (int x = 0; x < allQuestions.size(); x++) {
				if (!(questionTextVector.contains(allQuestions.get(x)
						.getQuestionText()))) {
					questionTextVector.add(allQuestions.get(x)
							.getQuestionText());
				}
			}

			questionScanner.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

	}

	// this is the line scanner used by the question scanner
	public static Question scanQLine(String line) {
		@SuppressWarnings("resource")
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		String qType = lineScanner.next();
		String qName = lineScanner.next();
		String qText = lineScanner.next();
		return new Question(qType, qName, qText);
	}

	public static class selectButtonHandler extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			System.out.println("test123");
		}
	}

}
