package openhome.model;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class UserService {

    LinkedList<Blah> newest = new LinkedList<Blah>();
    private AccountDAO accountDAO;
    private BlahDAO blahDAO;

    public UserService(AccountDAO accountDAO, BlahDAO blahDAO) {
        this.accountDAO = accountDAO;
        this.blahDAO = blahDAO;
    }

    public boolean isUserExisted(Account account) throws SQLException {
        return accountDAO.isUserExisted(account);
    }

    public void add(Account account) throws SQLException, IOException {
        accountDAO.addAccount(account);
    }

    public boolean checkLogin(Account account) throws SQLException, IOException {
        if (account.getName() != null && account.getPassword() != null) {
            Account sotreAccount = accountDAO.getAccount(account);
            return sotreAccount != null && sotreAccount.getPassword().equals(account.getPassword());
        }
        return false;
    }

    private class DateComparator implements Comparator<Blah> {
        @Override
        public int compare(Blah d1, Blah d2) {
            return -d1.getDate().compareTo(d2.getDate());
        }
    }

    private DateComparator comparator = new DateComparator();

    public List<Blah> getBlahs(Blah blah) throws IOException, SQLException {
        List<Blah> blahs = blahDAO.getBlahs(blah);
        Collections.sort(blahs, comparator);
        return blahs;
    }

    public void addBlah(Blah blah) throws IOException, SQLException {
        blahDAO.addBlah(blah);
        newest.addFirst(blah);
        //保证只显示最新的20条发言信息
        if (newest.size() > 20) {
            newest.removeLast();
        }
    }

    public void deleteBlah(Blah blah) throws SQLException {
        blahDAO.deleteBlah(blah);
        newest.remove(blah);
    }

    public List<Blah> getNewest() {
        return newest;
    }
}
