package br.edu.univas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Biblioteca {
    static Scanner scan = new Scanner(System.in);
    static String arquivo = "C:\\Users\\Luiz Felipe\\OneDrive\\Documentos\\biblioteca.csv";
    static boolean fecharPrograma = true;
    static String resposta;

    public static void main(String[] args) throws IOException {

        System.out.println("BEM-VINDO À BIBLIOTECA!!!");
        menu();
    }
    public static void cadastro () throws IOException {

        String nomeLivro;
        String nPaginas;
        String nomeAutor;
        String areaInteresse;
        String[] dadosSeparados;
        boolean livroJaExiste = true;

        do {
            String[] dadosJaCadastrados = Files.readAllLines(Paths.get(arquivo)).toArray(new String[0]);
            System.out.println("digite o nome do livro:");
            nomeLivro = scan.next();
            for (String dadosJaCadastrado : dadosJaCadastrados) {
                dadosSeparados = dadosJaCadastrado.split(",");
                if (dadosSeparados[0].equals(nomeLivro)) {
                    livroJaExiste = false;
                }
            }

            if (livroJaExiste) {
                do {
                    System.out.println("digite o número de páginas:");
                    nPaginas = scan.next();
                    if (nPaginas.matches("\\d+")) {
                        break;
                    } else {
                        System.out.println("Somente números são válidos!");
                    }
                } while(true);

                System.out.println("digite o nome do autor:");
                nomeAutor = scan.next();

                System.out.println("digite a área de interesse:");
                areaInteresse = scan.next();

                String dados = nomeLivro + "," + nPaginas + "," + nomeAutor + "," + areaInteresse;

                if (dadosJaCadastrados.length == 0 || dadosJaCadastrados[0].equals("")) {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));
                        writer.write(dados);
                        writer.newLine();
                        writer.close();
                        System.out.println("dados salvos");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true));
                        writer.write(dados);
                        writer.newLine();
                        writer.close();
                        System.out.println("dados salvos");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("livro já cadastrado");
            }
            livroJaExiste = true;

            while (true) {
                System.out.println("deseja continuar? (s/n)");
                resposta = scan.next();
                if (resposta.equalsIgnoreCase("n")) {
                    menu();
                    break;
                } else if (resposta.equalsIgnoreCase("s")) {
                    break;
                } else {
                    System.out.println("opção inválida");
                }
            }

        } while(fecharPrograma);
    }

    public static void excluir () throws IOException {

        String nomeProcura;
        String[] dadosSeparados;

        do {
            String[] dadosJaCadastrados = Files.readAllLines(Paths.get(arquivo)).toArray(new String[0]);
            boolean livroExiste = false;

            if (dadosJaCadastrados.length == 0 || dadosJaCadastrados[0].equals("")) {
                System.out.println("não há livros cadastrados");
                break;
            } else {
                System.out.println("digite o nome do livro");
                nomeProcura = scan.next();

                for (String dadosJaCadastrado : dadosJaCadastrados) {
                    dadosSeparados = dadosJaCadastrado.split(",");
                    if (dadosSeparados[0].equals(nomeProcura)) {
                        livroExiste = true;
                    }
                }

                if (livroExiste) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));

                    for (String dadosJaCadastrado : dadosJaCadastrados) {
                        dadosSeparados = dadosJaCadastrado.split(",");
                        if (!dadosSeparados[0].equals(nomeProcura)) {
                            try {
                                writer.write(dadosJaCadastrado);
                                writer.newLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    writer.close();
                    System.out.println("livro(s) excluído");

                } else {
                    System.out.println("livro não encontrado");
                }

                while (true) {
                    System.out.println("deseja continuar? (s/n)");
                    resposta = scan.next();
                    if (resposta.equalsIgnoreCase("n")) {
                        menu();
                        break;
                    } else if (resposta.equalsIgnoreCase("s")) {
                        break;
                    } else {
                        System.out.println("opção inválida");
                    }
                }
            }

        } while(fecharPrograma);
    }

    public static void buscar () throws IOException {

        String[] dadosJaCadastrados = Files.readAllLines(Paths.get(arquivo)).toArray(new String[0]);
        String nomeProcura;
        String[] dadosSeparados;

        if (dadosJaCadastrados.length == 0 || dadosJaCadastrados[0].equals("")) {
            System.out.println("não há livros cadastrados");
        } else {
            do {
                System.out.println("------------------------------");
                System.out.println("1 - Consultar por nome do livro");
                System.out.println("2 - consultar por autor");
                System.out.println("3 - consultar por área de interesse");
                System.out.println("9 - menu anterior");
                System.out.println("------------------------------");

                String escolha = scan.next();

                switch (escolha) {
                    case "1" -> {
                        System.out.println("digite o nome do livro");
                        nomeProcura = scan.next();
                        for (int i = 0; i < dadosJaCadastrados.length; i++) {
                            dadosSeparados = dadosJaCadastrados[i].split(",");
                            if (dadosSeparados[0].equals(nomeProcura)) {
                                System.out.println(dadosJaCadastrados[i]);
                                break;
                            } else if (i == dadosJaCadastrados.length - 1) {
                                System.out.println("não encontrado");
                            }
                        }
                    }
                    case "2" -> {
                        System.out.println("digite o nome do autor");
                        nomeProcura = scan.next();
                        for (int i = 0; i < dadosJaCadastrados.length; i++) {
                            dadosSeparados = dadosJaCadastrados[i].split(",");
                            if (dadosSeparados[2].equals(nomeProcura)) {
                                System.out.println(dadosJaCadastrados[i]);
                                break;
                            } else if (i == dadosJaCadastrados.length - 1) {
                                System.out.println("não encontrado");
                            }
                        }
                    }
                    case "3" -> {
                        System.out.println("digite a área de interesse");
                        nomeProcura = scan.next();
                        for (int i = 0; i < dadosJaCadastrados.length; i++) {
                            dadosSeparados = dadosJaCadastrados[i].split(",");
                            if (dadosSeparados[3].equals(nomeProcura)) {
                                System.out.println(dadosJaCadastrados[i]);
                                break;
                            } else if (i == dadosJaCadastrados.length - 1) {
                                System.out.println("não encontrado");
                            }
                        }
                    }
                    default -> System.out.println("você não digitou uma opção válida");
                    case "9" -> {
                        menu();
                        continue;
                    }
                }
                while (true) {
                    System.out.println("deseja continuar? (s/n)");
                    resposta = scan.next();
                    if (resposta.equalsIgnoreCase("n")) {
                        menu();
                        break;
                    } else if (resposta.equalsIgnoreCase("s")) {
                        break;
                    } else {
                        System.out.println("opção inválida");
                    }
                }
            } while (fecharPrograma);
        }
    }

    private static void menu() throws IOException {

        do {
            System.out.println("------------------------------");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Excluir");
            System.out.println("3 - Buscar");
            System.out.println("4 - Gerar relatório");
            System.out.println("9 - Sair");
            System.out.println("------------------------------");

            String escolha = scan.next();

            switch (escolha) {
                case "1" -> cadastro();
                case "2" -> excluir();
                case "3" -> buscar();
                case "4" -> gerarRelatorio();
                default -> System.out.println("você não digitou uma opção válida");
                case "9" -> fecharPrograma = false;
            }
        } while(fecharPrograma);
    }

    public static void gerarRelatorio() throws IOException {

        String[] dadosJaCadastrados = Files.readAllLines(Paths.get(arquivo)).toArray(new String[0]);
        int contadorLivros = 1;

        if (dadosJaCadastrados.length == 0 || dadosJaCadastrados[0].equals("")) {
            System.out.println("não há livros cadastrados");
        } else {
            System.out.println("nome do livro / número de páginas / nome do autor / área de interesse ");
            for (String dadosJaCadastrado : dadosJaCadastrados) {
                String[] dadosSeparados = dadosJaCadastrado.split(",");
                System.out.print("livro " + contadorLivros + ": ");
                contadorLivros++;
                for (int j = 0; j < dadosSeparados.length; j++) {
                    if (j == dadosSeparados.length - 1) {
                        System.out.print(dadosSeparados[j]);
                    } else {
                        System.out.print(dadosSeparados[j] + " / ");
                    }
                }
                System.out.println();
            }
        }
    }
}
