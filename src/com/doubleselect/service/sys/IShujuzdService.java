package com.doubleselect.service.sys;

import java.util.List;

import com.doubleselect.model.sys.Tshujuzd;


public interface IShujuzdService {
	
	public long queryTotal(String zdlb);
	
	public List<Tshujuzd> query(int page, int rows,String name);
	
	public boolean add(Tshujuzd sjzd);
	
	public void add(List<Tshujuzd> objList);
	
	public void update(List<Tshujuzd> objList);
	
	public void delete(int id)  throws Exception;
	
	public void update(Tshujuzd bean) ;
}
