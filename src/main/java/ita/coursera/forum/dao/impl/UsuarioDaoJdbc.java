/*
 * File:   UsuarioDaoJdbc.java
 *
 * Created on 04/11/16, 21:46
 */
package ita.coursera.forum.dao.impl;

import ita.coursera.forum.dao.UsuarioDao;
import ita.coursera.forum.entity.Usuario;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author higor
 */
public class UsuarioDaoJdbc implements UsuarioDao {
  private DataSource dataSource;

  public UsuarioDaoJdbc(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void inserir(Usuario usuario) throws SQLException {
    String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
    try (Connection c = dataSource.getConnection()) {
      PreparedStatement pstmt = c.prepareStatement(sql);
      pstmt.setString(1, usuario.getLogin());
      pstmt.setString(2, usuario.getEmail());
      pstmt.setString(3, usuario.getNome());
      pstmt.setString(4, usuario.getSenha());
      pstmt.setInt(5, usuario.getPontos());
      pstmt.executeUpdate();
    }
  }

  @Override
  public List<Usuario> recuperarTodos() throws SQLException {
    List<Usuario> resultList = new ArrayList<>();
    String sql = "SELECT * FROM usuario ORDER BY pontos DESC";
    try (Connection c = dataSource.getConnection()) {
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        Usuario usuario = new Usuario(rs.getString("login"), rs.getString("email"), rs.getString("nome"),
            rs.getString("senha"), rs.getInt("pontos"));
        resultList.add(usuario);
      }
    }
    return resultList;
  }

  public Usuario recuperar(String login) throws SQLException {
    String sql = "SELECT * FROM usuario WHERE login = ?";
    try (Connection c = dataSource.getConnection()) {
      PreparedStatement pstmt = c.prepareStatement(sql);
      pstmt.setString(1, login);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return new Usuario(rs.getString("login"), rs.getString("email"), rs.getString("nome"),
            rs.getString("senha"), rs.getInt("pontos"));
      }
      throw new SQLException("Impossível recuperar usuario " + login + " não encontrado.");
    }
  }

  public void adicionarPontos(String login, Integer pontos) throws SQLException {
    String sql = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?";
    try (Connection c = dataSource.getConnection()) {
      PreparedStatement pstmt = c.prepareStatement(sql);
      pstmt.setInt(1, pontos);
      pstmt.setString(2, login);
      pstmt.executeUpdate();
    }
  }

  public List<Usuario> ranking() throws SQLException {
    String sql = "SELECT * FROM usuario ORDER BY pontos DESC";
    try (Connection c = dataSource.getConnection()) {
      List<Usuario> usuarioList = new ArrayList<Usuario>();
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        usuarioList.add(new Usuario(rs.getString("login"), rs.getString("email"),
            rs.getString("nome"), rs.getString("senha"), rs.getInt("pontos")));
      }
      return usuarioList;
    }
  }
}
