import java.sql.*;


public class DBconnection {

    static DBconnection connection=null;


    private DBconnection() {

    }

    static DBconnection getInstance(){
        if(connection==null)
            connection=new DBconnection();
        return connection;
    }

    public void updateDate(String date,String day) {
        try (Connection DBconnection = DriverManager.getConnection(connectionUrl);
             Statement statement = DBconnection.createStatement();) {
            String selectSql = "update [dbo].[Queues] set [date]='" + date + "',[status]='free',[user]=null, [fullname]=null,[price]=null where [day]='" + day + "'";
            statement.execute(selectSql);
            statement.close();
            if(statement!=null) {
                statement.close();
            }
            if(DBconnection!=null) {
                DBconnection.close();
            }

        } catch (SQLException e) {
            System.out.println("err");
            e.printStackTrace();

        }
    }

    public void endTurn(String date,String time){
        try (Connection DBconnection = DriverManager.getConnection(connectionUrl);
             Statement statement = DBconnection.createStatement();) {
            String selectSql = "update [dbo].[Queues] set [status]='end' where [date]='"+date+"' and [time]='"+time+"'";
            statement.execute(selectSql);
            if(statement!=null) {
                statement.close();
            }
            if(DBconnection!=null) {
                DBconnection.close();
            }
        }
        catch (SQLException e) {
            System.out.println("err");
            e.printStackTrace();
        }
    }

    public boolean insertNewQueue(String date,String day,String time){
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {
            String selectSql = "insert into [dbo].[Queues] (date,day,time,status) values ('"+date+"','"+day+"','"+time+"','block')";
            return statement.execute(selectSql);
        }
        catch (SQLException e) {
            System.out.println("err");
            e.printStackTrace();
        }
        return  false;
    }
}
