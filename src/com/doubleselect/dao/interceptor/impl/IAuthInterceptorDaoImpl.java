package com.doubleselect.dao.interceptor.impl;

import org.springframework.stereotype.Repository;

import com.doubleselect.dao.impl.IBaseDaoImpl;
import com.doubleselect.dao.interceptor.IAuthInterceptorDao;
import com.doubleselect.model.sys.Tauth;


@Repository
public class IAuthInterceptorDaoImpl extends IBaseDaoImpl<Tauth> implements IAuthInterceptorDao{

}

