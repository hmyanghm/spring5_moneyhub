package com.moneyhub.web.adm;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moneyhub.web.cmm.IConsumer;
import com.moneyhub.web.cmm.IFunction;
import com.moneyhub.web.utl.Printer;


@RestController
@RequestMapping("/admins")
public class AdminCtrl {
	private static final Logger logger = LoggerFactory.getLogger(AdminCtrl.class);
	@Autowired Map<String, Object>map;
	@Autowired Admin admin;
	@Autowired Printer printer;
	@Autowired AdminMapper adminMapper;
	
	
	@PostMapping("/")
	public Map<?,?> register(@RequestBody Admin param) {
		printer.accept(" join 들어옴 : "+param.toString());
		IConsumer<Admin> c = o-> adminMapper.selectAdminByIdPw(param);
		c.accept(param);
		map.clear();
		map.put("msg", "SUCCESS");
		return map;
	}
	
	@PostMapping("/{aid}")
	public String access(@PathVariable String aid, @RequestBody Admin param) {
		IFunction<Admin,Admin> f = t-> adminMapper.selectAdminByIdPw(param);
		return (f.apply(param)!=null) ? "SUCCESS" : "FAIL";
	}
	
	@GetMapping("/{aid}")
	public Admin searchAdminById(@PathVariable String aid, @RequestBody Admin param) {
		IFunction<Admin,Admin> f = t-> adminMapper.selectAdminByIdPw(param);
		return f.apply(param);
	}
	
	@PutMapping("/{aid}")
	public Map<?,?> updateAdmin(@PathVariable String aid, @RequestBody Admin param) {
		IConsumer<Admin> c = o-> adminMapper.updateAdmin(param);
		c.accept(param);
		map.clear();
		map.put("msg", "SUCCESS");
		return map;
	}
	
	@DeleteMapping("/{aid}")
	public Map<?,?> removeAdmin(@PathVariable String aid, @RequestBody Admin param) {
		IConsumer<Admin> c = o-> adminMapper.deleteAdmin(param);
		c.accept(param);
		map.clear();
		map.put("msg", "SUCCESS");
		return map;
	}
}	