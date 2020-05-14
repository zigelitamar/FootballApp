package DataAccess.UserInformationDAL;

import DataAccess.DAL;
import Domain.Users.PersonalInfo;
import FootballExceptions.UserInformationException;
import javafx.util.Pair;

import java.sql.SQLException;

public class PersonalPagesDAL implements DAL<PersonalInfo,Integer> {
    @Override
    public boolean insert(PersonalInfo objectToInsert) throws SQLException {
        return false;
    }

    @Override
    public boolean update(PersonalInfo objectToUpdate, Pair<String, Object> valToUpdate) throws SQLException {
        return false;
    }

    @Override
    public PersonalInfo find(Integer objectIdentifier) throws SQLException, UserInformationException {
        return null;
    }

    @Override
    public boolean delete(Integer objectIdentifier) {
        return false;
    }
}
