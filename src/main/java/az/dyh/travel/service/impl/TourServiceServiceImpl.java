package az.dyh.travel.service.impl;

import az.dyh.travel.entity.TourService;
import az.dyh.travel.repository.TourServiceRepository;
import az.dyh.travel.service.TourServiceService;
import org.springframework.stereotype.Service;

@Service
public class TourServiceServiceImpl implements TourServiceService {

    private final TourServiceRepository tourServiceRepository;

    public TourServiceServiceImpl(TourServiceRepository tourServiceRepository) {
        this.tourServiceRepository = tourServiceRepository;
    }

    @Override
    public void createTourService(TourService tourService) {
        tourServiceRepository.save(tourService);
    }
}
