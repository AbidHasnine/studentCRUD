package aoop.assignment.studentCRUD.Service;

import aoop.assignment.studentCRUD.Model.Student;
import aoop.assignment.studentCRUD.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStudentById(long id) {
        this.studentRepository.deleteById(id);
    }
}
