package com.shakal.imageapi.contracts.service.user;

import com.shakal.imageapi.contracts.entity.ISocialUser;
import com.shakal.imageapi.dto.auth.SocialInputUserDTO;
import com.shakal.imageapi.model.enums.SocialTypeEnum;

public interface ISocialUserService {

	ISocialUser handleLogin(SocialInputUserDTO inputValue,SocialTypeEnum type);
}
