package DataAccess;

import DataAccess.Exceptions.NoConnectionException;
import Domain.Users.Member;
import FootballExceptions.NoPermissionException;
import FootballExceptions.UserInformationException;
import FootballExceptions.UserIsNotThisKindOfMemberException;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public interface DAL<T,E> {

    public boolean insert(T objectToInsert) throws SQLException, NoConnectionException, UserInformationException, UserIsNotThisKindOfMemberException, NoPermissionException;
    public boolean update(T objectToUpdate, Pair<String,Object> valToUpdate) throws SQLException, UserIsNotThisKindOfMemberException, UserInformationException, NoConnectionException, NoPermissionException;
    public T find(E objectIdentifier) throws SQLException, UserInformationException, UserIsNotThisKindOfMemberException, NoConnectionException, NoPermissionException;
    public boolean delete(E objectIdentifier);

    public default Connection connect(){
        String url= "jdbc:mysql://132.72.65.125:3306/footballapp";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,"root","ISE2424!");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
