package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.ProductApprovedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProductApprovedListener implements ApplicationListener<ProductApprovedEvent> {
    @Override
    public void onApplicationEvent(ProductApprovedEvent productApprovedEvent) {

    }
}
