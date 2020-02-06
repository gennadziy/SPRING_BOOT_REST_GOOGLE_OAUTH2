package gennadziy;

import gennadziy.model.KursWalut;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;


public interface KursyRepo extends JpaRepository<KursWalut, Integer> {

}
