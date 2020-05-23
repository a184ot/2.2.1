package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
User user2 = new User("User1", "Lastname1", "user1@mail.ru");
user2.setCar(new Car(user2,"VW",234));
userService.add(user2);
User user1 = new User("User2", "Lastname2", "user2@mail.ru");
user1.setCar(new Car(user1,"BMW",976));
userService.add(user1);
User user3 = new User("User3", "Lastname3", "user3@mail.ru");
user3.setCar(new Car(user3,"Lexus",457));
userService.add(user3);
User user4 = new User("User4", "Lastname4", "user4@mail.ru");
user4.setCar(new Car(user4,"MAN",446));
userService.add(user4);
User user5 = new User("User5", "Lastname5", "user5@mail.ru");
user5.setCar(new Car(user5,"ZAZ",666));
userService.add(user5);
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
          System.out.println("Car name = " + user.getCar().getName());
          System.out.println("Car series = " + user.getCar().getSeries());
         System.out.println();
      }
       System.out.println("check find user by name and series of car");
      String name = "Lexus";
      int series = 457;
       System.out.println("name = "+ name + " series = " + series);
       User userCar = userService.getUserByNameSeries(name,series);
       System.out.println("user name = " + userCar.getFirstName());
       System.out.println("user last name = " + userCar.getLastName());
       System.out.println("user email = " + userCar.getEmail());
      context.close();
   }

}
