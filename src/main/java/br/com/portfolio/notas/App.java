package br.com.portfolio.notas;

import java.util.Locale;
import java.util.Scanner;

public class App {
    private static final AlunoService service = new AlunoService();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.useLocale(Locale.US);

            while (true) {
                System.out.println("\n=== SISTEMA DE NOTAS ===");
                System.out.println("1 - Cadastrar aluno");
                System.out.println("2 - Registrar notas");
                System.out.println("3 - Listar alunos");
                System.out.println("4 - Consultar boletim");
                System.out.println("0 - Sair");
                System.out.print("Opcao: ");

                if (!scanner.hasNextLine()) {
                    return;
                }

                String opcao = scanner.nextLine().trim();

                try {
                    switch (opcao) {
                        case "1" -> cadastrar(scanner);
                        case "2" -> registrar(scanner);
                        case "3" -> listar();
                        case "4" -> boletim(scanner);
                        case "0" -> {
                            System.out.println("Encerrado.");
                            return;
                        }
                        default -> System.out.println("Opcao invalida.");
                    }
                } catch (IllegalArgumentException |
                         IllegalStateException |
                         java.util.NoSuchElementException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        }
    }

    private static String ler(Scanner scanner, String campo) {
        System.out.print(campo + ": ");
        return scanner.nextLine();
    }

    private static double lerNota(Scanner scanner, int numero) {
        String texto = ler(scanner, "Nota " + numero);
        try {
            return Double.parseDouble(texto.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Nota invalida.");
        }
    }

    private static void cadastrar(Scanner scanner) {
        String matricula = ler(scanner, "Matricula");
        String nome = ler(scanner, "Nome");
        service.cadastrar(matricula, nome);
        System.out.println("Aluno cadastrado.");
    }

    private static void registrar(Scanner scanner) {
        String matricula = ler(scanner, "Matricula");
        double n1 = lerNota(scanner, 1);
        double n2 = lerNota(scanner, 2);
        double n3 = lerNota(scanner, 3);

        service.registrarNotas(matricula, n1, n2, n3);
        System.out.println("Notas registradas.");
    }

    private static void listar() {
        if (service.listar().isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        for (Aluno aluno : service.listar()) {
            System.out.printf("%s - %s%n",
                aluno.getMatricula(), aluno.getNome());

            if (aluno.possuiNotas()) {
                System.out.printf(Locale.forLanguageTag("pt-BR"),
                    "Media: %.2f | %s%n",
                    aluno.calcularMedia(), aluno.getSituacao());
            } else {
                System.out.println("Notas pendentes.");
            }
        }
    }

    private static void boletim(Scanner scanner) {
        Aluno aluno = service.buscar(ler(scanner, "Matricula"));

        System.out.println("\n=== BOLETIM ===");
        System.out.println("Matricula: " + aluno.getMatricula());
        System.out.println("Aluno: " + aluno.getNome());

        if (!aluno.possuiNotas()) {
            System.out.println("Notas pendentes.");
            return;
        }

        double[] notas = aluno.getNotas();

        for (int i = 0; i < notas.length; i++) {
            System.out.printf(Locale.forLanguageTag("pt-BR"),
                "Nota %d: %.2f%n", i + 1, notas[i]);
        }

        System.out.printf(Locale.forLanguageTag("pt-BR"),
            "Media: %.2f%n", aluno.calcularMedia());
        System.out.println("Situacao: " + aluno.getSituacao());
    }
}
