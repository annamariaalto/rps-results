package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {

    private static final String POST_API_URL = "https://bad-api-assignment.reaktor.com/rps/history";

    public static void main( String[] args ) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POST_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //parse JSON into objects
        ObjectMapper objectMapper = new ObjectMapper();
        //ObjectNode objectNode = objectMapper.createObjectNode();

        JsonNode jsonNode = objectMapper.readTree(response.body());

        JsonNode cursor = jsonNode.get("cursor");
        System.out.println("Firstly: " + cursor);

        JsonNode data = jsonNode.get("data");
        //System.out.println("Secondly: " + data);

        traverse(data);

    }
    //Traverse through array
    public static void traverse (JsonNode root) {
        if (root.isArray()) {
            ArrayNode arrayNode = (ArrayNode) root;
            for (int i = 0; i < arrayNode.size(); i++) {
                JsonNode arrayElement = arrayNode.get(i);
                System.out.println("Result no: " + i + " " + arrayElement);
            }
        } else {
            System.out.println("Invalid element ID");
        }
    }
}
