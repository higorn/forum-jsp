package ita.coursera.forum.dao;/*
 * File:   TopicoDao.java
 *
 * Created on 09/01/17, 22:12
 */

import ita.coursera.forum.entity.Comentario;
import ita.coursera.forum.entity.Topico;

import java.sql.SQLException;
import java.util.List;

public interface TopicoDao {
  void inserir(Topico topico) throws SQLException;
  List<Topico> recuperarTodos() throws SQLException;
  Topico recuperar(Integer id) throws SQLException;
  void inserirComentario(Comentario comentario) throws SQLException;
}
