package present.programmer.rabbit;

import static present.programmer.rabbit.Constants.QUEUE_NAME;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

class MqChannelFactory {

	private static final boolean DURABLE = true;
	private static final boolean NON_EXCLUSIVE = false;
	private static final boolean NO_AUTO_DELETE = false;
	private static final Map<String, Object> NO_ARGUMENTS = null;

	Channel createMqChannel() throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException, IOException {
		final ConnectionFactory factory = new ConnectionFactory();
        // factory.setUri("amqp://test:test@localhost"); - for Docker RabbitMQ
		factory.setHost("localhost");
		factory.setConnectionTimeout(300000);
		final Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, DURABLE, NON_EXCLUSIVE, NO_AUTO_DELETE, NO_ARGUMENTS);
		return channel;
	}
}
