package present.programmer.rabbit;

import static java.nio.charset.StandardCharsets.UTF_8;
import static present.programmer.rabbit.Constants.QUEUE_NAME;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer {

	private static final boolean NO_AUTO_ACKNOWLEDGE = false;
	private static final boolean SINGLE = false;
	private static final boolean DO_NOT_REQUEUE = false;

	@SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, IOException, URISyntaxException, InterruptedException {
		final Channel channel = new MqChannelFactory().createMqChannel();
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, NO_AUTO_ACKNOWLEDGE, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			if (delivery != null) {
				try {
					final String message = new String(delivery.getBody(), UTF_8);
					System.out.println("Message consumed: " + message);
					channel.basicAck(delivery.getEnvelope().getDeliveryTag(), SINGLE);
				} catch (final Exception e) {
					long deliveryTag = delivery.getEnvelope().getDeliveryTag();
					System.err.println("Rejecting, delivery tag: " + deliveryTag);
					channel.basicReject(deliveryTag, DO_NOT_REQUEUE);
				}
			}
			Thread.sleep(2500);
		}
	}
}
