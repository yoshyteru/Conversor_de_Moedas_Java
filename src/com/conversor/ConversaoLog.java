package com.conversor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversaoLog {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final LocalDateTime dataHora;
    private final String origem;
    private final String destino;
    private final double valorEntrada;
    private final double valorSaida;
    private final double taxa;

    public ConversaoLog(String origem, String destino, double valorEntrada,
                        double valorSaida, double taxa) {
        this.dataHora = LocalDateTime.now();
        this.origem = origem;
        this.destino = destino;
        this.valorEntrada = valorEntrada;
        this.valorSaida = valorSaida;
        this.taxa = taxa;
    }

    @Override
    public String toString() {
        return String.format("[%s] %.2f %s → %.2f %s (taxa: %.4f)",
                dataHora.format(FORMATO), valorEntrada, origem, valorSaida, destino, taxa);
    }

    public String getOrigem() { return origem; }
    public String getDestino() { return destino; }
    public LocalDateTime getDataHora() { return dataHora; }
}