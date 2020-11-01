package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.VendorApprovedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VendorApprovedListener implements ApplicationListener<VendorApprovedEvent> {
    @Override
    public void onApplicationEvent(VendorApprovedEvent vendorApprovedEvent) {

    }
}
