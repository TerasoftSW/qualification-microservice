package com.terasoft.qualificationbc.command.application.handlers;


import com.terasoft.qualificationsbccontracts.events.QualificationCLCEdited;
import com.terasoft.qualificationsbccontracts.events.QualificationCLCRegistered;
import com.terasoft.qualificationsbccontracts.events.QualificationLAEdited;
import com.terasoft.qualificationsbccontracts.events.QualificationLARegistered;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class QualificationEventHandler {
    public QualificationEventHandler() {}

    @EventHandler
    public void on(QualificationCLCRegistered event) {}

    @EventHandler
    public void on(QualificationLARegistered event) {}

    @EventHandler
    public void on(QualificationCLCEdited event) {}

    @EventHandler
    public void on(QualificationLAEdited event) {}
}
