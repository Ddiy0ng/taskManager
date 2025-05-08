package com.example.taskmanager.repository;

import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Repository        //@Component와 동일, SpringBean으로 등록한다는 뜻
public class TaskRepository {
    //db역할: Repository
    //고유번호 지정 및 db에 저장
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public TaskResponseDto saveTask(Task task) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("task").usingGeneratedKeyColumns("id");
        jdbcInsert.withTableName("task").usingGeneratedKeyColumns("postDate");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("password", task.getPassword());
        parameters.put("name", task.getName());
        parameters.put("tasks", task.getTasks());

        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        Timestamp postDate = new Timestamp(System.currentTimeMillis());

        return new TaskResponseDto(key.longValue(), task.getName(), task.getTasks(), postDate);
    }

    public int deleteTask(Long id) {
        // 쿼리의 영향을 받은 row 수를 int로 반환한다.
        return jdbcTemplate.update("delete from task where id = ?", id);
    }

}
