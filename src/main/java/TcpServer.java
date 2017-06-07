import java.net.*;
import java.io.*;

/**
 * Created by akosyrev on 06.06.2017.
 * Inspired by http://www.quizful.net/post/java-socket-programming
 */
public class TcpServer {
    public static void main(String[] var) {
        int port = 6789;
        try {
            //Listen port and accept messages for RabbitMQ
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Listening at port "+ Integer.toString(port));
            //Wait for messages in an endless loop
            //and make Idea be quiet about this:
            //noinspection InfiniteLoopStatement
            while(true) {
                //wait for a new connection
                Socket client = ss.accept();
                //Read message
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String inString;
                inString = input.readLine();
                //Print message
                System.out.println("Sending message \'" + inString +"\' to rabbitmq");
                //Send message to RMQ
                RabbitmqProducer.Send(inString);
                //Close connection
                client.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
