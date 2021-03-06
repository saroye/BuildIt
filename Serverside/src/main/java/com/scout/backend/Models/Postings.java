package com.scout.backend.Models;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Postings")
public class Postings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//find out a way to call in a foreign table
	//@OneToOne(mappedBy = "posting")
	@ManyToOne
	@JoinColumn
	private User user;
	
	@NotEmpty
	private String projectname;
	
	@NotEmpty
	private String projectdescription;
	
	@NotEmpty
	private String neededroles;
	
	@NotEmpty
	private String compensation;
	
	private Blob picture;
	
	@NotEmpty
	private String thumbnaildescription;
	
	
	public String getProjectName() {
		return projectname;
	}
	
	public void setProjectName(String projectname) {
		this.projectname = projectname;
	}
	
	public String getProjectDescription() {
		return projectdescription;
	}
	
	public void setProjectDescription(String projectdescription) {
		this.projectdescription = projectdescription;
	}
	
	public String getNeededRoles() {
		return neededroles;
	}
	
	public void setNeededRoles(String neededroles) {
		this.neededroles = neededroles;
	}
	
	public String getCompensation() {
		return compensation;
	}
	
	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}
	
	public String getThumbnailDescription() {
		return thumbnaildescription;
	}
	
	public void setThumbnailDescription(String thumbnaildescription) {
		this.thumbnaildescription = thumbnaildescription;
	}
	
	@Override
    public String toString() {
		final StringBuilder sb = new StringBuilder("Posting{");
		sb.append("user_id=").append(user.getId());
		sb.append(", projectname= ").append(projectname);
		sb.append(", project description= ").append(projectdescription);
		sb.append(", compensation= ").append(compensation);
		sb.append(", thumbnail description= ").append(thumbnaildescription);
		sb.append("}");
		return sb.toString();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}