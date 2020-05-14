package DataAccess.UsersDAL;

import DataAccess.DAL;
import DataAccess.Exceptions.NoConnectionException;
import DataAccess.SeasonManagmentDAL.AssetsDAL;
import Domain.SeasonManagment.IAsset;
import Domain.Users.Coach;
import Domain.Users.Member;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.SQLException;

public class CoachesDAL implements DAL <Member,String> {
    Connection connection=null;
    MembersDAL membersDAL = new MembersDAL();
    AssetsDAL assetsDAL = new AssetsDAL();

    @Override
    public boolean insert(Member objectToInsert) throws SQLException, NoConnectionException {
        connection = connect();
        if(connection==null){
            throw new NoConnectionException();
        }
        membersDAL.insert(objectToInsert);
        assetsDAL.insert((IAsset)objectToInsert);
        objectToInsert=((Coach)objectToInsert);

        String statement =  "INSERT INTO coaches (";
        connection.close();
        return true;
    }

    @Override
    public boolean update(Member objectToUpdate, Pair<String, Object> valToUpdate) throws SQLException {
        return false;
    }

    @Override
    public Member find(String objectIdentifier) {
        return null;
    }

    @Override
    public boolean delete(String objectIdentifier) {
        return false;
    }
}
