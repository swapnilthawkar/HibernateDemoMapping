package Hibernate_Demo_Mapping.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Hibernate_Demo_Mapping.controller.StudentController;
import Hibernate_Demo_Mapping.entity.Laptop;
import Hibernate_Demo_Mapping.entity.Student;
import Hibernate_Demo_Mapping.service.StudentServiceImpl;
import ch.qos.logback.core.status.Status;

@WebMvcTest(StudentController.class)
@ExtendWith(SpringExtension.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentServiceImpl studentServiceMock;

	@Test
	public void testAddStudent() throws Exception {

		Student student = new Student();
		student.setStudId(1);
		student.setStudName("Rahul");
		student.setRollNum("12234");

		Laptop laptop = new Laptop();
		laptop.setLapId(1);
		laptop.setLapModel("Acer");
		List<Laptop> laptopList = new ArrayList<>();
		laptopList.add(laptop);

		student.setListLaptop(laptopList);

		when(studentServiceMock.saveStudent(any(Student.class))).thenReturn(student);

		String requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/student-request.json")));

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/Api/addStudent")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody)).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status);

		// Mvc to Json
		String contentAsString = result.getResponse().getContentAsString();

		// json_String to Student Object
		Student readValue = new ObjectMapper().readValue(contentAsString, Student.class);

		assertNotNull(readValue);
	}

	@Test
	public void getAllStudent() throws Exception {

		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student(1, "John Doe", "12345", new ArrayList<>()));
		studentList.add(new Student(2, "Jane Smith", "67890", new ArrayList<>()));

		when(studentServiceMock.getAllStudent()).thenReturn(studentList);

		// perform Get Request
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/Api/allStudent").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String string = mvcResult.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Student> responce = mapper.readValue(string, new TypeReference<List<Student>>() {
		});
		assertNotNull(responce);
		assertEquals(2, responce.size());

		// verify first student
		Student stud1 = responce.get(0);
		assertEquals(1, stud1.getStudId());
		assertEquals("John Doe", stud1.getStudName());
		assertEquals("12345", stud1.getRollNum());
		// verify second student
		Student student2 = responce.get(1);
		assertEquals(2, student2.getStudId());
		assertEquals("Jane Smith", student2.getStudName());
		assertEquals("67890", student2.getRollNum());

	}
	@Test
	public void getStudentById() throws Exception {
		
		 Student student = new Student(1, "John Doe", "12345", new ArrayList<>());
		 when(studentServiceMock.getStudentById(1)).thenReturn(student);
		 
		 MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/Api/getById").
				 param("studId","1")).andExpect(status().isOk()).andReturn();
		 
		String content = mvcResult.getResponse().getContentAsString();
		
		ObjectMapper mapper = new ObjectMapper();
		Student value = mapper.readValue(content,Student.class);
		
		assertNotNull(value);
		assertEquals(student.getStudId(),value.getStudId());
		assertEquals(student.getStudName(),value.getStudName());
		assertEquals(student.getRollNum(),value.getRollNum());
		
		
	}

}
