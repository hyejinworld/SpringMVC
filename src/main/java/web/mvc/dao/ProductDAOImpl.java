package web.mvc.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.mvc.dto.ProductDTO;
import web.mvc.exception.ErrorCode;
import web.mvc.exception.MyErrorException;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductDAOImpl implements ProductDAO {

	private final List<ProductDTO> list; //영속성 = db역할(CRUD작업)
	
	@PostConstruct
	public void init() {
		log.info("list = {}" , list);
	}
	
	@Override
	public List<ProductDTO> select() {
		
		return list;
	}

	@Override
	public int insert(ProductDTO productDTO) throws MyErrorException {
		
		boolean exists = list.stream()
				.anyMatch(p->p.getCode().equals(productDTO.getCode()));
		
		if(exists) {
			throw new MyErrorException(ErrorCode.DUPLICATE_PRODUCT_CODE);
			
		}
		
		list.add(productDTO);
		
		return 1;
	}

	//4
	@Override
	public int delete(String code) throws MyErrorException {
		  ProductDTO target = list.stream()
	                .filter(p -> p.getCode().equals(code))
	                .findFirst()
	                .orElse(null);

	        if (target == null) {
	            throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE); // 코드가 없다
	        }
	        list.remove(target);
	        return 1;
	}

	//3
	@Override
	public ProductDTO selectByCode(String code) {
		// 상세보기할때 필요한 selectbycode
		return list.stream()
				.filter(p->p.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE));
				
	}



    @Override
    public int updateByCode(ProductDTO productDTO) throws MyErrorException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equals(productDTO.getCode())) {
                list.set(i, productDTO); 
                return 1;
            }
        }
        return 0; 
    }


}
