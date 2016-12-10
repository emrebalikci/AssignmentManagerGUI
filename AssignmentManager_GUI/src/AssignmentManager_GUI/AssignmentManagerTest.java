/*
 * This application drives AssignmentManipulator class
 * 
 * Author : EMRE BALIKCI
 * Date : May 11,2014
 */


import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class AssignmentManagerTest {

	static Scanner input ;
	static AssignmentManager manager;
	static JFileChooser fileChooser;

	public static void main(String[] args) {
		// variable declarations
		String choice = "";
		manager = new AssignmentManager();
		fileChooser = new JFileChooser();

		do{	
			try{

				//print out menu && take user choice
				choice = showMenuGetOption();
				//deal with the choice
				evaluateChoice(choice);
				
			} catch(NullPointerException npe){
				JOptionPane.showMessageDialog(null, "Please choice an operation");
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "An exception is occured","Error",JOptionPane.ERROR_MESSAGE);
			}

			//exit condition
		}while(!choice.equals("Exit"));

	}


	private static void evaluateChoice(String choice) {
		switch(choice){

		case "Add a new Assignment": // add a new assignment info
			try{
				//obtain attribute values from the user
				String courseCode = JOptionPane.showInputDialog("Please enter course code");
				String assignmentType = JOptionPane.showInputDialog("Please enter assignment type[Project, Homework,Prelab,Postlab]");
				int assignmentNo = Integer.parseInt(JOptionPane.showInputDialog("Please enter assignment number"));
				String assignmentDate = JOptionPane.showInputDialog("Please enter assignment date");
				String dueDate = JOptionPane.showInputDialog("Please enter assignment due date");
				String submissionType = JOptionPane.showInputDialog("Please enter submission type [CD, e-mail, print-out, hand-written]");;
				String teamWorkInfo = JOptionPane.showInputDialog("Please enter team work info[YES,NO]");
				
				//create an assignment object with these values
				Assignment assignment = new Assignment(courseCode, assignmentType, assignmentNo, assignmentDate, dueDate, submissionType, teamWorkInfo);
				//add to the arraylist
				manager.addAssignment(assignment);

			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, "Please do not enter  a String for Assignment Number","Error", JOptionPane.ERROR_MESSAGE);
			}
			catch(NullPointerException npe){
				JOptionPane.showMessageDialog(null, "you cannot pass null","Error", JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, e,"Error", JOptionPane.ERROR_MESSAGE);
				//"An Exception is Occured"
			}


			break;

		case "Update an existing Assignment": //update an assignment info
			if(manager.getAssignments().size()<1){

				JOptionPane.showMessageDialog(null, "There is no assignment record", "Empty List", JOptionPane.INFORMATION_MESSAGE);
			}
			else{

				try{
					//obtain new attribute values from the user.
					//Note that course code, assignment type and assignment number must be same the assignment will be updated
					String courseCode = JOptionPane.showInputDialog("Please enter course code");
					String assignmentType = JOptionPane.showInputDialog("Please enter assignment type[Project, Homework,Prelab,Postlab]");
					int assignmentNo = Integer.parseInt(JOptionPane.showInputDialog("Please enter assignment number"));
					String assignmentDate = JOptionPane.showInputDialog("Please enter new assignment date");
					String dueDate = JOptionPane.showInputDialog("Please enter new assignment due date");
					String submissionType = JOptionPane.showInputDialog("Please new enter submission type [CD, e-mail, print-out, hand-written]");;
					String teamWorkInfo = JOptionPane.showInputDialog("Please enter new team work info[YES,NO]");
					Assignment assignment = new Assignment(courseCode, assignmentType, assignmentNo, assignmentDate, dueDate, submissionType, teamWorkInfo);
					//call update assignment 
					manager.updateAssignment(assignment);

				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null, "Please do not enter  a String for Assignment Number","Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(null, "you cannot pass null","Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e,"Error", JOptionPane.ERROR_MESSAGE);
					//"An Exception is Occured"
				}
			}
			break;

		case "Delete an existing Assignment": //delete an assignment info
			if(manager.getAssignments().size()<1){
				
				JOptionPane.showMessageDialog(null, "There is no assignment record", "Empty List", JOptionPane.INFORMATION_MESSAGE);

			}
			else{
				try{
					//obtain course code, assignment type and assignment number for the assignment will be updated from the user
					String courseCode = JOptionPane.showInputDialog("Please enter course code");
					String assignmentType = JOptionPane.showInputDialog("Please enter assignment type[Project, Homework,Prelab,Postlab]");
					int assignmentNo = Integer.parseInt(JOptionPane.showInputDialog("Please enter assignment number"));
					Assignment assignment = new Assignment(courseCode, assignmentType, assignmentNo, "","","","");
					
					// call the delete assignment method
					manager.deleteAssignment(assignment);

				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null, "Please do not enter  a String for Assignment Number","Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(NullPointerException npe){
					JOptionPane.showMessageDialog(null, "you cannot pass null","Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e,"Error", JOptionPane.ERROR_MESSAGE);
					//"An Exception is Occured"
				}
			}
			break;

		case "List Assignments" : // list all assignments
			if(manager.getAssignments().size()>=1){
				manager.listAssignments();
			}
			else{
				JOptionPane.showMessageDialog(null, "There is no assignment record", "Empty List", JOptionPane.INFORMATION_MESSAGE);
			}

			break;

		case "Set File Path":
			//give a title for JFileChooser 
			fileChooser.setDialogTitle("Choose a file");
			//set visibility property for JFileChooser
			fileChooser.setVisible(true);

			//get user action
			int fcState = fileChooser.showOpenDialog(null);
			//if Open button is clicked
			if (fcState  == JFileChooser.APPROVE_OPTION){
				//we have the selected file
				File selectedFile = fileChooser.getSelectedFile();
				String path = selectedFile.getPath() ;
				//check if file extension is .txt
				if(path.endsWith(".txt")){
					//set path of assignment manager to the selected file's path
					manager.setPath(path);
					manager.loadAssignments();
				}else{
					
					JOptionPane.showMessageDialog(null, "You can only choose .txt files","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
		}
	}

	// show user menu 
	private static String showMenuGetOption() {
		String msg = " This program is a simple Assignment Manager ";
		Object[] choices = {"Add a new Assignment", "Update an existing Assignment", "Delete an existing Assignment","List Assignments","Set File Path","Exit"};
		String choice = (String) JOptionPane.showInputDialog(null, msg," ",JOptionPane.PLAIN_MESSAGE,null, choices,choices[0]);
		return choice;
	}
}
