package ar.com.fluxit.kafkapoc.controller;

import ar.com.fluxit.kafkapoc.dto.PaymentRequestDTO;
import ar.com.fluxit.kafkapoc.dto.PaymentResponseDTO;
import ar.com.fluxit.kafkapoc.dto.QueryResponseDTO;
import ar.com.fluxit.kafkapoc.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> makePayment(@RequestBody PaymentRequestDTO dto){
        try {
            return ResponseEntity.ok(paymentService.executePayment(dto));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueryResponseDTO> queryForPayment(@PathVariable("id") String id){
        try {
            return ResponseEntity.ok(paymentService.queryForPayment(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
    }
}
