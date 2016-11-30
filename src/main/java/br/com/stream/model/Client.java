package br.com.stream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

	private static final long serialVersionUID = -4062041240858060028L;

	private Integer id;
	private String name;

	@DateTimeFormat(iso = DATE)
	private LocalDate birthDate;

}
