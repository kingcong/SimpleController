package com.ustc.config;

import java.lang.reflect.Field;
import java.util.List;

import com.ustc.bean.UserBean;
import com.ustc.dao.BaseDao;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月25日 下午9:20:23 
* 
*/
public class Conversation {
	
	private static Conversation instance;
	private Conversation(){}
	
	/**
	 * 0.实例化一个单例对象
	 * @return 对象本身
	 */
	public static synchronized Conversation getInstance() {
		if (instance == null) {
			instance = new Conversation();	
//			instance.config();
		}
		return instance;
	}

	private ORMapping orMapping;
	
	public void setOrMapping(ORMapping orMapping) {
		this.orMapping = orMapping;
	}
	
	public ORMapping getOrMapping() {
		return orMapping;
	}
		
	/**
	 * 根据配置创建表
	 */
	public void baseConfigCreateTable() {
		String sql = "CREATE TABLE IF NOT EXISTS ";
		List<ClassInfo> classInfos = orMapping.getClassInfos();
		for (ClassInfo classInfo : classInfos) {
			sql = sql + classInfo.getTable() + "(";
			sql = sql + classInfo.getId_name() + " INT PRIMARY KEY AUTO_INCREMENT" + ",";
			List<ColumnInfo> listColumns = classInfo.getColumnInfos();
			int i = 0;
			for (ColumnInfo columnInfo : listColumns) {
				// 添加列名
				sql = sql + columnInfo.getColumn()+" ";
				if (columnInfo.getType().equals("String")) {
					sql = sql + "VARCHAR(20)";
				}
				if (i != listColumns.size() - 1) {
					sql = sql + ",";
				} else {
					sql = sql + ")" + ";";
				}
				i++;
			}
		}
		System.out.println("sql:"+sql);
		new BaseDao().update(sql, null);
	}
	
	public UserBean queryObject(String key,String value) {
		// TODO Auto-generated method stub
		baseConfigCreateTable();
		String sql = "select * from t_user where name=?";
		
		BaseDao baseDao = new BaseDao();
		List<UserBean> list = baseDao.query(sql, new Object[]{value}, UserBean.class);
		return  (list!=null&&list.size()>0) ? list.get(0) : null;
	}

	/**
	 * 1.插入一个对象
	 * @param u
	 * @return
	 * @throws Exception
	 */
	public boolean insertUser(UserBean u) throws Exception {
		// TODO Auto-generated method stub
//		String sql = "insert into t_user(name,password) values (?,?)";
		baseConfigCreateTable();
		
		Object[] paramsValue = new Object[20];
		
		String sql = "insert into ";

		List<ClassInfo> classInfos = orMapping.getClassInfos();
		for (ClassInfo classInfo : classInfos) {
			if (classInfo.getName().equals("UserBean")) {
				sql = sql + classInfo.getTable() + "";
				Field[] fields=u.getClass().getDeclaredFields(); //获得对象所有属性
				Field field=null;
				
				sql = sql + "(";
				// 遍历属性
				for (int i = 0; i < fields.length; i++) {
					field=fields[i];
					field.setAccessible(true);//修改访问权限
					
					for (int j = 0; j < classInfo.getColumnInfos().size(); j++) {
						
						ColumnInfo columnInfo = classInfo.getColumnInfos().get(j);
						int x = 0;
						if (columnInfo.getName().equals(field.getName())) {	// 属性相等
						     System.out.println(field.getName()+":"+field.get(u));//读取属性值
						     paramsValue[i] = field.get(u);
						     x++;
						     sql = sql  + columnInfo.getColumn();
						     if (j != classInfo.getColumnInfos().size() -1) {
								sql = sql + ",";
							} else {
								sql = sql + ") ";
							}
						}
					}
				}
				sql = sql + "values " + "(";
				// 拼接参数
				for (int i = 0; i < fields.length; i++) {
					sql = sql+"?";
					if (i != fields.length -1) {
						sql = sql + ",";
					} else {
						sql = sql + ")";
					}
				}
				
				sql = sql + ";";
				System.out.println("sql:"+sql);
			}
		}
		
		System.out.println(paramsValue);
//		Object[] paramsValue1 = {u.getName(),u.getPassword()};
		new BaseDao().update(sql, paramsValue);
		return true;
	}

	public boolean updateUser(UserBean u) {
		// TODO Auto-generated method stub
		baseConfigCreateTable();
		
		String sql = "update t_user set name=?,password=?";
		Object[] paramsValue = {u.getName(),u.getPassword()};
		new BaseDao().update(sql, paramsValue);
		return true;
	}

	public boolean deleteUser(UserBean u) {
		// TODO Auto-generated method stub
		baseConfigCreateTable();

		String sql = "delete from t_user where name=?";
		Object[] paramsValue = {u.getName()};
		new BaseDao().update(sql, paramsValue);
		return true;
	}
}
