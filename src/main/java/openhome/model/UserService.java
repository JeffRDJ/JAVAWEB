package openhome.model;

import java.io.*;
import java.security.PrivilegedAction;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class UserService {
    public final String USERS;

    public UserService(String USERS) {
        this.USERS = USERS;
    }

    //校验邮件格式
    public boolean isInvalidEmail(String email) {
        return email == null
                || !email.matches("^[_a-z0-9-]+([.]"
                + "[_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
    }

    //校验用户名
    public boolean isInvalidUsername(String username) {
        for (String file : new File(USERS).list()) {
            if (file.equals(username)) {
                return true;
            }
        }
        return false;
    }

    //校验密码
    public boolean isInvalidPassword(String password, String confirmedPasswd) {
        return password == null
                || password.length() < 6
                || password.length() > 16
                || !password.equals(confirmedPasswd);
    }

    //注册成功后创建用户数据文件夹
    public void createUserData(String email, String username, String password) throws IOException {
        File userhome = new File(USERS + "/" + username);  //每个注册的用户创建一个文件夹
        userhome.mkdir();
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(userhome + "/profile")
        );
        writer.write(email + "\t" + password);
        writer.close();
    }

    // 检查用户登录，校验用户名和密码
    public boolean checkLogin(String username, String password) throws IOException {
        if (username != null && password != null) {
            for (String file : new File(USERS).list()) {
                if (file.equals(username)) {
                    BufferedReader reader = new BufferedReader(new FileReader(USERS + "/" + file + "/profile"));
                    String passwd = reader.readLine().split("\t")[1];
                    if (passwd.equals(password)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void addMessage(String username, String blabla) throws IOException {
        String file = USERS + "/" + username + "/" + new Date().getTime() + ".txt";
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        write.write(blabla);
        write.close();
    }

    //删除文件
    public static void deleteMessage(UserService userService, String username, String message) {
        File file = new File(userService.USERS + "/" + username + "/" + message + ".txt");
        if (file.exists()) {
            file.delete();
        }
    }

    public class TxtFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
        }
    }

    public TxtFilenameFilter filenameFilter = new TxtFilenameFilter();

    public class DateComparator implements Comparator<Date> {
        @Override
        public int compare(Date d1, Date d2) {
            return -d1.compareTo(d2);
        }
    }

    public DateComparator comparator = new DateComparator();

    public Map<Date, String> readMessage(String username) throws IOException {
        File border = new File(USERS + "/" + username);
        String[] txts = border.list(filenameFilter);

        Map<Date, String> messages = new TreeMap<Date, String>(comparator);
        for (String txt : txts) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(USERS + "/" + username + "/" + txt), "UTF-8"));
            String text = null;
            StringBuilder builder = new StringBuilder();
            while ((text = reader.readLine()) != null) {
                builder.append(text);
            }
            Date date = new Date(Long.parseLong(txt.substring(0, txt.indexOf(".txt"))));
            messages.put(date, builder.toString());
            reader.close();
        }

        return messages;
    }


}
