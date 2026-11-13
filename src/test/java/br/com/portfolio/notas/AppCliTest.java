package br.com.portfolio.notas;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class AppCliTest {

    @Test
    void consultaInexistenteNaoEncerraAplicacao() throws Exception {
        InputStream entradaOriginal = System.in;
        PrintStream saidaOriginal = System.out;

        String comandos = "4\n999\n0\n";
        ByteArrayInputStream entrada = new ByteArrayInputStream(
            comandos.getBytes(StandardCharsets.UTF_8)
        );
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try (PrintStream saida = new PrintStream(
                buffer, true, StandardCharsets.UTF_8)) {

            System.setIn(entrada);
            System.setOut(saida);

            assertDoesNotThrow(() -> App.main(new String[0]));

        } finally {
            System.setIn(entradaOriginal);
            System.setOut(saidaOriginal);
        }

        String resultado = buffer.toString(StandardCharsets.UTF_8);

        assertTrue(resultado.contains("Aluno nao encontrado."));
        assertTrue(resultado.contains("Encerrado."));
        assertEquals(2, resultado.split(
            "=== SISTEMA DE NOTAS ===", -1
        ).length - 1);
    }
}