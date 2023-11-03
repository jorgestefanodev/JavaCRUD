package org.example;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int opcao;
        int opcaoUpdate;

        CadastroDAO cadDao = new CadastroDAO();
        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println("---------- SISTEMA CRUD ----------");
            System.out.println("[1] - Criar novo usuário");
            System.out.println("[2] - Listar todos os usuários");
            System.out.println("[3] - Listar apenas um usuário");
            System.out.println("[4] - Atualizar dados do usuário");
            System.out.println("[5] - Deletar usuário");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.print("Digite o numero da opção desejada: ");
            opcao = Integer.parseInt(scan.nextLine());
            System.out.println();


            switch (opcao) {
                case 1:
                    Usuario user = new Usuario();

                     // Entrada de dados digitados pelo usuario

                    System.out.println("=-=-=-=-= CREATE - CRIAR NOVO USUÁRIO =-=-=-=-=-=");
                    System.out.println("Digite os dados abaixo:");
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

                    System.out.print("Nome completo: ");
                    user.setNomeCompleto(scan.nextLine());

                    System.out.print("Idade: ");
                    user.setIdade(scan.nextInt());

                    scan.nextLine();

                    System.out.print("Email: ");
                    user.setEmail(scan.nextLine());

                    System.out.print("Usuario: ");
                    user.setNomeUsuario(scan.nextLine());

                    System.out.print("Senha: ");
                    user.setSenha(scan.nextLine());

                    // Enviar dados para o Banco de dados
                    cadDao.Create(user);

                    break;
                case 2:
                    System.out.println("=-=-=-=-=-=- TODOS OS USUÁRIOS CADASTRADOS NO BANCO DE DADOS =-=-=-=-=-=-");
                    System.out.println();
                    for (Usuario u : cadDao.Read()) {
                        System.out.println("Nome Completo: " + u.getNomeCompleto());
                        System.out.println("idade: " + u.getIdade());
                        System.out.println("Email: " + u.getEmail());
                        System.out.println("Usuário: " + u.getNomeUsuario());
                        System.out.println("Senha: " + u.getSenha());
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("=-=-=-=-=-=- LISTAR APENAS UM USUÁRIO =-=-=-=-=-=-");
                    System.out.print("Digite o email para buscar o usuário: ");
                    String email = scan.nextLine();

                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

                    for (Usuario u : cadDao.ReadOne(email)) {
                        System.out.println("Nome completo: " + u.getNomeCompleto());
                        System.out.println("idade: " + u.getIdade());
                        System.out.println("Email: " + u.getEmail());
                        System.out.println("Usuário: " + u.getNomeUsuario());
                        System.out.println("Senha: " + u.getSenha());
                    }
                    break;
                case 4:
                    System.out.println("=-=-=-=-= UPDATE - ATUALIZAR DADOS DO USUÁRIO =-=-=-=-=");
                    System.out.print("Digite o email do usuário que deseja fazer UPDATE: ");
//                    scan.nextLine();
                    email = scan.nextLine();

                    System.out.println();
                    System.out.println("=-=-=-=-=-= Abaixo os dados do usuário solicitado =-=-=-=-=-=-");
                    System.out.println();

                    for (Usuario u : cadDao.ReadOne(email)) {
                        System.out.println("Nome Completo: " + u.getNomeCompleto());
                        System.out.println("idade: " + u.getIdade());
                        System.out.println("Email: " + u.getEmail());
                        System.out.println("Usuário: " + u.getNomeUsuario());
                        System.out.println("Senha: " + u.getSenha());
                    }

                    System.out.println();
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                    System.out.println();

                    System.out.println("Qual dos dados deseja alterar?");
                    System.out.println("[1] - Nome Completo");
                    System.out.println("[2] - Idade");
                    System.out.println("[3] - Email");
                    System.out.println("[4] - Nome de Usuário");
                    System.out.println("[5] - Senha");
                    System.out.print("Digite o numero da opção desejada: ");
                    opcaoUpdate = Integer.parseInt(scan.nextLine());

                    switch (opcaoUpdate) {
                        case 1:
                            String tipoUpdate = "nomeCompleto";

                            System.out.print("Digite o nome completo corrigido: ");
                            String dadoUpdate = scan.nextLine();

                            cadDao.Update(tipoUpdate, email, dadoUpdate);
                            break;
                        case 2:
                            tipoUpdate = "idade";

                            System.out.print("Digite a idade atualizada: ");
                            dadoUpdate = scan.nextLine();

                            cadDao.Update(tipoUpdate, email, dadoUpdate);
                            break;
                        case 3:
                            tipoUpdate = "email";

                            System.out.print("Digite o email atualizado: ");
                            dadoUpdate = scan.nextLine();

                            cadDao.Update(tipoUpdate, email, dadoUpdate);
                            break;
                        case 4:
                            tipoUpdate = "nomeUsuario";

                            System.out.print("Digite o novo nome de usuário: ");
                            dadoUpdate = scan.nextLine();

                            cadDao.Update(tipoUpdate, email, dadoUpdate);
                            break;
                        case 5:
                            tipoUpdate = "senha";

                            System.out.print("Digite a nova senha: ");
                            dadoUpdate = scan.nextLine();

                            cadDao.Update(tipoUpdate, email, dadoUpdate);
                            break;
                        default:
                            System.out.println("Opção inválidada");
                            break;

                    }
                    break;

                case 5:
                    System.out.println("=-=-=-=-=-=- DELETAR USUARIO =-=-=-=-=-=-");
                    System.out.print("Digite o email do usuário que deseja excluir: ");
                    email = scan.nextLine();

                    cadDao.Delete(email);
                    break;

                default:
                    System.out.println("Opção inválidada");
                    break;
            }
            System.out.print("Deseja continuar? [S] / [N]: ");
            String continuar = scan.next().toUpperCase();
            scan.nextLine();
            if (continuar.equals("S")) {
                continue;
            } else if (continuar.equals("N")) {
                System.out.println("Saindo da Aplicação...");
                break;
            } else {
                System.out.println("Opção inválida");
            }

        }
    }
}