package factconsultant.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import factconsultant.model.Admin;
@Repository
public interface UserRepository extends CrudRepository<Admin, Long> {


	Admin  findByUserName(String userName);
	

}
