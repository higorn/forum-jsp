/*
 * File:   Topicos.java
 *
 * Created on 29/12/16, 21:56
 */
package ita.coursera.forum.controller;

import ita.coursera.forum.dao.TopicoDao;
import ita.coursera.forum.dao.UsuarioDao;
import ita.coursera.forum.dao.impl.TopicoDaoJdbc;
import ita.coursera.forum.dao.impl.UsuarioDaoJdbc;
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
import java.util.List;

/**
 * @author higor
 */
@WebServlet(urlPatterns = {"/topicos"})
public class Topicos extends BaseServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Usuario usuario = getUsuario(req);
      req.setAttribute("usuario", usuario.getNome());
      req.setAttribute("titulo", "Tópicos");

      String id = req.getParameter("id");
      if (id == null) {
        listarTopicos(req, resp);
        return;
      }

      TopicoDao dao = new TopicoDaoJdbc(JdbcUtil.getDataSource());
      Topico topico = dao.recuperar(Integer.parseInt(id));
      req.setAttribute("topico", topico);
      req.getRequestDispatcher("topico.jsp").forward(req, resp);
    } catch (AutenticacaoException e) {
      e.printStackTrace();
      resp.sendRedirect("/login");
    } catch (SQLException e) {
      e.printStackTrace();
      req.setAttribute("erro", e.getMessage());
      req.getRequestDispatcher("topico.jsp").forward(req, resp);
    }
  }

  private void listarTopicos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    TopicoDao dao = new TopicoDaoJdbc(JdbcUtil.getDataSource());
    try {
      List<Topico> topicos = dao.recuperarTodos();
      req.setAttribute("topicos", topicos);
      req.getRequestDispatcher("topicos.jsp").forward(req, resp);
    } catch (SQLException e) {
      e.printStackTrace();
      req.setAttribute("erro", e.getMessage());
      req.getRequestDispatcher("topico.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String cmd = req.getParameter("cmd");
    if (cmd == null) {
      resp.sendRedirect("/topicos");
      return;
    }
    if (cmd.equals("criar")) {
      criar(req, resp);
    } else if (cmd.equals("salvar")) {
      salvar(req, resp);
    }
  }

  private void criar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("titulo", "Novo Tópico");
    req.getRequestDispatcher("novoTopico.jsp").forward(req, resp);
  }

  private void salvar(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    try {
      Usuario usuario = getUsuario(req);
      req.setAttribute("usuario", usuario.getNome());

      String titulo = req.getParameter("titulo");
      String conteudo = req.getParameter("conteudo");
      Topico topico = new Topico(null, titulo, conteudo, usuario.getLogin());
      TopicoDao dao = new TopicoDaoJdbc(JdbcUtil.getDataSource());
      dao.inserir(topico);
      UsuarioDao usuarioDao = new UsuarioDaoJdbc(JdbcUtil.getDataSource());
      usuarioDao.adicionarPontos(usuario.getLogin(), 10);
      resp.sendRedirect("/topicos");
    } catch (SQLException e) {
      e.printStackTrace();
      req.setAttribute("erro", e.getMessage());
      req.getRequestDispatcher("novoTopico.jsp").forward(req, resp);
    } catch (AutenticacaoException e) {
      e.printStackTrace();
      resp.sendRedirect("/login");
    }
  }

}
