package com.conversor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private static final String API_KEY = "Coloque sua API aqui";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private final HttpClient cliente;
    private JsonObject taxasCache;
    private String moedaBaseCache;

    public ApiClient() {
        this.cliente = HttpClient.newHttpClient();
    }

    public JsonObject obterTaxas(String moedaBase) {
        if (taxasCache != null && moedaBase.equals(moedaBaseCache)) {
            return taxasCache;
        }

        String url = BASE_URL + API_KEY + "/latest/" + moedaBase;

        try {
            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> resposta = cliente.send(
                    requisicao,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (resposta.statusCode() != 200) {
                throw new RuntimeException("Erro na API: Código " + resposta.statusCode());
            }

            JsonElement elemento = JsonParser.parseString(resposta.body());
            JsonObject objetoRaiz = elemento.getAsJsonObject();

            String resultado = objetoRaiz.get("result").getAsString();
            if (!resultado.equals("success")) {
                throw new RuntimeException("Erro na resposta da API: " + resultado);
            }

            taxasCache = objetoRaiz.getAsJsonObject("conversion_rates");
            moedaBaseCache = moedaBase;

            return taxasCache;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter taxas: " + e.getMessage());
        }
    }

    public double obterTaxaConversao(String moedaOrigem, String moedaDestino) {
        JsonObject taxas = obterTaxas(moedaOrigem);

        if (!taxas.has(moedaDestino)) {
            throw new RuntimeException("Moeda não encontrada: " + moedaDestino);
        }

        return taxas.get(moedaDestino).getAsDouble();
    }
}