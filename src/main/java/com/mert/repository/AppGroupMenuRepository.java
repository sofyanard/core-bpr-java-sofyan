package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.AppGroupMenu;

public interface AppGroupMenuRepository extends JpaRepository<AppGroupMenu, Integer> {
	
	@Query(value = "select m.* from appgroupmenu m where m.group_id = cast(:groupId as varchar) order by m.menu_id", nativeQuery=true)
	List<AppGroupMenu> findByGroup(@Param("groupId") String groupId);
	
	@Query(value = "select g.* from appgroupmenu g join appmenu m on g.menu_id = m.menu_id where m.level = 1 and g.group_id = :groupId order by g.menu_id", nativeQuery=true)
	List<AppGroupMenu> findParentByGroup(@Param("groupId") String groupId);
	
	@Query(value = "select g.* from appgroupmenu g join appmenu m on g.menu_id = m.menu_id where m.level = 2 and g.group_id = :groupId and m.parent = :parent order by g.menu_id", nativeQuery=true)
	List<AppGroupMenu> findChildByGroupParent(@Param("groupId") String groupId, @Param("parent") String parent);
	
}
