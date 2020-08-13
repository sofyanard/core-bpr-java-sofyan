package com.mert.repository;

import java.util.List;

/**
 * Created by Yasin Mert on 25.02.2017.
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mert.model.Kategori;
import com.mert.model.Task;
import com.mert.model.UserTask;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, Integer> {
	List<Task> findByKategori (Kategori kategori);
}
