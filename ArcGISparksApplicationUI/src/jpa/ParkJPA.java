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
import entities.Region;
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
	
	public List<Region> getDBRegionRecords(){
		List<Region> recordList = new ArrayList<Region>();
		if(em.isOpen())
		{
			Query q;
			q = em.createQuery("select x from Region x");
			@SuppressWarnings("unchecked")
			List<Region>myRecordList = q.getResultList();
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
	
	private String myQuery1;
	public void setMyQuery1(String newQuery1) {
		myQuery1 = newQuery1;
	}
	private String getMyQuery1() {
		return myQuery1;
	}
	
	private String myQuery2;
	public void setMyQuery2(String newQuery2) {
		myQuery2 = newQuery2;
	}
	private String getMyQuery2() {
		return myQuery2;
	}
	
	@SuppressWarnings("unchecked")
	public List<Site> getDBSites(){
		
		List<Site> entityList = new ArrayList<Site>();
		if(em.isOpen())
		{
			Query q0;
			q0 = em.createQuery(getMyQuery());
			
			@SuppressWarnings("unchecked")
			List<Site>myResultList = q0.getResultList();
			entityList.addAll(myResultList);
			
			//todo clean up duplicates
			if(getMyQuery1() != null) {
				Query q1;
				q1 = em.createQuery(getMyQuery1());
				myResultList = q1.getResultList();
				entityList.addAll(myResultList);
			}
			
			if(getMyQuery2() != null) {
				Query qSite;
				Query qState;
				String query = getMyQuery2();
				List<Site> mySiteResultsList;
				List<State> myStateResultsList;
				
				qState = em.createQuery(query);
				
				//q2 = em.createQuery(getMyQuery2());
				myStateResultsList = qState.getResultList();
				
				query = "Select x from Site x where x.state_id in ("+myStateResultsList+")";
				query = query.replace('[', ' ').replace(']', ' ');
				qSite = em.createQuery(query);
				mySiteResultsList = qSite.getResultList();
				
				entityList.addAll(mySiteResultsList);
			}
		}
		return entityList;
	}

}
