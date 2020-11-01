package controllers;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.pm.ecommerce.email_service.event_data.UserOrderReceived;
import com.pm.ecommerce.email_service.repositories.OrderRepository;
import com.pm.ecommerce.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;

@RestController
@RequestMapping("api/test")
public class TestController {
    @Autowired
    OrderRepository repository;

    @GetMapping("")
    public ResponseEntity<Order> readEmail() {
        try {
            System.out.println("Axrti");
            Order order = repository.findById(1).orElse(null);
            MustacheFactory mustache = new DefaultMustacheFactory();
            Mustache m = mustache.compile("templates/order-received.html");
            StringWriter writer = new StringWriter();
            m.execute(writer, new UserOrderReceived(order)).flush();
            String html = writer.toString();
            System.out.println(html);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(null);
    }
}
