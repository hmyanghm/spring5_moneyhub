package com.moneyhub.web.usr;

import org.springframework.stereotype.Repository;

@Repository
public interface ClientMapper {
	public void insertClient(Client client);
	public Client selectByIdPw(Client client);
	public int existId(String cid);

}
