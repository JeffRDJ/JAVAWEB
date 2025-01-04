package openhome.model;

import java.io.*;
import java.util.*;

public class BlahDAOFileImpl implements BlahDAO{
    private String USERS;

    public BlahDAOFileImpl(String USERS) {
        this.USERS = USERS;
    }
    @Override
    public List<Blah> getBlahs(Blah blah) throws IOException {
        File border = new File(USERS + "/" + blah.getName());
        String[] txts = border.list( filenameFilter);

//        Map<Date, String> messages = new TreeMap<Date, String>(comparator);
        List<Blah> blahs = new ArrayList<>();
        for(String txt : txts) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(USERS + "/" + blah.getName() + "/" + txt), "UTF-8"));
            String text = null;
            StringBuilder builder = new StringBuilder();
            while((text = reader.readLine()) != null) {
                builder.append(text);
            }
            Date date = new Date(Long.parseLong(txt.substring(0, txt.indexOf(".txt"))));
//            messages.put(date, builder.toString());
            blah.setDate(date);
            blah.setTxt( builder.toString());

            blahs.add(blah);
            reader.close();
        }


        return blahs;
    }

    @Override
    public void addBlah(Blah blah) throws IOException {
        String file = USERS + "/" + blah.getName() + "/" + new Date().getTime() + ".txt";
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        writer.write(blah.getTxt());
        writer.close();
    }

    @Override
    public void deleteBlah(Blah blah) {
        File file = new File(USERS + "/" + blah.getName() + "/" + blah.getDate().getTime() + ".txt");
        if(file.exists()) {
            file.delete();
        }
    }

    private class TxtFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
        }
    }

    private TxtFilenameFilter filenameFilter = new TxtFilenameFilter();

    private class DateComparator implements Comparator<Date> {
        @Override
        public int compare(Date d1, Date d2) {
            return -d1.compareTo(d2);
        }
    }

    private DateComparator comparator = new DateComparator();

}
