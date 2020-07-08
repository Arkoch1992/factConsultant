package factconsultant.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factconsultant.model.Appointment;
import factconsultant.model.Customer;
import factconsultant.model.CustomerRequest;
import factconsultant.repository.AppointmentsRepository;
import factconsultant.repository.CustomerRepository;
import factconsultant.utill.ExcelFileExporter;

@Service
public class CustomerServiceImpl implements CustomerService {

	CustomerRepository customerRepo;
	AppointmentsRepository appointmentsRepository;
	

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepo, AppointmentsRepository appointmentsRepository) {
		this.customerRepo = customerRepo;
		this.appointmentsRepository = appointmentsRepository;

	}

	@Override
	public List<Customer> getCustomerReports() {
		List<Customer> customerDetails = customerRepo.findAll();
		return customerDetails;

	}

	public void saveCustomerReports() {
		customerRepo.findAll();

	}

	@Override
	public void saveCustomerDetails(CustomerRequest customerRequest) {
		Customer customer = new Customer();
		customer.setEmailId(customerRequest.getEmailId());
		customer.setMessage(customerRequest.getMessage());
		customer.setName(customerRequest.getName());
		customer.setPhoneNo(customerRequest.getPhoneNo());

		customerRepo.save(customer);

	}

	@Override
	public void storeAppointment() {
		List<Appointment> appointList = appointmentsRepository.findAll();
		Appointment appointment = new Appointment();
		int count = 0;
		if (appointList.size() != 0) {
			count = appointList.get(0).getCount();
			count++;
		} else {
			count = 1;
		}
		appointment.setCount(count);
		appointmentsRepository.save(appointment);
	}

	@Override
	public int countAppointment() {
		List<Appointment> findCount = appointmentsRepository.findAll();
		int count = 0;
		if (findCount.size() != 0) {
			count = findCount.get(0).getCount();
		}

		return count;
	}

	@Override
	public void  createExcel( HttpServletResponse response) {
		
		try {
			List<Customer> customerDetails = customerRepo.findAll();
			
			response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment; filename=customers.xlsx");

	        ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(customerDetails);
	        IOUtils.copy(stream, response.getOutputStream());
			//return "Downloaded";

		 } catch (Exception ex) {

				//return "UnAble To download:"+ex.getMessage();
		 }

    
		
	}

	@Override
	public void deleteCustomer() {
		customerRepo.deleteAll();
		
	}

	

	
	
}
