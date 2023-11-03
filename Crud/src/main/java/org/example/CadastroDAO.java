package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CadastroDAO {

    // Todas as Funções do CRUD

    public void Create(Usuario user) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO cadastrousuarios(nomeCompleto, idade, email, nomeUsuario, senha) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // Criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionMySql();
            // Criamos um PreparedStatement para executar uma query
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getNomeCompleto());
            pstm.setInt(2, user.getIdade());
            pstm.setString(3, user.getEmail());
            pstm.setString(4, user.getNomeUsuario());
            pstm.setString(5, user.getSenha());

            // Executar a query
            pstm.execute();

            System.out.println("Cadastro do novo usuário realizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fechar as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public List<Usuario> Read() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM cadastrousuarios";

        List<Usuario> users = new ArrayList<Usuario>();

        Connection conn = null;
        PreparedStatement pstm = null;
        Usuario user;

        // Classe que vai recuperar os dados do BD
        ResultSet resultado = null;

        try {
            conn = ConnectionFactory.createConnectionMySql();
            pstm = conn.prepareStatement(sql);
            resultado = pstm.executeQuery();

            while (resultado.next()) {

                user = new Usuario();

                // Recuperar dados dos usuarios cadastradados no BD
                user.setNomeCompleto(resultado.getString("nomeCompleto"));
                user.setIdade(resultado.getInt("idade"));
                user.setEmail(resultado.getString("email"));
                user.setNomeUsuario(resultado.getString("nomeUsuario"));
                user.setSenha(resultado.getString("senha"));

                users.add(user);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public List<Usuario> ReadOne(String email) throws SQLException, ClassNotFoundException {

        List<Usuario> users = new ArrayList<Usuario>();

        Connection conn = null;
        PreparedStatement pstm = null;
        Usuario user;
        ResultSet resultado = null;

        String sql = "SELECT * FROM cadastrousuarios where email = ?";


        try {
            conn = ConnectionFactory.createConnectionMySql();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, email);
            resultado = pstm.executeQuery();

            while (resultado.next()) {

                user = new Usuario();

                user.setNomeCompleto(resultado.getString("nomeCompleto"));
                user.setIdade(resultado.getInt("idade"));
                user.setEmail(resultado.getString("email"));
                user.setNomeUsuario(resultado.getString("nomeUsuario"));
                user.setSenha(resultado.getString("senha"));

                users.add(user);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (resultado != null) {
                    resultado.close();            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;

    }

    public void Update(String tipoUpdate, String email,  String dadoUpdate) throws SQLException, ClassNotFoundException {


        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultado = null;
        Usuario user;

        tipoUpdate = tipoUpdate;
        email = email;
        dadoUpdate = dadoUpdate;

        String sqlNomeCompleto = "UPDATE cadastrousuarios SET nomeCompleto = ?  WHERE email = ?";
        String sqlIdade = "UPDATE cadastrousuarios SET idade = ?  WHERE email = ?";
        String sqlEmail = "UPDATE cadastrousuarios SET email = ?  WHERE email = ?";
        String sqlNomeUsuario = "UPDATE cadastrousuarios SET nomeUsuario = ?  WHERE email = ?";
        String sqlSenha = "UPDATE cadastrousuarios SET senha = ?  WHERE email = ?";

        try {
            conn = ConnectionFactory.createConnectionMySql();
            if (tipoUpdate.equals("nomeCompleto")) {
                pstm = conn.prepareStatement(sqlNomeCompleto);
                pstm.setString(1,  dadoUpdate);
                pstm.setString(2, email);
                pstm.executeUpdate();

                System.out.println("Nome atualizado com sucesso!");
            } else if (tipoUpdate.equals("idade")) {
                pstm = conn.prepareStatement(sqlIdade);
                pstm.setInt(1, Integer.parseInt(dadoUpdate));
                pstm.setString(2, email);
                pstm.executeUpdate();

                System.out.println("Idade atualizada com sucesso!");
            } else if (tipoUpdate.equals("email")) {
                pstm = conn.prepareStatement(sqlEmail);
                pstm.setString(1, dadoUpdate);
                pstm.setString(2, email);
                pstm.executeUpdate();

                System.out.println("Email atualizado com sucesso!");
            } else if (tipoUpdate.equals("nomeUsuario")) {
                pstm = conn.prepareStatement(sqlNomeUsuario);
                pstm.setString(1, dadoUpdate);
                pstm.setString(2, email);
                pstm.executeUpdate();

                System.out.println("Nome de usuário atualizado com sucesso!");
            } else if (tipoUpdate.equals("senha")) {
                pstm = conn.prepareStatement(sqlSenha);
                pstm.setString(1, dadoUpdate);
                pstm.setString(2, email);
                pstm.executeUpdate();

                System.out.println("Senha atualizada com sucesso!");
            } else {
                System.out.println("Opção inválida!");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (resultado != null) {
                    resultado.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void Delete(String email) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultado = null;

        String sql = "DELETE from cadastrousuarios where email = ?";

        try {
            conn = ConnectionFactory.createConnectionMySql();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, email);
            pstm.execute();

            System.out.println("Dados excluídos com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (resultado != null) {
                    resultado.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
