package org.company.TicketOnline2.service.mapper;

public interface MyMapper <T1,T2> {

    T1 toEntity(T2 dto);

    T2 toDto(T1 entity);

}
