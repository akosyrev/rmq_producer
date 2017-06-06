import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.util.concurrent.TimeoutException;

/**
 * Created by akosyrev on 06.06.2017.
 * Inspired by https://www.rabbitmq.com/tutorials/tutorial-one-java.html
 */
class RabbitmqProducer {
        private final static String QUEUE_NAME = "hello";
        static void Send(String msg) throws java.io.IOException, TimeoutException {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println(" [x] Sent '" + msg + "'");
        }
}
