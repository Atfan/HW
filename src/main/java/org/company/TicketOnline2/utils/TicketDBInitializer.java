package org.company.TicketOnline2.utils;

import lombok.extern.log4j.Log4j2;
import org.company.ticketonline2.exception.FileException;
import org.company.ticketonline2.model.Customer;
import org.company.ticketonline2.model.Event;
import org.company.ticketonline2.model.Place;
import org.company.ticketonline2.model.Ticket;
import org.company.ticketonline2.service.ticketinitservice.TicketInitService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Log4j2
@Service
public class TicketDBInitializer {
    private final TicketInitService ticketInitService;

    private static final Random RANDOM_GENERATOR = new Random();

    public TicketDBInitializer(TicketInitService ticketInitService) {
        this.ticketInitService = ticketInitService;
    }

    public void deleteAllRowsInDB(){
        ticketInitService.deleteAllRowsInDB();
        log.info("All rows deleted");
    }

    public void createRandomCustomer() throws FileException {
        TxtFileReader txtFileReaderNames = new TxtFileReader("data.names");
        List<String> randomNames = txtFileReaderNames.readFile();
        TxtFileReader txtFileReaderLastNames = new TxtFileReader("data.lastnames");
        List<String> randomLastNames = txtFileReaderLastNames.readFile();

        List<Customer> customerList = new ArrayList<>();
        for (int count = 0; count < 200; count++) {
            Customer addCustomer = new Customer();
            addCustomer.setFirstName(randomNames.get(RANDOM_GENERATOR.nextInt(randomNames.size())));
            addCustomer.setLastName(randomLastNames.get(RANDOM_GENERATOR.nextInt(randomLastNames.size())));
            addCustomer.setEmail(addCustomer.getFirstName()+"_"+addCustomer.getLastName()+RANDOM_GENERATOR.nextInt(100)+"@gmail.com");
            addCustomer.setPhone(createRandomPhone());
            customerList.add(addCustomer);
        }
        ticketInitService.saveCustomer(customerList);
        log.info("Random customers are created");
    }

    public void createRandomPlace() throws FileException {
        TxtFileReader txtFileReaderCity = new TxtFileReader("data.city");
        List<String> randomCity = txtFileReaderCity.readFile();
        TxtFileReader txtFileReaderStreet = new TxtFileReader("data.street");
        List<String> randomStreet = txtFileReaderStreet.readFile();
        TxtFileReader txtFileReaderAreas = new TxtFileReader("data.areas");
        List<String> randomAreas = txtFileReaderAreas.readFile();

        List<Place> placeList = new ArrayList<>();
        for(int count = 0; count < 50; count++) {
            String city=randomCity.get(RANDOM_GENERATOR.nextInt(randomCity.size()));

            Place addPlace = new Place();
            addPlace.setId((long) count+1);
            addPlace.setAddress(city + ", " + randomStreet.get(RANDOM_GENERATOR.nextInt(randomStreet.size()))+
                    ", "+ RANDOM_GENERATOR.nextInt(120));
            addPlace.setName(randomAreas.get(RANDOM_GENERATOR.nextInt(randomAreas.size()))+" in "+ city);
            placeList.add(addPlace);
        }
        ticketInitService.savePlace(placeList);
        log.info("Random places are created");
    }
    public void createRandomEvents() throws FileException {
        TxtFileReader txtFileReaderEvent = new TxtFileReader("data.name_events");
        List<String> randomEvent = txtFileReaderEvent.readFile();

        List<Place> placeList = ticketInitService.findAllPlaces();

        List<Event> eventList = new ArrayList<>();
        List<Ticket> ticketList = new ArrayList<>();
        boolean isUniqueEvent;
        for(int count = 0; count < 30; count++) {
            isUniqueEvent=false;
            Event addEvent = new Event();
            addEvent.setPlace(placeList.get(RANDOM_GENERATOR.nextInt(placeList.size())));
            addEvent.setName(randomEvent.get(RANDOM_GENERATOR.nextInt(randomEvent.size())));
            Date dateEvent = null;

            while (!isUniqueEvent) {
                dateEvent=createRandomDate();
                boolean isUniq= true;
                for (Event event : eventList) {
                    if (event.getDate().equals(dateEvent) && event.getPlace().equals(addEvent.getPlace())) {
                        isUniq=false;
                        break;
                    }
                }
                if (isUniq) {
                    isUniqueEvent=true;
                }
            }

            addEvent.setDate(dateEvent);

            //addEvent.setTickets(createRandomTickets(addEvent));

            eventList.add(addEvent);
            ticketList.addAll(createRandomTickets(addEvent));
        }
        ticketInitService.saveEvent(eventList);
        ticketInitService.saveTicket(ticketList);
        log.info("Random events are created");
    }

    private List<Ticket> createRandomTickets(Event event){
        List<Customer> customerList = ticketInitService.findAllCustomers();
        List<Ticket> ticketList = new ArrayList<>();
        for (int ticketCount = 0; ticketCount < RANDOM_GENERATOR.nextInt(20); ticketCount++) {
            Ticket addTicket = new Ticket();
            addTicket.setNumbr(ticketCount);
            addTicket.setCost(RANDOM_GENERATOR.nextInt(10)*200);
            addTicket.setEvent(event);

            if(RANDOM_GENERATOR.nextBoolean()){
                addTicket.setCustomer(customerList.get(RANDOM_GENERATOR.nextInt(customerList.size())));
                addTicket.setStatus("SOLD");
            }
            else {
                addTicket.setStatus("FREE");
            }

            ticketList.add(addTicket);
        }
        return ticketList;
    }

    private Date createRandomDate() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(30);
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomEpochDay = RANDOM_GENERATOR.nextLong(startEpochDay, endEpochDay + 1);

        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private String createRandomPhone() {
        PhoneOperatorsCode[] codes =  PhoneOperatorsCode.values();

        StringBuilder phone = new StringBuilder();
        phone.append(codes[RANDOM_GENERATOR.nextInt(codes.length)].getOperatorsCode());

        for (int counter = 0; counter < 7; counter++) {
            phone.append(RANDOM_GENERATOR.nextInt(9) + 1);
        }
        return phone.toString();
    }

}
