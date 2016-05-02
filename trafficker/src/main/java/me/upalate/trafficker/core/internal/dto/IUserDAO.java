package me.upalate.trafficker.core.internal.dto;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IUserDAO {
   public void saveUser();
}
