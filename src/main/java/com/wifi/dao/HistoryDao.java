package com.wifi.dao;

import com.wifi.dto.request.PositionRequestDTO;
import com.wifi.dto.response.HistoryResponseDTO;
import com.wifi.dto.response.PositionResponseDTO;
import com.wifi.util.SQLiteConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HistoryDao {

    private static final Logger logger = LoggerFactory.getLogger(HistoryDao.class);


    public void insertHistory(PositionRequestDTO positionRequestDTO) {
        String query = "INSERT INTO history (posX, posY,date) VALUES ( ?, ?, datetime('now','localtime'))";

        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, positionRequestDTO.getPosX());
            preparedStatement.setDouble(2, positionRequestDTO.getPosY());
            preparedStatement.executeUpdate();
            logger.info("Successfully added history");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to add history", e);
        }
    }

    public void deleteHistory(int id) {
        String query = "DELETE FROM history WHERE id = ?";

        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            logger.info("Successfully deleted history id : " + id);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to delete history", e);
        }
    }

    public List<HistoryResponseDTO> getHistory() {
        List<HistoryResponseDTO> historyList = new ArrayList<>();
        String query = "SELECT * FROM history order by id desc";

        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          ResultSet resultSet = preparedStatement.executeQuery()) {
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
            logger.error("Failed to get the list of history", e);
        }
        return historyList;
    }

    public PositionResponseDTO getLatestHistory() {
        PositionResponseDTO latestHistory = new PositionResponseDTO();
        String query = "SELECT * FROM history ORDER BY id DESC LIMIT 1";

        try (Connection connection = SQLiteConnection.SQLiteUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                latestHistory.setPosX(resultSet.getDouble("posX"));
                latestHistory.setPosY(resultSet.getDouble("posY"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Failed to get the latest history", e);
        }
        logger.info(latestHistory.toString());
        return latestHistory;
    }
}
