package test;

//Student.java (Model)
class Student {
 private String rollNo;
 private String name;

 public String getRollNo() {
     return rollNo;
 }

 public void setRollNo(String rollNo) {
     this.rollNo = rollNo;
 }

 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }
}

//StudentView.java (View)
class StudentView {
 public void printStudentDetails(String studentName, String studentRollNo) {
     System.out.println("Student: ");
     System.out.println("Name: " + studentName);
     System.out.println("Roll No: " + studentRollNo);
 }
}

//StudentController.java (Controller)
class StudentController {
 private Student model;
 private StudentView view;

 public StudentController(Student model, StudentView view) {
     this.model = model;
     this.view = view;
 }

 public void setStudentName(String name) {
     model.setName(name);
 }

 public String getStudentName() {
     return model.getName();
 }

 public void setStudentRollNo(String rollNo) {
     model.setRollNo(rollNo);
 }

 public String getStudentRollNo() {
     return model.getRollNo();
 }

 public void updateView() {
     view.printStudentDetails(model.getName(), model.getRollNo());
 }
}

//MVCPatternTest.java
public class MVCPatternTest {
 public static void main(String[] args) {
     // Fetch student record from the database
     Student model = retrieveStudentFromDatabase();

     // Create a view to write student details on console
     StudentView view = new StudentView();

     StudentController controller = new StudentController(model, view);

     controller.updateView();

     // Update model data
     controller.setStudentName("John");
     controller.updateView();
 }

 private static Student retrieveStudentFromDatabase() {
     Student student = new Student();
     student.setName("Robert");
     student.setRollNo("10");
     return student;
 }
}
