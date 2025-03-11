package com.mycompany.learning.Application.Services;


import com.mycompany.learning.Application.Dtos.UserDto;
import com.mycompany.learning.Application.Mappers.UserMapper;
import com.mycompany.learning.Domain.Entities.User;
import com.mycompany.learning.Domain.Repositories.IUserRepository;
import com.mycompany.learning.Domain.Results.PagedResult;
import com.mycompany.learning.Domain.Results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Override
    public Result<String> Add(UserDto userDto) {
        User user= UserMapper.mapToEntity(userDto);
        user.setId(null);
        userRepository.save(user);
        return Result.succeed("Kullanıcı Başarılı Bir Şekilde Eklendi.");
    }

    @Override
    public Result<UserDto> GetById(int id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            return Result.succeed(UserMapper.mapToDto(user));
        }
        return null;
    }

    @Override
    public Result<List<UserDto>> GetAll() {
        List<User> users = userRepository.findAll();
        return Result.succeed(users.stream().map(UserMapper::mapToDto).toList());
    }

    @Override
    public Result<String> Update(UserDto userDto) {
        User user = userRepository.findById(userDto.id).orElse(null);
        if(user != null){
            user.setFirstName(userDto.firstName);
            user.setLastName(userDto.lastName);
            user.setEmail(userDto.email);
            user.setPassword(userDto.password);
            user.setProfilePictureUrl(userDto.profilePictureUrl);
            userRepository.save(user);
            return Result.succeed("Kullanıcı Başarılı Bir Şekilde Güncellendi.");
        }
        return Result.failure("Kullanıcı Bulunamadı.");
    }

    @Override
    public Result<String> Delete(int id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            userRepository.delete(user);
            return Result.succeed("Kullanıcı Başarılı Bir Şekilde Silindi.");
        }
        return Result.failure("Kullanıcı Bulunamadı.");
    }

    @Override
    public PagedResult<UserDto> GetPagedList(int pageNumber, int pageSize) {
        List<User> users = userRepository.findAll();
        int totalCount = users.size();
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalCount);
        List<UserDto> userDtos = users.subList(startIndex, endIndex).stream().map(UserMapper::mapToDto).toList();
        return PagedResult.succeed(userDtos, totalCount, pageNumber, pageSize);
    }
}
