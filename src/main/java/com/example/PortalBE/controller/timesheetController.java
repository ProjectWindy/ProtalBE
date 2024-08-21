package com.example.PortalBE.controller;

import com.example.PortalBE.dto.TimeSheetDTO;
import com.example.PortalBE.services.TimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/v1/timesheets")
public class timesheetController {

    @Autowired
    private TimeSheetService timeSheetService;

    @GetMapping
    public List<TimeSheetDTO> getAllTimeSheets() {
        return timeSheetService.getAllTimeSheets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeSheetDTO> getTimeSheetById(@PathVariable Long id) {
        TimeSheetDTO timeSheet = timeSheetService.getTimeSheetById(id);
        return timeSheet != null ? ResponseEntity.ok(timeSheet) : ResponseEntity.notFound().build();
    }
    @GetMapping("/candidate/{candidateId}/month/{month}")
    public ResponseEntity<List<TimeSheetDTO>> getTimeSheetsByCandidateIdAndMonth(
            @PathVariable Long candidateId,
            @PathVariable String month) {
        try {
            // Chuyển đổi tháng thành YearMonth
            YearMonth yearMonth = YearMonth.parse(month); // Đảm bảo tháng là định dạng 'YYYY-MM'
            List<TimeSheetDTO> timeSheets = timeSheetService.getTimeSheetsByCandidateIdAndMonth(candidateId, yearMonth);
            return ResponseEntity.ok(timeSheets);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Trả về lỗi nếu không chuyển đổi thành công
        }
    }

    @PostMapping
    public TimeSheetDTO saveTimeSheet(@RequestBody TimeSheetDTO timeSheetDTO) {
        return timeSheetService.saveTimeSheet(timeSheetDTO);
    }
    @PostMapping("/batch")
    public ResponseEntity<String> saveBatchTimeSheets(@RequestBody List<TimeSheetDTO> timeSheetDTOs) {
        timeSheetDTOs.forEach(timeSheetDTO -> timeSheetService.saveTimeSheet(timeSheetDTO));
        return ResponseEntity.ok("Batch of time sheets added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeSheet(@PathVariable Long id) {
        timeSheetService.deleteTimeSheet(id);
        return ResponseEntity.noContent().build();
    }
}
