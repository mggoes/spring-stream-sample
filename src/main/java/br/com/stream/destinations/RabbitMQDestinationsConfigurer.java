package br.com.stream.destinations;

import java.util.concurrent.TimeUnit;

//@Configuration
public class RabbitMQDestinationsConfigurer {

	private static final String RABBIT_BINDER_NAME = "rabbit-local";
	private static final String ROUTE_KEY = "#";
	private static final Integer TTL_TIME = 1;
	private static final TimeUnit TTL_UNIT = TimeUnit.DAYS;

	private static final String REQUEST_EXCHANGE = "ha.requestExchange";
	private static final String RESPONSE_EXCHANGE = "ha.responseExchange";
	private static final String DLX = "ha.DLX";

	private static final String REQUEST_QUEUE = "ha.requestExchange.reqQueue";
	private static final String RESPONSE_QUEUE = "ha.responseExchange.respQueue";
	private static final String DLQ = "ha.DLX.deadLetterQueue";

	private static final boolean DURABLE = true;
	private static final boolean NON_EXCLUSIVE = false;
	private static final boolean NON_AUTO_DELETE = false;

	/*@Autowired
	private BinderFactory<?> binderFactory;

	@PostConstruct
	void declareDestinations() {
		final RabbitMessageChannelBinder binder = (RabbitMessageChannelBinder) this.binderFactory.getBinder(RABBIT_BINDER_NAME);
		this.declareExchanges(binder);
		this.declareQueues(binder);
		this.declareBindings(binder);
	}

	private void declareExchanges(final RabbitMessageChannelBinder binder) {
		binder.declareExchange(REQUEST_EXCHANGE, new TopicExchange(REQUEST_EXCHANGE, DURABLE, NON_AUTO_DELETE));
		binder.declareExchange(RESPONSE_EXCHANGE, new TopicExchange(RESPONSE_EXCHANGE, DURABLE, NON_AUTO_DELETE));
		binder.declareExchange(DLX, new TopicExchange(DLX, DURABLE, NON_AUTO_DELETE));
	}

	private void declareQueues(final RabbitMessageChannelBinder binder) {
		binder.declareQueue(REQUEST_QUEUE, new Queue(REQUEST_QUEUE, DURABLE, NON_EXCLUSIVE, NON_AUTO_DELETE));
		binder.declareQueue(RESPONSE_QUEUE, new Queue(RESPONSE_QUEUE, DURABLE, NON_EXCLUSIVE, NON_AUTO_DELETE, this.createResponseQueueProps()));
		binder.declareQueue(DLQ, new Queue(DLQ, DURABLE, NON_EXCLUSIVE, NON_AUTO_DELETE));
	}

	private void declareBindings(final RabbitMessageChannelBinder binder) {
		binder.declareBinding(REQUEST_QUEUE, new Binding(REQUEST_QUEUE, QUEUE, REQUEST_EXCHANGE, ROUTE_KEY, null));
		binder.declareBinding(RESPONSE_QUEUE, new Binding(RESPONSE_QUEUE, QUEUE, RESPONSE_EXCHANGE, ROUTE_KEY, null));
		binder.declareBinding(DLQ, new Binding(DLQ, QUEUE, DLX, ROUTE_KEY, null));
	}

	private Map<String, Object> createResponseQueueProps() {
		final Map<String, Object> propertiesMap = new HashMap<>();
		propertiesMap.put("x-dead-letter-exchange", DLX);
		propertiesMap.put("x-dead-letter-routing-key", DLQ);
		propertiesMap.put("x-message-ttl", TTL_UNIT.toMillis(TTL_TIME));
		return propertiesMap;
	}*/
}
