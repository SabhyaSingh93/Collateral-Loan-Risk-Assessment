package com.cts.clra.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/** pojo class for error details
 *
 */
public class ErrorDetails {

	private Date date;
	private String message;
	
}
