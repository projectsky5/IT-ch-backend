package com.projectsky.IT_ch_backend.mapper;

import com.projectsky.IT_ch_backend.dto.CreateVideoRecordRequest;
import com.projectsky.IT_ch_backend.dto.VideoRecordDto;
import com.projectsky.IT_ch_backend.model.VideoRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    VideoRecordDto toDto(VideoRecord videoRecord);
    VideoRecord toEntity(CreateVideoRecordRequest request);
}
