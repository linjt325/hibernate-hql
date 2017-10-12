package com.lin.hibernate_hql;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.lin.hibernate_hql.base.BaseConnection;
import com.lin.hibernate_hql.pojo.Device;
import com.lin.hibernate_hql.pojo.Point;

public class HQLTest extends BaseConnection{

	@Test
	public void qryTest(){
		
//		Point point=(Point) session.get(Point.class, "8a8a9c3f572251eb0157225b201a0000");
		StringBuilder sb=new StringBuilder(String.format("From Point p LEFT JOIN p.devices  where p.pointId=:id "));
		Query query =session.createQuery(sb.toString());
		
//		Point point= (Point) query.setString("id","8a8a9c3f572251eb0157225b201a0000").list().get(0);
		Object[] point=  (Object[]) query.setString("id","8a8a9c3f572251eb0157225b201a0000").list().get(0);
		System.out.println(point);
//		System.out.println(point.getDevices());
	}
	
	/**
	 * 根据构造器获取对应的投影
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetEntity(){
		StringBuilder sb=new StringBuilder(String.format("SELECT new Point(p.pointNo,p.pointName,null,null,null) From Point p  where p.pointId=:id "));
		Query query =session.createQuery(sb.toString());
		
//		Point point= (Point) query.setString("id","8a8a9c3f572251eb0157225b201a0000").list().get(0);
		List<Device> point=  (List<Device>) query.setString("id","8a8a9c3f572251eb0157225b201a0000").list();
		System.out.println(point);
	}
	
	@Test
	public void testtQBC(){
//		StringBuilder sb=new StringBuilder(String.format("From Point p   "));
		Criteria c=session.createCriteria(Point.class);
		Conjunction con=new Conjunction();//综合条件查询
		con.add(Restrictions.eq("isDeleted", 0));
		con.add(Restrictions.eq("pointId", "8a8a9c3f572251eb0157225b201a0000"));
		c.add(con);
		c.addOrder(Order.asc("pointId"));
		List<Point> ps=c.list();
		System.out.println(ps.get(0).getDevices());
		
//		Conjunction		//AND
//		Disjunction  // OR
//		Projection //统计查询
		
//		con.add(criterion)
//		Criterion
		
		
	}
}
