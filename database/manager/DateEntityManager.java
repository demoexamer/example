package org.orgname.app.database.manager;

import org.orgname.app.database.entity.DateEntity;
import org.orgname.app.util.MysqlDatabase;

import java.sql.*;

public class DateEntityManager
{
    private MysqlDatabase database;

    public DateEntityManager(MysqlDatabase database) {
        this.database = database;
    }

    public void add(DateEntity entity) throws SQLException
    {
        try(Connection c = database.getConnection())
        {
            String sql = "INSERT INTO date_entities(date_value) values(?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setTimestamp(1, new Timestamp(entity.getDate().getTime()));
            s.executeUpdate();

            ResultSet keys = s.getGeneratedKeys();
            if (keys.next()) {
                entity.setId(keys.getInt(1));
                return;
            }

            throw new SQLException("Date not added");
        }
    }

    public DateEntity getById(int id) throws SQLException
    {
        try(Connection c = database.getConnection())
        {
            String sql = "SELECT * FROM date_entities WHERE id=?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);

            ResultSet resultSet = s.executeQuery();
            if(resultSet.next()) {
                return new DateEntity(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("date_value") //тут не нужно делать приведение типов, тк Timestamp наследует Date
                );
            }

            return null;
        }
    }
}
