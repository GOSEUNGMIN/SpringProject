package com.example.project1.service;

import com.example.project1.dto.ItemDto;
import com.example.project1.dto.UserDto;
import com.example.project1.repository.ItemRepo;
import com.example.project1.repository.TaskRepo;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService
{
    private final TaskRepo taskRepo; // repo를 쓰기 위해 선언
    private final ItemRepo itemRepo;
    private final HttpSession session;

    public boolean login(UserDto dto)
    {
        Optional<UserDto> result = taskRepo.findByIdAndPassword(dto.getId(), dto.getPassword());
        boolean loginS;

        if (result.isEmpty()) // 값이 없으면. .isEmpty()는 컬렉션이나 문자열이 비어 있는지 확인
        {
            loginS = false; // false
        }
        else // 있으면
        {
            session.setAttribute("user", result.get());
            loginS = true; // true
        }
        return loginS;
    }

    public String SignUp(UserDto dto, String passwordCheck)
    {
        if (taskRepo.existsById(dto.getId()))
        {
            return "2";
        }

        if (!dto.getPassword().equals(passwordCheck))
        {
            return "2";
        }

//        UserDto userDto = new UserDto();
//        userDto.setId(dto.getId());
//        userDto.setPassword(dto.getPassword());
//        userDto.setUsername(dto.getUsername());

        taskRepo.save(dto);
        return "3";
    }

    public List<ItemDto> list()
    {
//        String userid = (String) session.getAttribute("user");
//        log.info(userid);
//        return itemRepo.findByUserid(userid);

        UserDto userDto = (UserDto) session.getAttribute("user");
        String userId = userDto.getId(); // UserDto에서 ID를 추출
        return itemRepo.findByUserid(userId); // 아이디로 게시글 조회
    }
}
