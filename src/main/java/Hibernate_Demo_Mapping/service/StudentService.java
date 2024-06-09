package Hibernate_Demo_Mapping.service;

import java.util.List;

import Hibernate_Demo_Mapping.entity.Student;

public interface StudentService {
	public Student saveStudent(Student student);
	public List<Student> getAllStudent();
	public Student getStudentById(Integer studId);
	public Student updateStudent(Integer studId,Student student);
	public void deleteStudent(Integer studId);
	public Student getByRollNum(String rollNum);

}
