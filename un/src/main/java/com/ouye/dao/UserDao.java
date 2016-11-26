package com.ouye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ouye.bean.UserEntity;


public interface UserDao extends CrudRepository<UserEntity, String>, PagingAndSortingRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

	@Query(value="SELECT SUBSTRING(t.create_time, 1, 10) createday,count(*) sumcount"+
			" FROM	egisp_rss_user t WHERE	t.source_id IN (1, 2) " +
			" and datediff('2015-12-25 23:59:59',t.create_time)<=365 " +
			" and datediff('2015-12-25 23:59:59',t.create_time)>=0 GROUP BY"+
			" createday ORDER BY createday ",nativeQuery=true)
	List<?> getDailyUserCounts();

	
}
