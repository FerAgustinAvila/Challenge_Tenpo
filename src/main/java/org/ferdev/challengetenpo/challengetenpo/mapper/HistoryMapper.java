package org.ferdev.challengetenpo.challengetenpo.mapper;

import org.ferdev.challengetenpo.challengetenpo.dto.EndpointHistoryDto;
import org.ferdev.challengetenpo.challengetenpo.entity.EndpointHistory;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface HistoryMapper {

    EndpointHistoryDto map(EndpointHistory endpointHistory);

    List<EndpointHistoryDto> map(List<EndpointHistory> endpointHistory);

}
