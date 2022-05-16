package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.repository.ChargePointRepository;
import com.example.demo.repository.ChargingSessionRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RfidRepository;
import com.example.demo.utility.DateTimeUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Configuration
public class ExerciseConfig {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            generateDummyData(customerRepository);
        };
    }

    private void generateDummyData(CustomerRepository repository){
        Customer university = new Customer("University of Waikato");
        Customer wintec = new Customer("Wintec");
        ChargePoint cpUni = new ChargePoint("Blink_uni", "uni1234567", university);
        ChargePoint cpWin = new ChargePoint("ABB_win", "win7654321", wintec);
        Connector connectorU1 = new Connector("blink0001uni", cpUni);
        Connector connectorU2 = new Connector("blink0002uni", cpUni);
        Connector connectorW1 = new Connector("abb0001win", cpWin);
        Connector connectorW2 = new Connector("abb0002win", cpWin);
        Vehicle tesla = new Vehicle("Model 3", "MYZ226");
        Vehicle hyundai = new Vehicle("Ioniq 5", "NPM171");
        Rfid tagUni = new Rfid("uni:rfid:0001", university, tesla);
        Rfid tagWin = new Rfid("win:rfid:0001",wintec, hyundai);
        university.addChargePoint(cpUni);
        university.addRfid(tagUni);
        wintec.addChargePoint(cpWin);
        wintec.addRfid(tagWin);
        cpUni.addConnector(connectorU1);
        cpUni.addConnector(connectorU2);
        cpWin.addConnector(connectorW1);
        cpWin.addConnector(connectorW2);
        repository.saveAll(List.of(university,wintec));
    }
}
