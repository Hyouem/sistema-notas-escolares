package br.com.portfolio.notas;

import java.util.Objects;

public class Aluno {
    private final String matricula;
    private final String nome;
    private double[] notas;

    public Aluno(String matricula, String nome) {
        this.matricula = validarTexto(matricula, "Matricula");
        this.nome = validarTexto(nome, "Nome");
    }

    private static String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " obrigatorio.");
        }
        return valor.trim();
    }

    public void registrarNotas(double n1, double n2, double n3) {
        validarNota(n1);
        validarNota(n2);
        validarNota(n3);
        this.notas = new double[] {n1, n2, n3};
    }

    private static void validarNota(double nota) {
        if (!Double.isFinite(nota) || nota < 0 || nota > 10) {
            throw new IllegalArgumentException(
                "Nota deve estar entre 0 e 10."
            );
        }
    }

    public double calcularMedia() {
        exigirNotas();
        return (notas[0] + notas[1] + notas[2]) / 3.0;
    }

    public SituacaoAluno getSituacao() {
        double media = calcularMedia();

        if (media >= 7) {
            return SituacaoAluno.APROVADO;
        }
        if (media >= 5) {
            return SituacaoAluno.RECUPERACAO;
        }
        return SituacaoAluno.REPROVADO;
    }

    public boolean possuiNotas() {
        return notas != null;
    }

    private void exigirNotas() {
        if (!possuiNotas()) {
            throw new IllegalStateException(
                "Aluno ainda nao possui notas."
            );
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public double[] getNotas() {
        exigirNotas();
        return notas.clone();
    }
}
