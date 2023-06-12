package Jason.ePayDemo.controller;

import Jason.ePayDemo.OrderService;
import Jason.ePayDemo.RequestMsg;
import Jason.ePayDemo.dto.GoldFlowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class userController {
    @Autowired
    OrderService orderService;
    @PostMapping("/ecpay")
    public ResponseEntity<?> ecpayDemo(@RequestBody  GoldFlowRequest goldFlowRequest){

        return ResponseEntity.ok().body(orderService.createGoldFlow(goldFlowRequest));

    }

    @RequestMapping("/successPay/{goldFlowNumber}")
    public ResponseEntity<String> paySuccess(@PathVariable("goldFlowNumber") String goldFlowNumber){
        System.out.println(goldFlowNumber +"成功付款");
        return ResponseEntity.ok().body("成功付款");
    }
}
