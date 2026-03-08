package com.conversor;

import java.util.ArrayList;
import java.util.List;

public class Historico {
    private static final int LIMITE = 10;
    private final List<ConversaoLog> logs;

    public Historico() {
        this.logs = new ArrayList<>();
    }

    public void adicionar(ConversaoLog log) {
        logs.add(log);
        if (logs.size() > LIMITE) {
            logs.remove(0);
        }
    }

    public void exibir() {
        if (logs.isEmpty()) {
            System.out.println("\nNenhuma conversão realizada ainda.");
            return;
        }

        System.out.println("\n=== HISTÓRICO (últimas " + logs.size() + ") ===");
        for (int i = logs.size() - 1; i >= 0; i--) {
            System.out.println((logs.size() - i) + ". " + logs.get(i));
        }
    }

    public List<ConversaoLog> getLogs() {
        return new ArrayList<>(logs);
    }
}