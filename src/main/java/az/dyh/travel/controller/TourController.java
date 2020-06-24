package az.dyh.travel.controller;

import az.dyh.travel.entity.Tour;
import az.dyh.travel.service.TourService;
import az.dyh.travel.service.TourServiceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class TourController {
    private final TourService tourService;
    private final TourServiceService tourServiceService;

    public TourController(TourService tourService, TourServiceService tourServiceService) {
        this.tourService = tourService;
        this.tourServiceService = tourServiceService;
    }

    @GetMapping("/")
    public String getAllTours(Model model) {
        model.addAttribute("tours", tourService.getAllTours());
        return "home";
    }

    @GetMapping("/tour/search")
    public String getToursByFields(Model model,
                                   @RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
                                   @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                   @RequestParam("status") String status) {
        model.addAttribute("tours", tourService.getToursByFields(beginDate, endDate, status));
        return "home";
    }

    @GetMapping("/admin/tour/add")
    public String addTour(Tour tour) {
        return "admin/add-tour";
    }

    @PostMapping("/admin/tour/add")
    public String createTour(Tour tour) {
        tourService.createTour(tour);
        return "redirect:/";
    }

    @PostMapping
    public void createTourService(@RequestBody az.dyh.travel.entity.TourService tourService) {
        tourServiceService.createTourService(tourService);
    }

    @PostMapping("admin/tour/update")
    public String updateTour(@RequestParam("status") String status, @RequestParam("id") Long id) {
        tourService.updateTour(id, status);
        return "redirect:/";
    }
}
