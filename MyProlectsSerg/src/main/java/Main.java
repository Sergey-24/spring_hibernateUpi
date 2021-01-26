import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = getConnection();
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        st.executeUpdate("create table IF NOT EXISTS survey (id int,name varchar(30), last_Name varchar(30));");
        st.executeUpdate("insert into survey (id,name, last_Name) values (1,'nameValue', 'namename')");
        st.executeUpdate("insert into survey (id,name, last_Name ) values (2,'Serg', 'setrr')");
        st.executeUpdate("insert into survey (id,name, last_Name ) values (3,'Tom','krot')");

        ResultSet rs = st.executeQuery("SELECT * FROM survey");


        // Move cursor to the last row
        rs.absolute(-2);
        // Get data at cursor
        String id = rs.getString(2);
        System.out.println(id);

        // Move cursor to the second-to-last row
        //rs.absolute(-2);

        rs.close();
        st.close();
        conn.close();
    }
    private static Connection getConnection() throws Exception {
        Connection connection = null;
        String url = "jdbc:mysql://localhost/tables_users?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
        String userName = "root";
        String pass = "1234";
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, userName, pass);
            if (!connection.isClosed()) {
                return connection;

            }
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
