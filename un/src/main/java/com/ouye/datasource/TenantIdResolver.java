package com.ouye.datasource;


import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import com.ouye.bean.LoginUser;

public class TenantIdResolver implements CurrentTenantIdentifierResolver {
    
	public String resolveCurrentTenantIdentifier() {
		
		String tenantId = LoginUser.getTenantId();
		return tenantId;
		
    }

	@Override
	public boolean validateExistingCurrentSessions() {
		// TODO Auto-generated method stub
		return false;
	}
}