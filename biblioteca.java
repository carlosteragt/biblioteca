package br.edu.univas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class biblioteca {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int menu;
        int tipoBusca;
        String buscaLivro;
        String buscaAutor;
        String buscaArea;
        String[] autor = new String[100];
        String[] nomeDoLivro = new String[100];
        String[] areaDeInteresse = new String[100];
        int[] numeroDePaginas = new int[100];
        int contandor = 0;
        while (contandor < 100) {
            System.out.println("Escolha uma opção:");
            System.out.println("Digite 1 para Adicionar um livro: ");
            System.out.println("Digite 2 para encontrar um livro: ");
            System.out.println("Digite 3 para remover um livro: ");
            System.out.println("Digite 4 para ver o relatorio: ");
            System.out.println("Digite 5 para recuperar livros salvos no computador: ");
            System.out.println("Digite 0 para encerrar: ");

            menu = scanner.nextInt();
            scanner.nextLine();
            if (menu == 1) {
                System.out.println("Digite o nome do livro: ");
                nomeDoLivro[contandor] = scanner.nextLine();
                System.out.println("Digite o nome do autor:  ");
                autor[contandor] = scanner.nextLine();
                System.out.println("digite a area de interesse: ");
                areaDeInteresse[contandor] = scanner.nextLine();
                System.out.println("Digite o numero de paginas: ");
                numeroDePaginas[contandor] = scanner.nextInt();
                adicionarLivro(nomeDoLivro, autor, areaDeInteresse, numeroDePaginas, contandor);
                contandor++;

            }
            else if (menu == 2) {
                System.out.println("Escolha o tipo de busca: ");
                System.out.println("Digita 1 para buscar livro pelo nome: ");
                System.out.println("Digita 2 para buscar livro pelo autor: ");
                System.out.println("Digita 3 para buscar livro pela Area de interesse: ");
                tipoBusca = scanner.nextInt();
                scanner.nextLine();
                if (tipoBusca == 1) {
                    System.out.println("Digite o nome do livro: ");
                    buscaLivro = scanner.nextLine();
                    try {
                        imprimirLivro(buscaLivro);
                    } catch (IOException e) {
                        System.out.println("Livro não encontrado");
                    }

                }
                else if (tipoBusca == 2) {
                    System.out.println("Digite o nome do autor: ");
                    buscaAutor = scanner.nextLine();
                    System.out.println("livro(s) do autor: ");
                    for (int i = 0; i < contandor; i++) {
                        if (buscaAutor.equals(autor[i])) {
                            imprimirLivro(nomeDoLivro[i]);
                            System.out.println();
                        }
                    }
                }
                else if (tipoBusca == 3) {
                    System.out.println("Digite a area de interesse: ");
                    buscaArea = scanner.nextLine();
                    System.out.println("Livro(s) na area de interesse: ");
                    for (int i = 0; i < contandor; i++) {
                        if (buscaArea.equals(areaDeInteresse[i])) {
                            imprimirLivro(nomeDoLivro[i]);
                            System.out.println();
                        }
                    }
                }
            }
            else if (menu == 3) {
                System.out.println("Digite o nome do livro: ");
                buscaLivro = scanner.nextLine();
                excluirLivro(buscaLivro);
            }
            else if (menu == 4) {
                gerarRelatorio(nomeDoLivro, autor, areaDeInteresse, numeroDePaginas, contandor);
            }
            else if (menu == 0) {
                break;
            }
            else {
                System.out.println("Opção inexistente ");
                System.out.println("");
            }
        }
    }


    public static void adicionarLivro(String[] nomeDoLivro, String[] autor, String[] areaDeInteresse, int[] numeroDePaginas, int contador) throws IOException {
        String nomeArquivo = nomeDoLivro[contador] + ".csv";
        File livro = new File(nomeArquivo);
        FileWriter escritor = new FileWriter(livro);
        escritor.write(nomeDoLivro[contador] + "," + numeroDePaginas[contador] + "," + autor[contador] + "," + areaDeInteresse[contador]);
        escritor.close();
    }

    public static void imprimirLivro(String nomeLivro) throws IOException {
        String nomeArquivo = nomeLivro + ".csv";
        String livro = new String(Files.readAllBytes(Paths.get(nomeArquivo)));
        System.out.println(livro);
    }

    public static void excluirLivro(String nomeLivro) {
        String nomeArquivo = nomeLivro + ".csv";
        File arquivo = new File(nomeArquivo);
        if (arquivo.exists()) {
            arquivo.delete();
            System.out.println("Livro excluído com sucesso!");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public static void gerarRelatorio(String[] nomeDoLivro, String[] autor, String[] areaDeInteresse, int[] numeroDePaginas, int contador) {
        int numAutores = contador;
        int numAreasDeInteresse = contador;
        int numDePaginasTotal = 0;
        for (int i = 0; i < contador; i++) {
            numDePaginasTotal += numeroDePaginas[i];
            for (int j = i + 1; j < contador; j++) {
                if (autor[i].equals(autor[j])) {
                    numAutores--;
                    break;
                }
            }
            for (int x = i + 1; x < contador; x++) {
                if (areaDeInteresse[i].equals(areaDeInteresse[x])) {
                    numAreasDeInteresse--;
                    break;
                }
            }
        }
        System.out.println("Numero de livros: " + contador);
        System.out.println("Numero de autores: " + numAutores);
        System.out.println("Numero de Areas de interesse: " + numAreasDeInteresse);
        System.out.println("Numero total de paginas: " + numDePaginasTotal);

    }
}






