package com.consol.citrus.samples.flightbooking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.integration.splitter.AbstractMessageSplitter;

import com.consol.citrus.samples.flightbooking.model.*;

public class BookingSplitter extends AbstractMessageSplitter {
    
    @Override
    public Object splitMessage(Message<?> message) {
        List<Message<FlightBookingRequestMessage>> flightRequests = new ArrayList<Message<FlightBookingRequestMessage>>();
        
        if(message.getPayload() instanceof TravelBookingRequestMessage == false) {
            throw new IllegalStateException("Unsupported message type: " + message.getPayload().getClass());
        }
        
        TravelBookingRequestMessage request  = ((TravelBookingRequestMessage)message.getPayload());
        
        for (Flight flight : request.getFlights()) {
            FlightBookingRequestMessage flightRequest = new FlightBookingRequestMessage();
            flightRequest.setFlight(flight);
            flightRequest.setCorrelationId(request.getCorrelationId());
            flightRequest.setCustomer(request.getCustomer());
            flightRequest.setBookingId("myBookingId");
            
            MessageBuilder<FlightBookingRequestMessage> messageBuilder = MessageBuilder.withPayload(flightRequest);
            messageBuilder.copyHeaders(message.getHeaders());
            
            flightRequests.add(messageBuilder.build());
        }
        
        return flightRequests;
    }
}