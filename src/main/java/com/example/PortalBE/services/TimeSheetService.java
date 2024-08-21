package com.example.PortalBE.services;

import com.example.PortalBE.dto.TimeSheetDTO;
import java.time.YearMonth;
import java.util.List;

public interface TimeSheetService {
    List<TimeSheetDTO> getAllTimeSheets();
    TimeSheetDTO getTimeSheetById(Long id);
    List<TimeSheetDTO> getTimeSheetsByCandidateIdAndMonth(Long candidateId, YearMonth month);
    TimeSheetDTO saveTimeSheet(TimeSheetDTO timeSheetDTO);
    void deleteTimeSheet(Long id);
}