package cn.letsky.movie.controller;

import cn.letsky.movie.entity.Rank;
import cn.letsky.movie.form.RankForm;
import cn.letsky.movie.repository.RankRepository;
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

    private final RankRepository rankRepository;

    public RankController(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    @PostMapping
    public ResponseEntity addRank(
            @RequestBody @Valid RankForm rankForm, BindingResult bindingResult) {
        Rank rank = new Rank();
        BeanUtils.copyProperties(rankForm, rank);
        return ResponseEntity.ok().build();
    }
}