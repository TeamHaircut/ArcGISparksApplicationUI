package jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Designation;
import entities.Site;
import entities.State;

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
	
	public List<Site> getDBSiteRecords(){
		List<Site> recordList = new ArrayList<Site>();
		if(em.isOpen())
		{
			Query q;
			q = em.createQuery("select x from Site x");
			@SuppressWarnings("unchecked")
			List<Site>myRecordList = q.getResultList();
			recordList.addAll(myRecordList);
		}
		return recordList;
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
	
	public List<State> getDBStateRecords(){
		List<State> recordList = new ArrayList<State>();
		if(em.isOpen())
		{
			Query q;
			q = em.createQuery("select x from State x");
			@SuppressWarnings("unchecked")
			List<State>myRecordList = q.getResultList();
			recordList.addAll(myRecordList);
		}
		return recordList;
	}
	
	private String myQuery;
	public void setMyQuery(String newQuery) {
		myQuery = newQuery;
	}
	private String getMyQuery() {
		return myQuery;
	}
	
	public List<Site> getDBSites(){
		
		List<Site> entityList = new ArrayList<Site>();
		if(em.isOpen())
		{
			Query q;
			q = em.createQuery(getMyQuery());
			@SuppressWarnings("unchecked")
			List<Site>myResultList = q.getResultList();
			entityList.addAll(myResultList);
		}
		return entityList;
	}

}
