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
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Listening at port "+ Integer.toString(port));
            //noinspection InfiniteLoopStatement
            while(true) {
                Socket client = ss.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String inString;
                inString = input.readLine();
                System.out.println("Sending message \'" + inString +"\' to rabbitmq");
                RabbitmqProducer.Send(inString);
                client.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
