package com.wimfra.tourplanner.businesslayer.mapquest;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wimfra.tourplanner.businesslayer.file.CSVFileImport;
import com.wimfra.tourplanner.configuration.AppConfiguration;
import com.wimfra.tourplanner.configuration.AppConfigurationLoader;
import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MapQuestAPI {
        private static final ILoggerWrapper logger = LoggerFactory.getLogger(MapQuestAPI.class);
        private static AppConfiguration appConfiguration =
        AppConfigurationLoader.getInstance().getAppConfiguration();

        public static Map<String, Object> getDirections(String from, String to) {
            try {
                Map<String, Object> directions = new HashMap<>();
                logger.debug("Getting directions from MapQuestAPI...");

                String params = "?key=" + appConfiguration.getMapquestKey() + "&from=" + from + "&to=" + to;
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://www.mapquestapi.com/directions/v2/route" + params))
                        .build();

                CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

                String body = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode obj = mapper.readTree(body);

                // Distance
                directions.put("distance", obj.get("route").get("distance").floatValue());

                //Time
                directions.put("time", obj.get("route").get("time").doubleValue());

                // Session
                directions.put("session", obj.get("route").get("sessionId").textValue());

                // BoundingBox
                JsonNode ul = obj.get("route").get("boundingBox").get("ul");
                JsonNode lr = obj.get("route").get("boundingBox").get("lr");
                directions.put("boundingBox", ul.get("lat").floatValue() + "," + ul.get("lng").floatValue() + "," + lr.get("lat").floatValue() + "," + lr.get("lng").floatValue());

                logger.debug("Received directions from MapQuestAPI successfully!");
                return directions;
            } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
                logger.error(e.getStackTrace().toString());
            }
            return null;
        }

        public static byte[] getStaticMap(String from, String to) {
            try {
                Map<String, Object> directions = MapQuestAPI.getDirections(from, to);
                logger.debug("Getting map from MapQuestAPI...");
                assert directions != null;
                String params = "?key=" + appConfiguration.getMapquestKey()
                        + "&size=640,480"
                        + "&defaultMarker=marker-sm"
                        + "&session=" + directions.get("session")
                        + "&boundingBox=" + directions.get("boundingBox")
                        + "&to=" + to;

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://www.mapquestapi.com/staticmap/v5/map" + params))
                        .build();

                HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

                logger.debug("Received map from MapQuestAPI successfully!");
                return response.body();
            } catch (IOException | InterruptedException e) {
                logger.error(e.getStackTrace().toString());
            }

            return null;
        }

    }

