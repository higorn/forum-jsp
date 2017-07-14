/*
 * File:   AutenticadorTest.java
 *
 * Created on 28/12/16, 21:27
 */
package ita.coursera.forum.service;

import ita.coursera.forum.dao.UsuarioDao;
import ita.coursera.forum.dao.impl.UsuarioDaoJdbc;
import ita.coursera.forum.entity.Usuario;
import ita.coursera.forum.util.JdbcUtil;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author higor
 */
public class AutenticadorTest {
  private static Map<String, Usuario> usuarios = new HashMap<>();
  static {
    usuarios.put("nicanor", new Usuario("nicanor", "nicanor@email.com", "Nicanor", "1234", 0));
  }

  private IDatabaseTester databaseTester;
  private UsuarioDao dao;
  private UsuarioDao daoMock;
  private Autenticador autenticador;

  @BeforeClass
  public static void dbSetup() throws SQLException, IOException {
  }

  @Before
  public void setUp() throws Exception {
    daoMock = new UsuarioDao() {
      @Override
      public void inserir(Usuario usuario) throws SQLException {

      }

      @Override
      public List<Usuario> recuperarTodos() throws SQLException {
        return null;
      }

      @Override
      public Usuario recuperar(String login) throws SQLException {
        if (usuarios.containsKey(login)) {
          return usuarios.get(login);
        }
        throw new SQLException("Usuario " + login + " não encontrado.");
      }

      @Override
      public void adicionarPontos(String login, Integer pontos) throws SQLException {

      }

      @Override
      public List<Usuario> ranking() throws SQLException {
        return null;
      }
    };
    autenticador = new Autenticador(daoMock);

    FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
    databaseTester = new JdbcDatabaseTester(JdbcUtil.getDbDriver(), JdbcUtil.getDbUrl(),
        JdbcUtil.getDbUser(), JdbcUtil.getDbPass());
    databaseTester.setDataSet(loader.load("/data/dataset.xml"));
    databaseTester.onSetup();
    dao = new UsuarioDaoJdbc(JdbcUtil.getDataSource());
  }

  @Test
  public void paraUsuarioESenhaValidosDeveAutenticarComSucessoMock() throws LoginException {
    String nomeUsuario = autenticador.autenticar("nicanor", "1234");
    assertEquals("Nicanor", nomeUsuario);
  }

  @Test(expected = LoginException.class)
  public void paraSenhaInvalidaDeveJogarExcessaoMock() throws LoginException {
    String nomeUsuario = autenticador.autenticar("nicanor", "123");
  }

  @Test(expected = LoginException.class)
  public void paraUsuarioInvalidoDeveJogarExcessaoMock() throws LoginException {
    String nomeUsuario = autenticador.autenticar("nicano", "1234");
  }

  @Test
  public void paraUsuarioESenhaValidosDeveAutenticarComSucesso() throws LoginException {
    Autenticador autenticador = new Autenticador(dao);
    String nomeUsuario = autenticador.autenticar("dante", "1234");
    assertEquals("Dante", nomeUsuario);
    nomeUsuario = autenticador.autenticar("ramon", "4321");
    assertEquals("Ramon", nomeUsuario);
  }

  @Test(expected = LoginException.class)
  public void paraSenhaInvalidaDeveJogarExcessao() throws LoginException {
    Autenticador autenticador = new Autenticador(dao);
    String nomeUsuario = autenticador.autenticar("ledesma", "123");
  }

  @Test(expected = LoginException.class)
  public void paraUsuarioInvalidoDeveJogarExcessao() throws LoginException {
    Autenticador autenticador = new Autenticador(dao);
    String nomeUsuario = autenticador.autenticar("ledemas", "1234");
  }
}
