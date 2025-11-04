//package com.example.ordermanagement.controller;
//
//import com.example.ordermanagement.model.ContractLine;
//import com.example.ordermanagement.service.ContractLineService;
//import com.example.ordermanagement.service.UnitsOfMeasureService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/contractlines")
//public class ContractLineController extends GenericController<ContractLine> {
//
//    private final UnitsOfMeasureService unitsOfMeasureService;
//
//    public ContractLineController(ContractLineService service, UnitsOfMeasureService unitsOfMeasureService) {
//        super(service, "contractline");
//        this.unitsOfMeasureService = unitsOfMeasureService;
//    }
//
//    //    @Override
////    @GetMapping("/new")
////    public String showCreateForm(Model model) {
////        model.addAttribute("contractline", new ContractLine());
////        return "contractline/form";
////    }
//@Override
//@GetMapping("/new")
//public String showCreateForm(Model model) {
//    model.addAttribute("contractline", new ContractLine());
//    model.addAttribute("units", UnitsOfMeasureService.getAll());
//    return "contractline/form";
//}
//
//}
package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.ContractLine;
import com.example.ordermanagement.service.ContractLineService;
import com.example.ordermanagement.service.UnitsOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contractlines")
public class ContractLineController extends GenericController<ContractLine> {

    private final UnitsOfMeasureService unitsOfMeasureService;

    public ContractLineController(ContractLineService service, UnitsOfMeasureService unitsOfMeasureService) {
        super(service, "contractline");
        this.unitsOfMeasureService = unitsOfMeasureService;
    }

    @Override
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contractline", new ContractLine());
        model.addAttribute("units", unitsOfMeasureService.getAll());
        return "contractline/form";
    }
}
