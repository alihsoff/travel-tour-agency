package az.dyh.travel.service;

import az.dyh.travel.entity.Tour;

import java.time.LocalDate;
import java.util.List;

public interface TourService {
    List<Tour> getToursByFields(LocalDate beginDate, LocalDate endDate, String status);

    List<Tour> getAllTours();

    void updateTour(Long id, String status);

    void createTour(Tour tour);

}
