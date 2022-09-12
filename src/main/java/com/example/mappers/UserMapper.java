package com.example.mappers;

import com.example.domains.User;
import com.example.requests.UserPostRequestBody;
import com.example.requests.UserPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUser(UserPostRequestBody userPostRequestBody);

    public abstract User toUser(UserPutRequestBody userPutRequestBody);

}
