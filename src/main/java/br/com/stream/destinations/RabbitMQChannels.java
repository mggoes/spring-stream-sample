package br.com.stream.destinations;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface RabbitMQChannels {

	String REQUEST_CHANNEL = "requestChannel";
	String RESPONSE_CHANNEL = "responseChannel";
	String ERROR_CHANNEL = "errorChannel";

	@Input(REQUEST_CHANNEL)
	SubscribableChannel request();

	@Output(RESPONSE_CHANNEL)
	MessageChannel response();

	@Output(ERROR_CHANNEL)
	MessageChannel error();
}
