package model;

public class Student {
	// students array list content
		private String fName;
		private String lName;
		private int marks;
		// constructor
		public Student(String fName, String lName, int marks){
			setfName (fName);
			setlName(lName);
			setMarks(marks);
			
		}
		// getters and setters
		public String getfName() {
			return fName;
		}
		public void setfName(String fName) {
			this.fName = fName;
		}
		public String getlName() {
			return lName;
		}
		public void setlName(String lName) {
			this.lName = lName;
		}
		public int getMarks() {
			return marks;
		}
		public void setMarks(int marks) {
			this.marks = marks;
		}

}
