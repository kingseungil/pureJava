package com.wifi.service;

import com.wifi.dao.HistoryDao;
import com.wifi.dto.request.PositionRequestDTO;
import com.wifi.dto.response.HistoryResponseDTO;
import com.wifi.dto.response.PositionResponseDTO;
import java.util.List;

public class HistoryService {

    public void insertHistory(PositionRequestDTO positionRequestDTO) {
        HistoryDao historyDao = new HistoryDao();
        historyDao.insertHistory(positionRequestDTO);
    }


    public List<HistoryResponseDTO> getHistory() {
        HistoryDao historyDao = new HistoryDao();
        return historyDao.getHistory();
    }

    public PositionResponseDTO getLatestHistory() {
        HistoryDao historyDao = new HistoryDao();
        return historyDao.getLatestHistory();
    }
}
