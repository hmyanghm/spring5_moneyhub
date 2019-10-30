package com.moneyhub.web.adm;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
	Admin selectAdminByIdPw(Admin param);
	Admin updateAdmin(Admin param);
	Admin deleteAdmin(Admin param);
	Admin selectAdminById(String param);
}
