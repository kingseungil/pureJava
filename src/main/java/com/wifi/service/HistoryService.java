package com.wifi.service;

import com.wifi.dto.request.PositionRequestDTO;
import com.wifi.dto.response.PositionResponseDTO;
import com.wifi.model.History;
import com.wifi.repository.HistoryRepository;
import java.util.List;

public class HistoryService {

    private final HistoryRepository historyRepository = new HistoryRepository();

    public void insertHistory(PositionRequestDTO positionRequestDTO) {
        historyRepository.insertHistory(positionRequestDTO);
    }


    public List<History> getHistory() {
        return historyRepository.getHistory();
    }

    public PositionResponseDTO getLatestHistory() {
        return historyRepository.getLatestHistory();
    }
}
