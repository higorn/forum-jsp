/*
 * File:   Comentarios.java
 *
 * Created on 12/01/17, 23:59
 */
package ita.coursera.forum.controller;

import ita.coursera.forum.dao.TopicoDao;
import ita.coursera.forum.dao.UsuarioDao;
import ita.coursera.forum.dao.impl.TopicoDaoJdbc;
import ita.coursera.forum.dao.impl.UsuarioDaoJdbc;
import ita.coursera.forum.entity.Comentario;
import ita.coursera.forum.entity.Topico;
import ita.coursera.forum.entity.Usuario;
import ita.coursera.forum.service.AutenticacaoException;
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
@WebServlet(urlPatterns = {"/comentario"})
public class Comentarios extends BaseServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Usuario usuario = getUsuario(req);
      String comentarioTxt = req.getParameter("comentario");
      String idTopico = req.getParameter("idTopico");
      TopicoDao topicoDao = new TopicoDaoJdbc(JdbcUtil.getDataSource());
      Topico topico = topicoDao.recuperar(Integer.parseInt(idTopico));
      Comentario comentario = new Comentario(null, comentarioTxt, usuario.getLogin(), topico);
      topicoDao.inserirComentario(comentario);
      UsuarioDao usuarioDao = new UsuarioDaoJdbc(JdbcUtil.getDataSource());
      usuarioDao.adicionarPontos(usuario.getLogin(), 3);
      resp.sendRedirect("/topicos?id="+idTopico);
    } catch (SQLException e) {
      e.printStackTrace();
      req.setAttribute("erro", e.getMessage());
      req.getRequestDispatcher("topico.jsp").forward(req, resp);
    } catch (AutenticacaoException e) {
      e.printStackTrace();
      resp.sendRedirect("/login");
    }
  }
}
