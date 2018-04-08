package Services;

import IServices.IServiceUser;
import Technique.DataSource;
import entities.User;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class ServiceUser implements IServiceUser{
    private PreparedStatement stmt;
    private Connection cnx;
    private ResultSet result;

    public ServiceUser(){
        cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public String Login(String mail, String pass) throws SQLException {
        String response =checkPassword(mail, pass);
        
        if (response.equals("successful")){
        String requete = "SELECT * FROM `user` WHERE email = ?";
        stmt = cnx.prepareStatement(requete);
        stmt.setString(1, mail);
        result = stmt.executeQuery();
        while (result.next())
       
        User.setInstance(new User(result.getInt("id"), result.getString("nom"), result.getString("prenom"), result.getString("email"),
                result.getString("email_canonical"), result.getString("username"), result.getString("username_canonical"), result.getString("password"),
                result.getString("roles"),
                result.getDate("created_at"), result.getDate("updated_at")));
        }
        return response;
    }

    @Override
    public boolean Insert(User user) {
        try {
            String req = "INSERT INTO `user`("
                    + "`username`, `username_canonical`, `email`, `email_canonical`, "
                    + "`enabled`, `roles`, `nom`, `prenom`" +
                    ", `last_login`, `created_at`, `updated_at`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?, NOW(), NOW(), NOW()) ";

            stmt = cnx.prepareStatement(req, com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getUsername_canonical());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getEmail_canonical());
            stmt.setInt(5, 1);
            stmt.setString(6, user.getRoles());
            stmt.setString(7, user.getFname());
            stmt.setString(8, user.getLname());

            if(stmt.executeUpdate()==1){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void Update(User user) {

    }

    @Override
    public void Delete(int id) {

    }

    private String checkPassword(String mail, String password_plaintext) throws SQLException {
        String requete = "SELECT password FROM `user` WHERE email = ?";
        String stored_hash = null;

        stmt = cnx.prepareStatement(requete);
        stmt.setString(1, mail);
        result = stmt.executeQuery();
        while (result.next())
            stored_hash = result.getString("password").replace("2y","2a");

        if (stored_hash == null) {
            System.out.println("Mail not found");
            return "Mail not found";
        }
        else if (!BCrypt.checkpw(password_plaintext, stored_hash))
        {
            System.out.println("Mot de passe eronné");
            return "Mot de passe eronné";
        }
        else {
            System.out.println("Login successful: " + mail + " | " + password_plaintext + " | " + stored_hash);
            return "successful";
        }
    }

    @Override
    public User Find(int id) {
        try {
            String requete = "SELECT * FROM `user` WHERE id = ?";
            stmt = cnx.prepareStatement(requete);
            stmt.setInt(1, id);
            result = stmt.executeQuery();
            if (result.next()) {
                return  new User(result.getInt("id"), result.getString("nom"), result.getString("prenom"), result.getString("email"),
                result.getString("email_canonical"), result.getString("username"), result.getString("username_canonical"), result.getString("password"),
                result.getString("roles"),
                result.getDate("created_at"), result.getDate("updated_at")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Database Exception");
        }

        return null;
    }

    public boolean findcode(String code)
    {
        try {
            String requete = "SELECT * FROM `user` WHERE confirmation_token = ?";
            stmt = cnx.prepareStatement(requete);
            stmt.setString( 1, code);

            result = stmt.executeQuery();
            if (result.next()) {

                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Database Exception");
        }
        return false;
    }
    public void updatepassword(String c,String mail)
    {
        String New=BCrypt.hashpw(c, BCrypt.gensalt());
        try {

            String req = "UPDATE `user` set password=? where email=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1,New);
            st.setString(2,mail);
            st.executeUpdate();
            String reqA = "UPDATE `user` set confirmation_token=NULL where email=?";
            PreparedStatement stA = cnx.prepareStatement(reqA);
            stA.setString(1,mail);
            stA.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Database Exception");
        }

    }
    public boolean findmail(String mail,String code)
    {
        try {
            String requete = "SELECT * FROM `user` WHERE email = ?";
            stmt = cnx.prepareStatement(requete);
            stmt.setString( 1, mail);

            result = stmt.executeQuery();
            if (result.next()) {
                String req = "UPDATE `user` set confirmation_token=? where email=?";
                PreparedStatement st = cnx.prepareStatement(req);
                st.setString(1,code);
                st.setString(2,mail);
                st.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Database Exception");
        }
        return false;
    }
}
