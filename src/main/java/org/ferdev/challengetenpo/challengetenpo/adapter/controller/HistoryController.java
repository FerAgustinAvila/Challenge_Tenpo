package org.ferdev.challengetenpo.challengetenpo.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.ferdev.challengetenpo.challengetenpo.dto.EndpointHistoryDto;
import org.ferdev.challengetenpo.challengetenpo.entity.EndpointHistory;
import org.ferdev.challengetenpo.challengetenpo.mapper.HistoryMapper;
import org.ferdev.challengetenpo.challengetenpo.usecase.GetHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/history")
public class HistoryController {

    private final GetHistory getHistory;
    private final HistoryMapper historyMapper;

    @Autowired
    public HistoryController(GetHistory getHistory,
                             HistoryMapper historyMapper) {
        this.getHistory = getHistory;
        this.historyMapper = historyMapper;
    }

    @Operation(summary = "Return a list of endpoint history")
    @GetMapping
    public ResponseEntity<List<EndpointHistoryDto>> getEndpointHistory(@RequestParam int page) {
        List<EndpointHistory> endpointHistory = getHistory.apply(page);
        return ResponseEntity.ok().body(historyMapper.map(endpointHistory));
    }
}
