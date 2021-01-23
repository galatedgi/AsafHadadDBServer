import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {
//        Thread app=new QueuesMang();
//        app.start();

        DBconnection dBconnection = DBconnection.getInstance();
        for(int i=11;i<19;i++) {
            String hour;
            if(i<10){
                hour="0"+i;
            }
            else {
                hour=""+i;
            }
            String time=hour+":00";
            dBconnection.insertNewQueue("14-01-2021", "Thursday", time);
            time=hour+":30";
            dBconnection.insertNewQueue("14-01-2021", "Thursday", time);
        }
        dBconnection.insertNewQueue("14-01-2021", "Thursday", "19:00");
    }

}
