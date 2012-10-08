package fr.pierre.todos.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@Entity
@Table(name = "TODO")
@SequenceGenerator(sequenceName = "SEQ_ID_TODO", name = "SEQ_TODO_GEN", initialValue = 1, allocationSize = 1)
@XmlType(name = "TodoEntity")
@XmlAccessorType(FIELD)
public class Todo {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = SEQUENCE, generator = "SEQ_TODO_GEN")
	private Long id;

	@Column(name = "titre")
	private String titre;

	@Column(name = "description")
	private String description;

	@Column(name = "done")
	private Boolean done;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start")
	private Date start = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "stop")
	private Date stop = new Date();

	public Todo() {
	}

	public Todo(Long id, String titre, String description, Boolean done, Date start, Date stop) {
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.done = done;
		this.start = start;
		this.stop = stop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getStop() {
		return stop;
	}

	public void setStop(Date stop) {
		this.stop = stop;
	}
}
