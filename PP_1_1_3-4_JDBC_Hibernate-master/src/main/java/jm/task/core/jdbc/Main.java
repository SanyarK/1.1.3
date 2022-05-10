package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    public Main() {
    }
   public static void main(String[] args) {
        // реализуйте алгоритм здесь
        userService.createUsersTable();
        userService.saveUser("Джо", "Байден", (byte)78);
        userService.saveUser("Трамп", "Дональд", (byte)74);
        userService.saveUser("Барак", "Обама", (byte)59);
        userService.saveUser("Джордж", "Буш", (byte)74);
        userService.removeUserById(2L);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}



