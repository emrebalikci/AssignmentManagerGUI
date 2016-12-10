/*
 * This is a simple type for an Assignment
 
 Author : EMRE BALIKCI
 */


public class Assignment {
	//instance variables
	private String courseCode;
	private String assignmentType;
	private int assignmentNo;
	private String assignmentDate;
	private String dueDate;
	private String submissionType;
	private String teamWorkInfo;
	
	//no-argument constructor 
	public Assignment(){
		// with dummy values 
		this("","",0,"","","","");
	}
	
	// constructor
	public Assignment(String courseCode, String assignmentType,
			int assignmentNo, String assignmentDate, String dueDate,
			String submissionType, String teamWorkInfo) {
		
		this.courseCode = courseCode;
		this.assignmentType = assignmentType;
		this.assignmentNo = assignmentNo;
		this.assignmentDate = assignmentDate;
		this.dueDate = dueDate;
		this.submissionType = submissionType;
		this.teamWorkInfo = teamWorkInfo;
	}
	
	//getters and setters
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getAssignmentType() {
		return assignmentType;
	}
	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
	}
	public int getAssignmentNo() {
		return assignmentNo;
	}
	public void setAssignmentNo(int assignmentNo) {
		this.assignmentNo = assignmentNo;
	}
	public String getAssignmentDate() {
		return assignmentDate;
	}
	public void setAssignmentDate(String assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getSubmissionType() {
		return submissionType;
	}
	public void setSubmissionType(String submissionType) {
		this.submissionType = submissionType;
	}
	public String teamWorkInfo() {
		return teamWorkInfo;
	}
	public void setTeamWork(String isTeamWork) {
		this.teamWorkInfo = isTeamWork;
	}

	//toString
	@Override
	public String toString() {
		return "courseCode=" + courseCode + ", assignmentType="
				+ assignmentType + ", assignmentNo=" + assignmentNo
				+ ", assignmentDate=" + assignmentDate + ", dueDate=" + dueDate
				+ ", submissionType=" + submissionType + ", isTeamWork="
				+ teamWorkInfo;
	}
	
	
	
	
}
