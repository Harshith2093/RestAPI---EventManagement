package com.jsp.eventmanagement.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event {

	@Id
	@GeneratedValue(stratergy = generatedType.Identity)
	private int id;
	@JsonProperty(value = "Event-Name")
	@NotBlank
	@Size(min = 3, max = 25,message = "The Name should containe Atleast 3 Character")
	private String name;
	
	@NotBlank
	@Size(min = 3, max = 250,message = "The Name should containe Atleast 3 Character")
	private String discription;
	
	@NotBlank
	@Size(min = 3, max = 25,message = "The Name should containe Atleast 3 Character")
	private String place;

	@Future(message = "The Event Date Should be in the Future")
	@JsonFormat(pattern = "dd/MM/yyyy") // Correct format for the date
	private LocalDate eventDate;
}
