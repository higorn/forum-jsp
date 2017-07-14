/*
 * File:   Comentario.java
 *
 * Created on 12/01/17, 21:30
 */
package ita.coursera.forum.entity;

/**
 * @author higor
 */
public class Comentario {
  private Integer id;
  private String comentario;
  private String login;
  private Topico topico;

  public Comentario(Integer id, String comentario, String login, Topico topico) {
    this.id = id;
    this.comentario = comentario;
    this.login = login;
    this.topico = topico;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public Topico getTopico() {
    return topico;
  }

  public void setTopico(Topico topico) {
    this.topico = topico;
  }
}
