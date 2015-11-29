package com.mattbozelka.repository;

import java.util.List;

import com.mattbozelka.model.User;

public interface LoginRepository {

	List<User> getUserList();

}
