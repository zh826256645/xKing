package xKing.dataSource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import xKing.config.DataConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataConfig.class})
public class TestDataSource {
	
//	@Autowired
	private DataSource dataSource;
	
	@PersistenceContext
	private EntityManager em;
	
	@Test
	public void Test2DataSource() {
		System.out.println(dataSource);
	}
	
	@Test
	@Transactional
	public void testEntityManager() {
		System.out.println(em);
	}
}
