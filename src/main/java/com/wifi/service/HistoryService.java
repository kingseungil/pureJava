package com.wifi.service;

import com.wifi.dao.HistoryDao;
import com.wifi.dto.request.PositionRequestDTO;
import com.wifi.dto.response.HistoryResponseDTO;
import com.wifi.dto.response.PositionResponseDTO;
import java.util.List;

public class HistoryService {

    private final HistoryDao historyDao = new HistoryDao();

    public void insertHistory(PositionRequestDTO positionRequestDTO) {
        historyDao.insertHistory(positionRequestDTO);
    }


    public List<HistoryResponseDTO> getHistory() {
        return historyDao.getHistory();
    }

    public PositionResponseDTO getLatestHistory() {
        return historyDao.getLatestHistory();
    }
}
