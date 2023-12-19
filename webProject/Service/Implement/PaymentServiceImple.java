package web.webProject.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.webProject.Repository.PaymentRepo;
import web.webProject.Service.PaymentService;
import web.webProject.models.Payment;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImple implements PaymentService {

    PaymentRepo paymentRepo;
    @Autowired
    public PaymentServiceImple(PaymentRepo paymentRepo){
        this.paymentRepo=paymentRepo;
    }
    @Override
    public void savePayment(Payment payment) {
     this.paymentRepo.save(payment);
    }

    @Override
    public List<Payment> listAllPayments() {
        return this.paymentRepo.findAll();
    }

    @Override
    public Payment findPaymentById(Long paymentId) {
        Optional<Payment> optional=paymentRepo.findById(paymentId);
        Payment payment=null;
        if (optional.isPresent()){
            payment=optional.get();
        }else {
            throw new RuntimeException("Payment Is not Found"+paymentId);
        }
        return payment;
    }

    @Override
    public void deletePayment(Long paymentId) {
        this.paymentRepo.deleteById(paymentId);
    }
}
