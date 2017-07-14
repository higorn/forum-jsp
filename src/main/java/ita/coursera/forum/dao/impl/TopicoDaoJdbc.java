/*
 * File:   TopicoDaoJdbc.java
 *
 * Created on 09/01/17, 22:13
 */
package ita.coursera.forum.dao.impl;

import ita.coursera.forum.dao.TopicoDao;
import ita.coursera.forum.entity.Comentario;
import ita.coursera.forum.entity.Topico;
import ita.coursera.forum.jdbc.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author higor
 */
public class TopicoDaoJdbc implements TopicoDao {
  private DataSource dataSource;

  public TopicoDaoJdbc(JdbcDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void inserir(Topico topico) throws SQLException {
    String sql = "INSERT INTO topico(titulo, conteudo, login) VALUES (?, ?, ?)";
    try (Connection c = dataSource.getConnection()) {
      PreparedStatement pstmt = c.prepareStatement(sql);
      pstmt.setString(1, topico.getTitulo());
      pstmt.setString(2, topico.getConteudo());
      pstmt.setString(3, topico.getLogin());
      pstmt.executeUpdate();
    }
  }

  @Override
  public List<Topico> recuperarTodos() throws SQLException {
    List<Topico> resultList = new ArrayList<>();
    String sql = "SELECT * FROM topico";
    try (Connection c = dataSource.getConnection()) {
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        Topico topico = new Topico(rs.getInt("id_topico"), rs.getString("titulo"),
            rs.getString("conteudo"), rs.getString("login"));
        resultList.add(topico);
      }
    }
    return resultList;
  }

  @Override
  public Topico recuperar(Integer id) throws SQLException {
    String sql = "SELECT * FROM topico WHERE id_topico = ?";
    try (Connection c = dataSource.getConnection()) {
      PreparedStatement pstmt = c.prepareStatement(sql);
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        Topico topico = new Topico(rs.getInt("id_topico"), rs.getString("titulo"),
            rs.getString("conteudo"), rs.getString("login"));

        List<Comentario> comentarios = new ArrayList<>();
        sql = "SELECT * FROM comentario WHERE id_topico = ?";
        pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        while (rs.next()) {
          comentarios.add(new Comentario(rs.getInt("id_comentario"), rs.getString("comentario"),
              rs.getString("login"), topico));
        }
        topico.setComentarios(comentarios);
        return topico;
      }
      throw new SQLException("Impossível recuperar topico " + id + ".");
    }
  }

  @Override
  public void inserirComentario(Comentario comentario) throws SQLException {
    String sql = "INSERT INTO comentario(comentario, login, id_topico) VALUES (?, ?, ?)";
    try (Connection c = dataSource.getConnection()) {
      PreparedStatement pstmt = c.prepareStatement(sql);
      pstmt.setString(1, comentario.getComentario());
      pstmt.setString(2, comentario.getLogin());
      pstmt.setInt(3, comentario.getTopico().getId());
      pstmt.executeUpdate();
    }
  }
}
