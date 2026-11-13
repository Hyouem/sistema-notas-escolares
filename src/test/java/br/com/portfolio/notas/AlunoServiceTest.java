package br.com.portfolio.notas;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class AlunoServiceTest {

    @Test
    void cadastraEBuscaAluno() {
        AlunoService service = new AlunoService();
        service.cadastrar("001", "Ana");

        assertEquals("Ana", service.buscar("001").getNome());
        assertEquals(1, service.listar().size());
    }

    @Test
    void impedeMatriculaDuplicada() {
        AlunoService service = new AlunoService();
        service.cadastrar("001", "Ana");

        assertThrows(IllegalArgumentException.class,
            () -> service.cadastrar("001", "Bruno"));
    }

    @Test
    void informaAlunoInexistente() {
        AlunoService service = new AlunoService();

        assertThrows(NoSuchElementException.class,
            () -> service.buscar("999"));
    }

    @Test
    void registraNotasPeloService() {
        AlunoService service = new AlunoService();
        service.cadastrar("001", "Ana");
        service.registrarNotas("001", 9, 8, 7);

        assertEquals(8, service.buscar("001").calcularMedia());
    }

    @Test
    void impedeAlteracaoDaListaExternamente() {
        AlunoService service = new AlunoService();
        service.cadastrar("001", "Ana");

        assertThrows(UnsupportedOperationException.class,
            () -> service.listar().clear());
    }
}
