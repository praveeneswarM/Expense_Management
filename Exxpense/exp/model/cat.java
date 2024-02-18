package exp.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.a.SqlDateValueEncoder;
public class cat {
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

    public static ArrayList CatView(String u_id)
    {
        try{
            String sql = "SELECT * FROM cat";
            PreparedStatement stmt=con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData metaData = (ResultSetMetaData)rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            ArrayList<ArrayList<Object>> resultList = new ArrayList<>();
            while (rs.next()) {
                ArrayList<Object> rowData = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    Object data = rs.getObject(i);
                    rowData.add(data);
                }
                resultList.add(rowData);
            }
            return resultList;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean Cval(String id,String amt,String u_id)
    {
        try{
            String sql="SELECT * FROM bud WHERE id=?";
            PreparedStatement bu=con.prepareStatement(sql);
            bu.setString(1, u_id);
            ResultSet rt=bu.executeQuery();
            if(rt.next())
            {
                String at=rt.getString("amt");
                if(Integer.valueOf(amt)>Integer.valueOf(at))
                {
                    return false;
                }
                else
                {
                    String sq="UPDATE bud SET amt=? WHERE id=?";
                    PreparedStatement sqq=con.prepareStatement(sq);
                    sqq.setInt(1, Integer.valueOf(at)-Integer.valueOf(amt));
                    sqq.setInt(2,Integer.valueOf(u_id));
                    int rtt=sqq.executeUpdate();
                    if(rtt>0)
                    {
                        String ss="SELECT cdes FROM cat WHERE id=?";
                        PreparedStatement s=con.prepareStatement(ss);
                        s.setString(1, id);
                        ResultSet st=s.executeQuery();
                        if(st.next())
                        {
                            String col=st.getString("cdes");
                            String q="UPDATE u_cat SET " + col + "=?  WHERE id=?";// Mobile_bill=239 where mobile_bill=mobile_bill;
                            PreparedStatement qq=con.prepareStatement(q);
                            qq.setString(1, amt);
                            qq.setString(2, u_id);
                            int rr=qq.executeUpdate();
                            if(rr>0)
                            return true;
                            else{
                                String sd="INSERT INTO u_cat (id) VALUES (?)";
                                PreparedStatement p=con.prepareStatement(sd);
                                p.setString(1, u_id);
                                int k=p.executeUpdate();
                                if(k>0)
                                {
                                    Cval(id, amt, u_id);
                                }
                            }
                        }
                        else 
                        return false;
                    }
                    
                }
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

}
