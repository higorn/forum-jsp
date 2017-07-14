/*
 * File:   Logout.java
 *
 * Created on 18/01/17, 21:22
 */
package ita.coursera.forum.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author higor
 */
@WebServlet(urlPatterns = {"/logout"})
public class Logout extends BaseServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Cookie cookie = new Cookie("login", "");
    resp.addCookie(cookie);
    resp.sendRedirect(resp.encodeRedirectURL("/login"));
  }
}
