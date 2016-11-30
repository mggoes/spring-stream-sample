package br.com.stream.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("retry-properties")
public class RetryProperties {
	private int maxAttemps;
	private long initialInterval;
	private double multiplier;
	private long maxInterval;
}
