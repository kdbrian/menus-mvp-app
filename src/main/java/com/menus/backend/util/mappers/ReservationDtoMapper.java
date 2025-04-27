package com.menus.backend.util.mappers;

import com.menus.backend.domain.dto.ReservationDto;
import com.menus.backend.domain.model.Reservation;
import com.menus.backend.util.EntityDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationDtoMapper extends EntityDtoMapper<Reservation, ReservationDto> {

    @Override
    public Reservation fromDto(ReservationDto reservationDto) {
        return Reservation.builder()
                .items(reservationDto.getItems())
                .build();
    }

    @Override
    public ReservationDto toDto(Reservation reservation) {
        return ReservationDto.builder()
                .appUser(reservation.getAppUser().getUid())
                .items(reservation.getItems())
                .build();
    }
}
