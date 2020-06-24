package az.dyh.travel.service.impl;

import az.dyh.travel.entity.Tour;
import az.dyh.travel.repository.TourRepository;
import az.dyh.travel.service.TourService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> getToursByFields(LocalDate beginDate, LocalDate endDate, String status) {
        return tourRepository.findByBeginDateAfterAndAndEndDateBeforeAndStatus(beginDate,
                endDate, status);
    }

    @Override
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @Override
    public void updateTour(Long id, String status) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tour doesn't exist"));
        tour.setStatus(status);
        tourRepository.save(tour);
    }

    @Override
    public void createTour(Tour tour) {
        tourRepository.save(tour);
    }
}
