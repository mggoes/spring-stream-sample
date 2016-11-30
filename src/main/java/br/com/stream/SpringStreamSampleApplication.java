package br.com.stream;

import br.com.stream.destinations.RabbitMQChannels;
import br.com.stream.properties.RetryProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * http://docs.spring.io/spring-cloud-stream/docs/1.0.2.RELEASE/reference/htmlsingle/index.html#multiple-systems
 * http://docs.spring.io/spring-cloud-stream/docs/1.0.2.RELEASE/reference/htmlsingle/index.html#binding-properties
 */
@SpringBootApplication(exclude = RabbitAutoConfiguration.class)
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableBinding(RabbitMQChannels.class)
@EnableConfigurationProperties(RetryProperties.class)
@EnableRetry
public class SpringStreamSampleApplication {

	public static void main(final String... args) {
		SpringApplication.run(SpringStreamSampleApplication.class, args);
	}

	@Bean
	public static RetryTemplate retryTemplate(final RetryProperties properties) {
		final ExponentialBackOffPolicy backoffPolicy = new ExponentialBackOffPolicy();
		backoffPolicy.setInitialInterval(properties.getInitialInterval());
		backoffPolicy.setMaxInterval(properties.getMaxInterval());
		backoffPolicy.setMultiplier(properties.getMultiplier());

		final Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
		retryableExceptions.put(IllegalArgumentException.class, true);

		final SimpleRetryPolicy policy = new SimpleRetryPolicy(properties.getMaxAttemps(), retryableExceptions);

		final RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setBackOffPolicy(backoffPolicy);
		retryTemplate.setRetryPolicy(policy);

		return retryTemplate;
	}

}
