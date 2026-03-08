package com.conversor;

public class ConversorService {
    private final ApiClient apiClient;
    private final Historico historico;

    public ConversorService() {
        this.apiClient = new ApiClient();
        this.historico = new Historico();
    }

    public Resultado converter(String origem, String destino, double valor) {
        double taxa = apiClient.obterTaxaConversao(origem, destino);
        double convertido = valor * taxa;

        ConversaoLog log = new ConversaoLog(origem, destino, valor, convertido, taxa);
        historico.adicionar(log);
        Logger.registrar(origem, destino, valor, convertido);

        return new Resultado(origem, destino, valor, convertido, taxa);
    }

    public Historico getHistorico() {
        return historico;
    }

    public static class Resultado {
        private final String moedaOrigem;
        private final String moedaDestino;
        private final double valorOriginal;
        private final double valorConvertido;
        private final double taxa;

        public Resultado(String moedaOrigem, String moedaDestino,
                         double valorOriginal, double valorConvertido, double taxa) {
            this.moedaOrigem = moedaOrigem;
            this.moedaDestino = moedaDestino;
            this.valorOriginal = valorOriginal;
            this.valorConvertido = valorConvertido;
            this.taxa = taxa;
        }

        @Override
        public String toString() {
            return String.format(
                    "\nValor: %.2f %s\nConvertido: %.2f %s\nTaxa: 1 %s = %.4f %s",
                    valorOriginal, moedaOrigem,
                    valorConvertido, moedaDestino,
                    moedaOrigem, taxa, moedaDestino
            );
        }
    }
}