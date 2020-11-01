package com.pm.ecommerce.email_service.events;

import com.pm.ecommerce.entities.Vendor;
import org.springframework.context.ApplicationEvent;

public class VendorApprovedEvent extends ApplicationEvent {
    private final Vendor vendor;
    public VendorApprovedEvent(Object source, Vendor vendor) {
        super(source);
        this.vendor = vendor;
    }
    public Vendor getVendor(){
        return this.vendor;
    }
}
