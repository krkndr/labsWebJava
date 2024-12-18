package kpi.labswebjava.web;

import kpi.labswebjava.dto.CosmoCat.CosmoCatDto;
import kpi.labswebjava.feature_toggle.FeatureToggles;
import kpi.labswebjava.feature_toggle.anotation.FeatureToggle;
import kpi.labswebjava.service.CosmoCatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cosmo-cats")
public class CosmoCatController {
    private final CosmoCatService cosmoCatService;
    public CosmoCatController(CosmoCatService cosmoCatService) {
        this.cosmoCatService = cosmoCatService;
    }

    @GetMapping
    @FeatureToggle(FeatureToggles.COSMO_CATS)
    public ResponseEntity<List<CosmoCatDto>> getAllCosmoCats() {
        List<CosmoCatDto> cosmoCats = cosmoCatService.getCosmoCats();
        return ResponseEntity.ok(cosmoCats);
    }
}
