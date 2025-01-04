package openhome.model;


import java.io.*;

public class AccountDAOFileImpl implements AccountDAO{
    private String USERS;

    public AccountDAOFileImpl(String USERS) {
        this.USERS = USERS;
    }

    @Override
    public boolean isUserExisted(Account account) {
            for (String file : new File(USERS).list()) {
                if (file.equals(account.getName())) {
                    return true;
                }
            }
            return false;
    }

    @Override
    public void addAccount(Account account) throws IOException {
            File userhome = new File(USERS + "/" + account.getName());
            userhome.mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(userhome
                    + "/profile"));
            writer.write(account.getEmail() + "\t" + account.getPassword());
            writer.close();
    }

    @Override
    public Account getAccount(Account account) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(USERS + "/" + account.getName() + "/profile"), "UTF-8"));
        String text = null;
        StringBuilder builder = new StringBuilder();
        while((text = reader.readLine()) != null) {
            builder.append(text);
        }
        String[] txts = builder.toString().split("\t");
        account.setPassword(txts[1]);
        account.setEmail(txts[0]);
        return account;
    }
}
