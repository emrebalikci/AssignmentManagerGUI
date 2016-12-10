/*
 * This is a simple assignment manager type.
 * 
 * Author : EMRE BALIKCI
 * Date : May 12,2014
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class AssignmentManager {
	// hold assignment collection
	private ArrayList<Assignment> assignments;
	// to read/write a file, you need to a path for specifying file location
	static String path;
	// input is a stream for reading from file
	private Scanner input;
	//output is a stream for writing to file
	private Formatter output;

	//constructor
	public AssignmentManager() {
		//create arraylist
		assignments = new ArrayList<Assignment>();
	}
	//set and get path
	public void setPath(String path) {
		this.path = path;
	}

	public static String getPath() {
		return path;
	}

	// get assignment list
	public ArrayList<Assignment> getAssignments() {
		return assignments;
	}

	//add a new assignment
	public void addAssignment(Assignment assignment){
		//check whether such a record there is or not
		int recordIndex = findAssignment(assignment.getCourseCode(), assignment.getAssignmentType(), assignment.getAssignmentNo());
		// if the assignment does not exist in your arraylist before
		if (recordIndex == -1){
			//add it to the list
			assignments.add(assignment);
			//write to the file
			openFile();
			writeRecords();
			closeFile();
		}
		else{// if the assignment already exists in your arraylist
			JOptionPane.showMessageDialog(null, "Duplicate assignment record is not allowed!","Duplicate Record",JOptionPane.ERROR_MESSAGE);
		}
	}

	//update an existing assignment
	public void updateAssignment(Assignment assignment){
		//check whether such a record there is or not
		int recordIndex = findAssignment(assignment.getCourseCode(), assignment.getAssignmentType(), assignment.getAssignmentNo());
		// if the assignment already exists in your arraylist
		if (recordIndex != -1){
			//first remove old one
			assignments.remove(recordIndex);
			//then add new one
			assignments.add(assignment);
			//write to the file
			openFile();
			writeRecords();
			closeFile();
			JOptionPane.showMessageDialog(null, "The record is updated!", "Update an Assignment", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			//System.out.println("The assignment specified cannot be found");
			JOptionPane.showMessageDialog(null, "The assignment specified cannot be found!", "Update an Assignment", JOptionPane.ERROR_MESSAGE);
		}
	}
	// list Assignments

	//remove an existing assignment
	public void deleteAssignment(Assignment assignment){
		//check whether such a record there is or not
		int recordIndex = findAssignment(assignment.getCourseCode(), assignment.getAssignmentType(), assignment.getAssignmentNo());
		//if the assignment exist  in the list
		if (recordIndex != -1){
			//remove it
			assignments.remove(recordIndex);
			//save the list to the file
			openFile();
			writeRecords();
			closeFile();
			JOptionPane.showMessageDialog(null, "The record is deleted!", "Delete an Assignment", JOptionPane.INFORMATION_MESSAGE);
		}
		else{

			JOptionPane.showMessageDialog(null, "You cannot remove it because there is no such an assignment!!", "Delete an Assignment", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void listAssignments(){
		String assignment ="";
		
		for(Assignment element : assignments){
			assignment += element+"\n";
		}
		JOptionPane.showMessageDialog(null, assignment, "Reading txt file", JOptionPane.INFORMATION_MESSAGE);
	}

	// load assignments
	public void loadAssignments(){
		//read file and store array list with these assignment info
		openFile();
		readRecords();
		closeFile();
	}

	public int findAssignment(String courseCode,String assignmentType,int assignmentNo ){
		// loop over the arraylist
		for(int i=0;i<assignments.size();i++){
			//get current array list item
			Assignment element = assignments.get(i);
			//if its course code, assignment type and assignment no matches, you find the assignment in the list
			if(element.getCourseCode().trim().equals(courseCode) && element.getAssignmentType().trim().equals(assignmentType)&& element.getAssignmentNo()==assignmentNo){
				//return the index of matched assignment 
				return i;
			}
		}
		// if there is no match, return -1
		return -1;

	}

	public void openFile(){
		try{//create two streams: one for reading file and another is for writing to the file
			input = new Scanner(new File(path));
			output = new Formatter (path);

		}catch(SecurityException e){
			JOptionPane.showMessageDialog(null, "You do not have write access to this file","Error",JOptionPane.ERROR_MESSAGE);
		
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "File not found!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	//read information from the txt file
	public void readRecords(){
		//create an empty assignment object
		Assignment record = new Assignment();
		try{
			//unless end of file
			while(input.hasNext()){
				//obtain each value and set attributes of the current assignment
				record.setCourseCode(input.next());
				record.setAssignmentType(input.next());
				record.setAssignmentNo(input.nextInt());
				record.setAssignmentDate(input.next());
				record.setDueDate(input.next());
				record.setSubmissionType(input.next());
				record.setTeamWork(input.next());
				//not to loose, add the assignment to the array list
				assignments.add(record);
			}
		}catch(NoSuchElementException nsee){

			JOptionPane.showMessageDialog(null, "No such element exception occured!","Error:",JOptionPane.ERROR_MESSAGE);

		}catch(IllegalStateException ise){
			JOptionPane.showMessageDialog(null, "Illegal State exception occured!","Error:",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void writeRecords(){
		try{
			//write to the file with an appropriate format
			for(Assignment element: assignments){
				output.format("%s  %s  %d  %s  %s  %s  %s", element.getCourseCode(),element.getAssignmentType(),element.getAssignmentNo(),element.getDueDate(),element.getDueDate(),element.getSubmissionType(),element.teamWorkInfo());
			}
		}catch(NoSuchElementException e){
			JOptionPane.showMessageDialog(null, "Formatter closed Exception!","Error",JOptionPane.ERROR_MESSAGE);
		}

	}
	//
	public void closeFile(){
		if(input!= null){
			//close input stream
			input.close();
		}
		if(output!= null){
			//close output stream
			output.close();
		}
	}
}
