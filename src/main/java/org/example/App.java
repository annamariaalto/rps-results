package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.security.jgss.GSSUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

@SpringBootApplication
public class App {

    private static final String POST_API_URL = "https://bad-api-assignment.reaktor.com/rps/history";

    public static void main( String[] args ) throws IOException, InterruptedException {
        //SpringApplication.run(App.class, args);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POST_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //parse JSON into objects
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.body());

        //Handle the cursor
        JsonNode cursor = jsonNode.get("cursor");
        String cursorText = cursor.asText();
        System.out.println("Here is the cursor: " + cursorText);

        //Handle the data
        JsonNode data = jsonNode.get("data");

        //traverse(data);
        personalResults(data);

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

    //To look individual's results
    public static void personalResults (JsonNode root) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the player's full name: ");
        String merkki = scanner.nextLine();

        if (root.isArray()) {
            int games = 0;
            int round = 0;
            int wins = 0;
            String winner = null;

            ArrayNode arrayNode = (ArrayNode) root;
            for (int i = 0 ; i < arrayNode.size(); i++) {
                JsonNode arrayElement = arrayNode.get(i);

                if (arrayElement.has("type")) {
                    JsonNode player1 = arrayElement.get("playerA");
                    JsonNode player2 = arrayElement.get("playerB");

                    JsonNode name1 = player1.get("name");
                    String nameText1 = name1.asText();

                    JsonNode name2 = player2.get("name");
                    String nameText2 = name2.asText();

                    if (nameText1.equals(merkki) || nameText2.equals(merkki)){
                        games++;
                        round++;

                        //Calculate the winner
                        JsonNode playedA = player1.get("played");
                        String playedAText = playedA.asText();

                        JsonNode playedB = player2.get("played");
                        String playedBText = playedB.asText();

                        System.out.println("Results for round no.: " + round);
                        System.out.println("Player A: " + nameText1 + " played " + playedAText);
                        System.out.println("Player B: " + nameText2 + " played " + playedBText);

                        String winnerHand = calculateWinner(playedAText, playedBText);

                        if (winnerHand.equals("playerA")) {
                            winner = nameText1;
                            System.out.println("Winner is " + nameText1 + "!");
                        } else if (winnerHand.equals("playerB")) {
                            winner = nameText2;
                            System.out.println("Winner is " + nameText2 + "!");
                        } else {
                            System.out.println("It's a tie!");
                        }
                        if (merkki.equals(winner)) wins++;
                        System.out.println();
                    }

                } else {
                    System.out.println("The player " + merkki + " has played " + games + " games.");
                }
            }

            double winPerc = calculateWinRatio(games, wins);
            final double winPercRound = (double) Math.round(winPerc * 10) / 10;
            System.out.println("The player " + merkki + " has played " + games + " games, which of (s)he has won " + wins + ".");
            System.out.println(merkki + "'s win ratio is " + winPercRound + " %.");
        }
    }

    //Calculate the winner
    public static String calculateWinner(String playerA, String playerB) {
        String winner = null;

        if (playerA.equals("ROCK") && (playerB.equals("SCISSORS"))) {
            winner = "playerA";
        } else if (playerA.equals("SCISSORS") && (playerB.equals("ROCK"))) {
            winner = "playerB";
        } else if (playerA.equals("SCISSORS") && (playerB.equals("PAPER"))) {
            winner = "playerA";
        } else if (playerA.equals("PAPER") && (playerB.equals("SCISSORS"))) {
            winner = "playerB";
        } else if (playerA.equals("PAPER") && (playerB.equals("ROCK"))) {
            winner = "playerA";
        } else if (playerA.equals("ROCK") && (playerB.equals("PAPER"))) {
            winner = "playerB";
        } else {
            winner = "tie";
        }

        return winner;
    }

    //Calculate the win ratio
    public static double calculateWinRatio (int games, int wins) {

        double result = ((double) wins/games)*100;
        return result;
    }

    //Player's most played hand
    public static String mostPlayedHand () {

        return null;
    }
}
