/*
 * File:   Login.java
 *
 * Created on 28/12/16, 21:23
 */
package ita.coursera.forum.controller;

import ita.coursera.forum.dao.impl.UsuarioDaoJdbc;
import ita.coursera.forum.service.Autenticador;
import ita.coursera.forum.util.JdbcUtil;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author higor
 */
@WebServlet(urlPatterns = {"/login"})
public class Login extends BaseServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String login = req.getParameter("login");
    String senha = req.getParameter("senha");
    Autenticador autenticador = new Autenticador(new UsuarioDaoJdbc(JdbcUtil.getDataSource()));
    try {
      String nomeUsuario = autenticador.autenticar(login, senha);
      Cookie cookie = new Cookie("login", login);
      resp.addCookie(cookie);
      resp.sendRedirect(resp.encodeRedirectURL("/topicos"));
    } catch (LoginException e) {
      e.printStackTrace();
      req.setAttribute("erro", e.getMessage());
      req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("login.jsp").forward(req, resp);
  }


}
