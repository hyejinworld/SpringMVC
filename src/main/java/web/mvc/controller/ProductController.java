package web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import web.mvc.dto.ProductDTO;
import web.mvc.service.ProductService;

@Controller
@Slf4j
public class ProductController {
    
	@Autowired
	private ProductService productService;
	
	/**
	 * 전체검색
	 * */
	@RequestMapping("/")
	public ModelAndView selectAll() {
		log.info("전체검색  call...");
		
		//서비스 호출
		List<ProductDTO> list = productService.select();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("productList");// WEB-INF/views/productList.jsp이동
		mv.addObject("productList",list); //jsp문서에서 ${productList} 사용하고 있다.
		return mv;
	}
	
	/**
	 * 상품등록폼
	 * */
	@RequestMapping(value="/insertForm", method = RequestMethod.GET)
	public String insertForm(){
		log.info("상품등록 form");
		return "insertForm";
	}
	
	/*상품 등록 처리 */
	
	@RequestMapping(value ="/products", method = RequestMethod.POST)
	public ModelAndView insert(ProductDTO productDTO) {
	
	//등록
		productService.insert(productDTO);
		
	List<ProductDTO> list = productService.select();
	
	ModelAndView mv = new ModelAndView();
	mv.setViewName("productList");
	mv.addObject("productList", list);
	return mv;
	}
	
	//상세보기
	
	@RequestMapping(value = "/read", method =RequestMethod.GET)
	public ModelAndView selectBAndView(@RequestParam("code") String code) {
		
		ProductDTO product = productService.selectByCode(code);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("read");
		mv.addObject("product", product);
		return mv;
	}
	
	//삭제컨트롤러
	
	@RequestMapping(value = "/del/{code}", method =RequestMethod.GET)
	public ModelAndView delete(@PathVariable("code") String code) {
		
		productService.delete(code);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		//mv.addObject("product", product);
		return mv;
	}
	
}





