package tech.valery.presentation.service;

import org.springframework.beans.factory.annotation.Autowired;
import tech.valery.presentation.model.Performance;
import tech.valery.presentation.model.Room;
import tech.valery.presentation.repository.PerformanceRepository;

import java.util.List;

public class PerformanceServiceImpl implements PerformanceService{

    @Autowired
    PerformanceRepository performanceRepository;

    @Override
    public List<Performance> findByPresentationId(Long presentationId) {
        return performanceRepository.findByPresentationId(presentationId);
    }

    @Override
    public Performance save(Performance performance) {
        return performanceRepository.save(performance);
    }

    @Override
    public List<Room> getRooms() {
        return performanceRepository.getRooms();
    }
}
