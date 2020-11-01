package com.pm.ecommerce.email_service.events;

import com.pm.ecommerce.entities.Product;
import org.springframework.context.ApplicationEvent;

public class ProductApprovedEvent extends ApplicationEvent {
    private final Product product;
    public ProductApprovedEvent(Object source, Product product) {
        super(source);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
