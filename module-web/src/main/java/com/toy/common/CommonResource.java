package com.toy.common;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;


public class CommonResource extends EntityModel<Object> {

    public CommonResource(Object object, Link... links){

        EntityModel.of(object, links);

        //아래와 같음
        //add(new Link("http://localhost:8080/api/events/" + event.getId()));
        //add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
    }
}
