/*
 * File:   Topico.java
 *
 * Created on 09/01/17, 22:16
 */
package ita.coursera.forum.entity;

import java.util.List;

/**
 * @author higor
 */
public class Topico {
  private Integer id;
  private String titulo;
  private String conteudo;
  private String login;
  private List<Comentario> comentarios;

  public Topico(Integer id, String titulo, String conteudo, String usuario) {
    this.id = id;
    this.titulo = titulo;
    this.conteudo = conteudo;
    this.login = usuario;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }


  public String getConteudo() {
    return conteudo;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public List<Comentario> getComentarios() {
    return comentarios;
  }

  public void setComentarios(List<Comentario> comentarios) {
    this.comentarios = comentarios;
  }
}
