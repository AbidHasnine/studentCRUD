package aoop.assignment.studentCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//Maven-SpringBoot3.5.10-H2-BasicFntEnd(Thymeleaf not used for REST api)
//Used swagger for api hits check -> http://localhost:8080/swagger-ui/index.html#/
//WebConfig to automatic redirection to main page
//Abid_0112410208
@SpringBootApplication
public class StudentCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCrudApplication.class, args);
	}

}
