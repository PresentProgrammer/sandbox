package present.programmer.rabbit;

import static java.nio.charset.StandardCharsets.UTF_8;
import static present.programmer.rabbit.Constants.QUEUE_NAME;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

public class Publisher {

	private static final String NO_EXCHANGE_NAME = "";
	private static final AMQP.BasicProperties NO_PROPERTIES = null;

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException, InterruptedException {
		final Channel channel = new MqChannelFactory().createMqChannel();
		for (int count = 0; count < 5000; count++) {
			final String message = "Message number == " + count;
			channel.basicPublish(NO_EXCHANGE_NAME, QUEUE_NAME, NO_PROPERTIES, message.getBytes(UTF_8));
			System.out.println("Published message: " + message);
			Thread.sleep(5000);
		}
	}
}
