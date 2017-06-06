import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.util.concurrent.TimeoutException;

/**
 * Created by akosyrev on 06.06.2017.
 * Inspired by https://www.rabbitmq.com/tutorials/tutorial-one-java.html
 */
class RabbitmqProducer {
        //Set rmq queue name
        private final static String QUEUE_NAME = "hello";
        static void Send(String msg) throws java.io.IOException, TimeoutException {
            try {
                //init rmq-client
                ConnectionFactory factory = new ConnectionFactory();
                //set rmq host
                factory.setHost("localhost");
                // make connection
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();
                // Declare queue. This is idempotent - no error occurres if queue already exists
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                //Put message
                channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
                //Report about that
                System.out.println(" [x] Sent '" + msg + "'");
                //Close channel and connection
                //We do it after every message
                channel.close();
                connection.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
}
