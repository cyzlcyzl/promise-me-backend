package mvc.promiseme.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.promiseme.calendar.dto.CalendarResponseDTO;
import mvc.promiseme.project.dto.ProjectRequestDTO;
import mvc.promiseme.project.dto.ProjectResponseDTO;
import mvc.promiseme.project.dto.RecommendMemberRequestDTO;
import mvc.promiseme.project.service.ProjectService;
import mvc.promiseme.project.service.RecommendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    private final RecommendService recommendService;
    @GetMapping("/")
    public ResponseEntity<List<ProjectResponseDTO>>calendarAll(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(projectService.projectAll(userId));

    }
    @GetMapping("/category")
    public ResponseEntity<List<String>> categoryRanking(){

        return ResponseEntity.ok(projectService.categoryRanking());

    }

    @PostMapping("/create")
    public ResponseEntity<String> insert(@RequestBody ProjectRequestDTO projectRequestDTO){

        return ResponseEntity.ok(projectService.insert(projectRequestDTO));
    }

    @GetMapping("/progress")
    public ResponseEntity<Map<String, Integer>> progress(@RequestParam("projectId")Long projectId){
        Map<String,Integer> progressResult = new HashMap<>();
        progressResult.put("Prgress", projectService.progress(projectId));
        return ResponseEntity.ok(progressResult);
    }

    @GetMapping("/dday")
    public ResponseEntity <Map<String,Integer>> dday(@RequestParam("projectId") Long projectId){
        Map<String,Integer> ddayResult = new HashMap<>();
        ddayResult.put("Dday", projectService.dday(projectId));

        return ResponseEntity.ok(ddayResult);
    }
    @PostMapping("/recommend/member")
    public ResponseEntity<Map<String,String>> recommendMember(@RequestBody RecommendMemberRequestDTO recommendMemberRequestDTO){
        return ResponseEntity.ok(recommendService.recommendMember(recommendMemberRequestDTO));
    }


}