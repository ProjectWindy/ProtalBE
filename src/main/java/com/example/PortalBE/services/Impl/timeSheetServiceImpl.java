package com.example.PortalBE.services.Impl;

import com.example.PortalBE.dto.TimeSheetDTO;
import com.example.PortalBE.entity.TimeSheet;
import com.example.PortalBE.repository.timeSheetRepository;
import com.example.PortalBE.services.TimeSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class timeSheetServiceImpl implements TimeSheetService {

    @Autowired
    private timeSheetRepository timeSheetRepository;

    @Override
    public List<TimeSheetDTO> getAllTimeSheets() {
        return timeSheetRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TimeSheetDTO getTimeSheetById(Long id) {
        return timeSheetRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public List<TimeSheetDTO> getTimeSheetsByCandidateIdAndMonth(Long candidateId, YearMonth month) {
        LocalDate startDate = month.atDay(1); // First day of the month
        LocalDate endDate = month.atEndOfMonth(); // Last day of the month
        List<TimeSheet> timeSheets = timeSheetRepository.findByCandidateIdAndDateBetween(candidateId, startDate, endDate);
        return timeSheets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TimeSheetDTO saveTimeSheet(TimeSheetDTO timeSheetDTO) {
        TimeSheet timeSheet = convertToEntity(timeSheetDTO);
        TimeSheet savedTimeSheet = timeSheetRepository.save(timeSheet);
        return convertToDTO(savedTimeSheet);
    }

    @Override
    public void deleteTimeSheet(Long id) {
        timeSheetRepository.deleteById(id);
    }

    private TimeSheetDTO convertToDTO(TimeSheet timeSheet) {
        return new TimeSheetDTO(
                timeSheet.getId(),
                timeSheet.getCandidateId(),
                timeSheet.getMonth(),
                timeSheet.getDate(),
                timeSheet.getProjectName(),
                timeSheet.getFromTime(),
                timeSheet.getToTime(),
                timeSheet.getTaskName(),
                timeSheet.getRegularHours(),
                timeSheet.getOvertimeHours(),
                timeSheet.isApproved()
        );
    }

    private TimeSheet convertToEntity(TimeSheetDTO timeSheetDTO) {
        return new TimeSheet(
                timeSheetDTO.getId(),
                timeSheetDTO.getCandidateId(),
                timeSheetDTO.getMonth(),
                timeSheetDTO.getDate(),
                timeSheetDTO.getProjectName(),
                timeSheetDTO.getFromTime(),
                timeSheetDTO.getToTime(),
                timeSheetDTO.getTaskName(),
                timeSheetDTO.getRegularHours(),
                timeSheetDTO.getOvertimeHours(),
                timeSheetDTO.isApproved()
        );
    }
}
