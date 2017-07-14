/*
 * File:   Home.java
 *
 * Created on 13/01/17, 00:22
 */
package ita.coursera.forum.controller;

import ita.coursera.forum.entity.Usuario;
import ita.coursera.forum.service.AutenticacaoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author higor
 */
@WebServlet(urlPatterns = {"/home"})
public class Home extends BaseServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Usuario usuario = getUsuario(req);
      resp.sendRedirect("/topicos");
    } catch (AutenticacaoException e) {
      e.printStackTrace();
      resp.sendRedirect("/login");
    }
  }
}
