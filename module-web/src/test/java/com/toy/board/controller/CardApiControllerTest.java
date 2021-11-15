package com.toy.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.board.domain.*;
import com.toy.board.dto.*;
import com.toy.board.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
class CardApiControllerTest {

    @Autowired
    EntityManager em;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    LabelRepository labelRepository;

    @Autowired
    CheckListRepository checkListRepository;

    @Autowired
    CheckItemRepository checkItemRepository;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("카드 등록 api 테스트")
    public void addCardApiTest() throws Exception{
        //given
        Topic topic = new Topic("testTopic");
        topicRepository.save(topic);

        CardRequestDto cardRequestDto = new CardRequestDto(topic.getId(), "cardName1");

        //when then
        mockMvc.perform(
                post("/api/card")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(cardRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("_links.update-card").exists())
                .andDo(document("create-card",
                        links(
                                linkWithRel("profile").description("link to card"),
                                linkWithRel("self").description("link to self"),
                                linkWithRel("update-card").description("link to update an existing card")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("topicId").description("identifier of new Card of Board"),
                                fieldWithPath("cardName").description("name of new Board")
                        )
                ))
        ;




    }

    @Test
    @DisplayName("카드 조회 api 테스트")
    public void findCardApiTest() throws Exception{
        //given
        Topic topic = new Topic("topic1");

        topicRepository.save(topic);

        Card card1 = new Card("testCard1", topic);

        cardRepository.save(card1);


        //when then
        mockMvc.perform(
                get("/api/card/{id}", card1.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"))
                .andExpect(jsonPath("result").isNotEmpty());

    }

    @Test
    @DisplayName("카드 수정 api 테스트")
    public void updateCardApiTest() throws Exception{
        //given
        Topic topic = new Topic("topic1");
        topicRepository.save(topic);
        Card card1 = new Card("card1", "dis1" ,topic);
        cardRepository.save(card1);

        CardRequestDto cardRequestDto = new CardRequestDto("cardName1", "desName1", "N");


        //when
        mockMvc.perform(
                put("/api/card/{id}", card1.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(cardRequestDto))
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("_links.select-card").exists())
                .andDo(document("create-card",
                        links(
                                linkWithRel("profile").description("link to card"),
                                linkWithRel("self").description("link to self"),
                                linkWithRel("select-card").description("link to select an existing card")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("topicId").description("identifier of new Card of Board"),
                                fieldWithPath("cardName").description("name of new Board")
                        )
                ))
        ;

        // then
        Card result = cardRepository.findById(card1.getId()).get();

        assertThat(result.getCardName()).isEqualTo(cardRequestDto.getCardName());
        assertThat(result.getDescription()).isEqualTo(cardRequestDto.getDescription());

    }

    @Test
    @DisplayName("라벨 생성 api 테스트")
    public void addLabelApiTest() throws Exception{
        //given
        LabelRequestDto labelRequestDto = new LabelRequestDto("label1", "black");

        //when then
        mockMvc.perform(
                post("/api/card/label")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(labelRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));


    }

    @Test
    @DisplayName("라벨 등록 api 테스트")
    public void registerLabelApiTest() throws Exception{
        //given
        Topic topic = new Topic("topic1");
        topicRepository.save(topic);
        Card card1 = new Card("card1", "dis1" ,topic);
        cardRepository.save(card1);

        Label label = new Label("label1", "black");
        labelRepository.save(label);

        CardLabelRequestDto cardLabelRequestDto = new CardLabelRequestDto(card1.getId(), label.getId());

        //when then
        mockMvc.perform(
                post("/api/card/label/register")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(cardLabelRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));


    }

    @Test
    @DisplayName("라벨 조회 api 테스트")
    public void findLabelsApiTest() throws Exception{
        //given
        Label label = new Label("label1", "black");
        Label label2 = new Label("label2", "black2");
        Label label3 = new Label("label3", "black3");
        Label label4 = new Label("label4", "black4");

        labelRepository.save(label);
        labelRepository.save(label2);
        labelRepository.save(label3);
        labelRepository.save(label4);

        //when then
        mockMvc.perform(
                get("/api/card/label")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"))
                .andExpect(jsonPath("result").isNotEmpty());
    }

    @Test
    @DisplayName("라벨 수정 api 테스트")
    public void updateLabelsApiTest() throws Exception{
        //given
        Label label = new Label("label1", "black");
        Label saveLabel = labelRepository.save(label);

        // when
        LabelRequestDto labelRequestDto = new LabelRequestDto("labelUpdate", "colorUpdate");

        //when then
        mockMvc.perform(
                put("/api/card/label/{id}",saveLabel.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(labelRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));

        Label findLabel = labelRepository.findById(saveLabel.getId()).get();

        assertThat(findLabel.getLabelName()).isEqualTo(labelRequestDto.getLabelName());
        assertThat(findLabel.getColor()).isEqualTo(labelRequestDto.getColor());

    }

    @Test
    @DisplayName("체크리스트 추가 api 테스트")
    public void addCheckListApiTest() throws Exception{
        // given
        Topic topic = new Topic("topic1");
        topicRepository.save(topic);
        Card card1 = new Card("card1", "dis1" ,topic);
        cardRepository.save(card1);

        //when
        CheckListRequestDto checkListRequestDto = new CheckListRequestDto("checkList1", card1.getId());

        mockMvc.perform(
                post("/api/card/checkList")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(checkListRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));


        // then
        List<CheckList> checkLists = cardRepository.findById(card1.getId()).get().getCheckLists();

        assertThat(checkLists).isNotEmpty();
        assertThat(checkLists.get(0).getCheckListName()).isEqualTo(checkListRequestDto.getCheckListTitle());
    }

    @Test
    @DisplayName("체크리스트 수정 api 테스트")
    public void updateCheckListApiTest() throws Exception{
        // given
        Topic topic = new Topic("topic1");
        topicRepository.save(topic);
        Card card1 = new Card("card1", "dis1" ,topic);
        cardRepository.save(card1);

        CheckList checkList = new CheckList("checkList1", card1);
        checkListRepository.save(checkList);

        card1.addCheckList(checkList);


        //when
        CheckListRequestDto checkListRequestDto = new CheckListRequestDto("updateList1", "Y");

        mockMvc.perform(
                put("/api/card/checkList/{id}", checkList.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(checkListRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));


        // then
        List<CheckList> checkLists = cardRepository.findById(card1.getId()).get().getCheckLists();

        assertThat(checkLists).isNotEmpty();
        assertThat(checkLists.get(0).getCheckListName()).isEqualTo(checkListRequestDto.getCheckListTitle());
        assertThat(checkLists.get(0).getDelFlag()).isEqualTo(checkListRequestDto.getDelFlag());
    }

    @Test
    @DisplayName("체크아이템 추가 api 테스트")
    public void addCheckItemApiTest() throws Exception{
        // given
        Topic topic = new Topic("topic1");
        topicRepository.save(topic);
        Card card1 = new Card("card1", "dis1" ,topic);
        cardRepository.save(card1);
        CheckList checkList = new CheckList("checkList1", card1);
        checkListRepository.save(checkList);
        card1.addCheckList(checkList);

        //when
        CheckItemRequestDto checkItemRequestDto = new CheckItemRequestDto("item1", checkList.getId());

        mockMvc.perform(
                post("/api/card/checkList/checkItem")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(checkItemRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));


        // then
        List<CheckList> checkLists = cardRepository.findById(card1.getId()).get().getCheckLists();

        assertThat(checkLists).isNotEmpty();
        assertThat(checkLists.get(0).getCheckItems().get(0).getCheckItemName()).isEqualTo(checkItemRequestDto.getCheckItemName());
    }

    @Test
    @DisplayName("체크아이템 수정 api 테스트")
    public void updateCheckItemApiTest() throws Exception{
        // given
        Topic topic = new Topic("topic1");
        topicRepository.save(topic);
        Card card1 = new Card("card1", "dis1" ,topic);
        cardRepository.save(card1);
        CheckList checkList = new CheckList("checkList1", card1);
        checkListRepository.save(checkList);
        card1.addCheckList(checkList);
        CheckItem checkItem = new CheckItem("checkItem1", checkList);
        checkItemRepository.save(checkItem);
        checkList.addCheckItem(checkItem);

        //when
        CheckItemRequestDto checkItemRequestDto = new CheckItemRequestDto("updateItem", "Y");

        mockMvc.perform(
                put("/api/card/checkList/checkItem/{id}", checkItem.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(checkItemRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));


        // then
        List<CheckList> checkLists = cardRepository.findById(card1.getId()).get().getCheckLists();

        assertThat(checkLists).isNotEmpty();
        assertThat(checkLists.get(0).getCheckItems().get(0).getCheckItemName()).isEqualTo(checkItemRequestDto.getCheckItemName());
        assertThat(checkLists.get(0).getCheckItems().get(0).getDelFlag()).isEqualTo(checkItemRequestDto.getDelFlag());
    }


    @Test
    @DisplayName("카드아이디의 라벨 조회 api 테스트")
    public void getCardLabelApiTest() throws Exception{

        //given
        Long cardId = 1L;

        // when then
        mockMvc.perform(
                get("/api/card/label/{cardId}", cardId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"))
                .andExpect(jsonPath("result").isNotEmpty());

    }

    @Test
    @DisplayName("카드라벨 수정 api 테스트")
    public void updateCardLabelApiTest() throws Exception{

        //given
        Topic topic = new Topic("topic1");
        topicRepository.save(topic);
        Card card1 = new Card("card1", "dis1" ,topic);
        cardRepository.save(card1);
        Label label = new Label("label1", "black");
        labelRepository.save(label);
        CardLabel cardLabel = new CardLabel(card1, label);
        card1.addCardLabel(cardLabel);

        // when
        CardLabelRequestDto cardLabelRequestDto = new CardLabelRequestDto(card1.getId(), label.getId(), "false");

        mockMvc.perform(
                put("/api/card/label/cardLabel")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(cardLabelRequestDto))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("resultMessage").value("success"));

        //then

        //assertThat(cardLabel.getDelFlag()).isEqualTo("Y");
    }
}