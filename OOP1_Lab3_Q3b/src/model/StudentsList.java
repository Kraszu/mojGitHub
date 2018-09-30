package model;

import java.util.ArrayList;

public class StudentsList {
	//creating an ArrayList which contains all students details
		private ArrayList<Student> student;
		// constructor
		public StudentsList (){
			this.student = new ArrayList<Student>();
			
		}
		//add student method
		public void addStudent (Student s){
			this.student.add(s);
		}
		// get and set for array list
		public ArrayList<Student> getStudents(){
			return student;
		}

		public void setStudents(ArrayList<Student> students) {
			this.student = students;
		}

}
