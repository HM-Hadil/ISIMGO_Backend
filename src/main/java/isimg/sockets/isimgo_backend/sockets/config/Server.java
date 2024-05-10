package isimg.sockets.isimgo_backend.sockets.config;

import io.jsonwebtoken.io.IOException;
import isimg.sockets.isimgo_backend.CRUD.user.services.PublicationService;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
public class Server {

        private final PublicationService publicationService; // Inject PublicationService
        private final int port;

        public Server(PublicationService publicationService, int port) {
            this.publicationService = publicationService;
            this.port = port;
        }


    public void start() throws IOException, java.io.IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Listening on port " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // Handle client connection in a new thread
                ClientHandler clientHandler = new ClientHandler(clientSocket, publicationService);
                clientHandler.start();
            }
        }
    }


    }