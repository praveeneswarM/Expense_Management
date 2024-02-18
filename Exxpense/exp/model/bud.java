package exp.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class bud {

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

    public static boolean addbud(String u_id,String amt,String std,String end)
    {
        try{
            String sq="SELECT id FROM bud";
            PreparedStatement c=con.prepareStatement(sq);
            ResultSet rr=c.executeQuery();
            if(rr.next())
            {
                String id=rr.getString("id");
                if(id.equals(u_id))
                {
                    String sql="UPDATE bud SET amt=?,std=?,end=? WHERE id=?";
                    PreparedStatement stmt=con.prepareStatement(sql);
                    stmt.setString(1, amt);
                    stmt.setString(2, std);
                    stmt.setString(3, end);
                    stmt.setString(4, u_id);
                    int rt=stmt.executeUpdate();
                    if(rt>0)
                    return true;
                    else
                    return false;
                }
            }
            else
            {
                String ss="INSERT INTO bud (id,amt,std,end) VALUES (?,?,?,?)";
                PreparedStatement cc=con.prepareStatement(ss);
                cc.setString(1, u_id);
                cc.setString(2, amt);
                cc.setString(3, std);
                cc.setString(4, end);
                int rrt=cc.executeUpdate();
                if(rrt>0)
                return true;
                else
                return false;

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
