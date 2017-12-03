package com.smilegate.serverdevcamp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class URL {
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String originalURL;
	
	@Transient
	private String ShortenedURL;
}
