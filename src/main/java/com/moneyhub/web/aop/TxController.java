package com.moneyhub.web.aop;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneyhub.web.cmm.IConsumer;
import com.moneyhub.web.cmm.IFunction;
import com.moneyhub.web.pxy.ProxyMap;
import com.moneyhub.web.utl.Printer;

@RestController
@Transactional
@RequestMapping("/tx")
public class TxController {
	// System.out.println(text);
	//@Autowired HashMap<String, String> map;
	@Autowired Printer p;
	@Autowired TxService txService;
	@Autowired ProxyMap map;
	
	@GetMapping("/crawling/{site}/{srch}")
	public void searchUrl(@PathVariable String site,
					@PathVariable String srch){
		p.accept(site+", srch: "+srch);
		HashMap<String, String> map = new HashMap<>();
		map.clear();
		map.put("site", site);
		map.put("srch", srch);
		txService.crawling(map);
	}
	
	

}
