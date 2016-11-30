package br.com.stream.listener;

import br.com.stream.model.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import static br.com.stream.destinations.RabbitMQChannels.REQUEST_CHANNEL;

@Slf4j
@Component
public class MessageListener {

	private final RetryTemplate retryTemplate;
	private final MessageHandler handler;
	private final MessageRecovery recovery;

	@Autowired
	public MessageListener(final RetryTemplate retryTemplate, final MessageHandler handler, final MessageRecovery recovery) {
		this.retryTemplate = retryTemplate;
		this.handler = handler;
		this.recovery = recovery;
	}

	@StreamListener(REQUEST_CHANNEL)
	public void receiveMessage(final Client client, @Header("requestId") final String requestId, @Header("error") final String error) throws JsonProcessingException {
		this.retryTemplate.execute(context -> this.handler.handle(client, requestId, error), context -> this.recovery.recover(client, requestId, context.getLastThrowable()));
	}
}
