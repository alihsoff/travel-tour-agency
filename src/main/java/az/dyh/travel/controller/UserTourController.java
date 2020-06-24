package az.dyh.travel.controller;

import az.dyh.travel.service.UserTourService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserTourController {

    private final UserTourService userTourService;

    public UserTourController(UserTourService userTourService) {
        this.userTourService = userTourService;
    }

    @RequestMapping("/usertour/book/{tourId}")
    public String bookTour(@PathVariable("tourId") Long tourId) {
        userTourService.createUserTour(tourId, "BOOKED");
        return "redirect:/user/index";
    }

    @RequestMapping("/usertour/buy/{tourId}")
    public String buyTour(@PathVariable("tourId") Long tourId) {
        userTourService.createUserTour(tourId, "PAID");
        return "redirect:/user/index";
    }

    @RequestMapping("/usertour/delete/{id}")
    public String deleteTour(@PathVariable("id") Long id) {
        userTourService.deleteById(id);
        return "redirect:/user/index";
    }
}
