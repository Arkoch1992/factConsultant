package factconsultant.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import factconsultant.model.Customer;
import factconsultant.model.CustomerRequest;
import factconsultant.repository.CustomerRepository;
import factconsultant.service.CustomerService;
import factconsultant.utill.ExcelFileExporter;

@RestController
@CrossOrigin("*")
public class FactConsultantController {
	public CustomerService customerService;
	public CustomerRepository customerRepo;

	@Autowired
	FactConsultantController(CustomerService customerService,CustomerRepository customerRepo) {
		this.customerService = customerService;
		this.customerRepo=customerRepo;

	}

	@GetMapping("/getDemo")
	String getDemo() {
		System.out.println("enter into");
		return "got data";
	}

	@GetMapping("/getReport")
	List<Customer> getCustomerReport() {
		List<Customer> customerReport = new ArrayList<>();
		try {
			customerReport = customerService.getCustomerReports();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customerReport;
	}
@PostMapping("/storeCustomerDetails")
	ResponseEntity<Map<String,Object>> saveCustomerDetails(@RequestBody CustomerRequest customerRequest) {
		Map<String,Object> response=new HashMap<String, Object>();
System.out.println("enter into");
		try {
			customerService.saveCustomerDetails(customerRequest);
			response.put("status", "Success");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			response.put("status", "Failed");
		}
		 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

	}


@PostMapping("/appointment")
void saveAppointmentCount() {
	HttpHeaders responseHeaders = new HttpHeaders();
	   

	try {
		customerService.storeAppointment();
		responseHeaders.set("status", "Success");
		
	} catch (Exception e) {
		e.printStackTrace();
		responseHeaders.set("status", "Failed");
	}

}

@GetMapping("/generateExcel")
ResponseEntity<Map<String,Object>> createExcel(HttpServletResponse httpresponse) throws IOException {
 //customerService.createExcel(httpresponse);
	Map<String,Object> response=new HashMap<String, Object>();
	try {
		List<Customer> customerDetails = customerRepo.findAll();
		httpresponse.setContentType("application/octet-stream");
		httpresponse.setHeader("Content-Disposition", "attachment; filename=customers.xlsx");
	    ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(customerDetails);
	    IOUtils.copy(stream, httpresponse.getOutputStream());
	    response.put("status", "Success Downloaded the ExcelFile");
	}
	catch(Exception e) {
		response.put("status", "Failed: "+e.getMessage());
	}
	 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

}

@GetMapping("/deleteCustomer")
ResponseEntity<Map<String,Object>>  deleteCustomer() {
	Map<String,Object> response=new HashMap<String, Object>();
	try {
		customerService.deleteCustomer();
		response.put("status", "Success");
		
		
	} catch (Exception e) {
		response.put("status", "Failed: "+e.getMessage());
		
		
	}
	 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
}


}


