package com.conversor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String ARQUIVO = "conversoes.log";
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void registrar(String origem, String destino, double entrada, double saida) {
        try (FileWriter fw = new FileWriter(ARQUIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {

            String timestamp = LocalDateTime.now().format(FORMATO);
            pw.printf("[%s] %s %.2f → %s %.2f%n",
                    timestamp, origem, entrada, destino, saida);

        } catch (IOException e) {
            System.err.println("Erro ao salvar log: " + e.getMessage());
        }
    }
}