package exp.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class bal {

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

    public static ArrayList<String> Bal(String u_id) {
        try {
            ArrayList<String> columnDataList = new ArrayList<>();
            String sql = "SELECT amt FROM bud WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.valueOf(u_id));
            ResultSet rt = stmt.executeQuery();
            if (rt.next()) {
                int amt = rt.getInt("amt");
                String sq = "SELECT * FROM u_cat WHERE id=?";
                PreparedStatement st = con.prepareStatement(sq);
                st.setInt(1, Integer.valueOf(u_id));
                ResultSet rr = st.executeQuery();
                java.sql.ResultSetMetaData metaData = rr.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Store column names along with data in an ArrayList
                while (rr.next()) {
                    for (int i = 2; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        int columnData = rr.getInt(i);
                        if(columnData!=0)
                        columnDataList.add(columnName + ":" + columnData);
                    }
                }
                columnDataList.add("Total Amount Remaining:"+amt);
                return columnDataList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
