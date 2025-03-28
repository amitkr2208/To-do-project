package org.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.todo.model.TodoDao;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoDao, Long>, JpaSpecificationExecutor<TodoDao> {
    List<TodoDao> findAllByUserIdAndActiveIsTrueOrderByPriorityDesc(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM todo WHERE user_id = ?1 AND id = ?2 and active = true order by priority asc")
    TodoDao findByUserIdAndId(Long userId, long Id);
}
