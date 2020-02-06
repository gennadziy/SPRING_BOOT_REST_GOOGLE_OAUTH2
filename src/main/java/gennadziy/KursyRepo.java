package gennadziy;

import gennadziy.model.KursWalut;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KursyRepo extends JpaRepository<KursWalut, Long> {

}
