package com.introduce.seoulgil.service;


import com.introduce.seoulgil.dto.NoticeDTO;
import com.introduce.seoulgil.dto.NoticeImageDTO;
import com.introduce.seoulgil.dto.PageRequestDTO;
import com.introduce.seoulgil.dto.PageResultDTO;
import com.introduce.seoulgil.entity.Notice;
import com.introduce.seoulgil.entity.NoticeImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface NoticeService {

    Long register(NoticeDTO noticeDTO);

    PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO requestDTO); //목록 처리

    NoticeDTO getNotice(Long mno);

    default NoticeDTO entitiesToDTO(Notice notice, List<NoticeImage> noticeImages, Double avg, Long reviewCnt){
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .mno(notice.getMno())
                .title(notice.getTitle())
                .regDate(notice.getRegDate())
                .modDate(notice.getModDate())
                .build();

        List<NoticeImageDTO> noticeImageDTOList = noticeImages.stream().map(noticeImage -> {
            return NoticeImageDTO.builder().imgName(noticeImage.getImgName())
                    .path(noticeImage.getPath())
                    .uuid(noticeImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        noticeDTO.setImageDTOList(noticeImageDTOList);
        noticeDTO.setAvg(avg);
        noticeDTO.setReviewCnt(reviewCnt.intValue());



        return noticeDTO;

    }

    default Map<String, Object> dtoToEntity(NoticeDTO noticeDTO){

        Map<String, Object> entityMap = new HashMap<>();

        Notice notice =  Notice.builder()
                .mno(noticeDTO.getMno())
                .title(noticeDTO.getTitle())
                .build();

        entityMap.put("notice", notice);

        List<NoticeImageDTO> imageDTOList = noticeDTO.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size() > 0 ) { //NoticeImageDTO 처리

            List<NoticeImage> noticeImageList = imageDTOList.stream().map(noticeImageDTO ->{

                NoticeImage noticeImage = NoticeImage.builder()
                        .path(noticeImageDTO.getPath())
                        .imgName(noticeImageDTO.getImgName())
                        .uuid(noticeImageDTO.getUuid())
                        .notice(notice)
                        .build();
                return noticeImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", noticeImageList);
        }

        return entityMap;
    }
}
