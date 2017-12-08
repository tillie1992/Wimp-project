package com.lmig.gfc.wimp.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToMany
	private List<Movie> movies;

	@OneToMany(mappedBy = "actor")
	private List<Award> awards;

	@Column(length = 75, nullable = false)
	private String firstName;

	@Column(length = 75)
	private String lastName;

	private Integer activeSinceYear;

	private Date birthDate;

	public Actor() {

	}

	public Actor(String firstName, String lastName, Integer activeSinceYear, Date birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.activeSinceYear = activeSinceYear;
		this.birthDate = birthDate;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getActiveSinceYear() {
		return activeSinceYear;
	}

	public void setActiveSinceYear(Integer activeSinceYear) {
		this.activeSinceYear = activeSinceYear;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public List<Movie> getMovies() {

		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}
