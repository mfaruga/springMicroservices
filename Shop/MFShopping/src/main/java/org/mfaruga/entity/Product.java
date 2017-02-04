package org.mfaruga.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product implements Serializable {

	/** */
	private static final long serialVersionUID = -7643497755478204042L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String name;
		
	private String description;
	
	@ElementCollection(targetClass=EProductTag.class)
	@Enumerated(EnumType.STRING)	
	private List<EProductTag> tags = new ArrayList<EProductTag>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EProductTag> getTags() {
		return Collections.unmodifiableList(tags);
	}
	
	public void clearTags() {
		tags.clear();
	}
	
	public void addTag(EProductTag tag) {
		if (!tags.contains(tag)) {
			tags.add(tag);
		}
	}
	
	public void removeTag(EProductTag tag) {
		tags.remove(tag);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[Product]").append("[Name]: " ).append(name).append(" [Description]:  ").append(description).append("[Tags]: ").append(tags);
		return builder.toString();		
	}
	
}
