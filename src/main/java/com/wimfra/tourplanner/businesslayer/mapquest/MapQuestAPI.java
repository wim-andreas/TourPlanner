package com.wimfra.tourplanner.businesslayer.mapquest;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wimfra.tourplanner.configuration.AppConfiguration;
import com.wimfra.tourplanner.configuration.AppConfigurationLoader;

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

        private static AppConfiguration appConfiguration =
        AppConfigurationLoader.getInstance().getAppConfiguration();

        public static Map<String, Object> getDirections(String from, String to) {
            try {
                Map<String, Object> directions = new HashMap<>();

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

                // Session
                directions.put("session", obj.get("route").get("sessionId").textValue());

                // BoundingBox
                JsonNode ul = obj.get("route").get("boundingBox").get("ul");
                JsonNode lr = obj.get("route").get("boundingBox").get("lr");
                directions.put("boundingBox", ul.get("lat").floatValue() + "," + ul.get("lng").floatValue() + "," + lr.get("lat").floatValue() + "," + lr.get("lng").floatValue());

                return directions;
            } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static byte[] getStaticMap(String from, String to) {
            try {
                Map<String, Object> directions = MapQuestAPI.getDirections(from, to);

                assert directions != null;
                String params = "?key=" + appConfiguration.getMapquestKey()
                        + "&size=640,480"
                        + "&defaultMarker=none"
                        + "&session=" + directions.get("session")
                        + "&boundingBox=" + directions.get("boundingBox")
                        + "&to=" + to;

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://www.mapquestapi.com/staticmap/v5/map" + params))
                        .build();

                HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

                return response.body();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

