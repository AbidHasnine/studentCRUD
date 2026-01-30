package aoop.assignment.studentCRUD.Service;

import aoop.assignment.studentCRUD.Model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudentById(long id);
    void deleteStudentById(long id);
}
