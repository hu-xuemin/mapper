package com.huxuemin.mapper.domain;

import com.huxuemin.mapper.mapper.UnitOfWork;

public abstract class DomainObject {
	
	protected void markNew(){
		UnitOfWork uok = UnitOfWork.getCurrent();
		if(uok != null){
			uok.registerNew(this);
		}
	}
	
	protected void markClean(){
		
	}
	
	protected void markDirty(){
		UnitOfWork uok = UnitOfWork.getCurrent();
		if(uok != null){
			uok.registerDirty(this);
		}
	}
	
	protected void markRemoved(){
		UnitOfWork uok = UnitOfWork.getCurrent();
		if(uok != null){
			uok.registerRemoved(this);
		}
	}
	
	public void delete(){
		markRemoved();
	}
}
