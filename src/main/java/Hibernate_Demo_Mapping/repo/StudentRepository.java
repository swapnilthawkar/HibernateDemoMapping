package Hibernate_Demo_Mapping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Hibernate_Demo_Mapping.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{
	@Query("select s from Student s where s.rollNum = :rollNum")
	Student findByRollNum(@Param("rollNum") String rollNum);

}
