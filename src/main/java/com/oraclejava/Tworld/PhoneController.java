package com.oraclejava.Tworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PhoneController {

	@Autowired
	private PhoneRepository phoneRepository;
	
	@RequestMapping(value="/uplus/create", method=RequestMethod.GET)//주소
	public String create(Model model) {
		return "phoneCreate";//화면명
	}
	
	@RequestMapping(value="/uplus/create", method=RequestMethod.POST)//주소
    public String create(Phone phone, Model model) {
		phoneRepository.save(phone);
		return "redirect:/";//추가 Controller에서 클라이언트의 요청을 처리한 후 다른 페이지로 Redirect 하고 싶을 경우


	}
	//수정화면
	@RequestMapping(value="/uplus/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable Integer id, Model model) {
		Phone phone=phoneRepository.findById(id).get();
		model.addAttribute("phone",phone);
		return "phoneUpdate";
	}
	//수정하기
	@RequestMapping(params="update",value="/uplus/update/{id}", method=RequestMethod.POST)
	public String update(Phone phone, Model model) {
		Phone sphone=phoneRepository.findById(phone.getId()).get();
		sphone.setName(phone.getName());
		sphone.setPrice(phone.getPrice());
		phoneRepository.save(sphone);
		return "redirect:/";
		
	}
	//삭제하기
	@RequestMapping(params="delete",value="/uplus/update/{id}", method=RequestMethod.POST)
	public String delete(@PathVariable Integer id, Model model) {
		Phone sphone=phoneRepository.findById(id).get();		
		phoneRepository.delete(sphone);
		return "redirect:/";
		
	}
	//댓글쓰기
	@RequestMapping(value="/uplus/comment", method=RequestMethod.POST)
	public String comment(Comments comments, Model model) {
		Phone p=phoneRepository.findById(comments.getPhone().getId()).get();		
		p.getComments().add(comments);
		phoneRepository.save(p);
		return "redirect:/uplus/update/"+comments.getPhone().getId();
		
	}
	
	
}
