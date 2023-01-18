package dtalk.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import dtalk.domain.Quiz;
import dtalk.domain.User;
import dtalk.dto.quiz.QuizListDTO;
import dtalk.dto.quiz.QuizSaveDto;
import dtalk.dto.quiz.QuizSendDTO;
import dtalk.dto.record.RecordDTO;
import dtalk.dto.user.UserDetailDTO;
import dtalk.dto.user.UserResponseDTO;
import dtalk.service.QuizService;
import dtalk.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
@Valid
public class QuizController {
    private final QuizService quizService;
    private final RecordService recordService;

    private String S3Bucket = "asdadz"; // Bucket 이름


    private final AmazonS3Client amazonS3Client;

    @PostMapping(consumes = {"multipart/form-data"})
    public String save(@RequestPart QuizSaveDto quizSaveDto, @RequestPart MultipartFile thumbnail) throws IOException {
        System.out.println("들어오긴했니");
        String originalName = thumbnail.getOriginalFilename(); // 파일 이름
        long size = thumbnail.getSize(); // 파일 크기

        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(thumbnail.getContentType());
        objectMetaData.setContentLength(size);

        // S3에 업로드
        amazonS3Client.putObject(
                new PutObjectRequest(S3Bucket, originalName, thumbnail.getInputStream(), objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        String imagePath = amazonS3Client.getUrl(S3Bucket, originalName).toString(); // 접근가능한 URL 가져오기
        quizSaveDto.setThumbImg(imagePath);
        return imagePath;
    }

    @GetMapping
    @Operation(description = "퀴즈디테일")
    public String detail(@RequestParam(required = false) Long idx){
        return quizService.findQuiz(idx).getDetail();
    }
    @PutMapping
    @Operation(description = "게임 플레이")
    public Boolean playQuiz( @RequestBody RecordDTO recordDTO){
        Quiz quiz = quizService.findQuiz(recordDTO.getQuizIdx());
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        boolean flag = false;
        if(recordDTO.getAnswer() ==quiz.getKeyword()){
            flag = true;
        };
        recordService.quizPlay(me,quiz,flag);
        return flag;
    }
    @GetMapping("/list")
    public List<QuizListDTO> myList(){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        List<Quiz> quizList = quizService.myList(me);
        List<QuizListDTO> responseList = new ArrayList<>();
        for (Quiz quiz: quizList) {
            responseList.add(QuizListDTO.getQuizListDTO(quiz));
        }
        return responseList;
    }
    @GetMapping("/count")
    @Operation(description = "퀴즈 수")
    public Long countList(@RequestParam(required = false) Long idx){
        User user = new User();
        user.setIdx(idx);
        if(idx == null) {
            User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal()).getUser();
            user.setIdx(me.getIdx());
        }
        return quizService.listCount(user);
    }
    @PostMapping("/send")
    @Operation(description = "퀴즈 보내기")
    public void quizSend(@RequestBody QuizSendDTO quizSendDTO){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        if(quizSendDTO.getUserIdx()==me.getIdx())
            return;
        quizService.quizSend(quizSendDTO.getRecord());
    }



    @Data
    static class Idx{
        private Long idx;
    }
}
