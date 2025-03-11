package com.mycompany.learning.Application.Services;

import com.mycompany.learning.Application.Dtos.UserDto;
import com.mycompany.learning.Domain.Entities.User;
import com.mycompany.learning.Domain.Results.PagedResult;
import com.mycompany.learning.Domain.Results.Result;
import java.util.List;
public interface IUserService {

    Result<String> Add(UserDto userDto);
    Result<UserDto> GetById(int id);
    Result<List<UserDto>> GetAll();

    Result<String> Update(UserDto userDto);

    Result<String> Delete(int id);

    PagedResult<UserDto> GetPagedList(int pageNumber, int pageSize);


}
