package az.dyh.travel.repository;

import az.dyh.travel.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByBeginDateAfterAndAndEndDateBeforeAndStatus(LocalDate beginDate,
                                                                LocalDate endDate, String status);
}
