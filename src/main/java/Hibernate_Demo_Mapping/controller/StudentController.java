package Hibernate_Demo_Mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hibernate_Demo_Mapping.entity.Student;
import Hibernate_Demo_Mapping.service.StudentServiceImpl;

@RestController
@RequestMapping("/Api")
public class StudentController {

	@Autowired
	private StudentServiceImpl studentService;

	@PostMapping("/addStudent")
	public ResponseEntity<Student> addStudent(@RequestBody Student studnet) {
		Student addStudent = studentService.saveStudent(studnet);
		return new ResponseEntity<Student>(addStudent, HttpStatus.CREATED);

	}

	@GetMapping("/allStudent")
	public List<Student> getAllStudent() {
		List<Student> getAllStudent = studentService.getAllStudent();
		return getAllStudent;

	}

	@GetMapping("/getById")
	public ResponseEntity<Student> getStudentById(@RequestParam int studId) {
		Student getStudById = studentService.getStudentById(studId);
		return new ResponseEntity<Student>(getStudById, HttpStatus.OK);

	}

	@PutMapping("/updateStudent")
	public ResponseEntity<Student> updateStudent(@RequestParam int studId, @RequestBody Student student) {
		Student updateStudent = studentService.updateStudent(studId, student);
		return new ResponseEntity<Student>(updateStudent, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteStudent")
	public void deleteStudent(@RequestParam int studId) {
		studentService.deleteStudent(studId);

	}
	@GetMapping("/getByRollNum/{rollNum}")
	public ResponseEntity<Student> getByRollNum(@PathVariable String rollNum){
		Student getByRollNum = studentService.getByRollNum(rollNum);
		return new ResponseEntity<Student>(getByRollNum,HttpStatus.OK);
		
	}

}
