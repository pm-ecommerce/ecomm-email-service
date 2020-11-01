package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.VendorDisapprovedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VendorDisapprovedListener implements ApplicationListener<VendorDisapprovedEvent> {
    @Override
    public void onApplicationEvent(VendorDisapprovedEvent vendorDisapprovedEvent) {

    }
}
