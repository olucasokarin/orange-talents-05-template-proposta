package br.com.zupedu.olucas.proposta.config.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalMetrics {

    private Counter proposalsCounter;
    private MeterRegistry meterRegistry;

    @Autowired
    public ProposalMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.initialCounters();
    }

    private void initialCounters() {
        this.proposalsCounter = this.meterRegistry.counter("proposal_created");
    }

    public void incrementCounter() {
        this.proposalsCounter.increment();
    }
}
