package web.webProject.Service;

import web.webProject.models.Payment;

import java.util.List;

public interface PaymentService {
    void savePayment(Payment payment);
    List<Payment>listAllPayments();
    Payment findPaymentById(Long paymentId);
    void deletePayment(Long paymentId);
}
