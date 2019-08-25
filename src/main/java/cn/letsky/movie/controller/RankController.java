package cn.letsky.movie.controller;

import cn.letsky.movie.entity.Rank;
import cn.letsky.movie.form.RankForm;
import cn.letsky.movie.service.RankService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ranks")
public class RankController {

    private final RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @PostMapping
    public ResponseEntity addRank(
            @RequestBody @Valid RankForm rankForm, BindingResult bindingResult) {
        Rank rank = new Rank();
        BeanUtils.copyProperties(rankForm, rank);
        rankService.add(rank);
        return ResponseEntity.ok().build();
    }


}
