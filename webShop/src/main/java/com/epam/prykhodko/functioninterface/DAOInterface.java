package com.epam.prykhodko.functioninterface;


import java.sql.SQLException;

public interface DAOInterface<T> {
    T exec() throws SQLException;
}
