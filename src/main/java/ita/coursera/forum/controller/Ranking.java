/*
 * File:   Ranking.java
 *
 * Created on 09/01/17, 21:45
 */
package ita.coursera.forum.controller;

import ita.coursera.forum.dao.UsuarioDao;
import ita.coursera.forum.dao.impl.UsuarioDaoJdbc;
import ita.coursera.forum.entity.Usuario;
import ita.coursera.forum.service.AutenticacaoException;
import ita.coursera.forum.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author higor
 */
@WebServlet(urlPatterns = {"/ranking"})
public class Ranking extends BaseServlet{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UsuarioDao usuarioDao = new UsuarioDaoJdbc(JdbcUtil.getDataSource());
    try {
      Usuario usuario = getUsuario(req);
      List<Usuario> usuarios = usuarioDao.recuperarTodos();
      req.setAttribute("usuarios", usuarios);
      req.setAttribute("usuario", usuario.getNome());
      req.setAttribute("titulo", "Ranking");
      req.getRequestDispatcher("ranking.jsp").forward(req, resp);
    } catch (SQLException e) {
      e.printStackTrace();
      req.setAttribute("erro", e.getMessage());
    } catch (AutenticacaoException e) {
      e.printStackTrace();
      resp.sendRedirect("/login");
    }
  }
}
