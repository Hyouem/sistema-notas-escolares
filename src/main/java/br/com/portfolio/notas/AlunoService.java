package br.com.portfolio.notas;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AlunoService {
    private final List<Aluno> alunos = new ArrayList<>();

    public Aluno cadastrar(String matricula, String nome) {
        Aluno aluno = new Aluno(matricula, nome);

        if (alunos.stream().anyMatch(a ->
                a.getMatricula().equals(aluno.getMatricula()))) {
            throw new IllegalArgumentException(
                "Matricula ja cadastrada."
            );
        }

        alunos.add(aluno);
        return aluno;
    }

    public Aluno buscar(String matricula) {
        return alunos.stream()
            .filter(a -> a.getMatricula().equals(matricula))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException(
                "Aluno nao encontrado."
            ));
    }

    public void registrarNotas(
            String matricula, double n1, double n2, double n3) {
        buscar(matricula).registrarNotas(n1, n2, n3);
    }

    public List<Aluno> listar() {
        return List.copyOf(alunos);
    }
}
