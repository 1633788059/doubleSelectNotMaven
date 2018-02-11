package com.doubleselect.dao.sys;

import java.util.Map;

import com.doubleselect.dao.IBaseDao;
import com.doubleselect.model.sys.Tuser;

public interface IUserDao extends IBaseDao<Tuser> {

	void update(String hql, Map<String, Object> params);

}
