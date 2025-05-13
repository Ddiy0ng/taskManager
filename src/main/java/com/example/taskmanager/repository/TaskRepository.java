package com.example.taskmanager.repository;
import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.entity.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository        //@Component와 동일, SpringBean으로 등록한다는 뜻
public class TaskRepository {
    //db역할: Repository
    //고유번호 지정 및 db에 저장

    private final JdbcTemplate jdbcTemplate;
    public TaskRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createTask(long userId, TaskRequestDto taskRequestDto){
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update("insert into schedule (password, userId, tasks, postDate, updateDate) values (?, ?, ?, ?, ?)", taskRequestDto.getPassword(), userId, taskRequestDto.getTasks(), currentDate, currentDate);
    }

    public List<Task> readAllTasks(long userId){
        List<Task> taskList = jdbcTemplate.query("select schedule.taskId, taskmanageruser.userName, schedule.tasks, schedule.postDate, schedule.updateDate from schedule as schedule join taskmanageruser as taskmanageruser on schedule.userId = taskmanageruser.userId where schedule.userId = ? order by schedule.updateDate desc", taskRowMapper(), userId);
        return taskList;
    }

    public Task readTask(long userId, long taskId){
        Task task = jdbcTemplate.queryForObject("select schedule.taskId, taskmanageruser.userName, schedule.tasks, schedule.postDate, schedule.updateDate from schedule as schedule join taskmanageruser as taskmanageruser on schedule.userId = taskmanageruser.userId where schedule.userId = ? and schedule.taskId = ?  order by schedule.updateDate desc", taskRowMapper(), userId, taskId);
        return task;
    }

    public void updateTask(long taskId, TaskRequestDto taskRequestDto){
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update("update schedule set tasks = ?, updateDate = ? where taskId = ?", taskRequestDto.getTasks(), currentDate, taskId);
    }

    public int deleteTask(long taskId){
        return jdbcTemplate.update("delete from schedule where taskId = ?", taskId);
    }

    private RowMapper<Task> taskRowMapper(){
        return new RowMapper<Task>() {
            @Override
            public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Task(
                        rs.getLong("taskId"),
                        rs.getString("userName"),
                        rs.getString("tasks"),
                        rs.getTimestamp("postDate"),
                        rs.getTimestamp("updateDate")
                );
            }
        };
    }

}
