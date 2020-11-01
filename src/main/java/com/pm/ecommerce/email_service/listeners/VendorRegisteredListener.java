package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.VendorRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VendorRegisteredListener implements ApplicationListener<VendorRegisteredEvent> {
    @Override
    public void onApplicationEvent(VendorRegisteredEvent vendorRegisteredEvent) {

    }
}
