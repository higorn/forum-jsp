/*
 * File:   TopicoDaoTest.java
 *
 * Created on 09/01/17, 22:11
 */
package ita.coursera.forum.dao;

import ita.coursera.forum.dao.impl.TopicoDaoJdbc;
import ita.coursera.forum.entity.Comentario;
import ita.coursera.forum.entity.Topico;
import ita.coursera.forum.util.JdbcUtil;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author higor
 */
public class TopicoDaoTest {
  private IDatabaseTester databaseTester;
  private TopicoDao dao;

  @Before
  public void setUp() throws Exception {
    FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
    databaseTester = new JdbcDatabaseTester(JdbcUtil.getDbDriver(), JdbcUtil.getDbUrl(),
        JdbcUtil.getDbUser(), JdbcUtil.getDbPass());
    databaseTester.setDataSet(loader.load("/data/dataset.xml"));
    databaseTester.onSetup();
    dao = new TopicoDaoJdbc(JdbcUtil.getDataSource());
  }

  @After
  public void tearDown() throws Exception {
    databaseTester.onTearDown();
  }

  @Test
  public void deveBuscarTodosOsTopicos() throws SQLException {
    List<Topico> topicos = dao.recuperarTodos();
    assertNotNull(topicos);
    assertEquals(2, topicos.size());
  }

  @Test
  public void deveRecuperarUmTopicoPeloId() throws SQLException {
    Topico topico = dao.recuperar(1);
    assertNotNull(topico);
    assertEquals("Java Avançado", topico.getTitulo());
    assertNotNull(topico.getComentarios());
    assertEquals(2, topico.getComentarios().size());
  }

  @Test
  public void deveInserirUmNovoTopico() throws Exception {
    Topico topico = new Topico(null, "Java Avançado Test", "lorem ipsum, bla blablaba", "ledesma");
    dao.inserir(topico);

    IDataSet currentDataset = databaseTester.getConnection().createDataSet();
    ITable currentTable = currentDataset.getTable("topico");
    FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
    IDataSet expectedDataset = loader.load("/data/datasetExpected.xml");
    ITable expectedTable = expectedDataset.getTable("topico");
    Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[] {"id_topico"});
  }

  @Test
  public void deveInserirUmNovoComentarioAUmTopico() throws Exception {
    Topico topico = dao.recuperar(2);
    Comentario comentario = new Comentario(1, "Cookie cookie = new Cookie(key, Val); response.addCookie" +
        "(cookie);", "ledesma", topico);
    dao.inserirComentario(comentario);

    IDataSet currentDataset = databaseTester.getConnection().createDataSet();
    ITable currentTable = currentDataset.getTable("comentario");
    FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
    IDataSet expectedDataset = loader.load("/data/datasetExpected.xml");
    ITable expectedTable = expectedDataset.getTable("comentario");
    Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[] {"id_comentario"});
  }
}
