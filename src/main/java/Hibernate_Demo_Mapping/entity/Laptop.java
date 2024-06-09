package Hibernate_Demo_Mapping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LAPTOP")
public class Laptop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LAPTOP_ID")
	private Integer lapId;
	@Column(name = "NotePad")
	private String lapModel;

	public Laptop() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Laptop(Integer lapId, String lapModel) {
		super();
		this.lapId = lapId;
		this.lapModel = lapModel;
	}

	public Integer getLapId() {
		return lapId;
	}

	public void setLapId(Integer lapId) {
		this.lapId = lapId;
	}

	public String getLapModel() {
		return lapModel;
	}

	public void setLapModel(String lapModel) {
		this.lapModel = lapModel;
	}

	@Override
	public String toString() {
		return "Laptop [lapId=" + lapId + ", lapModel=" + lapModel + "]";
	}

}
