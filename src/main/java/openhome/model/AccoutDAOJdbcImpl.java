package openhome.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccoutDAOJdbcImpl implements AccountDAO {
    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public AccoutDAOJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean isUserExisted(Account account) throws SQLException {
        return this.getAccount(account) != null;
    }

    @Override
    public void addAccount(Account account) throws SQLException {
        String sql = "insert into t_account values(?,?,?)";
        statement = conn.prepareStatement(sql);
        statement.setString(1, account.getName());
        statement.setString(2, account.getPassword());
        statement.setString(3, account.getEmail());
        int rows = statement.executeUpdate();
        System.out.println("add rows:" + rows);
    }

    @Override
    public Account getAccount(Account account) throws SQLException {
        String sql = "select * from t_account where name = ? and password = ?";
        statement = conn.prepareStatement(sql);
        statement.setString(1, account.getName());
        statement.setString(2, account.getPassword());
        resultSet = statement.executeQuery();
        Account acct = null;
        while (resultSet.next()) {
            acct = new Account();
            acct.setName(resultSet.getString("name"));
            acct.setPassword(resultSet.getString("password"));
            acct.setEmail(resultSet.getString("email"));
        }
        return acct;
    }
}
