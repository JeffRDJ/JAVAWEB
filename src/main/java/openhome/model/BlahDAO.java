package openhome.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BlahDAO {
    List<Blah> getBlahs(Blah blah) throws IOException, SQLException;
    void addBlah(Blah blah) throws IOException, SQLException;
    void deleteBlah(Blah blah) throws SQLException;
}
