package com.ossprac.openmind.calendar.dto;

import com.ossprac.openmind.calendar.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDto {

    private Long teamId;
    private String title;
    private String description;
    private String startDate;
    private String endDate;

    public Event toEntity() {
        return Event.builder()
                .title(title)
                .description(description)
                .startDate(LocalDate.parse(startDate))
                .endDate(LocalDate.parse(endDate))
                .build();
    }
}
