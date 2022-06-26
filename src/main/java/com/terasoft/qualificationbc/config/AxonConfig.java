package com.terasoft.qualificationbc.config;

import com.terasoft.qualificationbc.command.domain.entities.Qualification;
import com.thoughtworks.xstream.XStream;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();

        xStream.allowTypesByWildcard(new String[] {
                "com.terasoft.qualificationsbccontracts.**"
        });
        return xStream;
    }
    @Bean
    public Repository<Qualification> eventSourcingQualificationRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(Qualification.class)
                .eventStore(eventStore)
                .build();
    }
}
