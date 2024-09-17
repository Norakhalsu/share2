package com.example.distantcare.Repository;

import com.example.distantcare.Model.HotLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface HotLineRepository extends JpaRepository<HotLine, Integer> {
    HotLine findHotLinesByHotlineId(int hotlineId);
}
