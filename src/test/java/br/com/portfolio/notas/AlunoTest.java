package br.com.portfolio.notas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    @Test
    void calculaMediaCorretamente() {
        Aluno aluno = new Aluno("001", "Ana");
        aluno.registrarNotas(8, 7.5, 9);
        assertEquals(8.166666666666666,
            aluno.calcularMedia(), 0.000001);
        assertEquals(SituacaoAluno.APROVADO,
            aluno.getSituacao());
    }

    @Test
    void classificaLimites() {
        Aluno aluno = new Aluno("001", "Ana");

        aluno.registrarNotas(7, 7, 7);
        assertEquals(SituacaoAluno.APROVADO,
            aluno.getSituacao());

        aluno.registrarNotas(5, 5, 5);
        assertEquals(SituacaoAluno.RECUPERACAO,
            aluno.getSituacao());

        aluno.registrarNotas(4, 4, 4);
        assertEquals(SituacaoAluno.REPROVADO,
            aluno.getSituacao());
    }

    @Test
    void rejeitaDadosInvalidos() {
        assertThrows(IllegalArgumentException.class,
            () -> new Aluno(null, "Ana"));
        assertThrows(IllegalArgumentException.class,
            () -> new Aluno("001", " "));
    }

    @Test
    void rejeitaNotasInvalidas() {
        Aluno aluno = new Aluno("001", "Ana");

        assertThrows(IllegalArgumentException.class,
            () -> aluno.registrarNotas(-1, 5, 5));
        assertThrows(IllegalArgumentException.class,
            () -> aluno.registrarNotas(11, 5, 5));
        assertThrows(IllegalArgumentException.class,
            () -> aluno.registrarNotas(Double.NaN, 5, 5));
    }

    @Test
    void exigeNotasAntesDaMedia() {
        Aluno aluno = new Aluno("001", "Ana");
        assertThrows(IllegalStateException.class,
            aluno::calcularMedia);
    }

    @Test
    void protegeArrayInterno() {
        Aluno aluno = new Aluno("001", "Ana");
        aluno.registrarNotas(8, 8, 8);

        double[] copia = aluno.getNotas();
        copia[0] = 0;

        assertEquals(8, aluno.calcularMedia());
    }

    @Test
    void preservaNotasAposTentativaInvalida() {
        Aluno aluno = new Aluno("001", "Ana");
        aluno.registrarNotas(8, 8, 8);

        assertThrows(IllegalArgumentException.class,
            () -> aluno.registrarNotas(2, 20, 2));

        assertEquals(8, aluno.calcularMedia());
    }
}
