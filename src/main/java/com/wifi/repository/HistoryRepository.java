package com.wifi.repository;

import com.wifi.dto.request.PositionRequestDTO;
import com.wifi.dto.response.PositionResponseDTO;
import com.wifi.model.History;
import com.wifi.util.DaoUtil;
import java.util.List;

public class HistoryRepository {


    public void insertHistory(PositionRequestDTO positionRequestDTO) {
        String query = "INSERT INTO history (lat, lnt ,date) VALUES ( ?, ?, datetime('now','localtime'))";
        DaoUtil.executeUpdate(query, positionRequestDTO.getLat(), positionRequestDTO.getLnt());
    }

    public void deleteHistory(int id) {
        String query = "DELETE FROM history WHERE id = ?";
        DaoUtil.executeUpdate(query, id);
    }

    public List<History> getHistory() {
        String query = "SELECT * FROM history order by id desc";
        return DaoUtil.executeQuery(query, rs -> {
            History history = new History();
            history.setId(rs.getInt("id"));
            history.setLat(rs.getDouble("lat"));
            history.setLnt(rs.getDouble("lnt"));
            history.setDate(rs.getString("date"));
            return history;
        });
    }

    public PositionResponseDTO getLatestHistory() {
        String query = "SELECT * FROM history ORDER BY id DESC LIMIT 1";
        List<PositionResponseDTO> result = DaoUtil.executeQuery(query, rs -> {
            PositionResponseDTO positionResponseDTO = new PositionResponseDTO();
            positionResponseDTO.setLat(rs.getDouble("lat"));
            positionResponseDTO.setLnt(rs.getDouble("lnt"));
            return positionResponseDTO;
        });
        return result.isEmpty() ? null : result.get(0);
    }
}
