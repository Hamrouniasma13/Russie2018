package IServices;


import entities.User;
import java.sql.SQLException;

public interface IServiceUser {
    
    public String Login(String mail, String pass) throws SQLException;

    public boolean Insert(User user);

    public void Update(User user);

    public User Find(int id);

    public void Delete(int id);
}
