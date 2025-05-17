package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.CreateVideoRecordRequest;
import com.projectsky.IT_ch_backend.dto.VideoRecordDto;
import com.projectsky.IT_ch_backend.mapper.RecordMapper;
import com.projectsky.IT_ch_backend.model.Course;
import com.projectsky.IT_ch_backend.model.VideoRecord;
import com.projectsky.IT_ch_backend.repository.CourseRepository;
import com.projectsky.IT_ch_backend.repository.VideoRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    private final VideoRecordRepository recordRepository;
    private final CourseRepository courseRepository;
    private final RecordMapper recordMapper;

    public RecordServiceImpl(VideoRecordRepository recordRepository, CourseRepository courseRepository, RecordMapper recordMapper) {
        this.recordRepository = recordRepository;
        this.courseRepository = courseRepository;
        this.recordMapper = recordMapper;
    }

    @Override
    public List<VideoRecordDto> getRecordsByCourseId(Long courseId) {
        return recordRepository.findAll().stream()
                .filter(r -> r.getCourse().getId().equals(courseId))
                .map(recordMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void addRecordToCourse(Long courseId, CreateVideoRecordRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        VideoRecord record = recordMapper.toEntity(request);
        record.setCourse(course);

        recordRepository.save(record);

    }
}
