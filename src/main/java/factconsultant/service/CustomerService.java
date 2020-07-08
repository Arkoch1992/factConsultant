package factconsultant.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import factconsultant.model.Customer;
import factconsultant.model.CustomerRequest;

public interface  CustomerService {
	List<Customer> getCustomerReports();
	void saveCustomerDetails(CustomerRequest customerRequest);
	void storeAppointment();
	int countAppointment();
	void createExcel( HttpServletResponse response);
	void deleteCustomer();
	
}
