package br.com.stream.listener;

import br.com.stream.destinations.RabbitMQChannels;
import br.com.stream.model.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
public class MessageHandler {

	private final RabbitMQChannels channels;
	private final ObjectMapper mapper;

	@Autowired
	public MessageHandler(final RabbitMQChannels channels, final ObjectMapper mapper) {
		this.channels = channels;
		this.mapper = mapper;
	}

	public boolean handle(final Client client, final String requestId, final String error) throws JsonProcessingException {
		log.info("Success!");
		client.setId(new SecureRandom().nextInt(9999));
		if ("illegal".equals(error)) {
			throw new IllegalArgumentException("Teste de erro illegal!");
		} else if ("runtime".equals(error)) {
			throw new RuntimeException("Teste de erro runtime!");
		}
		return this.channels.response().send(MessageBuilder.withPayload(this.mapper.writeValueAsString(client))
				.setHeader("requestId", requestId)
				.setHeader(MessageHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
				.build());
	}
}
