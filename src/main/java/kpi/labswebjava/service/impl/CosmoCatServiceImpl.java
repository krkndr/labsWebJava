package kpi.labswebjava.service.impl;

import kpi.labswebjava.domain.CosmoCat;
import kpi.labswebjava.dto.CosmoCat.CosmoCatDto;
import kpi.labswebjava.service.CosmoCatService;
import kpi.labswebjava.service.exception.CatNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CosmoCatServiceImpl implements CosmoCatService {
    private final List<CosmoCatDto> cats = new ArrayList<>(buildAllCatMock());


    @Override
    public List<CosmoCatDto> getCosmoCats() {
        return cats;
    }

    private List<CosmoCatDto> buildAllCatMock() {
        return List.of(
                CosmoCatDto.builder()
                        .cat_id(UUID.randomUUID())
                        .cat_name("Meteor Whiskers")
                        .email("meteor.whiskers@cosmo.cat")
                        .build(),

                CosmoCatDto.builder()
                        .cat_id(UUID.randomUUID())
                        .cat_name("Comet Luna")
                        .email("comet.luna@cosmo.cat")
                        .build(),

                CosmoCatDto.builder()
                        .cat_id(UUID.randomUUID())
                        .cat_name("Star Nebula")
                        .email("star.nebula@cosmo.cat")
                        .build()
        );
    }
}
