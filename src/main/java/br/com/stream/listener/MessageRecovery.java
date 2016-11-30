package br.com.stream.listener;

import br.com.stream.destinations.RabbitMQChannels;
import br.com.stream.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
public class MessageRecovery {

	private final RabbitMQChannels channels;

	@Autowired
	public MessageRecovery(final RabbitMQChannels channels) {
		this.channels = channels;
	}

	public boolean recover(final Client client, final String requestId, final Throwable cause) {
		log.error("An error has occurred!");
		return this.channels.error().send(MessageBuilder.withPayload(cause.getMessage())
				.setHeader("requestId", requestId)
				.setHeader(MessageHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
				.build());
	}
}
