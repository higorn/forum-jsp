/*
 * File:   UsuarioDao.java
 *
 * Created on 04/11/16, 20:58
 */
package ita.coursera.forum.dao;


import ita.coursera.forum.entity.Usuario;

import java.sql.SQLException;
import java.util.List;

/**
 * @author higor
 */
public interface UsuarioDao {
  void inserir(Usuario usuario) throws SQLException;
  List<Usuario> recuperarTodos() throws SQLException;
  Usuario recuperar(String login) throws SQLException;
  void adicionarPontos(String login, Integer pontos) throws SQLException;
  List<Usuario> ranking() throws SQLException;
}
