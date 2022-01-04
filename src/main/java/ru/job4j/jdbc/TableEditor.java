package ru.job4j.jdbc;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        String driver = properties.getProperty("jdbc.driver");
        String url = properties.getProperty("jdbc.url");
        String login = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        Class.forName(driver);
        connection = DriverManager.getConnection(url, login, password);
    }

    private void executeSql(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printTable(Connection connection, String tableName) throws Exception {
        System.out.println(getTableScheme(connection, tableName));
    }

    public void createTable(String tableName) {
        String sql = String.format("create table if not exists %s(id serial primary key);", tableName);
        executeSql(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("drop table if exists %s;", tableName);
        executeSql(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("alter table %s add column %s %s;", tableName, columnName, type);
        executeSql(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("alter table %s drop column %s;", tableName, columnName);
        executeSql(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
        executeSql(sql);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("app.properties");
        Properties p = new Properties();
        p.load(reader);
        try (TableEditor tableEditor = new TableEditor(p)) {
            tableEditor.createTable("test");
            printTable(tableEditor.connection, "test");
            tableEditor.addColumn("test", "data", "text");
            printTable(tableEditor.connection, "test");
            tableEditor.renameColumn("test", "data", "new");
            printTable(tableEditor.connection, "test");
            tableEditor.dropColumn("test", "new");
            printTable(tableEditor.connection, "test");
            tableEditor.dropTable("test");
        }
        reader.close();
    }
}
