package jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import supportclasses.QueryState;
import entities.Designation;
import entities.Site;

public class ParkJPA {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("testParkDB");
	private EntityManager em;
	
	public ParkJPA()
	{
		em = null;
		em = getEntityManager("jdbc:derby:c:/users/public/documents/databases/derby/mydb/testParkDB;");
	}
	
	@SuppressWarnings("unchecked")
	protected EntityManager getEntityManager(String url) {
	    @SuppressWarnings("unused")
		EntityManager em = null;
	    @SuppressWarnings("rawtypes")
		Map properties = new HashMap();
	    properties.put("javax.persistence.jdbc.url", url);
	    try {
	        emf = Persistence.createEntityManagerFactory("testParkDB", properties);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return em = (EntityManager) emf.createEntityManager();
	}
	
	public List<Designation> getDBDesignationRecords(){
		List<Designation> recordList = new ArrayList<Designation>();
		if(em.isOpen())
		{
			Query q;
			q = em.createQuery("select x from Designation x");
			@SuppressWarnings("unchecked")
			List<Designation>myRecordList = q.getResultList();
			recordList.addAll(myRecordList);
		}
		return recordList;
	}
	
	public List<Site> getDBSites(QueryState state){
		
		String visitedCriteria = "";
		switch(state.getVisited().intValue()) {
			case 0:
				visitedCriteria = " where x.visited = 1";
				break;
			case 1:
				visitedCriteria = " where x.visited = 0";
				break;
		}
		
		List<Site> entityList = new ArrayList<Site>();
		if(em.isOpen())
		{
			Query q;
			//q = em.createQuery("select s.site_name, d.designation_name from Site s, Designation d where s.designation_id = d.designation_id");
			q = em.createQuery("select x from Site x"+ visitedCriteria);
			@SuppressWarnings("unchecked")
			List<Site>myResultList = q.getResultList();
			entityList.addAll(myResultList);
		}
		return entityList;
	}

}
