package az.dyh.travel.repository;

import az.dyh.travel.entity.UserTour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTourRepository extends JpaRepository<UserTour, Long> {
}
