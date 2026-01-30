package aoop.assignment.studentCRUD.Controller;

import aoop.assignment.studentCRUD.Model.Student;
import aoop.assignment.studentCRUD.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") long id,@RequestBody Student studentDetails) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setDepartment(studentDetails.getDepartment());
        
        Student updatedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(updatedStudent);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable(value = "id") long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

}
