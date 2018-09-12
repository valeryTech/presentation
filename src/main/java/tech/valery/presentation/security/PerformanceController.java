package tech.valery.presentation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tech.valery.presentation.model.Performance;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.Room;
import tech.valery.presentation.repository.PerformanceRepository;
import tech.valery.presentation.service.PerformanceService;
import tech.valery.presentation.service.PresentationService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class PerformanceController {

    @Autowired
    PresentationService presentationService;

    @Autowired
    PerformanceService performanceService;

    @ModelAttribute("rooms")
    public List<Room> populateRooms(){
        return performanceService.getRooms();
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("/users/*/presentations/{presentationId}/performances/new")
    public String initNewPerformanceForm(@PathVariable("presentationId") Long presentationId,
                                         Map<String, Object> model) {
        Presentation presentation = presentationService.findOne(presentationId);
        model.put("presentation", presentation);
        Performance performance = new Performance();
        presentation.addPerformance(performance);
        model.put("performance", performance);
        return "presentations/createOrUpdatePerfForm";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/users/{userId}/presentations/{presentationId}/performances/new")
    public String processNewVisitForm(@PathVariable("presentationId") Long presentationId, @Valid Performance performance, BindingResult result) {
        if (result.hasErrors()) {
            return "/presentations/createOrUpdatePerfForm";
        } else {
            Presentation presentation = presentationService.findOne(presentationId);
            presentation.addPerformance(performance);
            this.performanceService.save(performance);
            return "redirect:/users/{userId}";
        }
    }
}
