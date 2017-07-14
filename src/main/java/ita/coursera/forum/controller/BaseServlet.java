/*
 * File:   BaseServlet.java
 *
 * Created on 13/01/17, 00:02
 */
package ita.coursera.forum.controller;

import ita.coursera.forum.dao.UsuarioDao;
import ita.coursera.forum.dao.impl.UsuarioDaoJdbc;
import ita.coursera.forum.entity.Usuario;
import ita.coursera.forum.service.AutenticacaoException;
import ita.coursera.forum.util.JdbcUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author higor
 */
public abstract class BaseServlet extends HttpServlet {

  protected Usuario getUsuario(HttpServletRequest req) throws AutenticacaoException {
    for (Cookie cookie: req.getCookies()) {
      if (cookie.getName().equals("login") && !cookie.getValue().isEmpty()) {
        UsuarioDao dao = new UsuarioDaoJdbc(JdbcUtil.getDataSource());
        try {
          return dao.recuperar(cookie.getValue());
        } catch (SQLException e) {
          e.printStackTrace();
          throw new AutenticacaoException(e.getMessage(), e);
        }
      }
    }
    throw new AutenticacaoException("Usuário não atenticado.");
  }
}
