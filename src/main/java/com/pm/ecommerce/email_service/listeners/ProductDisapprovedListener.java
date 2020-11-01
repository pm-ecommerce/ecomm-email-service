package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.ProductDisapprovedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProductDisapprovedListener implements ApplicationListener<ProductDisapprovedEvent> {
    @Override
    public void onApplicationEvent(ProductDisapprovedEvent productDisapprovedEvent) {

    }
}
