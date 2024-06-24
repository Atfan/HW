package org.company.TicketOnline2.service.customerservice;

import org.company.ticketonline2.dao.customer.CustomerRepository;
import org.company.ticketonline2.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void save(Customer data) {
        customerRepository.save(data);
    }

    @Override
    public int[] saveToList(List<Customer> arrData) {
        customerRepository.saveAll(arrData);
        return new int[1];
    }

    @Override
    public void update(Customer data) {
        customerRepository.save(data);
    }

    @Override
    public void delete(Customer data) {
        customerRepository.delete(data);
    }

    @Override
    public List<Customer> toList() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> byId = customerRepository.findById(id);
        return byId.orElse(null);
    }
}
