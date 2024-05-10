package isimg.sockets.isimgo_backend;

import isimg.sockets.isimgo_backend.CRUD.user.services.PublicationService;
import isimg.sockets.isimgo_backend.sockets.config.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.io.IOException;

@SpringBootApplication
@EnableWebSocket
public class IsimgoBackendApplication implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PublicationService publicationService; // Inject PublicationService

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Replace with your actual port number
        int port = 9091;

        Server server = new Server(publicationService, port);
        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Autowired

    public static void main(String[] args) {
        SpringApplication.run(IsimgoBackendApplication.class, args);
    }


}
