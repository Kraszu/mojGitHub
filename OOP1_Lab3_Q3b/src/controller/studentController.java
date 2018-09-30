package controller;

//import java.util.ArrayList;
import java.util.Scanner;

import model.Student;
import model.StudentsList;

public class studentController {
	//access to model
		private StudentsList studentModel;
		Scanner n = new Scanner (System.in);
		
		// constructor
		public studentController (){
			this.studentModel = new StudentsList();
		}
		// creating new student array list values 
		public void addStudents(String fName, String lName, int marks){
			Student s = new Student(fName, lName, marks);
			this.studentModel.addStudent(s);
		}
		
		public void process(){
			// interactive input students details and marks
			
			String fName = "";
			String lName = "";
			int marks =0;
			System.out.println("Please enter Student's first name");
			fName = n.next();
			System.out.println("Please enter Student's last name");
			lName = n.next();
			System.out.println("Please enter Student's marks");
			marks = n.nextInt();
			addStudents(fName,  lName,  marks);
			
		}
		
		public void processRmStudent(){// interactive input students names to remove
			
			String fName = "";
			String lName = "";
			System.out.println("Please enter Student's first name");
			fName = n.next();
			System.out.println("Please enter Student's last name");
			lName = n.next();
			removeStudent(fName, lName);
		}
		
		
		
		// this method allows to output student grade base on inputed by user student's info 
		public void markRangeGrade(){
			// for loop going through ArrayList stusentsDetails checking marks.
			for (Student sGrade : this.studentModel.getStudents()){
				// if marks meets given criteria system prints output using getters
				if (sGrade.getMarks()>84 && sGrade.getMarks()<=100){
					System.out.println("Student " +sGrade.getfName()+" "+ sGrade.getlName()+" received a  Distinction for his mark "+ sGrade.getMarks());
				}
				if (sGrade.getMarks()>64 && sGrade.getMarks()<85){
					System.out.println("Student " +sGrade.getfName()+" "+ sGrade.getlName()+" received a  Merit for his mark "+ sGrade.getMarks());
				}
				if (sGrade.getMarks()>39 && sGrade.getMarks()<65){
					System.out.println("Student " +sGrade.getfName()+" "+ sGrade.getlName()+" received a  Pass for his mark "+ sGrade.getMarks());
				}
				if (sGrade.getMarks()>0 && sGrade.getMarks()<40){
					System.out.println("Student " +sGrade.getfName()+" "+ sGrade.getlName()+" have Faild for his mark "+ sGrade.getMarks());
				}
			}
		}
		//method removeStudent iterate through the arraylist and check if name we want to remove is on the list		
		public void removeStudent(String fName, String lName){
			int studentToRemove = -1;
			for (Student theStudent : this.studentModel.getStudents()){
				if (theStudent.getfName().equals(fName)&& theStudent.getlName().equals(lName)){
					studentToRemove = this.studentModel.getStudents().indexOf(theStudent);
					//if name match student will be removed
//					System.out.println(studentToRemove);  //0
//					System.out.println(this.studentModel.getStudents().size()); //1
				}
				else {
					System.out.println("Student do not exist in the records...");
				}
			}
			
			if(studentToRemove>-1){
				this.studentModel.getStudents().remove(studentToRemove);
			}
			
//			ArrayList<Student> testList = studentModel.getStudents();
//			for (int x = 0; x < testList.size(); x++){
//				if (testList.get(x).getfName().equals(fName)&& testList.get(x).getlName().equals(lName)){
//					//if name match student will be removed
//					testList.remove(x);
//				}
//				else {8
//					System.out.println("Student do not exist in the records...");
//				}
//			}
			
		}

}
