//package com.example.PortalBE.controller;
//import com.example.PortalBE.dto.AttendanceDTO;
//import com.example.PortalBE.entity.Attendance;
//import com.example.PortalBE.entity.Attendance;
//import com.example.PortalBE.entity.Candidate;
//import com.example.PortalBE.repository.candidateRepository;
//
//import com.example.PortalBE.repository.AttendanceRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//@RestController
//@RequestMapping("/api/v1/attendance")
//public class attendanceController {
//
//    @Autowired
//    private AttendanceRepository attendanceRepository;
//
//    @Autowired
//    private candidateRepository candidateRepository;
//
//    @PostMapping("/checkin")
//    public Attendance checkIn(@RequestBody AttendanceDTO attendanceDTO) {
//        Candidate candidate = candidateRepository.findById(attendanceDTO.getCandidateId())
//                .orElseThrow(() -> new RuntimeException("Candidate not found"));
//        // Kiểm tra xem ứng viên đã có bản ghi điểm danh trong ngày chưa
//        LocalDate today = LocalDate.now();
//        List<Attendance> attendances = attendanceRepository.findByCandidateIdAndDate(candidate.getId(), today);
//
//        Attendance attendance;
//        if (attendances.isEmpty()) {
//            // Nếu chưa có bản ghi điểm danh trong ngày, tạo mới
//            attendance = new Attendance();
//            attendance.setCheckInTime(LocalDateTime.now());
//            attendance.setCandidate(candidate);
//        } else {
//            // Nếu đã có, lấy bản ghi đầu tiên (hoặc có thể là bản ghi cụ thể nếu cần)
//            attendance = attendances.get(0);
//            attendance.setCheckInTime(LocalDateTime.now()); // Cập nhật thời gian check-in
//        }
//
//        return attendanceRepository.save(attendance); // Lưu hoặc cập nhật bản ghi điểm danh
//    }
//
//    @PostMapping("/checkout/{checkInId}")
//    public Attendance checkOut(@PathVariable Long checkInId) {
//        Attendance attendance = attendanceRepository.findById(checkInId)
//                .orElseThrow(() -> new RuntimeException("Attendance not found"));
//
//        attendance.setCheckOutTime(LocalDateTime.now());
//
//        return attendanceRepository.save(attendance); // Cập nhật bản ghi check-in với thời gian check-out
//    }
//
//    @GetMapping("/candidate/{candidateId}")
//    public List<Attendance> getAllAttendancesByCandidate(@PathVariable int candidateId) {
//        return attendanceRepository.findByCandidateId(candidateId);
//    }
//}
//

//package com.example.PortalBE.controller;
//
//import com.example.PortalBE.dto.AttendanceDTO;
//import com.example.PortalBE.entity.Attendance;
//import com.example.PortalBE.entity.Candidate;
//import com.example.PortalBE.repository.AttendanceRepository;
//import com.example.PortalBE.repository.candidateRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/attendance")
//public class attendanceController {
//
//    @Autowired
//    private AttendanceRepository attendanceRepository;
//
//    @Autowired
//    private candidateRepository candidateRepository;
//
//    @PostMapping("/checkin")
//    public Attendance checkIn(@RequestBody AttendanceDTO attendanceDTO) {
//        Candidate candidate = candidateRepository.findById(attendanceDTO.getCandidateId())
//                .orElseThrow(() -> new RuntimeException("Candidate not found"));
//
//        LocalDate today = LocalDate.now();
//        List<Attendance> attendances = attendanceRepository.findByCandidateIdAndDate(candidate.getId(), today);
//
//        Attendance attendance;
//        if (attendances.isEmpty()) {
//            // Nếu không có bản ghi điểm danh trong ngày, tạo mới
//            attendance = new Attendance();
//            attendance.setCheckInTime(LocalDateTime.now());
//            attendance.setCandidate(candidate);
//        } else {
//            // Nếu đã có bản ghi điểm danh trong ngày, lấy bản ghi đầu tiên (hoặc bản ghi cụ thể)
//            attendance = attendances.get(0);
//            attendance.setCheckInTime(LocalDateTime.now()); // Cập nhật thời gian check-in
//        }
//
//        return attendanceRepository.save(attendance);
//    }
//
//    @PostMapping("/checkout")
//    public Attendance checkOut(@RequestBody AttendanceDTO attendanceDTO) {
//        Candidate candidate = candidateRepository.findById(attendanceDTO.getCandidateId())
//                .orElseThrow(() -> new RuntimeException("Candidate not found"));
//
//        LocalDate today = LocalDate.now();
//        List<Attendance> attendances = attendanceRepository.findByCandidateIdAndDate(candidate.getId(), today);
//
//        if (attendances.isEmpty()) {
//            throw new RuntimeException("No check-in record found for today");
//        }
//
//        Attendance attendance = attendances.get(0);
//        attendance.setCheckOutTime(LocalDateTime.now());
//
//        return attendanceRepository.save(attendance);
//    }
//
//    @GetMapping("/candidate/{candidateId}")
//    public List<Attendance> getAllAttendancesByCandidate(@PathVariable int candidateId) {
//        return attendanceRepository.findByCandidateId(candidateId);
//    }
//}
package com.example.PortalBE.controller;

import com.example.PortalBE.dto.AttendanceDTO;
import com.example.PortalBE.entity.Attendance;
import com.example.PortalBE.entity.Candidate;
import com.example.PortalBE.repository.AttendanceRepository;
import com.example.PortalBE.repository.candidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
public class attendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private candidateRepository candidateRepository;

    @PostMapping("/checkin")
    public Attendance checkIn(@RequestBody AttendanceDTO attendanceDTO) {
        Candidate candidate = candidateRepository.findById(attendanceDTO.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        LocalDate today = LocalDate.now();
        List<Attendance> attendances = attendanceRepository.findByCandidateIdAndDate(candidate.getId(), today);

        Attendance attendance;
        if (attendances.isEmpty()) {
            // Nếu không có bản ghi điểm danh trong ngày, tạo mới
            attendance = new Attendance();
            attendance.setCheckInTime(LocalDateTime.now());
            attendance.setCandidate(candidate);
            attendance.setCheckOutTime(null); // Đảm bảo check-out thời gian null khi tạo mới
        } else {
            // Nếu đã có bản ghi điểm danh trong ngày, lấy bản ghi đầu tiên và cập nhật check-in
            attendance = attendances.get(0);
            attendance.setCheckInTime(LocalDateTime.now());
        }

        return attendanceRepository.save(attendance);
    }

    @PostMapping("/checkout")
    public Attendance checkOut(@RequestBody AttendanceDTO attendanceDTO) {
        Candidate candidate = candidateRepository.findById(attendanceDTO.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        LocalDate today = LocalDate.now();
        List<Attendance> attendances = attendanceRepository.findByCandidateIdAndDate(candidate.getId(), today);

        if (attendances.isEmpty()) {
            throw new RuntimeException("No check-in record found for today");
        }

        Attendance attendance = attendances.get(0);
        attendance.setCheckOutTime(LocalDateTime.now());

        return attendanceRepository.save(attendance);
    }

    @GetMapping("/candidate/{candidateId}")
    public List<Attendance> getAllAttendancesByCandidate(@PathVariable int candidateId) {
        return attendanceRepository.findByCandidateId(candidateId);
    }
}