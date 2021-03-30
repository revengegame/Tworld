package com.oraclejava.Tworld;


import com.oraclejava.Tworld.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhoneRepository extends PagingAndSortingRepository<Phone, Integer>{

}
