package openhome.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlahDAOJdbcImpl implements BlahDAO {
    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public BlahDAOJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Blah> getBlahs(Blah blah) throws SQLException {
        // 3.获取用于向数据库发送sql语句的statement
        String sql = "select * from t_blah where name = ?";
        statement = conn.prepareStatement(sql);
        statement.setString(1, blah.getName());
        // 4.向数据库发sql,并获取代表结果集的resultset
        resultSet = statement.executeQuery();
        // 5.取出结果集的数据
        List<Blah> blahList = new ArrayList<>();
        while (resultSet.next()) {
            Blah b = new Blah();
            b.setName(resultSet.getString("name"));
//            System.out.println(resultSet.getTimestamp("date"));
//            b.setDate(resultSet.getDate("date"));
            b.setDate(resultSet.getTimestamp("date"));
            b.setTxt(resultSet.getString("txt"));
            blahList.add(b);
        }
        return blahList;
    }

    @Override
    public void addBlah(Blah blah) throws SQLException {
        String sql = "insert into t_blah values(?,?,?)";
        statement = conn.prepareStatement(sql);
        statement.setString(1, blah.getName());
        //把java.util.Date转换成java.sql.Timestamp
        statement.setTimestamp(2, new Timestamp(blah.getDate().getTime()));
        statement.setString(3, blah.getTxt());
        int rows = statement.executeUpdate();
        System.out.println("add rows:" + rows);
    }

    @Override
    public void deleteBlah(Blah blah) throws SQLException {
        String sql = "delete from t_blah where date=?";
        statement = conn.prepareStatement(sql);
        //把java.util.Date转换成java.sql.Date
        statement.setTimestamp(1, new Timestamp(blah.getDate().getTime()));
        int rows = statement.executeUpdate();
        System.out.println("delete rows:" + rows);
    }
}
