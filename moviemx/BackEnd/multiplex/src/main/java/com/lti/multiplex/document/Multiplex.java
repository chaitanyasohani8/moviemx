package com.lti.multiplex.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("multiplexdb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Multiplex {
	@Id
	private String id;
	private String multiplexName;
	private String address;
	private String numberOfScreens;

	/**
	 * @param multiplexName
	 *//*
		 * public Multiplex(String multiplexName) { super(); this.multiplexName =
		 * multiplexName; }
		 */

//(f)private Date	ReleaseDate 

}
