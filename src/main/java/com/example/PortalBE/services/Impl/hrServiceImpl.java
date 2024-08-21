package com.example.PortalBE.services.Impl;

import com.example.PortalBE.dto.HrDTO;
import com.example.PortalBE.dto.HrRequest;
import com.example.PortalBE.entity.Hr;
import com.example.PortalBE.exception.ResourceNotFoundException;
import com.example.PortalBE.repository.hrRepository;
import com.example.PortalBE.repository.usersRepository;
import com.example.PortalBE.services.CloudinaryImageService;
import com.example.PortalBE.services.hrService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class hrServiceImpl implements hrService {
    private hrRepository hrRepository;
    private usersRepository usersRepository;
    private ModelMapper modelMapper;
    private CloudinaryImageService cloudinaryImageService;


    @Override
    @Transactional
    public HrDTO createNewHr(HrRequest request) {
        String urlImage = cloudinaryImageService.uploadImage(request.getAvatar());
        //         Hash the password before saving to the database
        BCryptPasswordEncoder hashPassword = new BCryptPasswordEncoder();
        String hashedPassword = hashPassword.encode(request.getPassword());

        Hr hrEntity = modelMapper.map(request, Hr.class);
        hrEntity.setImage(urlImage);
        hrEntity.setPassword(hashedPassword);
        Hr hrSave = hrRepository.save(hrEntity);

        HrDTO response = modelMapper.map(hrSave, HrDTO.class);

        return response;
    }

    @Override
    @Transactional
    public Page<HrDTO> getAllHr(int page, int size) {
        // Tạo một đối tượng Pageable để chỉ định trang và kích thước trang
        Pageable pageable = PageRequest.of(page, size);

        // Sử dụng phương thức findAll của repository với Pageable để trả về kết quả phân trang
        Page<Hr> hrPage = hrRepository.findAll(pageable);

        // Chuyển đổi trang kết quả sang đối tượng Page của UserDto
        Page<HrDTO> hrDtoPage = hrPage.map(hrEntity -> {
            HrDTO hrDTO = modelMapper.map(hrEntity, HrDTO.class);
            hrDTO.setPassword("");
            return hrDTO;
        });

        return hrDtoPage;
    }

//    @Override
//    @Transactional
//    public List<HrDTO> getAllHr() {
//        List<Hr> entityManagers = hrRepository.findAll();
//
//        List<HrDTO> response = new ArrayList<>();
//        for(int i = 0; i < entityManagers.size(); i++) {
//            HrDTO hrDTO = modelMapper.map(entityManagers.get(i), HrDTO.class);
//            response.add(hrDTO);
//        }
//        return response;
//    }

    @Override
    @Transactional
    public HrDTO getHrById(Integer hrId) {
        Hr hrEntity = hrRepository.findById(hrId).orElseThrow(() ->
                new ResourceNotFoundException("HR does not exist"));

        HrDTO response = modelMapper.map(hrEntity, HrDTO.class);
        return response;
    }

    @Override
    @Transactional
    public HrDTO updateHr(HrRequest request) {
        Hr entity = hrRepository.findById(request.getId()).orElseThrow(() ->
                new ResourceNotFoundException("HR doesn't exist"));

        if(request.getAvatar() == null) {
            request.setImage(entity.getImage());
        } else {
            String urlImage = cloudinaryImageService.uploadImage(request.getAvatar());
            request.setImage(urlImage);
        }
        if(request.getPassword() == null){
            request.setPassword(entity.getPassword());
        }else{
            BCryptPasswordEncoder hashPassword = new BCryptPasswordEncoder();
            String hashedPassword = hashPassword.encode(request.getPassword());
            request.setPassword(hashedPassword);
        }

        modelMapper.map(request, entity);
        Hr savedManager = hrRepository.save(entity);

        HrDTO res = modelMapper.map(savedManager, HrDTO.class);

        return res;
    }

    @Override
    @Transactional
    public void deleteHr(Integer hrId) {
        usersRepository.findById(hrId).orElseThrow(() -> new ResourceNotFoundException("This user could not be found!!"));
        hrRepository.findById(hrId).orElseThrow(() -> new ResourceNotFoundException("This HR could not be found!!"));
        usersRepository.deleteById(hrId);
        hrRepository.deleteById(hrId);
    }
}
