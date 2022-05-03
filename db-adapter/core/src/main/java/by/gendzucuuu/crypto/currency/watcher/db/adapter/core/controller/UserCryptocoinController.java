package by.gendzucuuu.crypto.currency.watcher.db.adapter.core.controller;

import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.service.UserCryptocoinService;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.UserCryptocoinResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController("/user-cryptocoins")
public class UserCryptocoinController {
    private final UserCryptocoinService userCryptocoinService;

    @PostMapping
    public ResponseEntity<UserCryptocoinResponseDto> notify(@RequestParam String username, @RequestParam String symbol) {

        return ResponseEntity.ok(userCryptocoinService.register(username.toLowerCase()
                .trim(), symbol.toUpperCase()
                .trim()));
    }

    @GetMapping
    public ResponseEntity<List<UserCryptocoinResponseDto>> getAll() {
        return ResponseEntity.ok(userCryptocoinService.findAll());
    }
}
