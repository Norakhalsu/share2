package com.example.distantcare.Service;

import com.example.distantcare.Api.ApiException;
import com.example.distantcare.Model.HotLine;
import com.example.distantcare.Repository.HotLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotLineService {
    private final HotLineRepository hotLineRepository;

    public List<HotLine> getHotLines() {
       return hotLineRepository.findAll();
    }
    public void addHotLineToSystem(HotLine hotLine) {
        hotLineRepository.save(hotLine);
    }

    public void updateHotLineToSystem(Integer hotlineId,HotLine hotLine) {
        HotLine hotLineToUpdate = hotLineRepository.findHotLinesByHotlineId(hotlineId);
        if(hotLineToUpdate == null) {
            throw new ApiException("hotline not found");
        }
        hotLineToUpdate.setDescription(hotLine.getDescription());
        hotLineToUpdate.setTitle(hotLine.getTitle());
        hotLineRepository.save(hotLineToUpdate);
    }
    public void deleteHotLineFromSystem(Integer hotLineId) {
        HotLine hotLine = hotLineRepository.findHotLinesByHotlineId(hotLineId);
        if (hotLine == null) {
            throw new ApiException("HotLine Not Found in System");
        }
        hotLineRepository.delete(hotLine);
    }

    // ---------------------------- end point --------------

}
