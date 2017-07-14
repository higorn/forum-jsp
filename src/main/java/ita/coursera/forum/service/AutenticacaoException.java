/*
 * File:   AuthorizationException.java
 *
 * Created on 18/01/17, 20:58
 */
package ita.coursera.forum.service;

/**
 * @author higor
 */
public class AutenticacaoException extends Exception {

  public AutenticacaoException(String message) {
    super(message);
  }

  public AutenticacaoException(String message, Throwable cause) {
    super(message, cause);
  }
}
