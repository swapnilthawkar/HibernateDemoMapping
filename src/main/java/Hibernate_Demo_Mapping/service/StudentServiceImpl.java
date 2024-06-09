package Hibernate_Demo_Mapping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Hibernate_Demo_Mapping.entity.Student;
import Hibernate_Demo_Mapping.repo.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Override
	public Student saveStudent(Student student) {

		return studentRepo.save(student);
	}

	@Override
	public List<Student> getAllStudent() {

		return studentRepo.findAll();
	}

	@Override
	public Student getStudentById(Integer studId) {
		Student getStudent = studentRepo.findById(studId)
				.orElseThrow(() -> new IllegalArgumentException("Please enter valid student Id"));
		return getStudent;
	}

	@Override
	public Student updateStudent(Integer studId, Student student) {
		Student existingStud = studentRepo.findById(studId)
				.orElseThrow(() -> new IllegalArgumentException("Please Enter valid student Id"));
		existingStud.setStudName(student.getStudName());
		existingStud.setRollNum(student.getRollNum());
		existingStud.setListLaptop(student.getListLaptop());

		Student updatedStudent = studentRepo.save(existingStud);

		return updatedStudent;

	}

	@Override
	public void deleteStudent(Integer studId) {
		Student deleteStudent = studentRepo.findById(studId)
				.orElseThrow(() -> new IllegalArgumentException("Please enter valid student Id"));

		studentRepo.delete(deleteStudent);

	}

	@Override
	public Student getByRollNum(String rollNum) {
	   	Student findByRollNum = studentRepo.findByRollNum(rollNum);
	   	return findByRollNum;
	
	}

}
