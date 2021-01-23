import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class QueuesMang extends Thread {

    //private Calendar systemCalendar;
    private DBconnection connection;
    private boolean setDateFlag;
    public QueuesMang() {
        super();
        //systemCalendar=Calendar.getInstance();
        connection=DBconnection.getInstance();
        setDateFlag=true;
    }

    @Override
    public void run() {
//        while (true){
//            try {
//                DateFormat timeFormat=new SimpleDateFormat("EEEE");
//                timeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
//                String day = timeFormat.format(Calendar.getInstance().getTime());
//                if (day.equals("Saturday") && setDateFlag) {
//                    setDates();
//                    setDateFlag = false;
//                } else if (!day.equals("Saturday")) {
//                    if (!setDateFlag)
//                        setDateFlag = true;
//                    updateEndTurn();
//                }
//                //this.sleep(30*60*1000);
//                this.sleep( 1000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
        setDates();
    }

    private void setDates(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        System.out.println("date:    " + date);
        for(int i=0;i<7;i++){
            DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
            String formattedDate = dateFormat.format(date);
            DateFormat dayFormat=new SimpleDateFormat("EEEE");
            dayFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
            String day = dayFormat.format(date);
            if(!day.equals("Saturday")){
                connection.updateDate(formattedDate,day);
                System.out.println(date +" " +day+" update");
            }
            calendar.add(Calendar.DATE, 1);
            date=calendar.getTime();
        }
    }

    private void updateEndTurn(){
        DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
        String formattedDate = dateFormat.format(Calendar.getInstance().getTime());
        DateFormat hourFormat=new SimpleDateFormat("HH");
        hourFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
        String hour=hourFormat.format(Calendar.getInstance().getTime());
        DateFormat minuteFormat=new SimpleDateFormat("mm");
        minuteFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
        int minute=Integer.parseInt(minuteFormat.format(Calendar.getInstance().getTime()));
        if(minute>=0 && minute<30){
            hour=hour+":00";
            //connection.endTurn(formattedDate,hour);
        }else if(minute>30 && minute<=59){
            hour=hour+":30";
            //connection.endTurn(formattedDate,hour);
        }
    }

}
