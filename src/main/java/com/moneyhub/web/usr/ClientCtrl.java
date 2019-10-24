package com.moneyhub.web.usr;

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
@RequestMapping("/client")
public class ClientCtrl {
	private static final Logger logger = LoggerFactory.getLogger(ClientCtrl.class);
	@Autowired Map<String, Object>map;
	@Autowired Client client;
	@Autowired Printer printer;
	@Autowired ClientMapper clientMapper;
	
	@GetMapping("/{cid}/exist")
    public Map<?,?> exist(@PathVariable String cid){
        System.out.println(cid);
        IFunction<String, Integer> p = o -> clientMapper.existId(cid);
        map.clear();
        map.put("msg", (p.apply(cid)==0) ? "SUCCESS" : "FAIL");
        System.out.println(map.get("msg"));
        return map;
    }
	
	@PostMapping("/")
	public Map<?,?> join(@RequestBody Client param) {
		printer.accept("join 들어옴 : "+param.toString());
		IConsumer<Client> c = o -> clientMapper.insertClient(param);
		c.accept(param);
		map.clear();
		map.put("msg", "SUCCESS");
		return map;
	}
	
	@PostMapping("/{cid}")
	public Client login(@PathVariable String cid, @RequestBody Client param){
		IFunction<Client, Client> f = t -> clientMapper.selectByIdPw(param);
		return f.apply(param);
	}
	
	@GetMapping("/{cid}")
	public Client searchClientById(@PathVariable String cid, @RequestBody Client param){
		IFunction<Client, Client> f = t -> clientMapper.selectByIdPw(param);
		return f.apply(param);
	}
	
	@PutMapping("/{cid}")
	public Map<?,?> updateClient(@PathVariable String cid, @RequestBody Client param){
		IConsumer<Client> c = t -> clientMapper.insertClient(param);
		c.accept(param);
		map.clear();
		map.put("msg", "SUCCESS");
		return map;
	}
	
	@DeleteMapping("/{cid}")
	public Map<?,?> removeClient(@PathVariable String cid, @RequestBody Client param){
		IConsumer<Client> c = t -> clientMapper.insertClient(param);
		c.accept(param);
		map.clear();
		map.put("msg", "SUCCESS");
		return map;
	}
}
