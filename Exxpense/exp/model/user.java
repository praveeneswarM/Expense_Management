package exp.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class user {

    static Conn db = new Conn();
    static Connection con;

    static {
        try {
            con = db.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static String signin(String mail,String pas)
    {
        try{
            String sql="SELECT * FROM user WHERE mail=?";   
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1, mail);
            ResultSet rt=stmt.executeQuery();
            if(rt.next())
            {
                String p=rt.getString("pass");
                String role=rt.getString("role");
                String name=rt.getString("name");
                String u_id=rt.getString("id");
                String ret=u_id+","+name+","+role+","+p;
                if(p.equals(pas))
                {
                    return ret;
                }   
            }
            return null;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static int signup(String name,String mail,String pass)
    {
        try{
            String sql="INSERT INTO user (name,mail,pass) VALUES (?,?,?)";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, mail);
            stmt.setString(3, pass);
            int rt=stmt.executeUpdate();
            if(rt>0)
            {
                return 1;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
