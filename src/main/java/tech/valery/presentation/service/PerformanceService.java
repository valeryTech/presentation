package tech.valery.presentation.service;

import tech.valery.presentation.model.Performance;
import tech.valery.presentation.model.Room;

import java.util.List;

public interface PerformanceService {
    List<Performance> findByPresentationId(Long presentationId);
    Performance save(Performance performance);

    List<Room> getRooms();
}
