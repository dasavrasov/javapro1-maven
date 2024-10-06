package ru.stepup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.stepup.entity.User;
import ru.stepup.service.UserService;

import java.util.List;

@ComponentScan("ru.stepup")
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        UserService userService = context.getBean(UserService.class);

        System.out.println("Create a new user 1");
        User user1 = new User("User1");
        userService.insert(user1);
        System.out.println("Create a new user 2");
        User user2 = new User("User2");
        userService.insert(user2);

        //findALL
        List<User> users = userService.findAll();
        users.forEach(user -> System.out.println(user.getId() + " " + user.getName()));

        //Find a user by Name
        System.out.println("Find a user by Name="+user1.getName());
        User foundUser=userService.findByName(user1.getName());
        System.out.println("Found User="+foundUser.getId() + " " + foundUser.getName());

        //update user
        foundUser.setName("User1Updated");
        userService.update(foundUser);
        //findAll after update
        System.out.println("Find All after update User1");
        users = userService.findAll();
        users.forEach(user -> System.out.println(user.getId() + " " + user.getName()));

        //delete user
        System.out.println("Delete foundUser");
        userService.delete(foundUser);

        //findAll after delete
        users = userService.findAll();
        users.forEach(user -> System.out.println(user.getId() + " " + user.getName()));

    }
}
