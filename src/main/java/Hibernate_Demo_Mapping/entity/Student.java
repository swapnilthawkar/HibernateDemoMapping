package Hibernate_Demo_Mapping.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENT_ID")
	private Integer studId;
	@Column(name = "STUDENT_NAME")
	private String studName;
	@Column(name = "ROLL_NUM")
	private String rollNum;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "STUDENT_ID")
	private List<Laptop> listLaptop;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Integer studId, String studName, String rollNum, List<Laptop> listLaptop) {
		super();
		this.studId = studId;
		this.studName = studName;
		this.rollNum = rollNum;
		this.listLaptop = listLaptop;
	}	

	public Integer getStudId() {
		return studId;
	}

	public void setStudId(Integer studId) {
		this.studId = studId;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public String getRollNum() {
		return rollNum;
	}

	public void setRollNum(String rollNum) {
		this.rollNum = rollNum;
	}

	public List<Laptop> getListLaptop() {
		return listLaptop;
	}

	public void setListLaptop(List<Laptop> listLaptop) {
		this.listLaptop = listLaptop;
	}

	@Override
	public String toString() {
		return "Student [studId=" + studId + ", studName=" + studName + ", rollNum=" + rollNum + ", listLaptop="
				+ listLaptop + "]";
	}

}
