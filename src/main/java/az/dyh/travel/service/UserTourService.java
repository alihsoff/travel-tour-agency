package az.dyh.travel.service;

public interface UserTourService {
    void createUserTour(Long tourId, String status);

    void deleteById(Long id);
}
