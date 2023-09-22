package com.wifi.dao;

import com.wifi.dto.request.PositionRequestDTO;
import com.wifi.dto.response.HistoryResponseDTO;
import com.wifi.dto.response.PositionResponseDTO;
import com.wifi.util.DaoUtil;
import java.util.List;

public class HistoryDao {


    public void insertHistory(PositionRequestDTO positionRequestDTO) {
        String query = "INSERT INTO history (posX, posY,date) VALUES ( ?, ?, datetime('now','localtime'))";
        DaoUtil.executeUpdate(query, positionRequestDTO.getPosX(), positionRequestDTO.getPosY());
    }

    public void deleteHistory(int id) {
        String query = "DELETE FROM history WHERE id = ?";
        DaoUtil.executeUpdate(query, id);
    }

    public List<HistoryResponseDTO> getHistory() {
        String query = "SELECT * FROM history order by id desc";
        return DaoUtil.executeQuery(query, rs -> {
            HistoryResponseDTO historyResponseDTO = new HistoryResponseDTO();
            historyResponseDTO.setId(rs.getInt("id"));
            historyResponseDTO.setPosX(rs.getDouble("posX"));
            historyResponseDTO.setPosY(rs.getDouble("posY"));
            historyResponseDTO.setDate(rs.getString("date"));
            return historyResponseDTO;
        });
    }

    public PositionResponseDTO getLatestHistory() {
        String query = "SELECT * FROM history ORDER BY id DESC LIMIT 1";
        List<PositionResponseDTO> result = DaoUtil.executeQuery(query, rs -> {
            PositionResponseDTO positionResponseDTO = new PositionResponseDTO();
            positionResponseDTO.setPosX(rs.getDouble("posX"));
            positionResponseDTO.setPosY(rs.getDouble("posY"));
            return positionResponseDTO;
        });
        return result.isEmpty() ? null : result.get(0);
    }
}
