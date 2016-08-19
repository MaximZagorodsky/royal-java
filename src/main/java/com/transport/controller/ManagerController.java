package com.transport.controller;

import com.transport.form.ManagerForm;
import com.transport.service.ManagerService;
import com.transport.service.OrderService;
import com.transport.util.FormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by AK on 7/7/2016.
 */


@Controller
@RequestMapping("api")
public class ManagerController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ManagerService managerService;

    public ManagerController() {
    }

    @RequestMapping(
            value = {"/manager"},
            method = {RequestMethod.GET}
    )
    public ModelAndView register(ModelAndView model) {
        ManagerForm managerForm = new ManagerForm();
        model.addObject("managerForm", managerForm);
        FormUtil.setEnumsToOrderModel(model);
        model.setViewName("/manager");
        return model;
    }

    @RequestMapping(
            value = {"/manager"},
            method = {RequestMethod.POST}
    )
    public ModelAndView register1(ModelAndView model) {
        model = new ModelAndView();
        model.setViewName("/manager");
        return model;
    }


    @RequestMapping(value = "/order{orderId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
//            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getOrder(@RequestParam String orderId) throws AuthenticationException {
        return new ResponseEntity<>(managerService.getManagerDTOById(Integer.valueOf(orderId)), HttpStatus.OK);
    }
}
