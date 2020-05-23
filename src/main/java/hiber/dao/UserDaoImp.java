package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().persist(user);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByNameSeries(String name, int series) {
      Session session = sessionFactory.openSession();
      try {
         Query query = session.createQuery("from Car where name= :name and series= :series");
         query.setParameter("name", name);
         query.setParameter("series", series);
         Car car = (Car) query.uniqueResult();
         Long id = car.getId();
         Query query2 = session.createQuery("from User where id= :id");
         query2.setParameter("id", id);
         User user = (User) query2.uniqueResult();
         return user;
      } catch (Exception r) {
         return null;
      } finally {
         session.close();
      }
   }

}
