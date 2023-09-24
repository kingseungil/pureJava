package com.wifi.service;

import com.wifi.dao.HistoryDao;
import com.wifi.dto.request.PositionRequestDTO;
import com.wifi.dto.response.PositionResponseDTO;
import com.wifi.model.History;
import java.util.List;

public class HistoryService {

    private final HistoryDao historyDao = new HistoryDao();

    public void insertHistory(PositionRequestDTO positionRequestDTO) {
        historyDao.insertHistory(positionRequestDTO);
    }


    public List<History> getHistory() {
        return historyDao.getHistory();
    }

    public PositionResponseDTO getLatestHistory() {
        return historyDao.getLatestHistory();
    }
}
