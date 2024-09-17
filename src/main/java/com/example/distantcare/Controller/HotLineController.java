package com.example.distantcare.Controller;


import com.example.distantcare.Model.HotLine;
import com.example.distantcare.Service.HotLineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hotline")
public class HotLineController {
    private final HotLineService hotLineService;

    @PostMapping("/add")
    public ResponseEntity addHotLine(@Valid @RequestBody HotLine hotLine) {
        hotLineService.addHotLineToSystem(hotLine);
        return ResponseEntity.status(200).body("HotLine added successfully");
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllHotLine() {
        return ResponseEntity.status(200).body(hotLineService.getHotLines());
    }
    @PutMapping("/update/{hotlineId}")
    private ResponseEntity updateHotLine(@PathVariable Integer hotlineId, @Valid @RequestBody HotLine hotLine) {
        hotLineService.updateHotLineToSystem(hotlineId,hotLine);
        return ResponseEntity.status(200).body("HotLine updated successfully");
    }

    @DeleteMapping("/delete/{hotlineId}")
    public ResponseEntity deleteHotLine(@PathVariable Integer hotlineId) {
        hotLineService.deleteHotLineFromSystem(hotlineId);
        return ResponseEntity.status(200).body("HotLine deleted successfully");
    }
}
