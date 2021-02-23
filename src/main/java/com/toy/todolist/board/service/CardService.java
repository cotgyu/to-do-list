package com.toy.todolist.board.service;

import com.toy.todolist.board.domain.*;
import com.toy.todolist.board.dto.CardRequestDto;
import com.toy.todolist.board.dto.CardResponseDto;
import com.toy.todolist.board.dto.LabelRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CardService {
    private final CardRepository cardRepository;

    private final LabelRepository labelRepository;

    @Transactional
    public Long saveCard(CardRequestDto cardRequestDto){

        Card card = cardRequestDto.toEntity();
        Card saveCard = cardRepository.save(card);

        return saveCard.getId();
    }

    @Transactional(readOnly = true)
    public CardResponseDto findCard(Long id){

        Card card = findById(id);

        return new CardResponseDto(card);
    }

    private Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 Card가 없습니다. id=" + id));
    }

    @Transactional
    public Long updateCard(Long id, CardRequestDto cardRequestDto){
        Card card = findById(id);

        card.update(cardRequestDto.getCardName(), cardRequestDto.getDescription());

        return card.getId();
    }

    @Transactional
    public Long saveLabel(LabelRequestDto labelRequestDto){

        Label label = labelRequestDto.toEntity();
        Label saveLabel = labelRepository.save(label);

        return saveLabel.getId();
    }

    @Transactional
    public Long registerLabel(LabelRequestDto labelRequestDto){

        Card card = findById(labelRequestDto.getCard_id());

        Label label = labelRepository.findById(labelRequestDto.getLabel_id()).orElseThrow(() -> new IllegalArgumentException("해당 Label가 없습니다. id=" + labelRequestDto.getLabel_id()));

        card.addCardLabel(new CardLabel(card, label));

        return label.getId();
    }

}
