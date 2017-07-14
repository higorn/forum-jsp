/*
 * File:   Cadastro.java
 *
 * Created on 05/01/17, 21:07
 */
package ita.coursera.forum.controller;

import ita.coursera.forum.dao.UsuarioDao;
import ita.coursera.forum.dao.impl.UsuarioDaoJdbc;
import ita.coursera.forum.entity.Usuario;
import ita.coursera.forum.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author higor
 */
@WebServlet(urlPatterns = {"/cadastro"})
public class Cadastro extends BaseServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String nome = req.getParameter("nome");
    String login = req.getParameter("login");
    String email = req.getParameter("email");
    String senha = req.getParameter("senha");
    Usuario usuario = new Usuario(login, email, nome, senha, 0);
    UsuarioDao dao = new UsuarioDaoJdbc(JdbcUtil.getDataSource());
    try {
      dao.inserir(usuario);
      resp.sendRedirect(resp.encodeRedirectURL("/login"));
    } catch (SQLException e) {
      e.printStackTrace();
      req.setAttribute("erro", e.getMessage());
      req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
    }
  }
}
