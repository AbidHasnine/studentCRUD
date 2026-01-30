package aoop.assignment.studentCRUD.Controller;


import aoop.assignment.studentCRUD.Model.Student;
import aoop.assignment.studentCRUD.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listStudents",studentService.getAllStudents());
        return "index";
    }

    @GetMapping("/addStudent")
    public String addStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "add_student";
    }
}
