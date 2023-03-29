package br.edu.univas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Biblioteca {
    static Scanner scan = new Scanner(System.in);
    static String arquivo = "C:\\Luiz\\biblioteca.csv";
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

        do {
            String[] dadosJaCadastrados = Files.readAllLines(Paths.get(arquivo)).toArray(new String[0]);
            System.out.println("digite o nome do livro:");
            nomeLivro = scan.next();

            for(int i = 0; i < dadosJaCadastrados.length; i++) {
                String[] dadosSeparados = dadosJaCadastrados[i].split(",");
                if (dadosSeparados[0].equals(nomeLivro)) {
                    System.out.println("livro já cadastrado");
                    break;

                } else if (i == dadosJaCadastrados.length - 1){

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

        } while(fecharPrograma);
    }

    public static void excluir () throws IOException {

        String nomeProcura;
        String[] dadosSeparados;

        do {
            String[] dadosJaCadastrados = Files.readAllLines(Paths.get(arquivo)).toArray(new String[0]);
            boolean livroExiste = false;

            System.out.println("digite o nome do livro");
            nomeProcura = scan.next();

            for (int i = 0; i < dadosJaCadastrados.length; i++) {
                dadosSeparados = dadosJaCadastrados[i].split(",");
                if (dadosSeparados[0].equals(nomeProcura)) {
                    livroExiste = true;
                }
            }

            if (livroExiste) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));

                for (int i = 0; i < dadosJaCadastrados.length; i++) {
                    dadosSeparados = dadosJaCadastrados[i].split(",");
                    if (!dadosSeparados[0].equals(nomeProcura)) {
                        try {
                            writer.write(dadosJaCadastrados[i]);
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

        } while(fecharPrograma);
    }

    public static void buscar () throws IOException {

        String[] dadosJaCadastrados = Files.readAllLines(Paths.get(arquivo)).toArray(new String[0]);
        String nomeProcura;
        String[] dadosSeparados;

        do {
            System.out.println("------------------------------");
            System.out.println("1 - Consultar por nome do livro");
            System.out.println("2 - consultar por autor");
            System.out.println("3 - consultar por área de interesse");
            System.out.println("9 - menu anterior");
            System.out.println("------------------------------");

            String escolha = scan.next();

            switch (escolha) {
                case "1":
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
                    break;
                case "2":
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

                    break;
                case "3":
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
                    break;
                default:
                    System.out.println("você não digitou uma opção válida");
                    break;
                case "9":
                    menu();
                    continue;
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
                case "1":
                    cadastro();
                    break;
                case "2":
                    excluir();
                    break;
                case "3":
                    buscar();
                    break;
                case "4":
                    gerarRelatorio();
                    break;
                default:
                    System.out.println("você não digitou uma opção válida");
                    break;
                case "9":
                    fecharPrograma = false;
                    break;
            }
        } while(fecharPrograma);
    }

    public static void gerarRelatorio() throws IOException {

        String[] dadosJaCadastrados = Files.readAllLines(Paths.get(arquivo)).toArray(new String[0]);
        int contadorLivros = 1;

        System.out.println("nome do livro / número de páginas / nome do autor / área de interesse ");

        for (int i = 0; i < dadosJaCadastrados.length; i++) {
            String[] dadosSeparados = dadosJaCadastrados[i].split(",");
            System.out.print("livro " + contadorLivros + ": ");
            contadorLivros++;
            for (int j = 0; j < dadosSeparados.length; j++) {
                if (j == dadosSeparados.length-1) {
                    System.out.print(dadosSeparados[j]);
                } else{
                    System.out.print(dadosSeparados[j] + " / ");
                }
            }
            System.out.println();
        }
    }
}
