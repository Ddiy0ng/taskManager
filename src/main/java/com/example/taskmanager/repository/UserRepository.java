package com.example.taskmanager.repository;

import com.example.taskmanager.dto.UserRequestDto;
import com.example.taskmanager.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //create
    public void createUser(UserRequestDto userRequestDto){
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update("insert into taskmanageruser (userName, email, postDate, updateDate) values (?, ?, ?, ?)", userRequestDto.getUserName(), userRequestDto.getEmail(), currentDate, currentDate);
    }

    //readAll
    public List<User> readAllUsers(){
        List<User> userList = jdbcTemplate.query("select * from taskmanageruser order by updateDate desc", userRowMapper());
        return userList;
    }

    //read
    public User readUser(Long userId){
       User user = jdbcTemplate.queryForObject("select * from taskmanageruser where userId = ?", userRowMapper(), userId);
       return user;
    }

    //update
    public void updateUser(Long userId, UserRequestDto userRequestDto){
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update("update taskmanageruser set userName = ?, email = ?, updateDate = ? where userId = ?", userRequestDto.getUserName(), userRequestDto.getEmail(), currentDate, userId);
    }

    //delete
    public int deleteUser(Long userId){
        return jdbcTemplate.update("delete from taskmanageruser where userId = ?", userId);
    }

    private RowMapper<User> userRowMapper(){
        return (rs, rowNum) -> new User(
                rs.getLong("userId"),
                rs.getString("userName"),
                rs.getString("email"),
                rs.getTimestamp("postDate"),
                rs.getTimestamp("updateDate")
        );
    }
}
