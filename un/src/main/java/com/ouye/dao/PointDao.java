package com.ouye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ouye.bean.PointEntity;


public interface PointDao extends CrudRepository<PointEntity, String>, PagingAndSortingRepository<PointEntity, String>, JpaSpecificationExecutor<PointEntity> {

	@Query(value="SELECT SUBSTRING(t.create_time, 1, 10) createday,count(*) sumcount"+
			" FROM	biz_point t WHERE	t.enterprise_id=:eid " +
			" and datediff('2015-12-25 23:59:59',t.create_time)<=365" +
			" and datediff('2015-12-25 23:59:59',t.create_time)>=0  GROUP BY"+
			" createday ORDER BY createday ",nativeQuery=true)
	List<?> getDailyPointsCounts(@Param("eid")String eid);
	
	
	@Query(value="SELECT SUBSTRING(t.import_time, 1, 10) createday,count(*) sumcount"+
			" FROM	order_base t WHERE	t.enterprise_id=:eid " +
			" and datediff('2015-12-25 23:59:59',t.import_time)<=365" +
			" and datediff('2015-12-25 23:59:59',t.import_time)>=0  GROUP BY"+
			" createday ORDER BY createday ",nativeQuery=true)
	List<?> getDailyOrdersCounts(@Param("eid")String eid);

	@Query(value="SELECT SUBSTRING(t.fendan_time, 1, 10) createday,count(*) sumcount"+
			" FROM	sys_api_fendan t WHERE	t.eid=:eid " +
			" and datediff('2015-12-25 23:59:59',t.fendan_time)<=365" +
			" and datediff('2015-12-25 23:59:59',t.fendan_time)>=0  GROUP BY"+
			" createday ORDER BY createday ",nativeQuery=true)
	List<?> getDailyFendanApiCounts(@Param("eid")String eid);
	
}
