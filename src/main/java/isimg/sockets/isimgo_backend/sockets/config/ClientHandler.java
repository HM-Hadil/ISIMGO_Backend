package isimg.sockets.isimgo_backend.sockets.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import isimg.sockets.isimgo_backend.CRUD.user.entity.Publications;
import isimg.sockets.isimgo_backend.CRUD.user.request.PublicationRequest;
import isimg.sockets.isimgo_backend.CRUD.user.result.PubResult;
import isimg.sockets.isimgo_backend.CRUD.user.services.PublicationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {

    private final Socket clientSocket;
    private final PublicationService publicationService;

    public ClientHandler(Socket clientSocket, PublicationService publicationService) {
        this.clientSocket = clientSocket;
        this.publicationService = publicationService;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            StringBuilder requestJsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestJsonBuilder.append(line);
            }
            String requestJson = requestJsonBuilder.toString().trim(); // Trim to remove leading/trailing whitespaces

            if (requestJson.isEmpty()) {
                writer.println("Empty request body");
                return;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            PublicationRequest request = objectMapper.readValue(requestJson, PublicationRequest.class);

            // Process request based on content
            String response;
            if ("ADD_PUBLICATION".equals(request.getContent())) {
                PubResult publication = publicationService.createPublication(request);
                response = objectMapper.writeValueAsString(publication);
            } else if ("GET_PUBLICATIONS".equals(request.getContent())) {
                List<Publications> publications = publicationService.getAllPublicationsByUserId(request.getUserId());
                response = objectMapper.writeValueAsString(publications);
            } else {
                response = "Invalid request";
            }

            // Send response back to client
            writer.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
