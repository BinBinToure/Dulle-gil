package com.introduce.seoulgil.service;


import com.introduce.seoulgil.dto.BoardDTO;
import com.introduce.seoulgil.dto.PageRequestDTO;
import com.introduce.seoulgil.dto.PageResultDTO;
import com.introduce.seoulgil.entity.Board;


public interface BoardService {

    Long register(BoardDTO dto);

    BoardDTO read(Long gno);

    PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO);

    default Board dtoToEntity(BoardDTO dto) {
        Board entity = Board.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default BoardDTO entityToDto(Board entity) {

        BoardDTO dto = BoardDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }

    void remove(Long gno);

    void modify(BoardDTO dto);

}
