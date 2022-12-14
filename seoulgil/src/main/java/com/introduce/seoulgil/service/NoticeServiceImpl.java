package com.introduce.seoulgil.service;

import com.introduce.seoulgil.dto.NoticeDTO;
import com.introduce.seoulgil.dto.PageRequestDTO;
import com.introduce.seoulgil.dto.PageResultDTO;
import com.introduce.seoulgil.entity.Notice;
import com.introduce.seoulgil.entity.NoticeImage;
import com.introduce.seoulgil.repository.NoticeImageRepository;
import com.introduce.seoulgil.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeImageRepository imageRepository;

    @Transactional
    @Override
    public Long register(NoticeDTO noticeDTO) {

        Map<String, Object> entityMap = dtoToEntity(noticeDTO);
        Notice notice = (Notice) entityMap.get("notice");
        List<NoticeImage> noticeImageList = (List<NoticeImage>) entityMap.get("imgList");

        noticeRepository.save(notice);

        noticeImageList.forEach(noticeImage -> {
            imageRepository.save(noticeImage);
        });
        return notice.getMno();
    }

   @Override
    public PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = noticeRepository.getListPage(pageable);

        Function<Object[], NoticeDTO> fn = (arr -> entitiesToDTO(
                (Notice)arr[0] ,
                (List<NoticeImage>)(Arrays.asList((NoticeImage)arr[1])),
                (Double) arr[2],
                (Long)arr[3])
        );

        return new PageResultDTO<>(result, fn);
   }

   @Override
   public NoticeDTO getNotice(Long mno) {

        List<Object[]> result= noticeRepository.getNoticeWithAll(mno);

       Notice notice = (Notice) result.get(0)[0];     // Notice ???????????? ?????? ?????? ?????? - ?????? Row??? ????????? ???

       List<NoticeImage> noticeImageList = new ArrayList<>();     // ????????? ????????? ???????????? ??Image ?????? ??????

       result.forEach(arr -> {
           NoticeImage noticeImage = (NoticeImage)arr[1];
           noticeImageList.add(noticeImage);
       });

       Double avg = (Double) result.get(0)[2];      // ?????? ?????? - ?????? Row??? ????????? ???
       Long reviewCnt = (Long) result.get(0)[3];    // ?????? ?????? - ?????? Row??? ????????? ???

       return entitiesToDTO(notice, noticeImageList, avg, reviewCnt);
   }
}
