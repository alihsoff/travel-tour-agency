package az.dyh.travel.service.impl;

import az.dyh.travel.bean.CustomUserDetail;
import az.dyh.travel.entity.User;
import az.dyh.travel.entity.UserTour;
import az.dyh.travel.exception.UnauthorizedException;
import az.dyh.travel.repository.TourRepository;
import az.dyh.travel.repository.UserTourRepository;
import az.dyh.travel.service.UserTourService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserTourServiceImpl implements UserTourService {

    private final UserTourRepository userTourRepository;
    private final TourRepository tourRepository;

    public UserTourServiceImpl(UserTourRepository userTourRepository, TourRepository tourRepository) {
        this.userTourRepository = userTourRepository;
        this.tourRepository = tourRepository;
    }


    @Override
    public void createUserTour(Long tourId, String status) {
        CustomUserDetail userDetails = getUserDetail();
        //System.out.println(userDetails.getUser().getUserTours().size());
        UserTour userTour = new UserTour();
        userTour.setPaymentStatus(status);
        userTour.setUser(userDetails.getUser());
        userTour.setTour(tourRepository.findById(tourId).get());
        userTourRepository.save(userTour);
    }

    @Override
    public void deleteById(Long id) {
        UserTour userTour = userTourRepository.findById(id).get();
        User currentUser = getUserDetail().getUser();
        User user = userTour.getUser();
        if (!user.getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("You don't have permission to delete tour");
        }
        userTourRepository.deleteById(id);
    }

    private CustomUserDetail getUserDetail() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ((CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
