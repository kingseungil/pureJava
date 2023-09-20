package com.wifi.dao;

import com.wifi.dto.request.PositionRequestDTO;
import com.wifi.dto.response.HistoryResponseDTO;
import com.wifi.util.SQLiteConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {

    public void insertHistory(PositionRequestDTO positionRequestDTO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO history (posX, posY,date) VALUES ( ?, ?, datetime('now','localtime'))";

        try {
            connection = SQLiteConnection.SQLiteUtil.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDouble(1, positionRequestDTO.getPosX());
            preparedStatement.setDouble(2, positionRequestDTO.getPosY());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            SQLiteConnection.SQLiteUtil.close(connection);
        }
    }

    public void deleteHistory(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM history WHERE id = ?";

        try {
            connection = SQLiteConnection.SQLiteUtil.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            SQLiteConnection.SQLiteUtil.close(connection);
        }
    }

    public List<HistoryResponseDTO> getHistory() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<HistoryResponseDTO> historyList = new ArrayList<>();
        String query = "SELECT * FROM history";

        try {
            connection = SQLiteConnection.SQLiteUtil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet == null) {
                return historyList;
            }

            while (resultSet.next()) {
                HistoryResponseDTO historyResponseDTO = new HistoryResponseDTO();
                historyResponseDTO.setId(resultSet.getInt("id"));
                historyResponseDTO.setPosX(resultSet.getDouble("posX"));
                historyResponseDTO.setPosY(resultSet.getDouble("posY"));
                historyResponseDTO.setDate(resultSet.getString("date"));
                historyList.add(historyResponseDTO);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            SQLiteConnection.SQLiteUtil.close(connection);
        }
        return historyList;
    }
}
