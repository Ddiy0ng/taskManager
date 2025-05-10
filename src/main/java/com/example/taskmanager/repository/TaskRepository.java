package com.example.taskmanager.repository;
import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.entity.Task;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository        //@Component와 동일, SpringBean으로 등록한다는 뜻
public class TaskRepository {
    //db역할: Repository
    //고유번호 지정 및 db에 저장
    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Task saveTask(Task task) {

        //jdbc 템플릿 사용
        // Insert: 데이터 저장 시 사용
        // insert query를 문자열로 작성하지 않아도 됨
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());

        //task 테이블에 insert할 것임
        //usingGeneratedKeyColums: 테이블의 키 값 설정
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("taskId");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("password", task.getPassword());
        parameters.put("name", task.getName());
        parameters.put("tasks", task.getTasks());
        parameters.put("postDate", currentDate);
        parameters.put("updateDate", currentDate);

        // insert하면 식별자가 autoIncrement로 생성이 됨. 저장 후 생성된 key 값을 number 타입으로 반환
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        Task resultTask = jdbcTemplate.queryForObject("select * from schedule where taskId = ?", taskRowMapper(), key.longValue());
        return resultTask;
    }

    public List<Task> readAllTasks() {
        List<Task> taskList = jdbcTemplate.query("select * from schedule order by updateDate desc", taskRowMapper());
        return taskList;
    }

    public Task readTask(Long taskId){
        try{
            Task readTask = jdbcTemplate.queryForObject("select * from schedule where taskId = ?", taskRowMapper(), taskId);
            return readTask;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public int updateTask(Long taskId, TaskRequestDto taskRequestDto){
        String name = taskRequestDto.getName();
        String tasks = taskRequestDto.getTasks();
        Timestamp updateDate = new Timestamp(System.currentTimeMillis());
        return jdbcTemplate.update("update schedule set name = ?, tasks = ?, updateDate = ? where taskId = ?", name, tasks, updateDate, taskId);
    }

    public int deleteTask(Long taskId) {
        // 쿼리의 영향을 받은 row 수를 int로 반환한다.
        return jdbcTemplate.update("delete from schedule where taskId = ?", taskId);
    }

    private RowMapper<Task> taskRowMapper(){
        return new RowMapper<Task>() {
            @Override
            public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Task(
                        rs.getLong("taskId"),
                        rs.getString("name"),
                        rs.getString("tasks"),
                        rs.getTimestamp("postDate"),
                        rs.getTimestamp("updateDate")
                );
            }
        };
    }
}
