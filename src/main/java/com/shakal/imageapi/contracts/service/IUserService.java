package com.shakal.imageapi.contracts.service;


import com.shakal.imageapi.exception.DuplicatedResourceException;
import com.shakal.imageapi.exception.ResourceNotFoundException;


public interface IUserService {

	long getCurrentUserId();
	void setCharacterToUserInStory(long storyId, long userId, Character character);
	//UserCreateDTO insertUser(UserCreateDTO createDto)  throws DuplicatedResourceException;
	/*
	UserStoryManagementInputDTO getUserManagementInput(long storyId);
	UserManagementUpdateDTO updateUsersInStory(UserManagementUpdateDTO inputDto) throws ResourceNotFoundException;
	*/
}
