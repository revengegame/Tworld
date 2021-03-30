package com.oraclejava.Tworld;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	private static final int PAGE_SIZE = 5;
	
	@RequestMapping(value={"/","/{pageNumber}"}, method=RequestMethod.GET)
	public ModelAndView index(@PathVariable Optional<Integer> pageNumber) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");  //뷰 이름
		mav.addObject("msg", "안녕하세요");  //모델
		
		//현재시간 취득
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		String nalja = year + "/" + month + "/" + day
				+ " " + hour + ":" + minute + ":" + second;
		
		mav.addObject("nalja", nalja);
		
		//mav.addObject("phoneList", phoneRepository.findAll());
		
		Page<Phone> phones = phoneRepository.findAll(
				PageRequest.of(pageNumber.isPresent()? pageNumber.get()-1:0,  PAGE_SIZE,
						Sort.by("id").ascending()));
		
		int current = phones.getNumber() + 1;
		int begin = 1;
		int end = phones.getTotalPages();
		
		mav.addObject("phoneList", phones);
		mav.addObject("beginIndex", begin);
		mav.addObject("endIndex", end);
		mav.addObject("currentIndex",  current);
		
		return mav;
		
	}
}