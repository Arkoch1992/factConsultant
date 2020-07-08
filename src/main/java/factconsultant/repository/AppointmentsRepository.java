package factconsultant.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import factconsultant.model.Appointment;
@Repository
public interface AppointmentsRepository extends CrudRepository<Appointment, Integer> {


	 List<Appointment>  findAll();
	

}
