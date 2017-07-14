package ita.coursera.forum.dao;

import ita.coursera.forum.dao.impl.UsuarioDaoJdbc;
import ita.coursera.forum.entity.Usuario;
import ita.coursera.forum.util.JdbcUtil;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/*
 * File:   UsuarioDAOTest.java
 *
 * Created on 29/10/16, 11:53
 */
public class UsuarioDAOTest {
  private IDatabaseTester databaseTester;
  private UsuarioDao dao;

  @Before
  public void setUp() throws Exception {
    FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
    databaseTester = new JdbcDatabaseTester(JdbcUtil.getDbDriver(), JdbcUtil.getDbUrl(),
        JdbcUtil.getDbUser(), JdbcUtil.getDbPass());
    databaseTester.setDataSet(loader.load("/data/dataset.xml"));
    databaseTester.onSetup();
    dao = new UsuarioDaoJdbc(JdbcUtil.getDataSource());
  }

  @Test
  public void deveInserirUmNovoUsuarioNoBanco() throws Exception {
    Usuario usuario = new Usuario("nicanor", "nicanor@email.com", "Nica Nor", "1234", 3);
    dao.inserir(usuario);

    IDataSet currentDataset = databaseTester.getConnection().createDataSet();
    ITable currentTable = currentDataset.getTable("usuario");
    FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
    IDataSet expectedDataset = loader.load("/data/datasetExpected.xml");
    ITable expectedTable = expectedDataset.getTable("usuario");
    Assertion.assertEquals(expectedTable, currentTable);
  }

  @Test
  public void deveRecuperarUmUsuarioDoBanco() throws SQLException {
    String expected = "ledesma";
    Usuario usuario = dao.recuperar(expected);
    assertEquals(expected, usuario.getLogin());
  }

  @Test
  public void deveRecuperarTodosOsUsuarios() throws SQLException {
    List<Usuario> usuarios = dao.recuperarTodos();
    assertNotNull(usuarios);
    assertEquals(3, usuarios.size());
  }

  @Test(expected = SQLException.class)
  public void ParaUsuarioInvalidodeveLancarExcessao() throws SQLException {
    String expected = "ledesm";
    Usuario usuario = dao.recuperar(expected);
    assertEquals(expected, usuario.getLogin());
  }

  @Test
  public void deveGravarPontosDeUmUsuarioNoBanco() throws SQLException {
    String expected = "dante";
    Usuario usuario = dao.recuperar(expected);
    assertEquals(expected, usuario.getLogin());
    assertEquals(Integer.valueOf(2), usuario.getPontos());
    dao.adicionarPontos(usuario.getLogin(), 3);
    usuario = dao.recuperar(expected);
    assertEquals(Integer.valueOf(5), usuario.getPontos());
  }

  @Test
  public void deveRetornarRankingDeUsuarios() throws SQLException {
    List<Usuario> usuarioList = dao.ranking();
    assertNotNull(usuarioList);
    assertFalse(usuarioList.isEmpty());
    assertEquals("ledesma", usuarioList.get(0).getLogin());
    assertEquals("ramon", usuarioList.get(1).getLogin());
    assertEquals("dante", usuarioList.get(2).getLogin());
  }
}