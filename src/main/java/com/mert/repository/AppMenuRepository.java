package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mert.model.AppMenu;

public interface AppMenuRepository extends JpaRepository<AppMenu, String> {
	
	@Query(value = "select x.* from appmenu x where x.level = :level order by x.menu_id", nativeQuery=true)
	List<AppMenu> findByLevel(Integer level);
	
	@Query(value = "select x.* from appmenu x where x.parent = :parent order by x.menu_id", nativeQuery=true)
	List<AppMenu> findByParent(String parent);
	
	@Query(value = "select x.* from appmenu x where where x.level = :level and x.parent = :parent order by x.menu_id", nativeQuery=true)
	List<AppMenu> findByLevelAndParent(Integer level, String parent);
	
}
