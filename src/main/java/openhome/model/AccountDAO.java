package openhome.model;

import java.io.IOException;
import java.sql.SQLException;

public interface AccountDAO {
    boolean isUserExisted(Account account) throws SQLException;
    void addAccount(Account account) throws IOException, SQLException;
    Account getAccount(Account account) throws IOException,SQLException;
}
