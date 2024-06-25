package com.ohgiraffers.oauthjwt.mapper;

import com.ohgiraffers.oauthjwt.entityDTO.RefreshEntity;
import com.ohgiraffers.oauthjwt.entityDTO.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserEntity findByUsername(String username);

    Boolean existsByRefresh(String refresh);

    void deleteByRefresh(String refresh);
    void saveUserEntity(UserEntity userEntity);
    void saveRefreshEntity(RefreshEntity refreshEntity);

}
