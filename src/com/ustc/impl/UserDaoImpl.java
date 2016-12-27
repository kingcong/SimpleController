package com.ustc.impl;

import java.util.List;

import com.ustc.bean.UserBean;
import com.ustc.config.Conversation;
import com.ustc.dao.BaseDao;
import com.ustc.dao.UserDao;


/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月18日 下午7:10:52 
* 
*/
public class UserDaoImpl extends BaseDao implements UserDao {

	
	@Override
	public UserBean queryUser(String userName) {
		// TODO Auto-generated method stub
//		String sql = "select * from admin where name=?";
//		List<UserBean> list = super.query(sql, new Object[]{userName}, UserBean.class);
//		return  (list!=null&&list.size()>0) ? list.get(0) : null;
		Conversation conversation = Conversation.getInstance();
		return conversation.queryObject("name", userName);
	}

	@Override
	public boolean insertUser(UserBean u) {
		// TODO Auto-generated method stub
//		String sql = "insert into t_user(name,password) values (?,?)";
//		Object[] paramsValue = {u.getName(),u.getPassword()};
//		super.update(sql, paramsValue);
		
		try {
			return Conversation.getInstance().insertUser(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(UserBean u) {
		// TODO Auto-generated method stub
//		String sql = "update t_user set name=?,password=?";
//		Object[] paramsValue = {u.getName(),u.getPassword()};
//		super.update(sql, paramsValue);
//		return true;
		return Conversation.getInstance().updateUser(u);
	}

	@Override
	public boolean deleteUser(UserBean u) {
		// TODO Auto-generated method stub
//		String sql = "delete from admin where name=?";
//		Object[] paramsValue = {u.getName()};
//		super.update(sql, paramsValue);
//		return true;
		return Conversation.getInstance().deleteUser(u);
	}
	
}
